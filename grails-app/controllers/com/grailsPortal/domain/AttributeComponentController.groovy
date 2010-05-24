package com.grailsPortal.domain

class AttributeComponentController {
/**
 *  AttributeComponentController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }
	def scaffold = true
}
