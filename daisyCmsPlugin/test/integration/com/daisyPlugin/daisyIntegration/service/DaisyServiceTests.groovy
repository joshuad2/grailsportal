package com.daisyPlugin.daisyIntegration.service

import grails.test.*

class DaisyServiceTests extends GrailsUnitTestCase {
	def daisyService
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetHtmlContent() {
     def s=  daisyService.getHtmlContent("index")
	 assert s !=null
    }
   void testGetIdFromName(){
	def s= daisyService.getContentIdFromName("index")
	assert s!=null
	}
}
