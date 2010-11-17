import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU
import groovy.text.SimpleTemplateEngine
import org.codehaus.groovy.grails.commons.spring.GrailsRuntimeConfigurator;
import com.grailsPortal.test.scaffold.DefaultGrailsTestTemplateGenerator;
import org.springframework.mock.web.MockServletContext
import org.springframework.core.io.Resource;

Ant.property(environment:"env")
grailsHome = Ant.project.properties."environment.GRAILS_HOME"

includeTargets << new File ( "${grailsHome}/scripts/Package.groovy" )

generateControllers = true
generateIntegrationTests = true
generateDomains = true
target('default': "The description of the script goes here!") {
    depends( checkVersion, packageApp )
    typeName = "Domain Class"
    doStuff()
}

target(doStuff: "The implementation task") {

    rootLoader.addURL(classesDir.toURL())
    def beans = new grails.spring.WebBeanBuilder().beans {
        resourceHolder(org.codehaus.groovy.grails.commons.spring.GrailsResourceHolder) {
            this.resources = "file:${basedir}/grails-app/domain/**/*.groovy"
        }
        grailsResourceLoader(org.codehaus.groovy.grails.commons.GrailsResourceLoaderFactoryBean) {
            grailsResourceHolder = resourceHolder
        }
        def pluginResources = [] as Resource[]
        if(new File("${basedir}/plugins/*/plugin.xml").exists()) {
            pluginResources = "file:${basedir}/plugins/*/plugin.xml"
        }

        pluginMetaManager(org.codehaus.groovy.grails.plugins.DefaultPluginMetaManager, pluginResources)
        grailsApplication(org.codehaus.groovy.grails.commons.DefaultGrailsApplication.class, ref("grailsResourceLoader"))
    }
                                                    
    def appCtx = beans.createApplicationContext()    
    appCtx.servletContext = new MockServletContext()
    def grailsApp = appCtx.grailsApplication

    grailsApp.initialise()
    def domainClasses = grailsApp.domainClasses
	def controllerClasses = grailsApp.controllerClasses
	def serviceClass=   grailsApp.serviceClasses

    if(!domainClasses) {
        println "Domain classes not found in grails-app/domain, trying hibernate mapped classes..."    
        try {
            def config = new GrailsRuntimeConfigurator(grailsApp, appCtx)
            appCtx = config.configure(appCtx.servletContext)                
        }
        catch(Exception e) {
            println e.message
            e.printStackTrace()
        }
        domainClasses = grailsApp.domainClasses
    }
    
    if(domainClasses) {
        def generator = new DefaultGrailsTestTemplateGenerator()
        domainClasses.each { domainClass ->                                                                               
            if(generateControllers) {
                event("StatusUpdate", ["Generating controller for domain class ${domainClass.fullName}"])    
                generator.generateTestControllers(domainClass,".")            
            }
            event("StatusUpdate", ["Finished generation for domain class ${domainClass.fullName}"])            
        }
        event("StatusFinal", ["Finished generation for domain classes"])
    }
    else {
        event("StatusFinal", ["No domain class found"])
    }
}
