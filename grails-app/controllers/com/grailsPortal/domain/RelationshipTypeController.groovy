

package com.grailsPortal.domain

class RelationshipTypeController {
/**
 *  RelationshipTypeController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ relationshipTypeInstanceList: RelationshipType.list( params ), relationshipTypeInstanceTotal: RelationshipType.count() ]
    }
	
    def show = {
        def relationshipTypeInstance = RelationshipType.get( params.id )

        if(!relationshipTypeInstance) {
            flash.message = "RelationshipType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[relationshipTypeInstance:relationshipTypeInstance],view:'show') 
			}
    }
	
    def delete = {
        def relationshipTypeInstance = RelationshipType.get( params.id )
        if(relationshipTypeInstance) {
            try {
                relationshipTypeInstance.delete(flush:true)
                flash.message = "RelationshipType ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "RelationshipType ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "RelationshipType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def relationshipTypeInstance = RelationshipType.get( params.id )

        if(!relationshipTypeInstance) {
            flash.message = "RelationshipType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ relationshipTypeInstance : relationshipTypeInstance ])
        }
    }

    def update = {
        def relationshipTypeInstance = RelationshipType.get( params.id )
        if(relationshipTypeInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(relationshipTypeInstance.version > version) {
                    
                    relationshipTypeInstance.errors.rejectValue("version", "relationshipType.optimistic.locking.failure", "Another user has updated this RelationshipType while you were editing.")
                    render(view:'edit',model:[relationshipTypeInstance:relationshipTypeInstance])
                    return
                }
            }
            relationshipTypeInstance.properties = params
            if(!relationshipTypeInstance.hasErrors() && relationshipTypeInstance.save()) {
                flash.message = "RelationshipType ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "RelationshipType ${params.id} has Errors on update Please try again"
				render(action:show,id:relationshipTypeInstance.id)
            }
        }
        else {
            flash.message = "RelationshipType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def relationshipTypeInstance = new RelationshipType()
        relationshipTypeInstance.properties = params
        render(view:'create',model:['relationshipTypeInstance':relationshipTypeInstance])
    }

    def save = {
        def relationshipTypeInstance = new RelationshipType(params)
        if(!relationshipTypeInstance.hasErrors() && relationshipTypeInstance.save()) {
            flash.message = "RelationshipType ${relationshipTypeInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "RelationshipType ${relationshipTypeInstance.id} error"
			render(action:show,id:relationshipTypeInstance.id)
        }
    }
}
