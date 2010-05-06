package com.daisyPlugin.controller
class DaisyController {
def daisyService
def dataService
    def index = { }
   def contentHandler={
	    def theId=params.id
		def documentType=daisyService.getDocumentType(theId)
		if (documentType=="Image"){
			daisyService.doImage(theId,response.getOutputStream())
			response.setContentType("image/jpeg")
		}else
		if (documentType=="SimpleDocument"){
			osw.write(daisyService.getHtmlDocumentFromId(theId))
		}
      }
   def contentList={
	   def documentName=params.documentName
	   def documentList=params.fieldType
	   def lst=daisyService.getFields(documentName,fieldType)
	   def osb=new BufferedOutputStream(response.getOutputStream())
       def osw=new OutputStreamWriter(osb,"UTF-8")
	   lst.each{
		   osw.write("<option value=\""+it+"\">"+it+"</option>")
	   }
     }
}