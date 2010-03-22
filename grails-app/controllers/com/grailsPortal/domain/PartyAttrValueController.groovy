

package com.grailsPortal.domain

class PartyAttrValueController {
/**
 *  PartyAttrValueController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ partyAttrValueInstanceList: PartyAttrValue.list( params ), partyAttrValueInstanceTotal: PartyAttrValue.count() ]
    }
	
    def show = {
        def partyAttrValueInstance = PartyAttrValue.get( params.id )

        if(!partyAttrValueInstance) {
            flash.message = "PartyAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[partyAttrValueInstance:partyAttrValueInstance],view:'show') 
			}
    }
	
    def delete = {
        def partyAttrValueInstance = PartyAttrValue.get( params.id )
        if(partyAttrValueInstance) {
            try {
                partyAttrValueInstance.delete(flush:true)
                flash.message = "PartyAttrValue ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PartyAttrValue ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PartyAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def partyAttrValueInstance = PartyAttrValue.get( params.id )

        if(!partyAttrValueInstance) {
            flash.message = "PartyAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ partyAttrValueInstance : partyAttrValueInstance ])
        }
    }

    def update = {
        def partyAttrValueInstance = PartyAttrValue.get( params.id )
        if(partyAttrValueInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(partyAttrValueInstance.version > version) {
                    
                    partyAttrValueInstance.errors.rejectValue("version", "partyAttrValue.optimistic.locking.failure", "Another user has updated this PartyAttrValue while you were editing.")
                    render(view:'edit',model:[partyAttrValueInstance:partyAttrValueInstance])
                    return
                }
            }
            partyAttrValueInstance.properties = params
            if(!partyAttrValueInstance.hasErrors() && partyAttrValueInstance.save()) {
                flash.message = "PartyAttrValue ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PartyAttrValue ${params.id} has Errors on update Please try again"
				render(action:show,id:partyAttrValueInstance.id)
            }
        }
        else {
            flash.message = "PartyAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def partyAttrValueInstance = new PartyAttrValue()
        partyAttrValueInstance.properties = params
        render(view:'create',model:['partyAttrValueInstance':partyAttrValueInstance])
    }

    def save = {
        def partyAttrValueInstance = new PartyAttrValue(params)
        if(!partyAttrValueInstance.hasErrors() && partyAttrValueInstance.save()) {
            flash.message = "PartyAttrValue ${partyAttrValueInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PartyAttrValue ${partyAttrValueInstance.id} error"
			render(action:show,id:partyAttrValueInstance.id)
        }
    }
}
