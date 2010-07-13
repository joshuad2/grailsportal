
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
class Subscription implements Serializable{
    static mapping = {
         table 'subscription'
         active column:'active' 
         endDate column:'end_date' 
         paidInFull column:'paid_in_full' 
         renewDate column:'renew_date' 
         startDate column:'start_date' 
         totalAmount column:'total_amount' 
         totalPaid column:'total_paid' 
         orderRecord column:'order_record_id'
    }
    String active
    Date endDate
    String paidInFull
    Date renewDate
    Date startDate
    Double totalAmount
    Double totalPaid
    OrderRecord orderRecord
    static constraints = {
        active(size: 1..255, blank: false,nullable:false)
        endDate(nullable: false)
        paidInFull(size: 1..255, blank: false,nullable:false)
        renewDate(nullable: false)
        startDate(nullable: false)
        totalAmount(nullable: false)
        totalPaid(nullable: false)
        orderRecord(nullable:false)
    }
    String toString() {
        return startDate+" "+endDate
    }
}
