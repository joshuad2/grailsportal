

package com.grailsPortal.domain

class ViewAttributeComponentGroupController {
/**
 *  ViewAttributeComponentGroupController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ viewAttributeComponentGroupInstanceList: ViewAttributeComponentGroup.list( params ), viewAttributeComponentGroupInstanceTotal: ViewAttributeComponentGroup.count() ]
    }
	
    def show = {
        def viewAttributeComponentGroupInstance = ViewAttributeComponentGroup.get( params.id )

        if(!viewAttributeComponentGroupInstance) {
            flash.message = "ViewAttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[viewAttributeComponentGroupInstance:viewAttributeComponentGroupInstance],view:'show') 
			}
    }
	
    def delete = {
        def viewAttributeComponentGroupInstance = ViewAttributeComponentGroup.get( params.id )
        if(viewAttributeComponentGroupInstance) {
            try {
                viewAttributeComponentGroupInstance.delete(flush:true)
                flash.message = "ViewAttributeComponentGroup ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ViewAttributeComponentGroup ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ViewAttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def viewAttributeComponentGroupInstance = ViewAttributeComponentGroup.get( params.id )

        if(!viewAttributeComponentGroupInstance) {
            flash.message = "ViewAttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ viewAttributeComponentGroupInstance : viewAttributeComponentGroupInstance ])
        }
    }

    def update = {
        def viewAttributeComponentGroupInstance = ViewAttributeComponentGroup.get( params.id )
        if(viewAttributeComponentGroupInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(viewAttributeComponentGroupInstance.version > version) {
                    
                    viewAttributeComponentGroupInstance.errors.rejectValue("version", "viewAttributeComponentGroup.optimistic.locking.failure", "Another user has updated this ViewAttributeComponentGroup while you were editing.")
                    render(view:'edit',model:[viewAttributeComponentGroupInstance:viewAttributeComponentGroupInstance])
                    return
                }
            }
            viewAttributeComponentGroupInstance.properties = params
            if(!viewAttributeComponentGroupInstance.hasErrors() && viewAttributeComponentGroupInstance.save()) {
                flash.message = "ViewAttributeComponentGroup ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ViewAttributeComponentGroup ${params.id} has Errors on update Please try again"
				render(action:show,id:viewAttributeComponentGroupInstance.id)
            }
        }
        else {
            flash.message = "ViewAttributeComponentGroup not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def viewAttributeComponentGroupInstance = new ViewAttributeComponentGroup()
        viewAttributeComponentGroupInstance.properties = params
        render(view:'create',model:['viewAttributeComponentGroupInstance':viewAttributeComponentGroupInstance])
    }

    def save = {
        def viewAttributeComponentGroupInstance = new ViewAttributeComponentGroup(params)
        if(!viewAttributeComponentGroupInstance.hasErrors() && viewAttributeComponentGroupInstance.save()) {
            flash.message = "ViewAttributeComponentGroup ${viewAttributeComponentGroupInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ViewAttributeComponentGroup ${viewAttributeComponentGroupInstance.id} error"
			render(action:show,id:viewAttributeComponentGroupInstance.id)
        }
    }
}
