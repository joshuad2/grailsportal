package com.daisyPlugin.controller
import com.daisyPlugin.daisyIntegration.service.*;
class DaisyController {
def daisyService
    def index = { }
   def contentHandler={
	    def theId=params.id
		if (theId!=null){
		  DaisyService ds=daisyService
		  def documentType=ds.getDocumentType(theId)
		  if (documentType=="Image"){
			ds.doImage(theId,response.getOutputStream())
			response.setContentType("image/jpeg")
		  }else
		  if (documentType=="SimpleDocument"){
			def osb=new BufferedOutputStream(response.getOutputStream())
			def osw=new OutputStreamWriter(osb,"UTF-8")
			osw.write(ds.getHtmlContent(theId))
		  }
		}
      }
   def contentList={
	   def documentName=params.documentName
	   def documentList=params.fieldType
	   if (documentName!=null && documentList!=null){
	     def lst=daisyService.getFields(documentName,fieldType)
	     def osb=new BufferedOutputStream(response.getOutputStream())
         def osw=new OutputStreamWriter(osb,"UTF-8")
	     lst.each{
		   osw.write("<option value=\""+it+"\">"+it+"</option>")
	     }
	   }
     }
}