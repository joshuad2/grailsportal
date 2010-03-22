

package com.grailsPortal.domain

class RegistrationEventPartyController {
/**
 *  RegistrationEventPartyController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ registrationEventPartyInstanceList: RegistrationEventParty.list( params ), registrationEventPartyInstanceTotal: RegistrationEventParty.count() ]
    }
	
    def show = {
        def registrationEventPartyInstance = RegistrationEventParty.get( params.id )

        if(!registrationEventPartyInstance) {
            flash.message = "RegistrationEventParty not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[registrationEventPartyInstance:registrationEventPartyInstance],view:'show') 
			}
    }
	
    def delete = {
        def registrationEventPartyInstance = RegistrationEventParty.get( params.id )
        if(registrationEventPartyInstance) {
            try {
                registrationEventPartyInstance.delete(flush:true)
                flash.message = "RegistrationEventParty ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "RegistrationEventParty ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "RegistrationEventParty not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def registrationEventPartyInstance = RegistrationEventParty.get( params.id )

        if(!registrationEventPartyInstance) {
            flash.message = "RegistrationEventParty not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ registrationEventPartyInstance : registrationEventPartyInstance ])
        }
    }

    def update = {
        def registrationEventPartyInstance = RegistrationEventParty.get( params.id )
        if(registrationEventPartyInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(registrationEventPartyInstance.version > version) {
                    
                    registrationEventPartyInstance.errors.rejectValue("version", "registrationEventParty.optimistic.locking.failure", "Another user has updated this RegistrationEventParty while you were editing.")
                    render(view:'edit',model:[registrationEventPartyInstance:registrationEventPartyInstance])
                    return
                }
            }
            registrationEventPartyInstance.properties = params
            if(!registrationEventPartyInstance.hasErrors() && registrationEventPartyInstance.save()) {
                flash.message = "RegistrationEventParty ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "RegistrationEventParty ${params.id} has Errors on update Please try again"
				render(action:show,id:registrationEventPartyInstance.id)
            }
        }
        else {
            flash.message = "RegistrationEventParty not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def registrationEventPartyInstance = new RegistrationEventParty()
        registrationEventPartyInstance.properties = params
        render(view:'create',model:['registrationEventPartyInstance':registrationEventPartyInstance])
    }

    def save = {
        def registrationEventPartyInstance = new RegistrationEventParty(params)
        if(!registrationEventPartyInstance.hasErrors() && registrationEventPartyInstance.save()) {
            flash.message = "RegistrationEventParty ${registrationEventPartyInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "RegistrationEventParty ${registrationEventPartyInstance.id} error"
			render(action:show,id:registrationEventPartyInstance.id)
        }
    }
}
