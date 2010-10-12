

package com.grailsPortal.domain.menu

class PortalSubMenuController {
/**
 *  PortalSubMenuController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ portalSubMenuInstanceList: PortalSubMenu.list( params ), portalSubMenuInstanceTotal: PortalSubMenu.count() ]
    }
	
    def show = {
        def portalSubMenuInstance = PortalSubMenu.get( params.id )

        if(!portalSubMenuInstance) {
            flash.message = "PortalSubMenu not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[portalSubMenuInstance:portalSubMenuInstance],view:'show') 
			}
    }
	
    def delete = {
        def portalSubMenuInstance = PortalSubMenu.get( params.id )
        if(portalSubMenuInstance) {
            try {
                portalSubMenuInstance.delete(flush:true)
                flash.message = "PortalSubMenu ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PortalSubMenu ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PortalSubMenu not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def portalSubMenuInstance = PortalSubMenu.get( params.id )

        if(!portalSubMenuInstance) {
            flash.message = "PortalSubMenu not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ portalSubMenuInstance : portalSubMenuInstance ])
        }
    }

    def update = {
        def portalSubMenuInstance = PortalSubMenu.get( params.id )
        if(portalSubMenuInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(portalSubMenuInstance.version > version) {
                    
                    portalSubMenuInstance.errors.rejectValue("version", "portalSubMenu.optimistic.locking.failure", "Another user has updated this PortalSubMenu while you were editing.")
                    render(view:'edit',model:[portalSubMenuInstance:portalSubMenuInstance])
                    return
                }
            }
            portalSubMenuInstance.properties = params
            if(!portalSubMenuInstance.hasErrors() && portalSubMenuInstance.save()) {
                flash.message = "PortalSubMenu ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PortalSubMenu ${params.id} has Errors on update Please try again"
				render(action:show,id:portalSubMenuInstance.id)
            }
        }
        else {
            flash.message = "PortalSubMenu not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def portalSubMenuInstance = new PortalSubMenu()
        portalSubMenuInstance.properties = params
        render(view:'create',model:['portalSubMenuInstance':portalSubMenuInstance])
    }

    def save = {
        def portalSubMenuInstance = new PortalSubMenu(params)
        if(!portalSubMenuInstance.hasErrors() && portalSubMenuInstance.save()) {
            flash.message = "PortalSubMenu ${portalSubMenuInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PortalSubMenu ${portalSubMenuInstance.id} error"
			render(action:show,id:portalSubMenuInstance.id)
        }
    }
}
