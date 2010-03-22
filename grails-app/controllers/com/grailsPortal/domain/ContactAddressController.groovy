

package com.grailsPortal.domain

class ContactAddressController {
/**
 *  ContactAddressController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ contactAddressInstanceList: ContactAddress.list( params ), contactAddressInstanceTotal: ContactAddress.count() ]
    }
	
    def show = {
        def contactAddressInstance = ContactAddress.get( params.id )

        if(!contactAddressInstance) {
            flash.message = "ContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[contactAddressInstance:contactAddressInstance],view:'show') 
			}
    }
	
    def delete = {
        def contactAddressInstance = ContactAddress.get( params.id )
        if(contactAddressInstance) {
            try {
                contactAddressInstance.delete(flush:true)
                flash.message = "ContactAddress ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ContactAddress ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def contactAddressInstance = ContactAddress.get( params.id )

        if(!contactAddressInstance) {
            flash.message = "ContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ contactAddressInstance : contactAddressInstance ])
        }
    }

    def update = {
        def contactAddressInstance = ContactAddress.get( params.id )
        if(contactAddressInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(contactAddressInstance.version > version) {
                    
                    contactAddressInstance.errors.rejectValue("version", "contactAddress.optimistic.locking.failure", "Another user has updated this ContactAddress while you were editing.")
                    render(view:'edit',model:[contactAddressInstance:contactAddressInstance])
                    return
                }
            }
            contactAddressInstance.properties = params
            if(!contactAddressInstance.hasErrors() && contactAddressInstance.save()) {
                flash.message = "ContactAddress ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ContactAddress ${params.id} has Errors on update Please try again"
				render(action:show,id:contactAddressInstance.id)
            }
        }
        else {
            flash.message = "ContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def contactAddressInstance = new ContactAddress()
        contactAddressInstance.properties = params
        render(view:'create',model:['contactAddressInstance':contactAddressInstance])
    }

    def save = {
        def contactAddressInstance = new ContactAddress(params)
        if(!contactAddressInstance.hasErrors() && contactAddressInstance.save()) {
            flash.message = "ContactAddress ${contactAddressInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ContactAddress ${contactAddressInstance.id} error"
			render(action:show,id:contactAddressInstance.id)
        }
    }
}
