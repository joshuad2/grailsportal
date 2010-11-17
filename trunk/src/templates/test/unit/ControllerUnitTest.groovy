<%=packageName ? "package ${packageName}\n\n" : ''%>class ${className}Controller extends grails.test.ControllerUnitTestCase{
/**
 *  ${className}Controller Unit Test
 *  @author Joshua Davis    
 */
	
    void testList(){
		controller.list()
		assertNotNull controller.response.contentAsString
		}
	
    def testShow(){
		controller.show()
		assertNotNull controller.response.contentAsString
		}
		
    def testDelete(){
		controller.delete()
		assertNotNull controller.response.contentAsString
		} 
	
    def testEdit(){
		controller.edit()
		assertNotNull controller.response.contentAsString
		} 
    def testUpdate(){
		controller.update()
		assertNotNull controller.response.contentAsString
		} 
    def testCreate(){
		controller.create()
		assertNotNull controller.response.contentAsString
		} 
    def testSave (){
		controller.save()
		assertNotNull controller.response.contentAsString
		} 
}
