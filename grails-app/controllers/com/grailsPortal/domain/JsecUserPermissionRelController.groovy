

package com.grailsPortal.domain

class JsecUserPermissionRelController {
/**
 *  JsecUserPermissionRelController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ jsecUserPermissionRelInstanceList: JsecUserPermissionRel.list( params ), jsecUserPermissionRelInstanceTotal: JsecUserPermissionRel.count() ]
    }
	
    def show = {
        def jsecUserPermissionRelInstance = JsecUserPermissionRel.get( params.id )

        if(!jsecUserPermissionRelInstance) {
            flash.message = "JsecUserPermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[jsecUserPermissionRelInstance:jsecUserPermissionRelInstance],view:'show') 
			}
    }
	
    def delete = {
        def jsecUserPermissionRelInstance = JsecUserPermissionRel.get( params.id )
        if(jsecUserPermissionRelInstance) {
            try {
                jsecUserPermissionRelInstance.delete(flush:true)
                flash.message = "JsecUserPermissionRel ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "JsecUserPermissionRel ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "JsecUserPermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def jsecUserPermissionRelInstance = JsecUserPermissionRel.get( params.id )

        if(!jsecUserPermissionRelInstance) {
            flash.message = "JsecUserPermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ jsecUserPermissionRelInstance : jsecUserPermissionRelInstance ])
        }
    }

    def update = {
        def jsecUserPermissionRelInstance = JsecUserPermissionRel.get( params.id )
        if(jsecUserPermissionRelInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(jsecUserPermissionRelInstance.version > version) {
                    
                    jsecUserPermissionRelInstance.errors.rejectValue("version", "jsecUserPermissionRel.optimistic.locking.failure", "Another user has updated this JsecUserPermissionRel while you were editing.")
                    render(view:'edit',model:[jsecUserPermissionRelInstance:jsecUserPermissionRelInstance])
                    return
                }
            }
            jsecUserPermissionRelInstance.properties = params
            if(!jsecUserPermissionRelInstance.hasErrors() && jsecUserPermissionRelInstance.save()) {
                flash.message = "JsecUserPermissionRel ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "JsecUserPermissionRel ${params.id} has Errors on update Please try again"
				render(action:show,id:jsecUserPermissionRelInstance.id)
            }
        }
        else {
            flash.message = "JsecUserPermissionRel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def jsecUserPermissionRelInstance = new JsecUserPermissionRel()
        jsecUserPermissionRelInstance.properties = params
        render(view:'create',model:['jsecUserPermissionRelInstance':jsecUserPermissionRelInstance])
    }

    def save = {
        def jsecUserPermissionRelInstance = new JsecUserPermissionRel(params)
        if(!jsecUserPermissionRelInstance.hasErrors() && jsecUserPermissionRelInstance.save()) {
            flash.message = "JsecUserPermissionRel ${jsecUserPermissionRelInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "JsecUserPermissionRel ${jsecUserPermissionRelInstance.id} error"
			render(action:show,id:jsecUserPermissionRelInstance.id)
        }
    }
}
