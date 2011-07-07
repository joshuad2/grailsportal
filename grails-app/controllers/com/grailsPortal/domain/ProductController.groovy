
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
 */

package com.grailsPortal.domain
import com.grailsPortal.domain.OrderRecord
import com.grailsPortal.taglib.RegistrationTagLib
class ProductController {
	/**
	 *  ProductController
	 *  @author Joshua Davis    
	 */
	def securityService
	def registrationService
	def contactUtilService
	def scaffold = "true"

	def selectProduct={
		def productId=params.id
		Product product=Product.get(productId)
		def spRegEvent=session.regEvent
		
		OrderRecord orderRecord=registrationService.doOrderRecord(new OrderRecord(),
				securityService.getRegisteredUser().party,
				"online Order",
				0.0,0.0,0.0,
				new java.util.Date(),
				"IA",
				"Registration")
		registrationService.handleSingleOrderRecordLineItem(orderRecord,productId)
		RegistrationEventOrderRecord roer=registrationService.handleRegistrationEventOrderRecord(orderRecord,spRegEvent)
		roer.save(flush:true)
		if (spRegEvent.orders==null){
			spRegEvent.orders=new ArrayList()
		}
		spRegEvent.orders.add(roer)
		RegistrationTagLib rtl=new RegistrationTagLib()
		def retVal=""
		retVal=rtl.doProduct(true,spRegEvent.id,product.ecommerceCode,product.productType.name,product.salesChannel.name)
        render retVal
	}
	
  def remove={
		def productId=params.id
		Product product=Product.get(productId)
		def spRegEvent=session.regEvent
        def theList=registrationService.getRegistrationEventOrderRecordByProduct(product.id,spRegEvent.id)
		registrationService.deleteOrders(theList)
		RegistrationTagLib rtl=new RegistrationTagLib()
		def retVal=""
		retVal=rtl.doProduct(true,spRegEvent.id,product.ecommerceCode,product.productType.name,product.salesChannel.name)
        render retVal
  }
}
