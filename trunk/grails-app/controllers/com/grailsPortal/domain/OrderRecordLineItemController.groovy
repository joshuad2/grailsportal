

package com.grailsPortal.domain

class OrderRecordLineItemController {
/**
 *  OrderRecordLineItemController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ orderRecordLineItemInstanceList: OrderRecordLineItem.list( params ), orderRecordLineItemInstanceTotal: OrderRecordLineItem.count() ]
    }
	
    def show = {
        def orderRecordLineItemInstance = OrderRecordLineItem.get( params.id )

        if(!orderRecordLineItemInstance) {
            flash.message = "OrderRecordLineItem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[orderRecordLineItemInstance:orderRecordLineItemInstance],view:'show') 
			}
    }
	
    def delete = {
        def orderRecordLineItemInstance = OrderRecordLineItem.get( params.id )
        if(orderRecordLineItemInstance) {
            try {
                orderRecordLineItemInstance.delete(flush:true)
                flash.message = "OrderRecordLineItem ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "OrderRecordLineItem ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "OrderRecordLineItem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def orderRecordLineItemInstance = OrderRecordLineItem.get( params.id )

        if(!orderRecordLineItemInstance) {
            flash.message = "OrderRecordLineItem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ orderRecordLineItemInstance : orderRecordLineItemInstance ])
        }
    }

    def update = {
        def orderRecordLineItemInstance = OrderRecordLineItem.get( params.id )
        if(orderRecordLineItemInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(orderRecordLineItemInstance.version > version) {
                    
                    orderRecordLineItemInstance.errors.rejectValue("version", "orderRecordLineItem.optimistic.locking.failure", "Another user has updated this OrderRecordLineItem while you were editing.")
                    render(view:'edit',model:[orderRecordLineItemInstance:orderRecordLineItemInstance])
                    return
                }
            }
            orderRecordLineItemInstance.properties = params
            if(!orderRecordLineItemInstance.hasErrors() && orderRecordLineItemInstance.save()) {
                flash.message = "OrderRecordLineItem ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "OrderRecordLineItem ${params.id} has Errors on update Please try again"
				render(action:show,id:orderRecordLineItemInstance.id)
            }
        }
        else {
            flash.message = "OrderRecordLineItem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def orderRecordLineItemInstance = new OrderRecordLineItem()
        orderRecordLineItemInstance.properties = params
        render(view:'create',model:['orderRecordLineItemInstance':orderRecordLineItemInstance])
    }

    def save = {
        def orderRecordLineItemInstance = new OrderRecordLineItem(params)
        if(!orderRecordLineItemInstance.hasErrors() && orderRecordLineItemInstance.save()) {
            flash.message = "OrderRecordLineItem ${orderRecordLineItemInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "OrderRecordLineItem ${orderRecordLineItemInstance.id} error"
			render(action:show,id:orderRecordLineItemInstance.id)
        }
    }
}
