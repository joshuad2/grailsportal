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
class OrderRecordLineItem implements Serializable{
    static mapping = {
         table 'order_record_line_item'
         amountPurchased          column:'amount_purchased' 
         lineItemAmount           column:'line_item_amount' 
         lineItemNumber           column:'line_item_number' 
         product                  column:'product_id'
         orderRecord              column:'order_record_id'
         lineItemDate             column:'line_item_date'
    }
    Integer     amountPurchased
    Double      lineItemAmount
    Integer     lineItemNumber
    Product     product
    OrderRecord orderRecord
    Date        lineItemDate
    static constraints = {
        amountPurchased(range:1..100,nullable: false)
        lineItemAmount(nullable: false)
        lineItemNumber(range:1..100,nullable: false)
        product(nullable:false)
        orderRecord(nullable:false)
    }
    String toString() {
        return lineItemNumber+":"+orderRecord+":"+product
    }
}
