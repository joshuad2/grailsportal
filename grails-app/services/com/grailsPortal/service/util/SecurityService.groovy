package com.grailsPortal.service.util
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.apache.shiro.session.Session
import com.grailsPortal.domain.*;
class SecurityService {

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
