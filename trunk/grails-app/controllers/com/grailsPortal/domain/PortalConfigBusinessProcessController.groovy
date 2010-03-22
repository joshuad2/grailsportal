

package com.grailsPortal.domain

class PortalConfigBusinessProcessController {
/**
 *  PortalConfigBusinessProcessController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ portalConfigBusinessProcessInstanceList: PortalConfigBusinessProcess.list( params ), portalConfigBusinessProcessInstanceTotal: PortalConfigBusinessProcess.count() ]
    }
	
    def show = {
        def portalConfigBusinessProcessInstance = PortalConfigBusinessProcess.get( params.id )

        if(!portalConfigBusinessProcessInstance) {
            flash.message = "PortalConfigBusinessProcess not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[portalConfigBusinessProcessInstance:portalConfigBusinessProcessInstance],view:'show') 
			}
    }
	
    def delete = {
        def portalConfigBusinessProcessInstance = PortalConfigBusinessProcess.get( params.id )
        if(portalConfigBusinessProcessInstance) {
            try {
                portalConfigBusinessProcessInstance.delete(flush:true)
                flash.message = "PortalConfigBusinessProcess ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PortalConfigBusinessProcess ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PortalConfigBusinessProcess not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def portalConfigBusinessProcessInstance = PortalConfigBusinessProcess.get( params.id )

        if(!portalConfigBusinessProcessInstance) {
            flash.message = "PortalConfigBusinessProcess not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ portalConfigBusinessProcessInstance : portalConfigBusinessProcessInstance ])
        }
    }

    def update = {
        def portalConfigBusinessProcessInstance = PortalConfigBusinessProcess.get( params.id )
        if(portalConfigBusinessProcessInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(portalConfigBusinessProcessInstance.version > version) {
                    
                    portalConfigBusinessProcessInstance.errors.rejectValue("version", "portalConfigBusinessProcess.optimistic.locking.failure", "Another user has updated this PortalConfigBusinessProcess while you were editing.")
                    render(view:'edit',model:[portalConfigBusinessProcessInstance:portalConfigBusinessProcessInstance])
                    return
                }
            }
            portalConfigBusinessProcessInstance.properties = params
            if(!portalConfigBusinessProcessInstance.hasErrors() && portalConfigBusinessProcessInstance.save()) {
                flash.message = "PortalConfigBusinessProcess ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PortalConfigBusinessProcess ${params.id} has Errors on update Please try again"
				render(action:show,id:portalConfigBusinessProcessInstance.id)
            }
        }
        else {
            flash.message = "PortalConfigBusinessProcess not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def portalConfigBusinessProcessInstance = new PortalConfigBusinessProcess()
        portalConfigBusinessProcessInstance.properties = params
        render(view:'create',model:['portalConfigBusinessProcessInstance':portalConfigBusinessProcessInstance])
    }

    def save = {
        def portalConfigBusinessProcessInstance = new PortalConfigBusinessProcess(params)
        if(!portalConfigBusinessProcessInstance.hasErrors() && portalConfigBusinessProcessInstance.save()) {
            flash.message = "PortalConfigBusinessProcess ${portalConfigBusinessProcessInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PortalConfigBusinessProcess ${portalConfigBusinessProcessInstance.id} error"
			render(action:show,id:portalConfigBusinessProcessInstance.id)
        }
    }
}
