

package com.grailsPortal.domain

class RegistrationEventAttrValueController {
/**
 *  RegistrationEventAttrValueController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ registrationEventAttrValueInstanceList: RegistrationEventAttrValue.list( params ), registrationEventAttrValueInstanceTotal: RegistrationEventAttrValue.count() ]
    }
	
    def show = {
        def registrationEventAttrValueInstance = RegistrationEventAttrValue.get( params.id )

        if(!registrationEventAttrValueInstance) {
            flash.message = "RegistrationEventAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[registrationEventAttrValueInstance:registrationEventAttrValueInstance],view:'show') 
			}
    }
	
    def delete = {
        def registrationEventAttrValueInstance = RegistrationEventAttrValue.get( params.id )
        if(registrationEventAttrValueInstance) {
            try {
                registrationEventAttrValueInstance.delete(flush:true)
                flash.message = "RegistrationEventAttrValue ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "RegistrationEventAttrValue ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "RegistrationEventAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def registrationEventAttrValueInstance = RegistrationEventAttrValue.get( params.id )

        if(!registrationEventAttrValueInstance) {
            flash.message = "RegistrationEventAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ registrationEventAttrValueInstance : registrationEventAttrValueInstance ])
        }
    }

    def update = {
        def registrationEventAttrValueInstance = RegistrationEventAttrValue.get( params.id )
        if(registrationEventAttrValueInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(registrationEventAttrValueInstance.version > version) {
                    
                    registrationEventAttrValueInstance.errors.rejectValue("version", "registrationEventAttrValue.optimistic.locking.failure", "Another user has updated this RegistrationEventAttrValue while you were editing.")
                    render(view:'edit',model:[registrationEventAttrValueInstance:registrationEventAttrValueInstance])
                    return
                }
            }
            registrationEventAttrValueInstance.properties = params
            if(!registrationEventAttrValueInstance.hasErrors() && registrationEventAttrValueInstance.save()) {
                flash.message = "RegistrationEventAttrValue ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "RegistrationEventAttrValue ${params.id} has Errors on update Please try again"
				render(action:show,id:registrationEventAttrValueInstance.id)
            }
        }
        else {
            flash.message = "RegistrationEventAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def registrationEventAttrValueInstance = new RegistrationEventAttrValue()
        registrationEventAttrValueInstance.properties = params
        render(view:'create',model:['registrationEventAttrValueInstance':registrationEventAttrValueInstance])
    }

    def save = {
        def registrationEventAttrValueInstance = new RegistrationEventAttrValue(params)
        if(!registrationEventAttrValueInstance.hasErrors() && registrationEventAttrValueInstance.save()) {
            flash.message = "RegistrationEventAttrValue ${registrationEventAttrValueInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "RegistrationEventAttrValue ${registrationEventAttrValueInstance.id} error"
			render(action:show,id:registrationEventAttrValueInstance.id)
        }
    }
}
