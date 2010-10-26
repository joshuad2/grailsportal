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

class PortalSubMenu  implements Serializable{
  String theController
  String theAction
  String text
  String isAjax
  String showSpinner
  PortalMenu mainMenu
  String showAdmin
  String showAnonymous
  int    sequence
  PortalMenuType portalMenuType
  static fetchMode = [mainMenu:"eager",portalMenuType:"eager"]
  static mapping = {
	  mainMenu lazy:false
	  portalMenuType lazy:false
  }
static constraints={
		theController(display:true,size:1..40,nullable:false,blank:false)
	    theAction(display:true,size:1..40,nullable:true,blank:true)
	    text(display:true,size:1..100,nullable:false,blank:false)
		sequence(display:true,range:(1..100),nullable:false,blank:false)
	    isAjax(display:true,size:1..2,nullable:false,blank:false,inList:["Y","N"])
		showSpinner(display:true,size:1..2,nullable:false,blank:false,inList:["Y","N"])
		mainMenu(display:true,nullable:false,blank:false)
		showAdmin(display:true,size:1..2,nullable:false,blank:false,inList:["Y","N"])
		showAnonymous(display:true,size:1..2,nullable:false,blank:false,inList:["Y","N"])
        portalMenuType(display:true,nullable:false,blank:false)
	}
String toString(){
		return text
	}
}

