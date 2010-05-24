

package com.grailsPortal.domain

class ContactAddressController {
/**
 *  ContactAddressController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

	def scaffold = true
}
