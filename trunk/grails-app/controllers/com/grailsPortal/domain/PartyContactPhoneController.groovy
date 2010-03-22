

package com.grailsPortal.domain

class PartyContactPhoneController {
/**
 *  PartyContactPhoneController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyContactPhoneInstanceList: PartyContactPhone.list( params ), partyContactPhoneInstanceTotal: PartyContactPhone.count() ]
    }
	
    def show = {
        def partyContactPhoneInstance = PartyContactPhone.get( params.id )

        if(!partyContactPhoneInstance) {
            flash.message = "PartyContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyContactPhoneInstance:partyContactPhoneInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyContactPhoneInstance = PartyContactPhone.get( params.id )
        if(partyContactPhoneInstance) {
            try {
                partyContactPhoneInstance.delete(flush:true)
                flash.message = "PartyContactPhone ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PartyContactPhone ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PartyContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyContactPhoneInstance = PartyContactPhone.get( params.id )

        if(!partyContactPhoneInstance) {
            flash.message = "PartyContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyContactPhoneInstance : partyContactPhoneInstance ])
        }
    }

    def update = {
        def partyContactPhoneInstance = PartyContactPhone.get( params.id )
        if(partyContactPhoneInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyContactPhoneInstance.version > version) {
                    
                    partyContactPhoneInstance.errors.rejectValue("version", "partyContactPhone.optimistic.locking.failure", "Another user has updated this PartyContactPhone while you were editing.")
                    render(view:'edit',model:[partyContactPhoneInstance:partyContactPhoneInstance])
                    return
                }
            }
            partyContactPhoneInstance.properties = params
            if(!partyContactPhoneInstance.hasErrors() && partyContactPhoneInstance.save()) {
                flash.message = "PartyContactPhone ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PartyContactPhone ${params.id} has Errors on update Please try again"
				render(action:show,id:partyContactPhoneInstance.id)
            }
        }
        else {
            flash.message = "PartyContactPhone not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyContactPhoneInstance = new PartyContactPhone()
        partyContactPhoneInstance.properties = params
        render(view:'create',model:['partyContactPhoneInstance':partyContactPhoneInstance])
    }

    def save = {
        def partyContactPhoneInstance = new PartyContactPhone(params)
        if(!partyContactPhoneInstance.hasErrors() && partyContactPhoneInstance.save()) {
            flash.message = "PartyContactPhone ${partyContactPhoneInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PartyContactPhone ${partyContactPhoneInstance.id} error"
			render(action:show,id:partyContactPhoneInstance.id)
        }
    }
}
