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
package com.grailsPortal.domain.attribute
/**
 * The Attribute Component domain class is the actualized class
 * that combines the description of the generic attribute with the
 * kind of component that will represent it. 
 */
class AttributeComponent implements Serializable{
	String name
	String labelText
	String labelCssStyle
	String labelCssClass
	Attribute attribute
	Component component
	Integer   displayOrder
	String    cssStyle
	String    cssClass
	String    cssId
	AttributeComponentGroup attributeComponentGroup
	Boolean active 
	static fetchMode = [attribute:"eager",
	                    component:"eager",
	                    attributeComponentGroup:"eager"]
    static constraints = {
		name(size:1..100,blank:false,nullable:false)
		attribute(nullable:false,blank:false)
		component(nullable:false,blank:false)
		labelText(size:1..200,nullable:false,blank:false)
		labelCssStyle(size:1..200,nullable:true,blank:true)
		labelCssClass(size:1..200,nullable:false,blank:false)
		displayOrder(nullable:true, blank:false)
		cssStyle(size:1..300,nullable:true, blank:true)
		cssClass(size:1..100,nullable:true, blank:true)
		cssId(size:1..20,nullable:true, blank:true)
		active(nullable:false,blank:false)
		attributeComponentGroup(nullable:true, blank:true)
    }
    String toString() {
        return name+":"+attribute.name+":"+component.name 
    }
}
