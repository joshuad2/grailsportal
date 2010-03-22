

package com.grailsPortal.domain

class PartyController {
/**
 *  PartyController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyInstanceList: Party.list( params ), partyInstanceTotal: Party.count() ]
    }
	
    def show = {
        def partyInstance = Party.get( params.id )

        if(!partyInstance) {
            flash.message = "Party not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyInstance:partyInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyInstance = Party.get( params.id )
        if(partyInstance) {
            try {
                partyInstance.delete(flush:true)
                flash.message = "Party ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Party ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Party not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyInstance = Party.get( params.id )

        if(!partyInstance) {
            flash.message = "Party not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyInstance : partyInstance ])
        }
    }

    def update = {
        def partyInstance = Party.get( params.id )
        if(partyInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyInstance.version > version) {
                    
                    partyInstance.errors.rejectValue("version", "party.optimistic.locking.failure", "Another user has updated this Party while you were editing.")
                    render(view:'edit',model:[partyInstance:partyInstance])
                    return
                }
            }
            partyInstance.properties = params
            if(!partyInstance.hasErrors() && partyInstance.save()) {
                flash.message = "Party ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "Party ${params.id} has Errors on update Please try again"
				render(action:show,id:partyInstance.id)
            }
        }
        else {
            flash.message = "Party not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyInstance = new Party()
        partyInstance.properties = params
        render(view:'create',model:['partyInstance':partyInstance])
    }

    def save = {
        def partyInstance = new Party(params)
        if(!partyInstance.hasErrors() && partyInstance.save()) {
            flash.message = "Party ${partyInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "Party ${partyInstance.id} error"
			render(action:show,id:partyInstance.id)
        }
    }
}
