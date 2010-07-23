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

/**
 * @author Joshua Davis
 * This domain class defines the steps that are
 * available to the views.  These are not updateable
 * by the users in this version because these are the
 * actual strings used in the Spring Web-Flow and subsequently
 * the controllers.
 */
class BusinessProcessStep implements Serializable {
	String name
	String dsc
	String cd
    static belongsTo = View
    static hasMany = [views:View]
    static constraints = {
    	cd(size:1..20, nullable:false, blank:false)
		name(size:1..100, nullable:false, blank:false)
    	dsc(size:1..200, nullable:true, blank:true)
    	views(nullable:true,blank:true)
    }
	String toString(){
		return name
	}
}
