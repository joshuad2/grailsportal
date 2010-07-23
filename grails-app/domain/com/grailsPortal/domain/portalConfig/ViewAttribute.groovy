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
package com.grailsPortal.domain.portalConfig
import com.grailsPortal.domain.attribute.*
class ViewAttribute implements Serializable, Comparable{
	View               view
	AttributeComponent attributeComponent
	Integer            displayOrder
	Boolean            mandatory
    static constraints = {
    	view(nullable:false, blank:false)
    	attributeComponent(nullable:false,blank:false)
    	displayOrder(range:1..100,nullable:false,blank:false)
    	mandatory(nullable:false,blank:false)
    }
	   int compareTo(obj) {
	       displayOrder.compareTo(obj.displayOrder)
	   }

    String toString() {
        return view.name+":"+attributeComponent.name 
    }
}