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
package com.grailsPortal.service.util
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.apache.shiro.session.Session
import com.grailsPortal.domain.*;
class SecurityService {
    static profiled=true
    boolean transactional = true

    def JsecUser getRegisteredUser() {
        Subject currentUser=SecurityUtils.getSubject()
        Session session = currentUser.getSession()
        return JsecUser.findByUsername(currentUser.getPrincipal())
    }
    def Boolean isAdmin(){
    	if ( SecurityUtils.getSubject().hasRole( "Administrator" ) )
          return true
    	else
    	  return false
    }
    def ArrayList <RegistrationEvent> getEmergencyContacts(){
    	def user=getRegisteredUser()
    	def parties = new ArrayList()
    	def regs=RegistrationEvent.findByRegistrationUser(user)
    	regs.each{
    		if (it.emergencyContact1 !=null){
    			parties.add(it.emergencyContact1)
    		}
    		if (it.emergencyContact2 !=null){
    			parties.add(it.emergencyContact2)
    		}
    		if (it.emergencyContact3 !=null){
    			parties.add(it.emergencyContact3)
    		}
    		
    	}
    	return regs
    }
   
}
