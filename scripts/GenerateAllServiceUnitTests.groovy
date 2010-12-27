	import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU
	import groovy.text.SimpleTemplateEngine
	import org.codehaus.groovy.grails.commons.spring.GrailsRuntimeConfigurator;
	import com.grailsPortal.test.scaffold.DefaultGrailsTestTemplateGenerator;
	import org.springframework.mock.web.MockServletContext
	import org.springframework.core.io.Resource;
	
	Ant.property(environment:"env")
	grailsHome = Ant.project.properties."environment.GRAILS_HOME"
	
	includeTargets << new File ( "${grailsHome}/scripts/Package.groovy" )
	
	generateServices = true
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
				this.resources = "file:${basedir}/grails-app/services/**/*.groovy"
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
		def serviceClasses = grailsApp.serviceClasses
		if(!serviceClasses) {
			println "Service classes not found in grails-app/services"
		}
		if(serviceClasses) {
			def generator = new DefaultGrailsTestTemplateGenerator()
			serviceClasses.each { serviceClass ->
					event("StatusUpdate", ["Generating test of service ${serviceClass.fullName}"])
					generator.generateTestServices(serviceClass,".")
					event("StatusUpdate", ["Finished generation for service class ${serviceClass.fullName}"])
			}
			event("StatusFinal", ["Finished generation for service classes"])
		}
		else {
			event("StatusFinal", ["No service class found"])
		}
	}
	
