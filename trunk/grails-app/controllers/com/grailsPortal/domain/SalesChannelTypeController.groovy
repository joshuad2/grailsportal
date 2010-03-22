

package com.grailsPortal.domain

class SalesChannelTypeController {
/**
 *  SalesChannelTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ salesChannelTypeInstanceList: SalesChannelType.list( params ), salesChannelTypeInstanceTotal: SalesChannelType.count() ]
    }
	
    def show = {
        def salesChannelTypeInstance = SalesChannelType.get( params.id )

        if(!salesChannelTypeInstance) {
            flash.message = "SalesChannelType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[salesChannelTypeInstance:salesChannelTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def salesChannelTypeInstance = SalesChannelType.get( params.id )
        if(salesChannelTypeInstance) {
            try {
                salesChannelTypeInstance.delete(flush:true)
                flash.message = "SalesChannelType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "SalesChannelType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "SalesChannelType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def salesChannelTypeInstance = SalesChannelType.get( params.id )

        if(!salesChannelTypeInstance) {
            flash.message = "SalesChannelType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ salesChannelTypeInstance : salesChannelTypeInstance ])
        }
    }

    def update = {
        def salesChannelTypeInstance = SalesChannelType.get( params.id )
        if(salesChannelTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(salesChannelTypeInstance.version > version) {
                    
                    salesChannelTypeInstance.errors.rejectValue("version", "salesChannelType.optimistic.locking.failure", "Another user has updated this SalesChannelType while you were editing.")
                    render(view:'edit',model:[salesChannelTypeInstance:salesChannelTypeInstance])
                    return
                }
            }
            salesChannelTypeInstance.properties = params
            if(!salesChannelTypeInstance.hasErrors() && salesChannelTypeInstance.save()) {
                flash.message = "SalesChannelType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "SalesChannelType ${params.id} has Errors on update Please try again"
				render(action:show,id:salesChannelTypeInstance.id)
            }
        }
        else {
            flash.message = "SalesChannelType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def salesChannelTypeInstance = new SalesChannelType()
        salesChannelTypeInstance.properties = params
        render(view:'create',model:['salesChannelTypeInstance':salesChannelTypeInstance])
    }

    def save = {
        def salesChannelTypeInstance = new SalesChannelType(params)
        if(!salesChannelTypeInstance.hasErrors() && salesChannelTypeInstance.save()) {
            flash.message = "SalesChannelType ${salesChannelTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "SalesChannelType ${salesChannelTypeInstance.id} error"
			render(action:show,id:salesChannelTypeInstance.id)
        }
    }
}
