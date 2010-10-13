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
class Product implements Serializable{
    static mapping = {
         table 'product'
         cd                  column:'cd',index: "product_cd_idx", unique: true
         dsc                 column:'dsc' 
         ecommerceCode       column:'ecommerce_code' 
         name                column:'name' 
         netCostAmount       column:'net_cost_amount' 
         netSalesAmount      column:'net_sales_amount' 
         productImageuripath column:'product_imageuripath' 
         salesChannel        column:'sales_channel_id'
         productType         column:'product_type_id'
    }
    String           cd
    String           dsc
    String           ecommerceCode
    String           name
    Double           netCostAmount
    Double           netSalesAmount
    String           productImageuripath
    SalesChannel     salesChannel
    ProductType      productType
    static hasMany = [
	                 productLineItems:ProductLineItem
	                 ]
    static constraints = {
        cd                 (size: 1..20, blank: false, nullable:false)
        dsc                (size: 1..200, blank: true, nullable:true)
        ecommerceCode      (size: 1..100, blank: false, nullable:false)
        name               (size: 1..100, blank: false, nullable:false)
        netCostAmount      (nullable: false)
        netSalesAmount     (nullable: false)
        productImageuripath(size: 1..255, blank: true,nullable:true)
        salesChannel       (nullable:false)
        productType        (nullable:false)
		productLineItems   (nullable:true,blank:true)
    }
    String toString() {
        return name 
    }
}
