

package com.grailsPortal.domain

class PartyContactEmailController {
/**
 *  PartyContactEmailController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyContactEmailInstanceList: PartyContactEmail.list( params ), partyContactEmailInstanceTotal: PartyContactEmail.count() ]
    }
	
    def show = {
        def partyContactEmailInstance = PartyContactEmail.get( params.id )

        if(!partyContactEmailInstance) {
            flash.message = "PartyContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyContactEmailInstance:partyContactEmailInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyContactEmailInstance = PartyContactEmail.get( params.id )
        if(partyContactEmailInstance) {
            try {
                partyContactEmailInstance.delete(flush:true)
                flash.message = "PartyContactEmail ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PartyContactEmail ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PartyContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyContactEmailInstance = PartyContactEmail.get( params.id )

        if(!partyContactEmailInstance) {
            flash.message = "PartyContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyContactEmailInstance : partyContactEmailInstance ])
        }
    }

    def update = {
        def partyContactEmailInstance = PartyContactEmail.get( params.id )
        if(partyContactEmailInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyContactEmailInstance.version > version) {
                    
                    partyContactEmailInstance.errors.rejectValue("version", "partyContactEmail.optimistic.locking.failure", "Another user has updated this PartyContactEmail while you were editing.")
                    render(view:'edit',model:[partyContactEmailInstance:partyContactEmailInstance])
                    return
                }
            }
            partyContactEmailInstance.properties = params
            if(!partyContactEmailInstance.hasErrors() && partyContactEmailInstance.save()) {
                flash.message = "PartyContactEmail ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PartyContactEmail ${params.id} has Errors on update Please try again"
				render(action:show,id:partyContactEmailInstance.id)
            }
        }
        else {
            flash.message = "PartyContactEmail not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyContactEmailInstance = new PartyContactEmail()
        partyContactEmailInstance.properties = params
        render(view:'create',model:['partyContactEmailInstance':partyContactEmailInstance])
    }

    def save = {
        def partyContactEmailInstance = new PartyContactEmail(params)
        if(!partyContactEmailInstance.hasErrors() && partyContactEmailInstance.save()) {
            flash.message = "PartyContactEmail ${partyContactEmailInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PartyContactEmail ${partyContactEmailInstance.id} error"
			render(action:show,id:partyContactEmailInstance.id)
        }
    }
}
