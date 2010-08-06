
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */package com.grailsPortal.controller


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
