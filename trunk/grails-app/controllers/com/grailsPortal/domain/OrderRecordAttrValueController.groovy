

package com.grailsPortal.domain

class OrderRecordAttrValueController {
/**
 *  OrderRecordAttrValueController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ orderRecordAttrValueInstanceList: OrderRecordAttrValue.list( params ), orderRecordAttrValueInstanceTotal: OrderRecordAttrValue.count() ]
    }
	
    def show = {
        def orderRecordAttrValueInstance = OrderRecordAttrValue.get( params.id )

        if(!orderRecordAttrValueInstance) {
            flash.message = "OrderRecordAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[orderRecordAttrValueInstance:orderRecordAttrValueInstance],view:'show') 
			}
    }
	
    def delete = {
        def orderRecordAttrValueInstance = OrderRecordAttrValue.get( params.id )
        if(orderRecordAttrValueInstance) {
            try {
                orderRecordAttrValueInstance.delete(flush:true)
                flash.message = "OrderRecordAttrValue ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "OrderRecordAttrValue ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "OrderRecordAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def orderRecordAttrValueInstance = OrderRecordAttrValue.get( params.id )

        if(!orderRecordAttrValueInstance) {
            flash.message = "OrderRecordAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ orderRecordAttrValueInstance : orderRecordAttrValueInstance ])
        }
    }

    def update = {
        def orderRecordAttrValueInstance = OrderRecordAttrValue.get( params.id )
        if(orderRecordAttrValueInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(orderRecordAttrValueInstance.version > version) {
                    
                    orderRecordAttrValueInstance.errors.rejectValue("version", "orderRecordAttrValue.optimistic.locking.failure", "Another user has updated this OrderRecordAttrValue while you were editing.")
                    render(view:'edit',model:[orderRecordAttrValueInstance:orderRecordAttrValueInstance])
                    return
                }
            }
            orderRecordAttrValueInstance.properties = params
            if(!orderRecordAttrValueInstance.hasErrors() && orderRecordAttrValueInstance.save()) {
                flash.message = "OrderRecordAttrValue ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "OrderRecordAttrValue ${params.id} has Errors on update Please try again"
				render(action:show,id:orderRecordAttrValueInstance.id)
            }
        }
        else {
            flash.message = "OrderRecordAttrValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def orderRecordAttrValueInstance = new OrderRecordAttrValue()
        orderRecordAttrValueInstance.properties = params
        render(view:'create',model:['orderRecordAttrValueInstance':orderRecordAttrValueInstance])
    }

    def save = {
        def orderRecordAttrValueInstance = new OrderRecordAttrValue(params)
        if(!orderRecordAttrValueInstance.hasErrors() && orderRecordAttrValueInstance.save()) {
            flash.message = "OrderRecordAttrValue ${orderRecordAttrValueInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "OrderRecordAttrValue ${orderRecordAttrValueInstance.id} error"
			render(action:show,id:orderRecordAttrValueInstance.id)
        }
    }
}
