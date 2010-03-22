

package com.grailsPortal.domain

class OrderRecordTypeController {
/**
 *  OrderRecordTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ orderRecordTypeInstanceList: OrderRecordType.list( params ), orderRecordTypeInstanceTotal: OrderRecordType.count() ]
    }
	
    def show = {
        def orderRecordTypeInstance = OrderRecordType.get( params.id )

        if(!orderRecordTypeInstance) {
            flash.message = "OrderRecordType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[orderRecordTypeInstance:orderRecordTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def orderRecordTypeInstance = OrderRecordType.get( params.id )
        if(orderRecordTypeInstance) {
            try {
                orderRecordTypeInstance.delete(flush:true)
                flash.message = "OrderRecordType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "OrderRecordType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "OrderRecordType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def orderRecordTypeInstance = OrderRecordType.get( params.id )

        if(!orderRecordTypeInstance) {
            flash.message = "OrderRecordType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ orderRecordTypeInstance : orderRecordTypeInstance ])
        }
    }

    def update = {
        def orderRecordTypeInstance = OrderRecordType.get( params.id )
        if(orderRecordTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(orderRecordTypeInstance.version > version) {
                    
                    orderRecordTypeInstance.errors.rejectValue("version", "orderRecordType.optimistic.locking.failure", "Another user has updated this OrderRecordType while you were editing.")
                    render(view:'edit',model:[orderRecordTypeInstance:orderRecordTypeInstance])
                    return
                }
            }
            orderRecordTypeInstance.properties = params
            if(!orderRecordTypeInstance.hasErrors() && orderRecordTypeInstance.save()) {
                flash.message = "OrderRecordType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "OrderRecordType ${params.id} has Errors on update Please try again"
				render(action:show,id:orderRecordTypeInstance.id)
            }
        }
        else {
            flash.message = "OrderRecordType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def orderRecordTypeInstance = new OrderRecordType()
        orderRecordTypeInstance.properties = params
        render(view:'create',model:['orderRecordTypeInstance':orderRecordTypeInstance])
    }

    def save = {
        def orderRecordTypeInstance = new OrderRecordType(params)
        if(!orderRecordTypeInstance.hasErrors() && orderRecordTypeInstance.save()) {
            flash.message = "OrderRecordType ${orderRecordTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "OrderRecordType ${orderRecordTypeInstance.id} error"
			render(action:show,id:orderRecordTypeInstance.id)
        }
    }
}
