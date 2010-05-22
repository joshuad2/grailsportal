package com.grailsPortal.controller


import com.grailsPortal.domain.*
class CampOpsController {
    def registrationService    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        def pt=ProductType.findByName("Classes")
        [ productInstanceList: Product.findAllByProductType( pt ) ]
    }

    def showRemote={
        if(!params.max) params.max = 10
        def pt=ProductType.findByName("Classes")
        def productInstance = Product.get( params.id )
        def regEvents=registrationService.getRegistrationEventsAndLineItemsForProduct(productInstance)
        if(!productInstance) {
            flash.message = "Product not found with id ${params.id}"
            redirect(action:list)
        }
        else { 
        	render (model:[regEvents:regEvents,product:productInstance],view:'show')
        	return 
        	}
    }
    def showFinish={
  	  def regEvent=RegistrationEvent.get(params.id)
  	  
  	  render(model:[regEvent:regEvent],view:'showRegistration')
  	  return
      }
    def show = {
        if(!params.max) params.max = 10
        def pt=ProductType.findByName("Classes")
        def productInstance = Product.get( params.id )
        def regEvents=registrationService.getRegistrationEventsAndLineItemsForProduct(productInstance)
        if(!productInstance) {
            flash.message = "Product not found with id ${params.id}"
            redirect(action:list)
        }
        else { 
        	render (model:[productInstanceList: Product.findAllByProductType( pt ),regEvents:regEvents,product:productInstance],view:'list')
        	return 
        	}
    }
}
