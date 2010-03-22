

package com.grailsPortal.domain

class PartyTypeController {
/**
 *  PartyTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyTypeInstanceList: PartyType.list( params ), partyTypeInstanceTotal: PartyType.count() ]
    }
	
    def show = {
        def partyTypeInstance = PartyType.get( params.id )

        if(!partyTypeInstance) {
            flash.message = "PartyType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyTypeInstance:partyTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyTypeInstance = PartyType.get( params.id )
        if(partyTypeInstance) {
            try {
                partyTypeInstance.delete(flush:true)
                flash.message = "PartyType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PartyType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PartyType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyTypeInstance = PartyType.get( params.id )

        if(!partyTypeInstance) {
            flash.message = "PartyType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyTypeInstance : partyTypeInstance ])
        }
    }

    def update = {
        def partyTypeInstance = PartyType.get( params.id )
        if(partyTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyTypeInstance.version > version) {
                    
                    partyTypeInstance.errors.rejectValue("version", "partyType.optimistic.locking.failure", "Another user has updated this PartyType while you were editing.")
                    render(view:'edit',model:[partyTypeInstance:partyTypeInstance])
                    return
                }
            }
            partyTypeInstance.properties = params
            if(!partyTypeInstance.hasErrors() && partyTypeInstance.save()) {
                flash.message = "PartyType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PartyType ${params.id} has Errors on update Please try again"
				render(action:show,id:partyTypeInstance.id)
            }
        }
        else {
            flash.message = "PartyType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyTypeInstance = new PartyType()
        partyTypeInstance.properties = params
        render(view:'create',model:['partyTypeInstance':partyTypeInstance])
    }

    def save = {
        def partyTypeInstance = new PartyType(params)
        if(!partyTypeInstance.hasErrors() && partyTypeInstance.save()) {
            flash.message = "PartyType ${partyTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PartyType ${partyTypeInstance.id} error"
			render(action:show,id:partyTypeInstance.id)
        }
    }
}
