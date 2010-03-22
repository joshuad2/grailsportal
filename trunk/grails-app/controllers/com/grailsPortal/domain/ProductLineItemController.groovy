

package com.grailsPortal.domain

class ProductLineItemController {
/**
 *  ProductLineItemController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ productLineItemInstanceList: ProductLineItem.list( params ), productLineItemInstanceTotal: ProductLineItem.count() ]
    }
	
    def show = {
        def productLineItemInstance = ProductLineItem.get( params.id )

        if(!productLineItemInstance) {
            flash.message = "ProductLineItem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[productLineItemInstance:productLineItemInstance],view:'show') 
			}
    }
	
    def delete = {
        def productLineItemInstance = ProductLineItem.get( params.id )
        if(productLineItemInstance) {
            try {
                productLineItemInstance.delete(flush:true)
                flash.message = "ProductLineItem ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ProductLineItem ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ProductLineItem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def productLineItemInstance = ProductLineItem.get( params.id )

        if(!productLineItemInstance) {
            flash.message = "ProductLineItem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ productLineItemInstance : productLineItemInstance ])
        }
    }

    def update = {
        def productLineItemInstance = ProductLineItem.get( params.id )
        if(productLineItemInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(productLineItemInstance.version > version) {
                    
                    productLineItemInstance.errors.rejectValue("version", "productLineItem.optimistic.locking.failure", "Another user has updated this ProductLineItem while you were editing.")
                    render(view:'edit',model:[productLineItemInstance:productLineItemInstance])
                    return
                }
            }
            productLineItemInstance.properties = params
            if(!productLineItemInstance.hasErrors() && productLineItemInstance.save()) {
                flash.message = "ProductLineItem ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ProductLineItem ${params.id} has Errors on update Please try again"
				render(action:show,id:productLineItemInstance.id)
            }
        }
        else {
            flash.message = "ProductLineItem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def productLineItemInstance = new ProductLineItem()
        productLineItemInstance.properties = params
        render(view:'create',model:['productLineItemInstance':productLineItemInstance])
    }

    def save = {
        def productLineItemInstance = new ProductLineItem(params)
        if(!productLineItemInstance.hasErrors() && productLineItemInstance.save()) {
            flash.message = "ProductLineItem ${productLineItemInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ProductLineItem ${productLineItemInstance.id} error"
			render(action:show,id:productLineItemInstance.id)
        }
    }
}
