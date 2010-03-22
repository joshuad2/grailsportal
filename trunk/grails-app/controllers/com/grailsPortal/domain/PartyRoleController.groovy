

package com.grailsPortal.domain

class PartyRoleController {
/**
 *  PartyRoleController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyRoleInstanceList: PartyRole.list( params ), partyRoleInstanceTotal: PartyRole.count() ]
    }
	
    def show = {
        def partyRoleInstance = PartyRole.get( params.id )

        if(!partyRoleInstance) {
            flash.message = "PartyRole not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyRoleInstance:partyRoleInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyRoleInstance = PartyRole.get( params.id )
        if(partyRoleInstance) {
            try {
                partyRoleInstance.delete(flush:true)
                flash.message = "PartyRole ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PartyRole ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PartyRole not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyRoleInstance = PartyRole.get( params.id )

        if(!partyRoleInstance) {
            flash.message = "PartyRole not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyRoleInstance : partyRoleInstance ])
        }
    }

    def update = {
        def partyRoleInstance = PartyRole.get( params.id )
        if(partyRoleInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyRoleInstance.version > version) {
                    
                    partyRoleInstance.errors.rejectValue("version", "partyRole.optimistic.locking.failure", "Another user has updated this PartyRole while you were editing.")
                    render(view:'edit',model:[partyRoleInstance:partyRoleInstance])
                    return
                }
            }
            partyRoleInstance.properties = params
            if(!partyRoleInstance.hasErrors() && partyRoleInstance.save()) {
                flash.message = "PartyRole ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PartyRole ${params.id} has Errors on update Please try again"
				render(action:show,id:partyRoleInstance.id)
            }
        }
        else {
            flash.message = "PartyRole not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyRoleInstance = new PartyRole()
        partyRoleInstance.properties = params
        render(view:'create',model:['partyRoleInstance':partyRoleInstance])
    }

    def save = {
        def partyRoleInstance = new PartyRole(params)
        if(!partyRoleInstance.hasErrors() && partyRoleInstance.save()) {
            flash.message = "PartyRole ${partyRoleInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PartyRole ${partyRoleInstance.id} error"
			render(action:show,id:partyRoleInstance.id)
        }
    }
}
