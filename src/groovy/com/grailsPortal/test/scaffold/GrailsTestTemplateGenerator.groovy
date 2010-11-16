package com.grailsPortal.test.scaffold

import java.io.Writer;

import org.codehaus.groovy.grails.commons.GrailsDomainClass;
import org.springframework.core.io.ResourceLoader;

interface GrailsTestTemplateGenerator {
	void generateTestControllers(GrailsDomainClass domainClass, String destdir)
	void generateTestControllers(GrailsDomainClass domainClass, Writer out)
	void generateTestDomains(GrailsDomainClass domainClass, String destdir)
	void generateTestDomains(GrailsDomainClass domainClass, Writer out)
	void setResourceLoader(ResourceLoader rl)
}
