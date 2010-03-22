

package com.grailsPortal.domain

class InventoryOrderController {
/**
 *  InventoryOrderController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ inventoryOrderInstanceList: InventoryOrder.list( params ), inventoryOrderInstanceTotal: InventoryOrder.count() ]
    }
	
    def show = {
        def inventoryOrderInstance = InventoryOrder.get( params.id )

        if(!inventoryOrderInstance) {
            flash.message = "InventoryOrder not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[inventoryOrderInstance:inventoryOrderInstance],view:'show') 
			}
    }
	
    def delete = {
        def inventoryOrderInstance = InventoryOrder.get( params.id )
        if(inventoryOrderInstance) {
            try {
                inventoryOrderInstance.delete(flush:true)
                flash.message = "InventoryOrder ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "InventoryOrder ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "InventoryOrder not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def inventoryOrderInstance = InventoryOrder.get( params.id )

        if(!inventoryOrderInstance) {
            flash.message = "InventoryOrder not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ inventoryOrderInstance : inventoryOrderInstance ])
        }
    }

    def update = {
        def inventoryOrderInstance = InventoryOrder.get( params.id )
        if(inventoryOrderInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(inventoryOrderInstance.version > version) {
                    
                    inventoryOrderInstance.errors.rejectValue("version", "inventoryOrder.optimistic.locking.failure", "Another user has updated this InventoryOrder while you were editing.")
                    render(view:'edit',model:[inventoryOrderInstance:inventoryOrderInstance])
                    return
                }
            }
            inventoryOrderInstance.properties = params
            if(!inventoryOrderInstance.hasErrors() && inventoryOrderInstance.save()) {
                flash.message = "InventoryOrder ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "InventoryOrder ${params.id} has Errors on update Please try again"
				render(action:show,id:inventoryOrderInstance.id)
            }
        }
        else {
            flash.message = "InventoryOrder not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def inventoryOrderInstance = new InventoryOrder()
        inventoryOrderInstance.properties = params
        render(view:'create',model:['inventoryOrderInstance':inventoryOrderInstance])
    }

    def save = {
        def inventoryOrderInstance = new InventoryOrder(params)
        if(!inventoryOrderInstance.hasErrors() && inventoryOrderInstance.save()) {
            flash.message = "InventoryOrder ${inventoryOrderInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "InventoryOrder ${inventoryOrderInstance.id} error"
			render(action:show,id:inventoryOrderInstance.id)
        }
    }
}
