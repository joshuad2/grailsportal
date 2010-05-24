

package com.grailsPortal.domain

class AttributeController {
/**
 *  AttributeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

	def scaffold = true
}
