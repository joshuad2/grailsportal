

package com.grailsPortal.domain

class JsecUserRoleRelController {
/**
 *  JsecUserRoleRelController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ jsecUserRoleRelInstanceList: JsecUserRoleRel.list( params ), jsecUserRoleRelInstanceTotal: JsecUserRoleRel.count() ]
    }
	
    def show = {
        def jsecUserRoleRelInstance = JsecUserRoleRel.get( params.id )

        if(!jsecUserRoleRelInstance) {
            flash.message = "JsecUserRoleRel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[jsecUserRoleRelInstance:jsecUserRoleRelInstance],view:'show') 
			}
    }
	
    def delete = {
        def jsecUserRoleRelInstance = JsecUserRoleRel.get( params.id )
        if(jsecUserRoleRelInstance) {
            try {
                jsecUserRoleRelInstance.delete(flush:true)
                flash.message = "JsecUserRoleRel ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "JsecUserRoleRel ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "JsecUserRoleRel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def jsecUserRoleRelInstance = JsecUserRoleRel.get( params.id )

        if(!jsecUserRoleRelInstance) {
            flash.message = "JsecUserRoleRel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ jsecUserRoleRelInstance : jsecUserRoleRelInstance ])
        }
    }

    def update = {
        def jsecUserRoleRelInstance = JsecUserRoleRel.get( params.id )
        if(jsecUserRoleRelInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(jsecUserRoleRelInstance.version > version) {
                    
                    jsecUserRoleRelInstance.errors.rejectValue("version", "jsecUserRoleRel.optimistic.locking.failure", "Another user has updated this JsecUserRoleRel while you were editing.")
                    render(view:'edit',model:[jsecUserRoleRelInstance:jsecUserRoleRelInstance])
                    return
                }
            }
            jsecUserRoleRelInstance.properties = params
            if(!jsecUserRoleRelInstance.hasErrors() && jsecUserRoleRelInstance.save()) {
                flash.message = "JsecUserRoleRel ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "JsecUserRoleRel ${params.id} has Errors on update Please try again"
				render(action:show,id:jsecUserRoleRelInstance.id)
            }
        }
        else {
            flash.message = "JsecUserRoleRel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def jsecUserRoleRelInstance = new JsecUserRoleRel()
        jsecUserRoleRelInstance.properties = params
        render(view:'create',model:['jsecUserRoleRelInstance':jsecUserRoleRelInstance])
    }

    def save = {
        def jsecUserRoleRelInstance = new JsecUserRoleRel(params)
        if(!jsecUserRoleRelInstance.hasErrors() && jsecUserRoleRelInstance.save()) {
            flash.message = "JsecUserRoleRel ${jsecUserRoleRelInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "JsecUserRoleRel ${jsecUserRoleRelInstance.id} error"
			render(action:show,id:jsecUserRoleRelInstance.id)
        }
    }
}
