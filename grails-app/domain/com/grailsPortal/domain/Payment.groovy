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
class Payment implements Serializable{
    static mapping = {
         table 'payment'
         paymentAmount   column:'payment_amount' 
         paymentDate     column:'payment_date' 
         subscription    column:'subscription_id'
         party           column:'party_id'
         paymentMethod   column:'payment_method_id'
         paymentType     column:'payment_type_id'
         inventoryOrder  column:'inventory_order_id'
         displayOrder    column:'displayOrder'
    }
    Double             paymentAmount
    Integer            displayOrder
    Date               paymentDate
    Subscription       subscription
    Party              party
    PaymentMethodType  paymentMethod
    PaymentType        paymentType
    InventoryOrder     inventoryOrder
    static constraints = {
    	displayOrder(nullable:false)
        paymentAmount(nullable: false)
        paymentDate(nullable: false)
        subscription(nullable:false)
        party(nullable:false)
        paymentMethod(nullable:false)
        paymentType(nullable:false)
        inventoryOrder(nullable:false)
    }
    String toString() {
        return paymentDate+"-"+paymentAmount 
    }
}
