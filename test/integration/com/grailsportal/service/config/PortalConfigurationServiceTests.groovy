package com.grailsportal.service.config

import grails.test.*
import com.grailsPortal.domain.*
class PortalConfigurationServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSaveContactPhone() {
      ContactPhone cp=new ContactPhone()
	  cp.active=true
      cp.save(flush:true)
	  cp.validate()
	  cp.errors.each{
	  def error=it
	  def val=g.message(code: error.code, args: error.args)
      }
    }
}
