

package com.grailsPortal.domain

class AttributeController {
/**
 *  AttributeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ attributeInstanceList: Attribute.list( params ), attributeInstanceTotal: Attribute.count() ]
    }
	
    def show = {
        def attributeInstance = Attribute.get( params.id )

        if(!attributeInstance) {
            flash.message = "Attribute not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[attributeInstance:attributeInstance],view:'show') 
			}
    }
	
    def delete = {
        def attributeInstance = Attribute.get( params.id )
        if(attributeInstance) {
            try {
                attributeInstance.delete(flush:true)
                flash.message = "Attribute ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Attribute ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Attribute not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attributeInstance = Attribute.get( params.id )

        if(!attributeInstance) {
            flash.message = "Attribute not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ attributeInstance : attributeInstance ])
        }
    }

    def update = {
        def attributeInstance = Attribute.get( params.id )
        if(attributeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(attributeInstance.version > version) {
                    
                    attributeInstance.errors.rejectValue("version", "attribute.optimistic.locking.failure", "Another user has updated this Attribute while you were editing.")
                    render(view:'edit',model:[attributeInstance:attributeInstance])
                    return
                }
            }
            attributeInstance.properties = params
            if(!attributeInstance.hasErrors() && attributeInstance.save()) {
                flash.message = "Attribute ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "Attribute ${params.id} has Errors on update Please try again"
				render(action:show,id:attributeInstance.id)
            }
        }
        else {
            flash.message = "Attribute not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def attributeInstance = new Attribute()
        attributeInstance.properties = params
        render(view:'create',model:['attributeInstance':attributeInstance])
    }

    def save = {
        def attributeInstance = new Attribute(params)
        if(!attributeInstance.hasErrors() && attributeInstance.save()) {
            flash.message = "Attribute ${attributeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "Attribute ${attributeInstance.id} error"
			render(action:show,id:attributeInstance.id)
        }
    }
}
