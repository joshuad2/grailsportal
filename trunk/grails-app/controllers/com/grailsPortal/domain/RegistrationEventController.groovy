

package com.grailsPortal.domain

class RegistrationEventController {
/**
 *  RegistrationEventController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ registrationEventInstanceList: RegistrationEvent.list( params ), registrationEventInstanceTotal: RegistrationEvent.count() ]
    }
	
    def show = {
        def registrationEventInstance = RegistrationEvent.get( params.id )

        if(!registrationEventInstance) {
            flash.message = "RegistrationEvent not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[registrationEventInstance:registrationEventInstance],view:'show') 
			}
    }
	
    def delete = {
        def registrationEventInstance = RegistrationEvent.get( params.id )
        if(registrationEventInstance) {
            try {
                registrationEventInstance.delete(flush:true)
                flash.message = "RegistrationEvent ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "RegistrationEvent ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "RegistrationEvent not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def registrationEventInstance = RegistrationEvent.get( params.id )

        if(!registrationEventInstance) {
            flash.message = "RegistrationEvent not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ registrationEventInstance : registrationEventInstance ])
        }
    }

    def update = {
        def registrationEventInstance = RegistrationEvent.get( params.id )
        if(registrationEventInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(registrationEventInstance.version > version) {
                    
                    registrationEventInstance.errors.rejectValue("version", "registrationEvent.optimistic.locking.failure", "Another user has updated this RegistrationEvent while you were editing.")
                    render(view:'edit',model:[registrationEventInstance:registrationEventInstance])
                    return
                }
            }
            registrationEventInstance.properties = params
            if(!registrationEventInstance.hasErrors() && registrationEventInstance.save()) {
                flash.message = "RegistrationEvent ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "RegistrationEvent ${params.id} has Errors on update Please try again"
				render(action:show,id:registrationEventInstance.id)
            }
        }
        else {
            flash.message = "RegistrationEvent not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def registrationEventInstance = new RegistrationEvent()
        registrationEventInstance.properties = params
        render(view:'create',model:['registrationEventInstance':registrationEventInstance])
    }

    def save = {
        def registrationEventInstance = new RegistrationEvent(params)
        if(!registrationEventInstance.hasErrors() && registrationEventInstance.save()) {
            flash.message = "RegistrationEvent ${registrationEventInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "RegistrationEvent ${registrationEventInstance.id} error"
			render(action:show,id:registrationEventInstance.id)
        }
    }
}
