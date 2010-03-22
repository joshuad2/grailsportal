

package com.grailsPortal.domain

class PortalConfigAttributeController {
/**
 *  PortalConfigAttributeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ portalConfigAttributeInstanceList: PortalConfigAttribute.list( params ), portalConfigAttributeInstanceTotal: PortalConfigAttribute.count() ]
    }
	
    def show = {
        def portalConfigAttributeInstance = PortalConfigAttribute.get( params.id )

        if(!portalConfigAttributeInstance) {
            flash.message = "PortalConfigAttribute not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[portalConfigAttributeInstance:portalConfigAttributeInstance],view:'show') 
			}
    }
	
    def delete = {
        def portalConfigAttributeInstance = PortalConfigAttribute.get( params.id )
        if(portalConfigAttributeInstance) {
            try {
                portalConfigAttributeInstance.delete(flush:true)
                flash.message = "PortalConfigAttribute ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PortalConfigAttribute ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PortalConfigAttribute not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def portalConfigAttributeInstance = PortalConfigAttribute.get( params.id )

        if(!portalConfigAttributeInstance) {
            flash.message = "PortalConfigAttribute not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ portalConfigAttributeInstance : portalConfigAttributeInstance ])
        }
    }

    def update = {
        def portalConfigAttributeInstance = PortalConfigAttribute.get( params.id )
        if(portalConfigAttributeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(portalConfigAttributeInstance.version > version) {
                    
                    portalConfigAttributeInstance.errors.rejectValue("version", "portalConfigAttribute.optimistic.locking.failure", "Another user has updated this PortalConfigAttribute while you were editing.")
                    render(view:'edit',model:[portalConfigAttributeInstance:portalConfigAttributeInstance])
                    return
                }
            }
            portalConfigAttributeInstance.properties = params
            if(!portalConfigAttributeInstance.hasErrors() && portalConfigAttributeInstance.save()) {
                flash.message = "PortalConfigAttribute ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PortalConfigAttribute ${params.id} has Errors on update Please try again"
				render(action:show,id:portalConfigAttributeInstance.id)
            }
        }
        else {
            flash.message = "PortalConfigAttribute not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def portalConfigAttributeInstance = new PortalConfigAttribute()
        portalConfigAttributeInstance.properties = params
        render(view:'create',model:['portalConfigAttributeInstance':portalConfigAttributeInstance])
    }

    def save = {
        def portalConfigAttributeInstance = new PortalConfigAttribute(params)
        if(!portalConfigAttributeInstance.hasErrors() && portalConfigAttributeInstance.save()) {
            flash.message = "PortalConfigAttribute ${portalConfigAttributeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PortalConfigAttribute ${portalConfigAttributeInstance.id} error"
			render(action:show,id:portalConfigAttributeInstance.id)
        }
    }
}
