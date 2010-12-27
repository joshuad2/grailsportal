<%=packageName ? "package ${packageName}\n\n" : ''%>
class ${className}Tests extends grails.test.ControllerUnitTestCase{
/**
 *  ${className}Controller Unit Test
 *  @author Joshua Davis    
 */
<% 
  closures.each { 
   String actionName=it	  
   %>
   void test${actionName}(){
	   controller.${actionName}()
	   assertNotNull controller.response.contentAsString
	   }
 <%	  
  }
%>
}
