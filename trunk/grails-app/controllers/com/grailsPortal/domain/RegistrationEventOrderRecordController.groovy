

package com.grailsPortal.domain

class RegistrationEventOrderRecordController {
/**
 *  RegistrationEventOrderRecordController
 *  @author Joshua Davis    
 */
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ registrationEventOrderRecordInstanceList: RegistrationEventOrderRecord.list( params ), registrationEventOrderRecordInstanceTotal: RegistrationEventOrderRecord.count() ]
    }
	
    def show = {
        def registrationEventOrderRecordInstance = RegistrationEventOrderRecord.get( params.id )

        if(!registrationEventOrderRecordInstance) {
            flash.message = "RegistrationEventOrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
        else {
			render (model:[registrationEventOrderRecordInstance:registrationEventOrderRecordInstance],view:'show') 
			}
    }
	
    def delete = {
        def registrationEventOrderRecordInstance = RegistrationEventOrderRecord.get( params.id )
        if(registrationEventOrderRecordInstance) {
            try {
                registrationEventOrderRecordInstance.delete(flush:true)
                flash.message = "RegistrationEventOrderRecord ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "RegistrationEventOrderRecord ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "RegistrationEventOrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def registrationEventOrderRecordInstance = RegistrationEventOrderRecord.get( params.id )

        if(!registrationEventOrderRecordInstance) {
            flash.message = "RegistrationEventOrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            render(view:'edit',model: [ registrationEventOrderRecordInstance : registrationEventOrderRecordInstance ])
        }
    }

    def update = {
        def registrationEventOrderRecordInstance = RegistrationEventOrderRecord.get( params.id )
        if(registrationEventOrderRecordInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(registrationEventOrderRecordInstance.version > version) {
                    
                    registrationEventOrderRecordInstance.errors.rejectValue("version", "registrationEventOrderRecord.optimistic.locking.failure", "Another user has updated this RegistrationEventOrderRecord while you were editing.")
                    render(view:'edit',model:[registrationEventOrderRecordInstance:registrationEventOrderRecordInstance])
                    return
                }
            }
            registrationEventOrderRecordInstance.properties = params
            if(!registrationEventOrderRecordInstance.hasErrors() && registrationEventOrderRecordInstance.save()) {
                flash.message = "RegistrationEventOrderRecord ${params.id} updated"
				redirect(action:list)
            }
            else {
				flash.message = "RegistrationEventOrderRecord ${params.id} has Errors on update Please try again"
				render(action:show,id:registrationEventOrderRecordInstance.id)
            }
        }
        else {
            flash.message = "RegistrationEventOrderRecord not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def registrationEventOrderRecordInstance = new RegistrationEventOrderRecord()
        registrationEventOrderRecordInstance.properties = params
        render(view:'create',model:['registrationEventOrderRecordInstance':registrationEventOrderRecordInstance])
    }

    def save = {
        def registrationEventOrderRecordInstance = new RegistrationEventOrderRecord(params)
        if(!registrationEventOrderRecordInstance.hasErrors() && registrationEventOrderRecordInstance.save()) {
            flash.message = "RegistrationEventOrderRecord ${registrationEventOrderRecordInstance.id} created"
			redirect(action:list)
        }
        else {
			flash.message = "RegistrationEventOrderRecord ${registrationEventOrderRecordInstance.id} error"
			render(action:show,id:registrationEventOrderRecordInstance.id)
        }
    }
}
