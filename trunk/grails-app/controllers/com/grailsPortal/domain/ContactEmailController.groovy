

package com.grailsPortal.domain

class ContactEmailController {
/**
 *  ContactEmailController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ contactEmailInstanceList: ContactEmail.list( params ), contactEmailInstanceTotal: ContactEmail.count() ]
    }
	
    def show = {
        def contactEmailInstance = ContactEmail.get( params.id )

        if(!contactEmailInstance) {
            flash.message = "ContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[contactEmailInstance:contactEmailInstance],view:'show') 
			}
    }
	
    def delete = {
        def contactEmailInstance = ContactEmail.get( params.id )
        if(contactEmailInstance) {
            try {
                contactEmailInstance.delete(flush:true)
                flash.message = "ContactEmail ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ContactEmail ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def contactEmailInstance = ContactEmail.get( params.id )

        if(!contactEmailInstance) {
            flash.message = "ContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ contactEmailInstance : contactEmailInstance ])
        }
    }

    def update = {
        def contactEmailInstance = ContactEmail.get( params.id )
        if(contactEmailInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(contactEmailInstance.version > version) {
                    
                    contactEmailInstance.errors.rejectValue("version", "contactEmail.optimistic.locking.failure", "Another user has updated this ContactEmail while you were editing.")
                    render(view:'edit',model:[contactEmailInstance:contactEmailInstance])
                    return
                }
            }
            contactEmailInstance.properties = params
            if(!contactEmailInstance.hasErrors() && contactEmailInstance.save()) {
                flash.message = "ContactEmail ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ContactEmail ${params.id} has Errors on update Please try again"
				render(action:show,id:contactEmailInstance.id)
            }
        }
        else {
            flash.message = "ContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def contactEmailInstance = new ContactEmail()
        contactEmailInstance.properties = params
        render(view:'create',model:['contactEmailInstance':contactEmailInstance])
    }

    def save = {
        def contactEmailInstance = new ContactEmail(params)
        if(!contactEmailInstance.hasErrors() && contactEmailInstance.save()) {
            flash.message = "ContactEmail ${contactEmailInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ContactEmail ${contactEmailInstance.id} error"
			render(action:show,id:contactEmailInstance.id)
        }
    }
}
