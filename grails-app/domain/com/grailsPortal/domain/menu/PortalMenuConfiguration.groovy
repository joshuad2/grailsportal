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
package com.grailsPortal.domain.menu;
import java.io.Serializable;

import com.grailsPortal.domain.portalConfig.PortalConfig
class PortalMenuConfiguration  implements Serializable{
 String  menuName
 String  position
 String  hideDelay
 String  lazyLoad
 String  style
 String  width
 String  height
 String  borderStyle
 String  borderWidth
 String  borderHeight
 String  margin
 PortalConfig portalConfig
 String isActive
 static hasMany=[portalMenus:PortalMenu]
 static mapping={
	 portalMenus lazy:false
 }

 static constraints={
		menuName(size:1..100,blank:false,nullable:false)
	    position(size:1..20,blank:false,nullable:false,inList:["static","dynamic"])
	    hideDelay(size:1..20,blank:false,nullable:false)
	    lazyLoad(size:1..2,inList:["Y","N"],blank:false,nullable:false)
		style(size:1..200,blank:true,nullable:true)
		width(size:1..30,blank:true,nullable:true)
		height(size:1..30,blank:true,nullable:true)
		borderStyle(size:1..300,blank:true,nullable:true)
		borderWidth(size:1..30,blank:true,nullable:true)
		borderHeight(size:1..30,blank:true,nullable:true)
		margin(size:1..30,blank:true,nullable:true)
	    isActive(size:1..2,inList:["Y","N"],blank:false,nullable:false)
	    portalConfig(blank:false,nullable:false)
	}
  String toString(){
		return menuName
	}
}
