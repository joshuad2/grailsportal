

package com.grailsPortal.domain

class AttributeTypeController {
/**
 *  AttributeTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ attributeTypeInstanceList: AttributeType.list( params ), attributeTypeInstanceTotal: AttributeType.count() ]
    }
	
    def show = {
        def attributeTypeInstance = AttributeType.get( params.id )

        if(!attributeTypeInstance) {
            flash.message = "AttributeType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[attributeTypeInstance:attributeTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def attributeTypeInstance = AttributeType.get( params.id )
        if(attributeTypeInstance) {
            try {
                attributeTypeInstance.delete(flush:true)
                flash.message = "AttributeType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "AttributeType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "AttributeType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attributeTypeInstance = AttributeType.get( params.id )

        if(!attributeTypeInstance) {
            flash.message = "AttributeType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ attributeTypeInstance : attributeTypeInstance ])
        }
    }

    def update = {
        def attributeTypeInstance = AttributeType.get( params.id )
        if(attributeTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(attributeTypeInstance.version > version) {
                    
                    attributeTypeInstance.errors.rejectValue("version", "attributeType.optimistic.locking.failure", "Another user has updated this AttributeType while you were editing.")
                    render(view:'edit',model:[attributeTypeInstance:attributeTypeInstance])
                    return
                }
            }
            attributeTypeInstance.properties = params
            if(!attributeTypeInstance.hasErrors() && attributeTypeInstance.save()) {
                flash.message = "AttributeType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "AttributeType ${params.id} has Errors on update Please try again"
				render(action:show,id:attributeTypeInstance.id)
            }
        }
        else {
            flash.message = "AttributeType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def attributeTypeInstance = new AttributeType()
        attributeTypeInstance.properties = params
        render(view:'create',model:['attributeTypeInstance':attributeTypeInstance])
    }

    def save = {
        def attributeTypeInstance = new AttributeType(params)
        if(!attributeTypeInstance.hasErrors() && attributeTypeInstance.save()) {
            flash.message = "AttributeType ${attributeTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "AttributeType ${attributeTypeInstance.id} error"
			render(action:show,id:attributeTypeInstance.id)
        }
    }
}
