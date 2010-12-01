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
target('default': "The description of the script goes here!") {
    depends( checkVersion, packageApp )
    typeName = "Controller Class"
    doStuff()
}

target(doStuff: "The implementation task") {
	event("StatusUpdate",["In script"])
    rootLoader.addURL(classesDir.toURL())
    def beans = new grails.spring.WebBeanBuilder().beans {
        resourceHolder(org.codehaus.groovy.grails.commons.spring.GrailsResourceHolder) {
            this.resources = "file:${basedir}/grails-app/controller/**/*.groovy"
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
	def controllerClasses = grailsApp.controllerClasses
	if(!controllerClasses) {
		println "Controller classes not found in grails-app/domain"
	}
    if(controllerClasses) {
        def generator = new DefaultGrailsTestTemplateGenerator()
        controllerClasses.each { controllerClass ->                                                                               
                event("StatusUpdate", ["Generating test of controller ${controllerClass.fullName}"])    
                generator.generateTestControllers(controllerClass,".")            
                event("StatusUpdate", ["Finished generation for controller class ${controllerClass.fullName}"])            
        }
        event("StatusFinal", ["Finished generation for controller classes"])
    }
    else {
        event("StatusFinal", ["No controller class found"])
    }
}
