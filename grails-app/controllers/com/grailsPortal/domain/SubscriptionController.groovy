

package com.grailsPortal.domain

class SubscriptionController {
/**
 *  SubscriptionController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ subscriptionInstanceList: Subscription.list( params ), subscriptionInstanceTotal: Subscription.count() ]
    }
	
    def show = {
        def subscriptionInstance = Subscription.get( params.id )

        if(!subscriptionInstance) {
            flash.message = "Subscription not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[subscriptionInstance:subscriptionInstance],view:'show') 
			}
    }
	
    def delete = {
        def subscriptionInstance = Subscription.get( params.id )
        if(subscriptionInstance) {
            try {
                subscriptionInstance.delete(flush:true)
                flash.message = "Subscription ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Subscription ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Subscription not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def subscriptionInstance = Subscription.get( params.id )

        if(!subscriptionInstance) {
            flash.message = "Subscription not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ subscriptionInstance : subscriptionInstance ])
        }
    }

    def update = {
        def subscriptionInstance = Subscription.get( params.id )
        if(subscriptionInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(subscriptionInstance.version > version) {
                    
                    subscriptionInstance.errors.rejectValue("version", "subscription.optimistic.locking.failure", "Another user has updated this Subscription while you were editing.")
                    render(view:'edit',model:[subscriptionInstance:subscriptionInstance])
                    return
                }
            }
            subscriptionInstance.properties = params
            if(!subscriptionInstance.hasErrors() && subscriptionInstance.save()) {
                flash.message = "Subscription ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "Subscription ${params.id} has Errors on update Please try again"
				render(action:show,id:subscriptionInstance.id)
            }
        }
        else {
            flash.message = "Subscription not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def subscriptionInstance = new Subscription()
        subscriptionInstance.properties = params
        render(view:'create',model:['subscriptionInstance':subscriptionInstance])
    }

    def save = {
        def subscriptionInstance = new Subscription(params)
        if(!subscriptionInstance.hasErrors() && subscriptionInstance.save()) {
            flash.message = "Subscription ${subscriptionInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "Subscription ${subscriptionInstance.id} error"
			render(action:show,id:subscriptionInstance.id)
        }
    }
}
