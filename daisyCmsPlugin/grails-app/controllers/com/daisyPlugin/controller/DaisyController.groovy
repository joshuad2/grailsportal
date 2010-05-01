package com.daisyPlugin.controller


class DaisyController {
def daisyService
def dataService
    def index = { }
    def image={
		response.setContentType("image/jpeg");
	    byte[] ba=daisyService.getImage(params.name,response.getOutputStream())
   }
   def contentHandler={
		def ba=daisyService.getDocumentContent(params.name,response.getOutputStream())
		if (ba){
			response.setContentType("image/jpeg")
		}
      }
   def contentList={
	   def lst=daisyService.getFields("Services-List","service")
	   def osb=new BufferedOutputStream(response.getOutputStream())
       def osw=new OutputStreamWriter(osb,"UTF-8")
	   lst.each{
		   osw.write("<option value=\""+it+"\">"+it+"</option>")
	   }
     }
}