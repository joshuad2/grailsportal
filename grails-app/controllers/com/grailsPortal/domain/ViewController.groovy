

package com.grailsPortal.domain

class ViewController {
/**
 *  ViewController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ viewInstanceList: View.list( params ), viewInstanceTotal: View.count() ]
    }
	
    def show = {
        def viewInstance = View.get( params.id )

        if(!viewInstance) {
            flash.message = "View not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[viewInstance:viewInstance],view:'show') 
			}
    }
	
    def delete = {
        def viewInstance = View.get( params.id )
        if(viewInstance) {
            try {
                viewInstance.delete(flush:true)
                flash.message = "View ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "View ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "View not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def viewInstance = View.get( params.id )

        if(!viewInstance) {
            flash.message = "View not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ viewInstance : viewInstance ])
        }
    }

    def update = {
        def viewInstance = View.get( params.id )
        if(viewInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(viewInstance.version > version) {
                    
                    viewInstance.errors.rejectValue("version", "view.optimistic.locking.failure", "Another user has updated this View while you were editing.")
                    render(view:'edit',model:[viewInstance:viewInstance])
                    return
                }
            }
            viewInstance.properties = params
            if(!viewInstance.hasErrors() && viewInstance.save()) {
                flash.message = "View ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "View ${params.id} has Errors on update Please try again"
				render(action:show,id:viewInstance.id)
            }
        }
        else {
            flash.message = "View not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def viewInstance = new View()
        viewInstance.properties = params
        render(view:'create',model:['viewInstance':viewInstance])
    }

    def save = {
        def viewInstance = new View(params)
        if(!viewInstance.hasErrors() && viewInstance.save()) {
            flash.message = "View ${viewInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "View ${viewInstance.id} error"
			render(action:show,id:viewInstance.id)
        }
    }
}
