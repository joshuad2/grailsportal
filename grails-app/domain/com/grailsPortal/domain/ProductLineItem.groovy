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
class ProductLineItem implements Serializable{
    static mapping = {
         table 'product_line_item'
         dsc column:'dsc' 
         grossCost column:'gross_cost' 
         isTaxable column:'is_taxable' 
         netCost column:'net_cost' 
         retailAmount column:'retail_amount' 
         wholesaleAmount column:'wholesale_amount' 
         product column:'product_id'
    }
    String  dsc
    Integer lineItemNumber
    Double  grossCost
    String  isTaxable
    Double  netCost
    Double  retailAmount
    Double  wholesaleAmount
    Product product
    
    static constraints = {
        dsc(size: 1..200, blank: false)
        grossCost(nullable: false)
        lineItemNumber(range:1..200)
        isTaxable(size: 1..255, blank: false)
        netCost(nullable: false)
        retailAmount(nullable: false)
        wholesaleAmount(nullable: false)
        product()
    }
    String toString() {
        return  lineItemNumber+"-"+dsc
    }
}
