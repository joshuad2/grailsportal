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
 * Attribute Domain class holds the description
 * of the attributes that have the potential to 
 * affect the appearance of the portal.  The data
 * in this domain clas describes a generic representation
 * of data that extends the existing set of tables described
 * in the Portal Application.
 * 
 * @author Joshua Davis
 *
 */
class Attribute implements Serializable{

    String cd
    String dsc
    String name
	Integer maximumWidth
    AttributeType attributeType
    AttributeDataType attributeDataType
    String pathToAttribute
    static constraints = {
        cd(size: 1..20, blank: false,nullable:false)
        dsc(size: 1..200, blank: false,nullable:false)
        name(size: 1..100, blank: false,nullable:false)
        attributeDataType(nullable:true)
        attributeType(nullable:false)
		maximumWidth(nullable:true,blank:true)
        pathToAttribute(size:1..100, blank:true,nullable:true)
    }
    String toString() {
        return name 
    }
}
