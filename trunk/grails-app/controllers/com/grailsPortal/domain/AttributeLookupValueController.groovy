

package com.grailsPortal.domain

class AttributeLookupValueController {
/**
 *  AttributeLookupValueController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ attributeLookupValueInstanceList: AttributeLookupValue.list( params ), attributeLookupValueInstanceTotal: AttributeLookupValue.count() ]
    }
	
    def show = {
        def attributeLookupValueInstance = AttributeLookupValue.get( params.id )

        if(!attributeLookupValueInstance) {
            flash.message = "AttributeLookupValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[attributeLookupValueInstance:attributeLookupValueInstance],view:'show') 
			}
    }
	
    def delete = {
        def attributeLookupValueInstance = AttributeLookupValue.get( params.id )
        if(attributeLookupValueInstance) {
            try {
                attributeLookupValueInstance.delete(flush:true)
                flash.message = "AttributeLookupValue ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "AttributeLookupValue ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "AttributeLookupValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attributeLookupValueInstance = AttributeLookupValue.get( params.id )

        if(!attributeLookupValueInstance) {
            flash.message = "AttributeLookupValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ attributeLookupValueInstance : attributeLookupValueInstance ])
        }
    }

    def update = {
        def attributeLookupValueInstance = AttributeLookupValue.get( params.id )
        if(attributeLookupValueInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(attributeLookupValueInstance.version > version) {
                    
                    attributeLookupValueInstance.errors.rejectValue("version", "attributeLookupValue.optimistic.locking.failure", "Another user has updated this AttributeLookupValue while you were editing.")
                    render(view:'edit',model:[attributeLookupValueInstance:attributeLookupValueInstance])
                    return
                }
            }
            attributeLookupValueInstance.properties = params
            if(!attributeLookupValueInstance.hasErrors() && attributeLookupValueInstance.save()) {
                flash.message = "AttributeLookupValue ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "AttributeLookupValue ${params.id} has Errors on update Please try again"
				render(action:show,id:attributeLookupValueInstance.id)
            }
        }
        else {
            flash.message = "AttributeLookupValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def attributeLookupValueInstance = new AttributeLookupValue()
        attributeLookupValueInstance.properties = params
        render(view:'create',model:['attributeLookupValueInstance':attributeLookupValueInstance])
    }

    def save = {
        def attributeLookupValueInstance = new AttributeLookupValue(params)
        if(!attributeLookupValueInstance.hasErrors() && attributeLookupValueInstance.save()) {
            flash.message = "AttributeLookupValue ${attributeLookupValueInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "AttributeLookupValue ${attributeLookupValueInstance.id} error"
			render(action:show,id:attributeLookupValueInstance.id)
        }
    }
}
