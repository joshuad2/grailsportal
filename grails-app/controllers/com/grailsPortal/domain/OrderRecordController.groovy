

package com.grailsPortal.domain

class OrderRecordController {
/**
 *  OrderRecordController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ orderRecordInstanceList: OrderRecord.list( params ), orderRecordInstanceTotal: OrderRecord.count() ]
    }
	
    def show = {
        def orderRecordInstance = OrderRecord.get( params.id )

        if(!orderRecordInstance) {
            flash.message = "OrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[orderRecordInstance:orderRecordInstance],view:'show') 
			}
    }
	
    def delete = {
        def orderRecordInstance = OrderRecord.get( params.id )
        if(orderRecordInstance) {
            try {
                orderRecordInstance.delete(flush:true)
                flash.message = "OrderRecord ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "OrderRecord ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "OrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def orderRecordInstance = OrderRecord.get( params.id )

        if(!orderRecordInstance) {
            flash.message = "OrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ orderRecordInstance : orderRecordInstance ])
        }
    }

    def update = {
        def orderRecordInstance = OrderRecord.get( params.id )
        if(orderRecordInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(orderRecordInstance.version > version) {
                    
                    orderRecordInstance.errors.rejectValue("version", "orderRecord.optimistic.locking.failure", "Another user has updated this OrderRecord while you were editing.")
                    render(view:'edit',model:[orderRecordInstance:orderRecordInstance])
                    return
                }
            }
            orderRecordInstance.properties = params
            if(!orderRecordInstance.hasErrors() && orderRecordInstance.save()) {
                flash.message = "OrderRecord ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "OrderRecord ${params.id} has Errors on update Please try again"
				render(action:show,id:orderRecordInstance.id)
            }
        }
        else {
            flash.message = "OrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def orderRecordInstance = new OrderRecord()
        orderRecordInstance.properties = params
        render(view:'create',model:['orderRecordInstance':orderRecordInstance])
    }

    def save = {
        def orderRecordInstance = new OrderRecord(params)
        if(!orderRecordInstance.hasErrors() && orderRecordInstance.save()) {
            flash.message = "OrderRecord ${orderRecordInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "OrderRecord ${orderRecordInstance.id} error"
			render(action:show,id:orderRecordInstance.id)
        }
    }
}
