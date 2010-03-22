

package com.grailsPortal.domain

class BusinessProcessStepController {
/**
 *  BusinessProcessStepController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ businessProcessStepInstanceList: BusinessProcessStep.list( params ), businessProcessStepInstanceTotal: BusinessProcessStep.count() ]
    }
	
    def show = {
        def businessProcessStepInstance = BusinessProcessStep.get( params.id )

        if(!businessProcessStepInstance) {
            flash.message = "BusinessProcessStep not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[businessProcessStepInstance:businessProcessStepInstance],view:'show') 
			}
    }
	
    def delete = {
        def businessProcessStepInstance = BusinessProcessStep.get( params.id )
        if(businessProcessStepInstance) {
            try {
                businessProcessStepInstance.delete(flush:true)
                flash.message = "BusinessProcessStep ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "BusinessProcessStep ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "BusinessProcessStep not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def businessProcessStepInstance = BusinessProcessStep.get( params.id )

        if(!businessProcessStepInstance) {
            flash.message = "BusinessProcessStep not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ businessProcessStepInstance : businessProcessStepInstance ])
        }
    }

    def update = {
        def businessProcessStepInstance = BusinessProcessStep.get( params.id )
        if(businessProcessStepInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(businessProcessStepInstance.version > version) {
                    
                    businessProcessStepInstance.errors.rejectValue("version", "businessProcessStep.optimistic.locking.failure", "Another user has updated this BusinessProcessStep while you were editing.")
                    render(view:'edit',model:[businessProcessStepInstance:businessProcessStepInstance])
                    return
                }
            }
            businessProcessStepInstance.properties = params
            if(!businessProcessStepInstance.hasErrors() && businessProcessStepInstance.save()) {
                flash.message = "BusinessProcessStep ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "BusinessProcessStep ${params.id} has Errors on update Please try again"
				render(action:show,id:businessProcessStepInstance.id)
            }
        }
        else {
            flash.message = "BusinessProcessStep not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def businessProcessStepInstance = new BusinessProcessStep()
        businessProcessStepInstance.properties = params
        render(view:'create',model:['businessProcessStepInstance':businessProcessStepInstance])
    }

    def save = {
        def businessProcessStepInstance = new BusinessProcessStep(params)
        if(!businessProcessStepInstance.hasErrors() && businessProcessStepInstance.save()) {
            flash.message = "BusinessProcessStep ${businessProcessStepInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "BusinessProcessStep ${businessProcessStepInstance.id} error"
			render(action:show,id:businessProcessStepInstance.id)
        }
    }
}
