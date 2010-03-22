

package com.grailsPortal.domain

class PortalConfigController {
/**
 *  PortalConfigController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ portalConfigInstanceList: PortalConfig.list( params ), portalConfigInstanceTotal: PortalConfig.count() ]
    }
	
    def show = {
        def portalConfigInstance = PortalConfig.get( params.id )

        if(!portalConfigInstance) {
            flash.message = "PortalConfig not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[portalConfigInstance:portalConfigInstance],view:'show') 
			}
    }
	
    def delete = {
        def portalConfigInstance = PortalConfig.get( params.id )
        if(portalConfigInstance) {
            try {
                portalConfigInstance.delete(flush:true)
                flash.message = "PortalConfig ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PortalConfig ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PortalConfig not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def portalConfigInstance = PortalConfig.get( params.id )

        if(!portalConfigInstance) {
            flash.message = "PortalConfig not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ portalConfigInstance : portalConfigInstance ])
        }
    }

    def update = {
        def portalConfigInstance = PortalConfig.get( params.id )
        if(portalConfigInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(portalConfigInstance.version > version) {
                    
                    portalConfigInstance.errors.rejectValue("version", "portalConfig.optimistic.locking.failure", "Another user has updated this PortalConfig while you were editing.")
                    render(view:'edit',model:[portalConfigInstance:portalConfigInstance])
                    return
                }
            }
            portalConfigInstance.properties = params
            if(!portalConfigInstance.hasErrors() && portalConfigInstance.save()) {
                flash.message = "PortalConfig ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PortalConfig ${params.id} has Errors on update Please try again"
				render(action:show,id:portalConfigInstance.id)
            }
        }
        else {
            flash.message = "PortalConfig not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def portalConfigInstance = new PortalConfig()
        portalConfigInstance.properties = params
        render(view:'create',model:['portalConfigInstance':portalConfigInstance])
    }

    def save = {
        def portalConfigInstance = new PortalConfig(params)
        if(!portalConfigInstance.hasErrors() && portalConfigInstance.save()) {
            flash.message = "PortalConfig ${portalConfigInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PortalConfig ${portalConfigInstance.id} error"
			render(action:show,id:portalConfigInstance.id)
        }
    }
}
