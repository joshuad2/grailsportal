

package com.grailsPortal.domain.menu

class PortalMenuConfigurationController {
/**
 *  PortalMenuConfigurationController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ portalMenuConfigurationInstanceList: PortalMenuConfiguration.list( params ), portalMenuConfigurationInstanceTotal: PortalMenuConfiguration.count() ]
    }
	
    def show = {
        def portalMenuConfigurationInstance = PortalMenuConfiguration.get( params.id )

        if(!portalMenuConfigurationInstance) {
            flash.message = "PortalMenuConfiguration not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[portalMenuConfigurationInstance:portalMenuConfigurationInstance],view:'show') 
			}
    }
	
    def delete = {
        def portalMenuConfigurationInstance = PortalMenuConfiguration.get( params.id )
        if(portalMenuConfigurationInstance) {
            try {
                portalMenuConfigurationInstance.delete(flush:true)
                flash.message = "PortalMenuConfiguration ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PortalMenuConfiguration ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PortalMenuConfiguration not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def portalMenuConfigurationInstance = PortalMenuConfiguration.get( params.id )

        if(!portalMenuConfigurationInstance) {
            flash.message = "PortalMenuConfiguration not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ portalMenuConfigurationInstance : portalMenuConfigurationInstance ])
        }
    }

    def update = {
        def portalMenuConfigurationInstance = PortalMenuConfiguration.get( params.id )
        if(portalMenuConfigurationInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(portalMenuConfigurationInstance.version > version) {
                    
                    portalMenuConfigurationInstance.errors.rejectValue("version", "portalMenuConfiguration.optimistic.locking.failure", "Another user has updated this PortalMenuConfiguration while you were editing.")
                    render(view:'edit',model:[portalMenuConfigurationInstance:portalMenuConfigurationInstance])
                    return
                }
            }
            portalMenuConfigurationInstance.properties = params
            if(!portalMenuConfigurationInstance.hasErrors() && portalMenuConfigurationInstance.save()) {
                flash.message = "PortalMenuConfiguration ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PortalMenuConfiguration ${params.id} has Errors on update Please try again"
				render(action:show,id:portalMenuConfigurationInstance.id)
            }
        }
        else {
            flash.message = "PortalMenuConfiguration not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def portalMenuConfigurationInstance = new PortalMenuConfiguration()
        portalMenuConfigurationInstance.properties = params
        render(view:'create',model:['portalMenuConfigurationInstance':portalMenuConfigurationInstance])
    }

    def save = {
        def portalMenuConfigurationInstance = new PortalMenuConfiguration(params)
        if(!portalMenuConfigurationInstance.hasErrors() && portalMenuConfigurationInstance.save()) {
            flash.message = "PortalMenuConfiguration ${portalMenuConfigurationInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PortalMenuConfiguration ${portalMenuConfigurationInstance.id} error"
			render(action:show,id:portalMenuConfigurationInstance.id)
        }
    }
}
