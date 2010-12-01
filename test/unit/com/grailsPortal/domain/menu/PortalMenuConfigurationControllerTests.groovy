package com.grailsPortal.domain.menu

import com.grailsPortal.domain.menu.PortalMenuConfigurationController;
import com.grailsPortal.domain.menu.PortalMenuConfiguration
import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty;
import org.codehaus.groovy.grails.commons.GrailsClassUtils
class PortalMenuConfigurationControllerTests extends grails.test.ControllerUnitTestCase{

/**
 *  PortalMenuConfigurationControllerController Unit Test
 *  @author Joshua Davis    
 */
   def standardValues={obj->
	   obj.getProperties().each {
		   def prop=it
		   def tpe=GrailsClassUtils.getPropertyType(obj.getClass(),prop.key)
		   if (GrailsClassUtils.isPropertyOfType(obj.getClass(), prop.key, java.lang.String)){
			 obj."${prop.key}"="test"
		   }
		   else if (GrailsClassUtils.isPropertyOfType(obj.getClass(), prop.key, java.lang.Integer)){
			 obj."${prop.key}"=1
		   }else if (GrailsClassUtils.isPropertyOfType(obj.getClass(), prop.key, java.lang.Long)){
			 obj."${prop.key}"=1L
		   }else if (GrailsClassUtils.isPropertyOfType(obj.getClass(), prop.key, java.util.Date)){
			 obj."${prop.key}"=new java.util.Date()
		   }else if (GrailsClassUtils.isPropertyOfType(obj.getClass(), prop.key, java.lang.Double)){
			 obj."${prop.key}"=1.0
		   }
	   }
   }
   
   void setup(){
	   super.setUp()
	   controller= new PortalMenuConfigurationController()
	   }
   
   void testindex(){
	   controller.index()
	   assertNotNull controller.response.contentAsString
	   }
 
   void testupdate(){
	   PortalMenuConfiguration config=new PortalMenuConfiguration()
	   def configs=[standardValues(config)]
	   mockDomain(PortalMenuConfiguration,configs)
	   controller.update(1)
	   assertNotNull controller.response.contentAsString
	   }
 
   void testlist(){
	   PortalMenuConfiguration config=new PortalMenuConfiguration()
	   def configs=[standardValues(config)]
	   mockDomain(PortalMenuConfiguration,configs)
	   def returnValue=controller.list()
	   assertNotNull controller.response.contentAsString
	   }
 
   void testshow(){
   	   PortalMenuConfiguration config=new PortalMenuConfiguration()
	   def configs=[standardValues(config)]
	   mockDomain(PortalMenuConfiguration,configs)
	   controller.show(1)
	   assertNotNull controller.response.contentAsString
	   }
 
}
