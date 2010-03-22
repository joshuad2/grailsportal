

package com.grailsPortal.domain

class OrderStatusController {
/**
 *  OrderStatusController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ orderStatusInstanceList: OrderStatus.list( params ), orderStatusInstanceTotal: OrderStatus.count() ]
    }
	
    def show = {
        def orderStatusInstance = OrderStatus.get( params.id )

        if(!orderStatusInstance) {
            flash.message = "OrderStatus not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[orderStatusInstance:orderStatusInstance],view:'show') 
			}
    }
	
    def delete = {
        def orderStatusInstance = OrderStatus.get( params.id )
        if(orderStatusInstance) {
            try {
                orderStatusInstance.delete(flush:true)
                flash.message = "OrderStatus ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "OrderStatus ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "OrderStatus not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def orderStatusInstance = OrderStatus.get( params.id )

        if(!orderStatusInstance) {
            flash.message = "OrderStatus not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ orderStatusInstance : orderStatusInstance ])
        }
    }

    def update = {
        def orderStatusInstance = OrderStatus.get( params.id )
        if(orderStatusInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(orderStatusInstance.version > version) {
                    
                    orderStatusInstance.errors.rejectValue("version", "orderStatus.optimistic.locking.failure", "Another user has updated this OrderStatus while you were editing.")
                    render(view:'edit',model:[orderStatusInstance:orderStatusInstance])
                    return
                }
            }
            orderStatusInstance.properties = params
            if(!orderStatusInstance.hasErrors() && orderStatusInstance.save()) {
                flash.message = "OrderStatus ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "OrderStatus ${params.id} has Errors on update Please try again"
				render(action:show,id:orderStatusInstance.id)
            }
        }
        else {
            flash.message = "OrderStatus not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def orderStatusInstance = new OrderStatus()
        orderStatusInstance.properties = params
        render(view:'create',model:['orderStatusInstance':orderStatusInstance])
    }

    def save = {
        def orderStatusInstance = new OrderStatus(params)
        if(!orderStatusInstance.hasErrors() && orderStatusInstance.save()) {
            flash.message = "OrderStatus ${orderStatusInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "OrderStatus ${orderStatusInstance.id} error"
			render(action:show,id:orderStatusInstance.id)
        }
    }
}
