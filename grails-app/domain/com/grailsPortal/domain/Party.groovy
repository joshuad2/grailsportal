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

class Party implements Serializable{
    String         firstName
    String         middleName
    String         lastName
    String         prefix
    String         suffix
    Date           birthDate
    PartyType      partyType
    static hasMany = [ orderRecordList : OrderRecord,
                       addressList:      ContactAddress,
                       phoneList:        ContactPhone,
                       emailList:        ContactEmail,
                       partyAttrValues:  PartyAttrValue
                     ]
                       
    static constraints = {
        prefix         (size:1..20, blank:true,nullable:true)
        firstName      (size: 1..100, blank: false,nullable:false)
        middleName     (size:1..50, blank:true,nullable:true)
        lastName       (size: 1..100, blank: false,nullable:false)
        suffix         (size:1..20, blank:true,nullable:true)
        birthDate      (nullable:true,blank:true)
        partyType      (nullable:true,blank:true)
        addressList    (nullable:true,blank:true)
        phoneList      (nullable:true,blank:true)
        emailList      (nullable:true,blank:true)
        orderRecordList(nullable:true,blank:true)
        partyAttrValues(nullable:true,blank:true)
    }
    String toString() {
        return firstName+" "+lastName 
    }
}
