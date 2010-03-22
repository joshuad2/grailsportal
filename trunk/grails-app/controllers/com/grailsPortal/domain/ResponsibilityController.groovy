

package com.grailsPortal.domain

class ResponsibilityController {
/**
 *  ResponsibilityController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ responsibilityInstanceList: Responsibility.list( params ), responsibilityInstanceTotal: Responsibility.count() ]
    }
	
    def show = {
        def responsibilityInstance = Responsibility.get( params.id )

        if(!responsibilityInstance) {
            flash.message = "Responsibility not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[responsibilityInstance:responsibilityInstance],view:'show') 
			}
    }
	
    def delete = {
        def responsibilityInstance = Responsibility.get( params.id )
        if(responsibilityInstance) {
            try {
                responsibilityInstance.delete(flush:true)
                flash.message = "Responsibility ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Responsibility ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Responsibility not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def responsibilityInstance = Responsibility.get( params.id )

        if(!responsibilityInstance) {
            flash.message = "Responsibility not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ responsibilityInstance : responsibilityInstance ])
        }
    }

    def update = {
        def responsibilityInstance = Responsibility.get( params.id )
        if(responsibilityInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(responsibilityInstance.version > version) {
                    
                    responsibilityInstance.errors.rejectValue("version", "responsibility.optimistic.locking.failure", "Another user has updated this Responsibility while you were editing.")
                    render(view:'edit',model:[responsibilityInstance:responsibilityInstance])
                    return
                }
            }
            responsibilityInstance.properties = params
            if(!responsibilityInstance.hasErrors() && responsibilityInstance.save()) {
                flash.message = "Responsibility ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "Responsibility ${params.id} has Errors on update Please try again"
				render(action:show,id:responsibilityInstance.id)
            }
        }
        else {
            flash.message = "Responsibility not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def responsibilityInstance = new Responsibility()
        responsibilityInstance.properties = params
        render(view:'create',model:['responsibilityInstance':responsibilityInstance])
    }

    def save = {
        def responsibilityInstance = new Responsibility(params)
        if(!responsibilityInstance.hasErrors() && responsibilityInstance.save()) {
            flash.message = "Responsibility ${responsibilityInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "Responsibility ${responsibilityInstance.id} error"
			render(action:show,id:responsibilityInstance.id)
        }
    }
}
