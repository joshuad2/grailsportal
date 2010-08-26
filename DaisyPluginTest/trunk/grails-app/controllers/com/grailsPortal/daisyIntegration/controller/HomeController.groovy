package com.grailsPortal.daisyIntegration.controller

class HomeController {
def daisyService
    def index = {
}
 def wellnessEssentials={
	     [wellnessEssentials:daisyService.getHtmlContentFromName("wellnessEssentials"),screen:"spa"]
 }
 def dayspa={
	     [dayspa:daisyService.getHtmlContentFromName("dayspa"),screen:"dayspa"]
 }
 def club={
     [club:daisyService.getHtmlContentFromName("club"),screen:"club"]
 }
 def specials={
	     [specials:daisyService.getHtmlContentFromName("specials"),screen:"specials"] 
 }
 def retail={
	     [retail:daisyService.getHtmlContentFromName("retail"),screen:"retail"]
		 
 }
 def appointments={
		   def lst=daisyService.getFields("Services-List","service")
		   def options=""
		   int i=0;
		    lst.each{
			   options+="<option value=\""+it+"\">"+it+"</option>"
		   }
		   log.error(options)
		 [screen:"appointments",serviceOptions:options]
 }
 def contact={		 
		[screen:"contact"]
 }
 def policies={
	     [policies:daisyService.getHtmlContentFromName("policies")]
		 
 }
}
