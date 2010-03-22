

package com.grailsPortal.domain

class PortalConfigAttrValueController {
/**
 *  PortalConfigAttrValueController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ portalConfigAttrValueInstanceList: PortalConfigAttrValue.list( params ), portalConfigAttrValueInstanceTotal: PortalConfigAttrValue.count() ]
    }
	
    def show = {
        def portalConfigAttrValueInstance = PortalConfigAttrValue.get( params.id )

        if(!portalConfigAttrValueInstance) {
            flash.message = "PortalConfigAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[portalConfigAttrValueInstance:portalConfigAttrValueInstance],view:'show') 
			}
    }
	
    def delete = {
        def portalConfigAttrValueInstance = PortalConfigAttrValue.get( params.id )
        if(portalConfigAttrValueInstance) {
            try {
                portalConfigAttrValueInstance.delete(flush:true)
                flash.message = "PortalConfigAttrValue ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PortalConfigAttrValue ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PortalConfigAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def portalConfigAttrValueInstance = PortalConfigAttrValue.get( params.id )

        if(!portalConfigAttrValueInstance) {
            flash.message = "PortalConfigAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ portalConfigAttrValueInstance : portalConfigAttrValueInstance ])
        }
    }

    def update = {
        def portalConfigAttrValueInstance = PortalConfigAttrValue.get( params.id )
        if(portalConfigAttrValueInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(portalConfigAttrValueInstance.version > version) {
                    
                    portalConfigAttrValueInstance.errors.rejectValue("version", "portalConfigAttrValue.optimistic.locking.failure", "Another user has updated this PortalConfigAttrValue while you were editing.")
                    render(view:'edit',model:[portalConfigAttrValueInstance:portalConfigAttrValueInstance])
                    return
                }
            }
            portalConfigAttrValueInstance.properties = params
            if(!portalConfigAttrValueInstance.hasErrors() && portalConfigAttrValueInstance.save()) {
                flash.message = "PortalConfigAttrValue ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PortalConfigAttrValue ${params.id} has Errors on update Please try again"
				render(action:show,id:portalConfigAttrValueInstance.id)
            }
        }
        else {
            flash.message = "PortalConfigAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def portalConfigAttrValueInstance = new PortalConfigAttrValue()
        portalConfigAttrValueInstance.properties = params
        render(view:'create',model:['portalConfigAttrValueInstance':portalConfigAttrValueInstance])
    }

    def save = {
        def portalConfigAttrValueInstance = new PortalConfigAttrValue(params)
        if(!portalConfigAttrValueInstance.hasErrors() && portalConfigAttrValueInstance.save()) {
            flash.message = "PortalConfigAttrValue ${portalConfigAttrValueInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PortalConfigAttrValue ${portalConfigAttrValueInstance.id} error"
			render(action:show,id:portalConfigAttrValueInstance.id)
        }
    }
}
