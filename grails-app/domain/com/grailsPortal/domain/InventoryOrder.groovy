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
class InventoryOrder implements Serializable{
    static mapping = {
         table 'inventory_order'
         expectedArrivalDate column:'expected_arrival_date' 
         orderComment        column:'order_comment' 
         shipDate            column:'ship_date' 
         shippingComment     column:'shipping_comment' 
         shippingCost        column:'shipping_cost' 
         shippingTrackingNum column:'shipping_tracking_num' 
         shippingEmail       column:'shipping_email_id'
         inventoryItem       column:'inventory_item_id'
         shippingAddress     column:'shipping_address_id'
         shippingPhone       column:'shipping_phone_id'
    }
    Date   expectedArrivalDate
    String orderNumber
    String orderComment
    Date   shipDate
    String shippingComment
    Double shippingCost
    String shippingTrackingNum
    ContactEmail shippingEmail
    InventoryItem inventoryItem
    ContactAddress shippingAddress
    ContactPhone shippingPhone
    static constraints = {
    	orderNumber(nullable: false)
        expectedArrivalDate(nullable: false)
        orderComment(size: 1..1000, blank: false)
        shipDate(nullable: false)
        shippingComment(size: 1..255, blank: false)
        shippingCost(nullable: false)
        shippingTrackingNum(size: 1..255, blank: false)
        shippingEmail(nullable:true)
        inventoryItem(nullable:false)
        shippingAddress(nullable:true)
        shippingPhone(nullable:true)
    }
    String toString() {
        return  orderNumber+" "+expectedArrivalDate
    }
}
