

package com.grailsPortal.domain

class PaymentMethodTypeController {
/**
 *  PaymentMethodTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ paymentMethodTypeInstanceList: PaymentMethodType.list( params ), paymentMethodTypeInstanceTotal: PaymentMethodType.count() ]
    }
	
    def show = {
        def paymentMethodTypeInstance = PaymentMethodType.get( params.id )

        if(!paymentMethodTypeInstance) {
            flash.message = "PaymentMethodType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[paymentMethodTypeInstance:paymentMethodTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def paymentMethodTypeInstance = PaymentMethodType.get( params.id )
        if(paymentMethodTypeInstance) {
            try {
                paymentMethodTypeInstance.delete(flush:true)
                flash.message = "PaymentMethodType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PaymentMethodType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PaymentMethodType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def paymentMethodTypeInstance = PaymentMethodType.get( params.id )

        if(!paymentMethodTypeInstance) {
            flash.message = "PaymentMethodType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ paymentMethodTypeInstance : paymentMethodTypeInstance ])
        }
    }

    def update = {
        def paymentMethodTypeInstance = PaymentMethodType.get( params.id )
        if(paymentMethodTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(paymentMethodTypeInstance.version > version) {
                    
                    paymentMethodTypeInstance.errors.rejectValue("version", "paymentMethodType.optimistic.locking.failure", "Another user has updated this PaymentMethodType while you were editing.")
                    render(view:'edit',model:[paymentMethodTypeInstance:paymentMethodTypeInstance])
                    return
                }
            }
            paymentMethodTypeInstance.properties = params
            if(!paymentMethodTypeInstance.hasErrors() && paymentMethodTypeInstance.save()) {
                flash.message = "PaymentMethodType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PaymentMethodType ${params.id} has Errors on update Please try again"
				render(action:show,id:paymentMethodTypeInstance.id)
            }
        }
        else {
            flash.message = "PaymentMethodType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def paymentMethodTypeInstance = new PaymentMethodType()
        paymentMethodTypeInstance.properties = params
        render(view:'create',model:['paymentMethodTypeInstance':paymentMethodTypeInstance])
    }

    def save = {
        def paymentMethodTypeInstance = new PaymentMethodType(params)
        if(!paymentMethodTypeInstance.hasErrors() && paymentMethodTypeInstance.save()) {
            flash.message = "PaymentMethodType ${paymentMethodTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PaymentMethodType ${paymentMethodTypeInstance.id} error"
			render(action:show,id:paymentMethodTypeInstance.id)
        }
    }
}
