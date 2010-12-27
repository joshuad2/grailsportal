package com.grailsPortal.test.scaffold;


import grails.util.BuildSettingsHolder
import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.commons.GrailsControllerClass
import org.codehaus.groovy.grails.commons.GrailsServiceClass
import org.springframework.context.ResourceLoaderAware
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.util.Assert;
import org.codehaus.groovy.grails.cli.CommandLineHelper
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.plugins.PluginManagerHolder
import org.codehaus.groovy.grails.scaffolding.*
import org.hsqldb.lib.HashMap;


class DefaultGrailsTestTemplateGenerator implements GrailsTestTemplateGenerator, ResourceLoaderAware {

    static final Log LOG = LogFactory.getLog(DefaultGrailsTestTemplateGenerator)

    String basedir = "./"
	def scaffoldedMethods=["index","update","list","show"]
    boolean overwrite = false
    def engine = new SimpleTemplateEngine()
    ResourceLoader resourceLoader
    Template renderEditorTemplate

    /**
     * Used by the scripts so that they can pass in their AntBuilder instance.
     */
    DefaultGrailsTestTemplateGenerator(ClassLoader classLoader) {
        engine = new SimpleTemplateEngine(classLoader)
    }

    /**
     * Default constructor.
     */
    DefaultGrailsTestTemplateGenerator() {}

    void setResourceLoader(ResourceLoader rl) {
        LOG.info "Test Scaffolding template generator set to use resource loader ${rl}"
        this.resourceLoader = rl
    }
	void generateTestDomains(domainClass, destdir) {
		LOG.info "In GenerateTestDomains"
		Assert.hasText destdir, "Argument [destdir] not specified"

		if (domainClass) {
			def fullName = domainClass.fullName
			def pkg = ""
			def pos = fullName.lastIndexOf('.')
			if (pos != -1) {
				// Package name with trailing '.'
				pkg = fullName[0..pos]
			}

			def destFile = new File("${destdir}/test/unit/${pkg.replace('.' as char, '/' as char)}${domainClass.shortName}Tests.groovy")
			if (canWrite(destFile)) {
				destFile.parentFile.mkdirs()
				destFile.withWriter { w ->
					generateTestDomains(domainClass, w)
				}
				LOG.info("Domain generated at ${destFile}")
			}
		}
	}
	
	void generateTestDomains(domainClass, Writer out) {
		def templateText = getTemplateText("src/grails/templates/test/unit/domainUnitTest.groovy")
		java.util.Vector closures = new java.util.Vector()
		def uris=domainClass.getURIs()
		def props=domainClass.getProperties()
		uris.each {
			String uri=it
			String propertyName=domainClass.getClosurePropertyName(uri)
			closures.add(propertyName)
		}
		  def binding = [
					   packageName: serviceClass.packageName,
					   serviceClass: serviceClass,
					   className: serviceClass.shortName,
					   closures: closures
					   ]
		  def t = engine.createTemplate(templateText)
		  t.make(binding).writeTo(out)
	}
	void generateTestServices(serviceClass, destdir) {
		LOG.info "In GenerateTestServices"
		Assert.hasText destdir, "Argument [destdir] not specified"

		if (serviceClass) {
			def fullName = serviceClass.fullName
			def pkg = ""
			def pos = fullName.lastIndexOf('.')
			if (pos != -1) {
				// Package name with trailing '.'
				pkg = fullName[0..pos]
			}

			def destFile = new File("${destdir}/test/unit/${pkg.replace('.' as char, '/' as char)}${serviceClass.shortName}Tests.groovy")
			if (canWrite(destFile)) {
				destFile.parentFile.mkdirs()
				destFile.withWriter { w ->
					generateTestServices(serviceClass, w)
				}
				LOG.info("Service generated at ${destFile}")
			}
		}
	}
	
	void generateTestServices(serviceClass, Writer out) {
		def templateText = getTemplateText("c:/Workspace/Portal/src/templates/test/unit/ServiceUnitTest.groovy")
		java.util.Vector closures = new java.util.Vector()
		GrailsServiceClass sc=serviceClass
		def uris = sc.getProperties()
		uris.each {
			String propertyName=it
			closures.add(propertyName)
		}
		  def binding = [
					   packageName: serviceClass.packageName,
					   serviceClass: serviceClass,
					   className: serviceClass.shortName,
					   closures: closures
					   ]
		  def t = engine.createTemplate(templateText)
		  t.make(binding).writeTo(out)
	}
	
    void generateTestControllers(controllerClass, destdir) {
		Assert.hasText destdir, "Argument [destdir] not specified"
        if (controllerClass) {
            def fullName = controllerClass.fullName
            def pkg = ""
            def pos = fullName.lastIndexOf('.')
            if (pos != -1) {
                // Package name with trailing '.'
                pkg = fullName[0..pos]
            }

            def destFile = new File("${destdir}/test/unit/${pkg.replace('.' as char, '/' as char)}${controllerClass.shortName}Tests.groovy")
            if (canWrite(destFile)) {
                destFile.parentFile.mkdirs()
                destFile.withWriter { w ->
                    generateTestControllers(controllerClass, w)
                }
                LOG.info("Controller generated at ${destFile}")
            }
        }
    }
	
    void generateTestControllers(controllerClass, Writer out) {
        def templateText = getTemplateText("/src/grails/templates/test/unit/ControllerUnitTest.groovy")
		java.util.Vector closures = new java.util.Vector()
		def uris=controllerClass.getURIs()
		def props=controllerClass.getProperties()
		Boolean isScaffolded=false
		uris.each {
			String uri=it
			String propertyName=controllerClass.getClosurePropertyName(uri)
			if (propertyName =="scaffold" || propertyName.endsWith("Service")|| closures.indexOf(propertyName)!=-1){
				if (propertyName == "scaffold"){
					isScaffolded=true
				}
				}else{
			  closures.add(propertyName)
			}
		}
		if (isScaffolded){
			scaffoldedMethods.each{
			String name=it
		 	if (closures.indexOf(name)==-1){
			  closures.add(name)  
		      }
			}
		}
          def binding = [
			           packageName: controllerClass.packageName,
                       controllerClass: controllerClass,
                       className: controllerClass.shortName,
					   closures: closures
					   ]
          def t = engine.createTemplate(templateText)
          t.make(binding).writeTo(out)
    }

    private helper = new CommandLineHelper()
    private canWrite(testFile) {
        if (!overwrite && testFile.exists()) {
            try {
                def response = helper.userInput("File ${testFile} already exists. Overwrite?",['y','n','a'] as String[])
                overwrite = overwrite || response == "a"
                return overwrite || response == "y"
            }
            catch (Exception e) {
                // failure to read from standard in means we're probably running from an automation tool like a build server
                return true
            }
        }
        return true
    }
	private getTemplateText(template) {
		def application = ApplicationHolder.getApplication()
		def templateFile = new FileSystemResource(template)
		return templateFile.inputStream.getText()
	}

}

