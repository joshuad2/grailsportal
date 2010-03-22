

package com.grailsPortal.domain

class JsecPermissionController {
/**
 *  JsecPermissionController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ jsecPermissionInstanceList: JsecPermission.list( params ), jsecPermissionInstanceTotal: JsecPermission.count() ]
    }
	
    def show = {
        def jsecPermissionInstance = JsecPermission.get( params.id )

        if(!jsecPermissionInstance) {
            flash.message = "JsecPermission not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[jsecPermissionInstance:jsecPermissionInstance],view:'show') 
			}
    }
	
    def delete = {
        def jsecPermissionInstance = JsecPermission.get( params.id )
        if(jsecPermissionInstance) {
            try {
                jsecPermissionInstance.delete(flush:true)
                flash.message = "JsecPermission ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "JsecPermission ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "JsecPermission not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def jsecPermissionInstance = JsecPermission.get( params.id )

        if(!jsecPermissionInstance) {
            flash.message = "JsecPermission not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ jsecPermissionInstance : jsecPermissionInstance ])
        }
    }

    def update = {
        def jsecPermissionInstance = JsecPermission.get( params.id )
        if(jsecPermissionInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(jsecPermissionInstance.version > version) {
                    
                    jsecPermissionInstance.errors.rejectValue("version", "jsecPermission.optimistic.locking.failure", "Another user has updated this JsecPermission while you were editing.")
                    render(view:'edit',model:[jsecPermissionInstance:jsecPermissionInstance])
                    return
                }
            }
            jsecPermissionInstance.properties = params
            if(!jsecPermissionInstance.hasErrors() && jsecPermissionInstance.save()) {
                flash.message = "JsecPermission ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "JsecPermission ${params.id} has Errors on update Please try again"
				render(action:show,id:jsecPermissionInstance.id)
            }
        }
        else {
            flash.message = "JsecPermission not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def jsecPermissionInstance = new JsecPermission()
        jsecPermissionInstance.properties = params
        render(view:'create',model:['jsecPermissionInstance':jsecPermissionInstance])
    }

    def save = {
        def jsecPermissionInstance = new JsecPermission(params)
        if(!jsecPermissionInstance.hasErrors() && jsecPermissionInstance.save()) {
            flash.message = "JsecPermission ${jsecPermissionInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "JsecPermission ${jsecPermissionInstance.id} error"
			render(action:show,id:jsecPermissionInstance.id)
        }
    }
}
