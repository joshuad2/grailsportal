package com.grailsPortal.test.scaffold

import java.io.Writer;

import org.codehaus.groovy.grails.commons.GrailsControllerClass;
import org.codehaus.groovy.grails.commons.GrailsServiceClass;
import org.springframework.core.io.ResourceLoader;

interface GrailsTestTemplateGenerator {
	void generateTestControllers(controllerClass, destdir)
	void generateTestServices(serviceClass, destdir)
	void generateTestDomains(domainClass, destdir)
}