

package com.grailsPortal.domain

class SalesChannelController {
/**
 *  SalesChannelController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ salesChannelInstanceList: SalesChannel.list( params ), salesChannelInstanceTotal: SalesChannel.count() ]
    }
	
    def show = {
        def salesChannelInstance = SalesChannel.get( params.id )

        if(!salesChannelInstance) {
            flash.message = "SalesChannel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[salesChannelInstance:salesChannelInstance],view:'show') 
			}
    }
	
    def delete = {
        def salesChannelInstance = SalesChannel.get( params.id )
        if(salesChannelInstance) {
            try {
                salesChannelInstance.delete(flush:true)
                flash.message = "SalesChannel ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "SalesChannel ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "SalesChannel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def salesChannelInstance = SalesChannel.get( params.id )

        if(!salesChannelInstance) {
            flash.message = "SalesChannel not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ salesChannelInstance : salesChannelInstance ])
        }
    }

    def update = {
        def salesChannelInstance = SalesChannel.get( params.id )
        if(salesChannelInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(salesChannelInstance.version > version) {
                    
                    salesChannelInstance.errors.rejectValue("version", "salesChannel.optimistic.locking.failure", "Another user has updated this SalesChannel while you were editing.")
                    render(view:'edit',model:[salesChannelInstance:salesChannelInstance])
                    return
                }
            }
            salesChannelInstance.properties = params
            if(!salesChannelInstance.hasErrors() && salesChannelInstance.save()) {
                flash.message = "SalesChannel ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "SalesChannel ${params.id} has Errors on update Please try again"
				render(action:show,id:salesChannelInstance.id)
            }
        }
        else {
            flash.message = "SalesChannel not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def salesChannelInstance = new SalesChannel()
        salesChannelInstance.properties = params
        render(view:'create',model:['salesChannelInstance':salesChannelInstance])
    }

    def save = {
        def salesChannelInstance = new SalesChannel(params)
        if(!salesChannelInstance.hasErrors() && salesChannelInstance.save()) {
            flash.message = "SalesChannel ${salesChannelInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "SalesChannel ${salesChannelInstance.id} error"
			render(action:show,id:salesChannelInstance.id)
        }
    }
}
