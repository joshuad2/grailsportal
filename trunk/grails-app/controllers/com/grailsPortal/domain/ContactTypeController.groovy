

package com.grailsPortal.domain

class ContactTypeController {
/**
 *  ContactTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ contactTypeInstanceList: ContactType.list( params ), contactTypeInstanceTotal: ContactType.count() ]
    }
	
    def show = {
        def contactTypeInstance = ContactType.get( params.id )

        if(!contactTypeInstance) {
            flash.message = "ContactType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[contactTypeInstance:contactTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def contactTypeInstance = ContactType.get( params.id )
        if(contactTypeInstance) {
            try {
                contactTypeInstance.delete(flush:true)
                flash.message = "ContactType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ContactType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ContactType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def contactTypeInstance = ContactType.get( params.id )

        if(!contactTypeInstance) {
            flash.message = "ContactType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ contactTypeInstance : contactTypeInstance ])
        }
    }

    def update = {
        def contactTypeInstance = ContactType.get( params.id )
        if(contactTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(contactTypeInstance.version > version) {
                    
                    contactTypeInstance.errors.rejectValue("version", "contactType.optimistic.locking.failure", "Another user has updated this ContactType while you were editing.")
                    render(view:'edit',model:[contactTypeInstance:contactTypeInstance])
                    return
                }
            }
            contactTypeInstance.properties = params
            if(!contactTypeInstance.hasErrors() && contactTypeInstance.save()) {
                flash.message = "ContactType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ContactType ${params.id} has Errors on update Please try again"
				render(action:show,id:contactTypeInstance.id)
            }
        }
        else {
            flash.message = "ContactType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def contactTypeInstance = new ContactType()
        contactTypeInstance.properties = params
        render(view:'create',model:['contactTypeInstance':contactTypeInstance])
    }

    def save = {
        def contactTypeInstance = new ContactType(params)
        if(!contactTypeInstance.hasErrors() && contactTypeInstance.save()) {
            flash.message = "ContactType ${contactTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ContactType ${contactTypeInstance.id} error"
			render(action:show,id:contactTypeInstance.id)
        }
    }
}
