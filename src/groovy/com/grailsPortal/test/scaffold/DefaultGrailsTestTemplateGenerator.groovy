package com.grailsPortal.test.scaffold;


import grails.util.BuildSettingsHolder
import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.commons.GrailsDomainClass
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


class DefaultGrailsTestTemplateGenerator implements GrailsTestTemplateGenerator, ResourceLoaderAware {

    static final Log LOG = LogFactory.getLog(DefaultGrailsTestTemplateGenerator)

    String basedir = "."
    boolean overwrite = false
    def engine = new SimpleTemplateEngine()
    ResourceLoader resourceLoader
    Template renderEditorTemplate
    String domainSuffix = 'Instance'

    /**
     * Used by the scripts so that they can pass in their AntBuilder instance.
     */
    DefaultGrailsTestTemplateGenerator(ClassLoader classLoader) {
        engine = new SimpleTemplateEngine(classLoader)
        def suffix = ConfigurationHolder.config?.grails?.test?.templates?.domainSuffix
        if (suffix != [:]) {
            domainSuffix = suffix
        }
    }

    /**
     * Default constructor.
     */
    DefaultGrailsTestTemplateGenerator() {}

    void setResourceLoader(ResourceLoader rl) {
        LOG.info "Test Scaffolding template generator set to use resource loader ${rl}"
        this.resourceLoader = rl
    }

    // uses the type to render the appropriate editor
    def renderEditor = { property ->
        def domainClass = property.domainClass
        def cp
        if (PluginManagerHolder.pluginManager.hasGrailsPlugin('hibernate')) {
            cp = domainClass.constrainedProperties[property.name]
        }

        if (!renderEditorTemplate) {
            // create template once for performance
            def templateText = getTemplateText("renderEditor.template")
            renderEditorTemplate = engine.createTemplate(templateText)
        }

        def binding = [property: property,
                       domainClass: domainClass,
                       cp: cp,
                       domainInstance:getPropertyName(domainClass)]
        return renderEditorTemplate.make(binding).toString()
    }

    void generateTestControllers(GrailsDomainClass domainClass, String destdir) {
        Assert.hasText destdir, "Argument [destdir] not specified"

        if (domainClass) {
            def fullName = domainClass.fullName
            def pkg = ""
            def pos = fullName.lastIndexOf('.')
            if (pos != -1) {
                // Package name with trailing '.'
                pkg = fullName[0..pos]
            }

            def destFile = new File("${destdir}/test/unit/controllers/${pkg.replace('.' as char, '/' as char)}${domainClass.shortName}ControllerUnitTest.groovy")
            if (canWrite(destFile)) {
                destFile.parentFile.mkdirs()

                destFile.withWriter { w ->
                    generateTestControllers(domainClass, w)
                }

                LOG.info("Controller generated at ${destFile}")
            }
        }
    }
	
    void generateTestControllers(GrailsDomainClass domainClass, Writer out) {
        def templateText = getTemplateText("ControllerUnitTest.groovy")

        boolean hasHibernate = PluginManagerHolder.pluginManager.hasGrailsPlugin('hibernate')
        def binding = [packageName: domainClass.packageName,
                       domainClass: domainClass,
                       className: domainClass.shortName,
                       propertyName: getPropertyName(domainClass),
                       comparator: hasHibernate ? DomainClassPropertyComparator : SimpleDomainClassPropertyComparator]

        def t = engine.createTemplate(templateText)
        t.make(binding).writeTo(out)
    }

	
	void generateTestDomains(GrailsDomainClass domainClass, String destdir){
		Assert.hasText destdir, "Argument [destdir] not specified"
		
				if (domainClass) {
					def fullName = domainClass.fullName
					def pkg = ""
					def pos = fullName.lastIndexOf('.')
					if (pos != -1) {
						// Package name with trailing '.'
						pkg = fullName[0..pos]
					}
		
					def destFile = new File("${destdir}/test/unit/domain/${pkg.replace('.' as char, '/' as char)}${domainClass.shortName}Domain.groovy")
					if (canWrite(destFile)) {
						destFile.parentFile.mkdirs()
		
						destFile.withWriter { w ->
							generateTestDomain(domainClass, w)
						}
		
						LOG.info("Controller generated at ${destFile}")
					}
				}
	}
	
	void generateTestDomains(GrailsDomainClass domainClass, Writer out) {
		def templateText = getTemplateText("DomainUnitTest.groovy")

		boolean hasHibernate = PluginManagerHolder.pluginManager.hasGrailsPlugin('hibernate')
		def binding = [packageName: domainClass.packageName,
					   domainClass: domainClass,
					   className: domainClass.shortName,
					   propertyName: getPropertyName(domainClass),
					   comparator: hasHibernate ? DomainClassPropertyComparator : SimpleDomainClassPropertyComparator]

		def t = engine.createTemplate(templateText)
		t.make(binding).writeTo(out)
	}
    private String getPropertyName(GrailsDomainClass domainClass) { "${domainClass.propertyName}${domainSuffix}" }

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

    private getTemplateText(String template) {
        def application = ApplicationHolder.getApplication()
        // first check for presence of template in application
        if (resourceLoader && application?.warDeployed) {
            return resourceLoader.getResource("/WEB-INF/templates/test/unit/${template}").inputStream.text
        }

        def templateFile = new FileSystemResource("${basedir}/src/templates/test/unit/${template}")
        if (!templateFile.exists()) {
            // template not found in application, use default template
            def grailsHome = BuildSettingsHolder.settings?.grailsHome

            if (grailsHome) {
                templateFile = new FileSystemResource("${grailsHome}/src/grails/templates/test/unit/${template}")
            }
            else {
                templateFile = new ClassPathResource("src/grails/templates/test/unit/${template}")
            }
        }
        return templateFile.inputStream.getText()
    }

}

