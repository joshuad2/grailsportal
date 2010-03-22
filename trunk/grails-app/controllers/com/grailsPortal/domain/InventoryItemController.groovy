

package com.grailsPortal.domain

class InventoryItemController {
/**
 *  InventoryItemController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ inventoryItemInstanceList: InventoryItem.list( params ), inventoryItemInstanceTotal: InventoryItem.count() ]
    }
	
    def show = {
        def inventoryItemInstance = InventoryItem.get( params.id )

        if(!inventoryItemInstance) {
            flash.message = "InventoryItem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[inventoryItemInstance:inventoryItemInstance],view:'show') 
			}
    }
	
    def delete = {
        def inventoryItemInstance = InventoryItem.get( params.id )
        if(inventoryItemInstance) {
            try {
                inventoryItemInstance.delete(flush:true)
                flash.message = "InventoryItem ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "InventoryItem ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "InventoryItem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def inventoryItemInstance = InventoryItem.get( params.id )

        if(!inventoryItemInstance) {
            flash.message = "InventoryItem not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ inventoryItemInstance : inventoryItemInstance ])
        }
    }

    def update = {
        def inventoryItemInstance = InventoryItem.get( params.id )
        if(inventoryItemInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(inventoryItemInstance.version > version) {
                    
                    inventoryItemInstance.errors.rejectValue("version", "inventoryItem.optimistic.locking.failure", "Another user has updated this InventoryItem while you were editing.")
                    render(view:'edit',model:[inventoryItemInstance:inventoryItemInstance])
                    return
                }
            }
            inventoryItemInstance.properties = params
            if(!inventoryItemInstance.hasErrors() && inventoryItemInstance.save()) {
                flash.message = "InventoryItem ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "InventoryItem ${params.id} has Errors on update Please try again"
				render(action:show,id:inventoryItemInstance.id)
            }
        }
        else {
            flash.message = "InventoryItem not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def inventoryItemInstance = new InventoryItem()
        inventoryItemInstance.properties = params
        render(view:'create',model:['inventoryItemInstance':inventoryItemInstance])
    }

    def save = {
        def inventoryItemInstance = new InventoryItem(params)
        if(!inventoryItemInstance.hasErrors() && inventoryItemInstance.save()) {
            flash.message = "InventoryItem ${inventoryItemInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "InventoryItem ${inventoryItemInstance.id} error"
			render(action:show,id:inventoryItemInstance.id)
        }
    }
}
