<%=packageName ? "package ${packageName}\n\n" : ''%>

import com.grailsPortal.domain.menu.${className}Controller;
import com.grailsPortal.domain.menu.${className}
import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty;
import org.codehaus.groovy.grails.commons.GrailsClassUtils

class ${className}Tests extends grails.test.ControllerUnitTestCase{

/**
 *  ${className}Controller Unit Test
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
	   controller= new ${className}Controller()
	   }
   
   void testindex(){
	   controller.index()
	   assertNotNull controller.response.contentAsString
	   }
 
   void testupdate(){
	   ${className} config=new ${className}()
	   def configs=[standardValues(config)]
	   mockDomain(${className},configs)
	   controller.update(1)
	   assertNotNull controller.response.contentAsString
	   }
 
   void testlist(){
	   ${className} config=new ${className}()
	   def configs=[standardValues(config)]
	   mockDomain(${className},configs)
	   def returnValue=controller.list()
	   assertNotNull controller.response.contentAsString
	   }
 
   void testshow(){
	   ${className} config=new ${className}()
	   def configs=[standardValues(config)]
	   mockDomain(${className),configs)
	   controller.show(1)
	   assertNotNull controller.response.contentAsString
	   }
 
}
