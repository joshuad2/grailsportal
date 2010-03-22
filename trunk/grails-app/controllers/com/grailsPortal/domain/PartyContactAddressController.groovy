

package com.grailsPortal.domain

class PartyContactAddressController {
/**
 *  PartyContactAddressController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyContactAddressInstanceList: PartyContactAddress.list( params ), partyContactAddressInstanceTotal: PartyContactAddress.count() ]
    }
	
    def show = {
        def partyContactAddressInstance = PartyContactAddress.get( params.id )

        if(!partyContactAddressInstance) {
            flash.message = "PartyContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyContactAddressInstance:partyContactAddressInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyContactAddressInstance = PartyContactAddress.get( params.id )
        if(partyContactAddressInstance) {
            try {
                partyContactAddressInstance.delete(flush:true)
                flash.message = "PartyContactAddress ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PartyContactAddress ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PartyContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyContactAddressInstance = PartyContactAddress.get( params.id )

        if(!partyContactAddressInstance) {
            flash.message = "PartyContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyContactAddressInstance : partyContactAddressInstance ])
        }
    }

    def update = {
        def partyContactAddressInstance = PartyContactAddress.get( params.id )
        if(partyContactAddressInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyContactAddressInstance.version > version) {
                    
                    partyContactAddressInstance.errors.rejectValue("version", "partyContactAddress.optimistic.locking.failure", "Another user has updated this PartyContactAddress while you were editing.")
                    render(view:'edit',model:[partyContactAddressInstance:partyContactAddressInstance])
                    return
                }
            }
            partyContactAddressInstance.properties = params
            if(!partyContactAddressInstance.hasErrors() && partyContactAddressInstance.save()) {
                flash.message = "PartyContactAddress ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PartyContactAddress ${params.id} has Errors on update Please try again"
				render(action:show,id:partyContactAddressInstance.id)
            }
        }
        else {
            flash.message = "PartyContactAddress not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyContactAddressInstance = new PartyContactAddress()
        partyContactAddressInstance.properties = params
        render(view:'create',model:['partyContactAddressInstance':partyContactAddressInstance])
    }

    def save = {
        def partyContactAddressInstance = new PartyContactAddress(params)
        if(!partyContactAddressInstance.hasErrors() && partyContactAddressInstance.save()) {
            flash.message = "PartyContactAddress ${partyContactAddressInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PartyContactAddress ${partyContactAddressInstance.id} error"
			render(action:show,id:partyContactAddressInstance.id)
        }
    }
}
