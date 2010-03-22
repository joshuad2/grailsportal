

package com.grailsPortal.domain

class ProductTypeController {
/**
 *  ProductTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ productTypeInstanceList: ProductType.list( params ), productTypeInstanceTotal: ProductType.count() ]
    }
	
    def show = {
        def productTypeInstance = ProductType.get( params.id )

        if(!productTypeInstance) {
            flash.message = "ProductType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[productTypeInstance:productTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def productTypeInstance = ProductType.get( params.id )
        if(productTypeInstance) {
            try {
                productTypeInstance.delete(flush:true)
                flash.message = "ProductType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ProductType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ProductType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def productTypeInstance = ProductType.get( params.id )

        if(!productTypeInstance) {
            flash.message = "ProductType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ productTypeInstance : productTypeInstance ])
        }
    }

    def update = {
        def productTypeInstance = ProductType.get( params.id )
        if(productTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(productTypeInstance.version > version) {
                    
                    productTypeInstance.errors.rejectValue("version", "productType.optimistic.locking.failure", "Another user has updated this ProductType while you were editing.")
                    render(view:'edit',model:[productTypeInstance:productTypeInstance])
                    return
                }
            }
            productTypeInstance.properties = params
            if(!productTypeInstance.hasErrors() && productTypeInstance.save()) {
                flash.message = "ProductType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "ProductType ${params.id} has Errors on update Please try again"
				render(action:show,id:productTypeInstance.id)
            }
        }
        else {
            flash.message = "ProductType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def productTypeInstance = new ProductType()
        productTypeInstance.properties = params
        render(view:'create',model:['productTypeInstance':productTypeInstance])
    }

    def save = {
        def productTypeInstance = new ProductType(params)
        if(!productTypeInstance.hasErrors() && productTypeInstance.save()) {
            flash.message = "ProductType ${productTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "ProductType ${productTypeInstance.id} error"
			render(action:show,id:productTypeInstance.id)
        }
    }
}
