

package com.grailsPortal.domain

class ViewAttributeController {
/**
 *  ViewAttributeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ viewAttributeInstanceList: ViewAttribute.list( params ), viewAttributeInstanceTotal: ViewAttribute.count() ]
    }
	
    def show = {
        def viewAttributeInstance = ViewAttribute.get( params.id )

        if(!viewAttributeInstance) {
            flash.message = "ViewAttribute not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[viewAttributeInstance:viewAttributeInstance],view:'show') 
			}
    }
	
    def delete = {
        def viewAttributeInstance = ViewAttribute.get( params.id )
        if(viewAttributeInstance) {
            try {
                viewAttributeInstance.delete(flush:true)
                flash.message = "ViewAttribute ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ViewAttribute ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ViewAttribute not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def viewAttributeInstance = ViewAttribute.get( params.id )

        if(!viewAttributeInstance) {
            flash.message = "ViewAttribute not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ viewAttributeInstance : viewAttributeInstance ])
        }
    }

    def update = {
        def viewAttributeInstance = ViewAttribute.get( params.id )
        if(viewAttributeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(viewAttributeInstance.version > version) {
                    
                    viewAttributeInstance.errors.rejectValue("version", "viewAttribute.optimistic.locking.failure", "Another user has updated this ViewAttribute while you were editing.")
                    render(view:'edit',model:[viewAttributeInstance:viewAttributeInstance])
                    return
                }
            }
            viewAttributeInstance.properties = params
            if(!viewAttributeInstance.hasErrors() && viewAttributeInstance.save()) {
                flash.message = "ViewAttribute ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ViewAttribute ${params.id} has Errors on update Please try again"
				render(action:show,id:viewAttributeInstance.id)
            }
        }
        else {
            flash.message = "ViewAttribute not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def viewAttributeInstance = new ViewAttribute()
        viewAttributeInstance.properties = params
        render(view:'create',model:['viewAttributeInstance':viewAttributeInstance])
    }

    def save = {
        def viewAttributeInstance = new ViewAttribute(params)
        if(!viewAttributeInstance.hasErrors() && viewAttributeInstance.save()) {
            flash.message = "ViewAttribute ${viewAttributeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ViewAttribute ${viewAttributeInstance.id} error"
			render(action:show,id:viewAttributeInstance.id)
        }
    }
}
