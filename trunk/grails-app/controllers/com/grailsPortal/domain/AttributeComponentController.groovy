

package com.grailsPortal.domain

class AttributeComponentController {
/**
 *  AttributeComponentController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ attributeComponentInstanceList: AttributeComponent.list( params ), attributeComponentInstanceTotal: AttributeComponent.count() ]
    }
	
    def show = {
        def attributeComponentInstance = AttributeComponent.get( params.id )

        if(!attributeComponentInstance) {
            flash.message = "AttributeComponent not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[attributeComponentInstance:attributeComponentInstance],view:'show') 
			}
    }
	
    def delete = {
        def attributeComponentInstance = AttributeComponent.get( params.id )
        if(attributeComponentInstance) {
            try {
                attributeComponentInstance.delete(flush:true)
                flash.message = "AttributeComponent ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "AttributeComponent ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "AttributeComponent not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attributeComponentInstance = AttributeComponent.get( params.id )

        if(!attributeComponentInstance) {
            flash.message = "AttributeComponent not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ attributeComponentInstance : attributeComponentInstance ])
        }
    }

    def update = {
        def attributeComponentInstance = AttributeComponent.get( params.id )
        if(attributeComponentInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(attributeComponentInstance.version > version) {
                    
                    attributeComponentInstance.errors.rejectValue("version", "attributeComponent.optimistic.locking.failure", "Another user has updated this AttributeComponent while you were editing.")
                    render(view:'edit',model:[attributeComponentInstance:attributeComponentInstance])
                    return
                }
            }
            attributeComponentInstance.properties = params
            if(!attributeComponentInstance.hasErrors() && attributeComponentInstance.save()) {
                flash.message = "AttributeComponent ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "AttributeComponent ${params.id} has Errors on update Please try again"
				render(action:show,id:attributeComponentInstance.id)
            }
        }
        else {
            flash.message = "AttributeComponent not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def attributeComponentInstance = new AttributeComponent()
        attributeComponentInstance.properties = params
        render(view:'create',model:['attributeComponentInstance':attributeComponentInstance])
    }

    def save = {
        def attributeComponentInstance = new AttributeComponent(params)
        if(!attributeComponentInstance.hasErrors() && attributeComponentInstance.save()) {
            flash.message = "AttributeComponent ${attributeComponentInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "AttributeComponent ${attributeComponentInstance.id} error"
			render(action:show,id:attributeComponentInstance.id)
        }
    }
}
