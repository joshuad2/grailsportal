

package com.grailsPortal.domain

class JsecUserAttributeValueController {
/**
 *  JsecUserAttributeValueController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ jsecUserAttributeValueInstanceList: JsecUserAttributeValue.list( params ), jsecUserAttributeValueInstanceTotal: JsecUserAttributeValue.count() ]
    }
	
    def show = {
        def jsecUserAttributeValueInstance = JsecUserAttributeValue.get( params.id )

        if(!jsecUserAttributeValueInstance) {
            flash.message = "JsecUserAttributeValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[jsecUserAttributeValueInstance:jsecUserAttributeValueInstance],view:'show') 
			}
    }
	
    def delete = {
        def jsecUserAttributeValueInstance = JsecUserAttributeValue.get( params.id )
        if(jsecUserAttributeValueInstance) {
            try {
                jsecUserAttributeValueInstance.delete(flush:true)
                flash.message = "JsecUserAttributeValue ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "JsecUserAttributeValue ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "JsecUserAttributeValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def jsecUserAttributeValueInstance = JsecUserAttributeValue.get( params.id )

        if(!jsecUserAttributeValueInstance) {
            flash.message = "JsecUserAttributeValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ jsecUserAttributeValueInstance : jsecUserAttributeValueInstance ])
        }
    }

    def update = {
        def jsecUserAttributeValueInstance = JsecUserAttributeValue.get( params.id )
        if(jsecUserAttributeValueInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(jsecUserAttributeValueInstance.version > version) {
                    
                    jsecUserAttributeValueInstance.errors.rejectValue("version", "jsecUserAttributeValue.optimistic.locking.failure", "Another user has updated this JsecUserAttributeValue while you were editing.")
                    render(view:'edit',model:[jsecUserAttributeValueInstance:jsecUserAttributeValueInstance])
                    return
                }
            }
            jsecUserAttributeValueInstance.properties = params
            if(!jsecUserAttributeValueInstance.hasErrors() && jsecUserAttributeValueInstance.save()) {
                flash.message = "JsecUserAttributeValue ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "JsecUserAttributeValue ${params.id} has Errors on update Please try again"
				render(action:show,id:jsecUserAttributeValueInstance.id)
            }
        }
        else {
            flash.message = "JsecUserAttributeValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def jsecUserAttributeValueInstance = new JsecUserAttributeValue()
        jsecUserAttributeValueInstance.properties = params
        render(view:'create',model:['jsecUserAttributeValueInstance':jsecUserAttributeValueInstance])
    }

    def save = {
        def jsecUserAttributeValueInstance = new JsecUserAttributeValue(params)
        if(!jsecUserAttributeValueInstance.hasErrors() && jsecUserAttributeValueInstance.save()) {
            flash.message = "JsecUserAttributeValue ${jsecUserAttributeValueInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "JsecUserAttributeValue ${jsecUserAttributeValueInstance.id} error"
			render(action:show,id:jsecUserAttributeValueInstance.id)
        }
    }
}
