

package com.grailsPortal.domain

class PaymentTypeController {
/**
 *  PaymentTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ paymentTypeInstanceList: PaymentType.list( params ), paymentTypeInstanceTotal: PaymentType.count() ]
    }
	
    def show = {
        def paymentTypeInstance = PaymentType.get( params.id )

        if(!paymentTypeInstance) {
            flash.message = "PaymentType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[paymentTypeInstance:paymentTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def paymentTypeInstance = PaymentType.get( params.id )
        if(paymentTypeInstance) {
            try {
                paymentTypeInstance.delete(flush:true)
                flash.message = "PaymentType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "PaymentType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "PaymentType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def paymentTypeInstance = PaymentType.get( params.id )

        if(!paymentTypeInstance) {
            flash.message = "PaymentType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ paymentTypeInstance : paymentTypeInstance ])
        }
    }

    def update = {
        def paymentTypeInstance = PaymentType.get( params.id )
        if(paymentTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(paymentTypeInstance.version > version) {
                    
                    paymentTypeInstance.errors.rejectValue("version", "paymentType.optimistic.locking.failure", "Another user has updated this PaymentType while you were editing.")
                    render(view:'edit',model:[paymentTypeInstance:paymentTypeInstance])
                    return
                }
            }
            paymentTypeInstance.properties = params
            if(!paymentTypeInstance.hasErrors() && paymentTypeInstance.save()) {
                flash.message = "PaymentType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "PaymentType ${params.id} has Errors on update Please try again"
				render(action:show,id:paymentTypeInstance.id)
            }
        }
        else {
            flash.message = "PaymentType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def paymentTypeInstance = new PaymentType()
        paymentTypeInstance.properties = params
        render(view:'create',model:['paymentTypeInstance':paymentTypeInstance])
    }

    def save = {
        def paymentTypeInstance = new PaymentType(params)
        if(!paymentTypeInstance.hasErrors() && paymentTypeInstance.save()) {
            flash.message = "PaymentType ${paymentTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "PaymentType ${paymentTypeInstance.id} error"
			render(action:show,id:paymentTypeInstance.id)
        }
    }
}
