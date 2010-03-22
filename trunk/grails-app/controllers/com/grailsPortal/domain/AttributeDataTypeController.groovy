

package com.grailsPortal.domain

class AttributeDataTypeController {
/**
 *  AttributeDataTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ attributeDataTypeInstanceList: AttributeDataType.list( params ), attributeDataTypeInstanceTotal: AttributeDataType.count() ]
    }
	
    def show = {
        def attributeDataTypeInstance = AttributeDataType.get( params.id )

        if(!attributeDataTypeInstance) {
            flash.message = "AttributeDataType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[attributeDataTypeInstance:attributeDataTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def attributeDataTypeInstance = AttributeDataType.get( params.id )
        if(attributeDataTypeInstance) {
            try {
                attributeDataTypeInstance.delete(flush:true)
                flash.message = "AttributeDataType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "AttributeDataType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "AttributeDataType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attributeDataTypeInstance = AttributeDataType.get( params.id )

        if(!attributeDataTypeInstance) {
            flash.message = "AttributeDataType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ attributeDataTypeInstance : attributeDataTypeInstance ])
        }
    }

    def update = {
        def attributeDataTypeInstance = AttributeDataType.get( params.id )
        if(attributeDataTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(attributeDataTypeInstance.version > version) {
                    
                    attributeDataTypeInstance.errors.rejectValue("version", "attributeDataType.optimistic.locking.failure", "Another user has updated this AttributeDataType while you were editing.")
                    render(view:'edit',model:[attributeDataTypeInstance:attributeDataTypeInstance])
                    return
                }
            }
            attributeDataTypeInstance.properties = params
            if(!attributeDataTypeInstance.hasErrors() && attributeDataTypeInstance.save()) {
                flash.message = "AttributeDataType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "AttributeDataType ${params.id} has Errors on update Please try again"
				render(action:show,id:attributeDataTypeInstance.id)
            }
        }
        else {
            flash.message = "AttributeDataType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def attributeDataTypeInstance = new AttributeDataType()
        attributeDataTypeInstance.properties = params
        render(view:'create',model:['attributeDataTypeInstance':attributeDataTypeInstance])
    }

    def save = {
        def attributeDataTypeInstance = new AttributeDataType(params)
        if(!attributeDataTypeInstance.hasErrors() && attributeDataTypeInstance.save()) {
            flash.message = "AttributeDataType ${attributeDataTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "AttributeDataType ${attributeDataTypeInstance.id} error"
			render(action:show,id:attributeDataTypeInstance.id)
        }
    }
}
