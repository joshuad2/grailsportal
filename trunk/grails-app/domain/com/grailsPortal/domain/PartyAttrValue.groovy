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

import com.grailsPortal.domain.attribute.Attribute;

class PartyAttrValue implements Serializable{
    static mapping = {
         table 'party_attr_value'
         attrValue    column:'attr_value' 
         party        column:'party_id'
         attribute    column:'attribute_id'
    }
    String    attrValue
    Party     party
    Attribute attribute
    Integer   displayOrder
    static constraints = {
        attrValue(size: 1..200, blank: false)
        party()
        attribute()
    }
    String toString() {
        return attrValue 
    }
}
