

package com.grailsPortal.domain

class JsecRolePermissionRelController {
/**
 *  JsecRolePermissionRelController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ jsecRolePermissionRelInstanceList: JsecRolePermissionRel.list( params ), jsecRolePermissionRelInstanceTotal: JsecRolePermissionRel.count() ]
    }
	
    def show = {
        def jsecRolePermissionRelInstance = JsecRolePermissionRel.get( params.id )

        if(!jsecRolePermissionRelInstance) {
            flash.message = "JsecRolePermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[jsecRolePermissionRelInstance:jsecRolePermissionRelInstance],view:'show') 
			}
    }
	
    def delete = {
        def jsecRolePermissionRelInstance = JsecRolePermissionRel.get( params.id )
        if(jsecRolePermissionRelInstance) {
            try {
                jsecRolePermissionRelInstance.delete(flush:true)
                flash.message = "JsecRolePermissionRel ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "JsecRolePermissionRel ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "JsecRolePermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def jsecRolePermissionRelInstance = JsecRolePermissionRel.get( params.id )

        if(!jsecRolePermissionRelInstance) {
            flash.message = "JsecRolePermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ jsecRolePermissionRelInstance : jsecRolePermissionRelInstance ])
        }
    }

    def update = {
        def jsecRolePermissionRelInstance = JsecRolePermissionRel.get( params.id )
        if(jsecRolePermissionRelInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(jsecRolePermissionRelInstance.version > version) {
                    
                    jsecRolePermissionRelInstance.errors.rejectValue("version", "jsecRolePermissionRel.optimistic.locking.failure", "Another user has updated this JsecRolePermissionRel while you were editing.")
                    render(view:'edit',model:[jsecRolePermissionRelInstance:jsecRolePermissionRelInstance])
                    return
                }
            }
            jsecRolePermissionRelInstance.properties = params
            if(!jsecRolePermissionRelInstance.hasErrors() && jsecRolePermissionRelInstance.save()) {
                flash.message = "JsecRolePermissionRel ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "JsecRolePermissionRel ${params.id} has Errors on update Please try again"
				render(action:show,id:jsecRolePermissionRelInstance.id)
            }
        }
        else {
            flash.message = "JsecRolePermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def jsecRolePermissionRelInstance = new JsecRolePermissionRel()
        jsecRolePermissionRelInstance.properties = params
        render(view:'create',model:['jsecRolePermissionRelInstance':jsecRolePermissionRelInstance])
    }

    def save = {
        def jsecRolePermissionRelInstance = new JsecRolePermissionRel(params)
        if(!jsecRolePermissionRelInstance.hasErrors() && jsecRolePermissionRelInstance.save()) {
            flash.message = "JsecRolePermissionRel ${jsecRolePermissionRelInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "JsecRolePermissionRel ${jsecRolePermissionRelInstance.id} error"
			render(action:show,id:jsecRolePermissionRelInstance.id)
        }
    }
}
