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

class View implements Serializable{

	String name
	String viewName
	String actionName
	String dsc
	String title
	String template
	String rawHtmlHeader
	String rawHtmlStartBody
	String rawHtmlEndBody
	Integer displayOrder
	SortedSet viewAttributes
	PortalConfigBusinessProcess portalConfigBusinessProcess
	static hasMany=[viewAttributes:ViewAttribute,
	                viewAttributeComponentGroups:ViewAttributeComponentGroup]
    static constraints = {
       name(size:1..100,nullable:false,blank:false)
       viewName(size:1..200,nullable:false,blank:false)
       actionName(size:1..100,nullable:true,blank:true)      
       title(size:1..100, nullable:false,blank:false)
       portalConfigBusinessProcess(nullable:false,blank:false)
       displayOrder(range:1..200,nullable:false,blank:false)
       template(size:1..100, nullable:true,blank:true)
	   dsc(size:1..200,nullable:true,blank:true)
       rawHtmlHeader(size:1..400,nullable:true,blank:true)
       rawHtmlStartBody(size:1..200,nullable:true,blank:true)
       rawHtmlEndBody(size:1..200,nullable:true,blank:true)
       viewAttributes(nullable:true,blank:true)
       viewAttributeComponentGroups(nullable:true,blank:true)
    }
	String toString() {
        return name 
    }
}
