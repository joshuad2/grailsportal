

package com.grailsPortal.domain

class ContactPhoneController {
/**
 *  ContactPhoneController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ contactPhoneInstanceList: ContactPhone.list( params ), contactPhoneInstanceTotal: ContactPhone.count() ]
    }
	
    def show = {
        def contactPhoneInstance = ContactPhone.get( params.id )

        if(!contactPhoneInstance) {
            flash.message = "ContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[contactPhoneInstance:contactPhoneInstance],view:'show') 
			}
    }
	
    def delete = {
        def contactPhoneInstance = ContactPhone.get( params.id )
        if(contactPhoneInstance) {
            try {
                contactPhoneInstance.delete(flush:true)
                flash.message = "ContactPhone ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ContactPhone ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def contactPhoneInstance = ContactPhone.get( params.id )

        if(!contactPhoneInstance) {
            flash.message = "ContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ contactPhoneInstance : contactPhoneInstance ])
        }
    }

    def update = {
        def contactPhoneInstance = ContactPhone.get( params.id )
        if(contactPhoneInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(contactPhoneInstance.version > version) {
                    
                    contactPhoneInstance.errors.rejectValue("version", "contactPhone.optimistic.locking.failure", "Another user has updated this ContactPhone while you were editing.")
                    render(view:'edit',model:[contactPhoneInstance:contactPhoneInstance])
                    return
                }
            }
            contactPhoneInstance.properties = params
            if(!contactPhoneInstance.hasErrors() && contactPhoneInstance.save()) {
                flash.message = "ContactPhone ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ContactPhone ${params.id} has Errors on update Please try again"
				render(action:show,id:contactPhoneInstance.id)
            }
        }
        else {
            flash.message = "ContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def contactPhoneInstance = new ContactPhone()
        contactPhoneInstance.properties = params
        render(view:'create',model:['contactPhoneInstance':contactPhoneInstance])
    }

    def save = {
        def contactPhoneInstance = new ContactPhone(params)
        if(!contactPhoneInstance.hasErrors() && contactPhoneInstance.save()) {
            flash.message = "ContactPhone ${contactPhoneInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ContactPhone ${contactPhoneInstance.id} error"
			render(action:show,id:contactPhoneInstance.id)
        }
    }
}
