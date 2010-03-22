

package com.grailsPortal.domain

class PartyRelationshipController {
/**
 *  PartyRelationshipController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyRelationshipInstanceList: PartyRelationship.list( params ), partyRelationshipInstanceTotal: PartyRelationship.count() ]
    }
	
    def show = {
        def partyRelationshipInstance = PartyRelationship.get( params.id )

        if(!partyRelationshipInstance) {
            flash.message = "PartyRelationship not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyRelationshipInstance:partyRelationshipInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyRelationshipInstance = PartyRelationship.get( params.id )
        if(partyRelationshipInstance) {
            try {
                partyRelationshipInstance.delete(flush:true)
                flash.message = "PartyRelationship ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PartyRelationship ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PartyRelationship not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyRelationshipInstance = PartyRelationship.get( params.id )

        if(!partyRelationshipInstance) {
            flash.message = "PartyRelationship not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyRelationshipInstance : partyRelationshipInstance ])
        }
    }

    def update = {
        def partyRelationshipInstance = PartyRelationship.get( params.id )
        if(partyRelationshipInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyRelationshipInstance.version > version) {
                    
                    partyRelationshipInstance.errors.rejectValue("version", "partyRelationship.optimistic.locking.failure", "Another user has updated this PartyRelationship while you were editing.")
                    render(view:'edit',model:[partyRelationshipInstance:partyRelationshipInstance])
                    return
                }
            }
            partyRelationshipInstance.properties = params
            if(!partyRelationshipInstance.hasErrors() && partyRelationshipInstance.save()) {
                flash.message = "PartyRelationship ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PartyRelationship ${params.id} has Errors on update Please try again"
				render(action:show,id:partyRelationshipInstance.id)
            }
        }
        else {
            flash.message = "PartyRelationship not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyRelationshipInstance = new PartyRelationship()
        partyRelationshipInstance.properties = params
        render(view:'create',model:['partyRelationshipInstance':partyRelationshipInstance])
    }

    def save = {
        def partyRelationshipInstance = new PartyRelationship(params)
        if(!partyRelationshipInstance.hasErrors() && partyRelationshipInstance.save()) {
            flash.message = "PartyRelationship ${partyRelationshipInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PartyRelationship ${partyRelationshipInstance.id} error"
			render(action:show,id:partyRelationshipInstance.id)
        }
    }
}
