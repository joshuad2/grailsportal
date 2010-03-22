

package com.grailsPortal.domain

class AttributeComponentGroupController {
/**
 *  AttributeComponentGroupController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ attributeComponentGroupInstanceList: AttributeComponentGroup.list( params ), attributeComponentGroupInstanceTotal: AttributeComponentGroup.count() ]
    }
	
    def show = {
        def attributeComponentGroupInstance = AttributeComponentGroup.get( params.id )

        if(!attributeComponentGroupInstance) {
            flash.message = "AttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[attributeComponentGroupInstance:attributeComponentGroupInstance],view:'show') 
			}
    }
	
    def delete = {
        def attributeComponentGroupInstance = AttributeComponentGroup.get( params.id )
        if(attributeComponentGroupInstance) {
            try {
                attributeComponentGroupInstance.delete(flush:true)
                flash.message = "AttributeComponentGroup ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "AttributeComponentGroup ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "AttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attributeComponentGroupInstance = AttributeComponentGroup.get( params.id )

        if(!attributeComponentGroupInstance) {
            flash.message = "AttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ attributeComponentGroupInstance : attributeComponentGroupInstance ])
        }
    }

    def update = {
        def attributeComponentGroupInstance = AttributeComponentGroup.get( params.id )
        if(attributeComponentGroupInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(attributeComponentGroupInstance.version > version) {
                    
                    attributeComponentGroupInstance.errors.rejectValue("version", "attributeComponentGroup.optimistic.locking.failure", "Another user has updated this AttributeComponentGroup while you were editing.")
                    render(view:'edit',model:[attributeComponentGroupInstance:attributeComponentGroupInstance])
                    return
                }
            }
            attributeComponentGroupInstance.properties = params
            if(!attributeComponentGroupInstance.hasErrors() && attributeComponentGroupInstance.save()) {
                flash.message = "AttributeComponentGroup ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "AttributeComponentGroup ${params.id} has Errors on update Please try again"
				render(action:show,id:attributeComponentGroupInstance.id)
            }
        }
        else {
            flash.message = "AttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def attributeComponentGroupInstance = new AttributeComponentGroup()
        attributeComponentGroupInstance.properties = params
        render(view:'create',model:['attributeComponentGroupInstance':attributeComponentGroupInstance])
    }

    def save = {
        def attributeComponentGroupInstance = new AttributeComponentGroup(params)
        if(!attributeComponentGroupInstance.hasErrors() && attributeComponentGroupInstance.save()) {
            flash.message = "AttributeComponentGroup ${attributeComponentGroupInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "AttributeComponentGroup ${attributeComponentGroupInstance.id} error"
			render(action:show,id:attributeComponentGroupInstance.id)
        }
    }
}
