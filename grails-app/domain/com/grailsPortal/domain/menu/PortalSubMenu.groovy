package com.grailsPortal.domain.menu;

class PortalSubMenu {
  String controller
  String action
  String text
  Boolean isAjax
  Boolean showSpinner
  PortalMenu mainMenu

static constraints={
		controller(display:true,size:1..40,nullable:false,blank:false)
	    action(display:true,size:1..40,nullable:true,blank:true)
	    text(display:true,size:1..100,nullable:false,blank:false)
	    isAjax(display:true)
	}
}
