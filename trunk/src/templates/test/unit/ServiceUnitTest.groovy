<%=packageName ? "package ${packageName}\n\n" : ''%>
class ${className}Tests extends grails.test.ServiceUnitTestCase{
/**
 *  ${className}Service Unit Test
 *  @author Joshua Davis    
 */
<% 
  closures.each { 
   String closureName=it	  
   %>
   void test${closureName}(){
	   }
 <%	  
  }
%>
}

