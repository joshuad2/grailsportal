

package com.grailsPortal.domain

class ComponentController {
/**
 *  ComponentController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ componentInstanceList: Component.list( params ), componentInstanceTotal: Component.count() ]
    }
	
    def show = {
        def componentInstance = Component.get( params.id )

        if(!componentInstance) {
            flash.message = "Component not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[componentInstance:componentInstance],view:'show') 
			}
    }
	
    def delete = {
        def componentInstance = Component.get( params.id )
        if(componentInstance) {
            try {
                componentInstance.delete(flush:true)
                flash.message = "Component ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Component ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Component not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def componentInstance = Component.get( params.id )

        if(!componentInstance) {
            flash.message = "Component not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ componentInstance : componentInstance ])
        }
    }

    def update = {
        def componentInstance = Component.get( params.id )
        if(componentInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(componentInstance.version > version) {
                    
                    componentInstance.errors.rejectValue("version", "component.optimistic.locking.failure", "Another user has updated this Component while you were editing.")
                    render(view:'edit',model:[componentInstance:componentInstance])
                    return
                }
            }
            componentInstance.properties = params
            if(!componentInstance.hasErrors() && componentInstance.save()) {
                flash.message = "Component ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "Component ${params.id} has Errors on update Please try again"
				render(action:show,id:componentInstance.id)
            }
        }
        else {
            flash.message = "Component not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def componentInstance = new Component()
        componentInstance.properties = params
        render(view:'create',model:['componentInstance':componentInstance])
    }

    def save = {
        def componentInstance = new Component(params)
        if(!componentInstance.hasErrors() && componentInstance.save()) {
            flash.message = "Component ${componentInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "Component ${componentInstance.id} error"
			render(action:show,id:componentInstance.id)
        }
    }
}
