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

class OrderStatus implements Serializable{
    String name
	String cd
	String dsc
	Boolean active = true
    static constraints = {
		name(size:1..100,blank:false, nullable:false)
		cd(size:1..20,blank:false, nullable:false)
		dsc(size:1..200,blank:true, nullable:true)
		active(blank:false, nullable:false)
    }
}
