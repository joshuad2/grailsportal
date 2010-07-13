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

class OrderRecord implements Serializable{
    static mapping = {
         table 'order_record'
         grossAmount       column:'gross_amount' 
         orderComment      column:'order_comment' 
         orderDate         column:'order_date' 
         taxAmount         column:'tax_amount' 
         totalAmount       column:'total_amount' 
         orderStatus       column:'order_status' 
         orderRecordType   column:'order_record_type_id'
    }
    String          orderNumber
    Double          grossAmount
    String          orderComment
    Date            orderDate
    Double          taxAmount
    Double          totalAmount
    OrderStatus     orderStatus
    OrderRecordType orderRecordType
    Party           party
	static hasMany=[
	               subscriptions:Subscription,
	               registrationEventOrderRecords:RegistrationEventOrderRecord
	               ]
    static constraints = {
    	orderNumber(nullable: false,blank:false)
        grossAmount(nullable: false,blank:false)
        orderRecordType(nullable:false,blank:false)
		orderStatus(nullable:false,blank:false)
        party(nullable:false,blank:false)
        orderComment(size: 1..1000, blank: false, nullable:true)
        orderDate(nullable: false,blank:false)
        taxAmount(nullable: false,blank:false)
        totalAmount(nullable: false,blank:false)
		subscriptions(nullable:true,blank:true)
		registrationEventOrderRecords(nullable:true,blank:true)
    }
    String toString() {
        return  orderNumber
    }
}
