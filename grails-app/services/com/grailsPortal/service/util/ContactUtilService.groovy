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
import com.grailsPortal.exception.ValidationException
import org.apache.shiro.crypto.hash.Sha256Hash
import com.grailsPortal.ui.util.ContactUtilHandler
import com.grailsPortal.domain.*
import org.grails.grailsui.DialogTagLib
class ContactUtilService {
	    static profiled=true
		private static final String DATEFORMAT="yyyy-MM-dd"
		private static final String ADDRESS1="address1"
		private static final String ADDRESS2="address2"
		private static final String CITY="city"
		private static final String STATE="state"
		private static final String ZIPCODE="zipcode"
		private static final String CHILD="Child"
		private static final String PARENT="Parent"
		private static final String HOME="Home"
		private static final String MOBILE="Mobile"
		private static final String WORK="Work"
		private static final String HOMEPHONENUMBER="homePhoneNumber"
	    private static final String CELLPHONENUMBER="cellPhoneNumber"
	    private static final String WORKPHONENUMBER="workPhoneNumber"
		private static final String EMAILADDRESS="emailAddress"
			
    boolean transactional = true
    def cuh=new ContactUtilHandler()

	def validate={obj->
		if(!obj.validate()){
			throw new ValidationException(obj)
		}
		obj.save()
		return obj
	}
	
    def JsecUser doUser(jsecUser,partyType,attrs){
		      JsecUser ju=jsecUser
  		      ju.username=attrs.userName
			  ju.party.firstName=attrs.firstName
  		      ju.party.lastName=attrs.lastName
  		      ju.party.birthDate=attrs.birthDate
		      ju.party.partyType=partyType
			  ju.active=true
			  ju.passwordHash=new Sha256Hash(attrs.password).toHex()
			  ju.party.save(flush:true)
  		      ju.save(flush:true)
			return ju
		}

    def ContactAddress doAddress(String cd,ContactAddress adr, params){
		if ( cuh.isEmpty(params[ADDRESS1]) &&
		     cuh.isEmpty(params[CITY]) &&
		     cuh.isEmpty(params[STATE]) &&
		     cuh.isEmpty(params[ZIPCODE])){
			return null
		}
		def address1=params[ADDRESS1]
		def address2=params[ADDRESS2]
		def city=params[CITY]
		def state=params[STATE]
		def zipcode=params[ZIPCODE]
    	if (adr==null){
	      adr=new ContactAddress()
    	}
    	return cuh.doAddress(adr,address1,address2,city,state,zipcode,ContactType.findByCd(cd)).save()
		}
    
	def ContactAddress validateAddress(ContactAddress adr)throws ValidationException{
        return validate(adr)
    }
	
    def ContactAddress doHomeAddress(ContactAddress address,params){
    	return doAddress(HOME,address,params);
    }
    def ContactEmail doEmail(ContactEmail email, String suffix,params){
    	   if (cuh.isEmpty(params[EMAILADDRESS+suffix])){
    		   return null
    	   }
           if (email==null){
        	   email=new ContactEmail()
           }
    	   return cuh.doEmail(email,params[EMAILADDRESS+suffix],ContactType.findByCd(HOME)).save()
    }

    def ContactEmail validateEmail(ContactEmail email)throws ValidationException{
       return validate(email)
    }
    def ContactPhone doPhone(String cd,String field,ContactPhone phone,suffix,params){
    	def p=params[field+suffix]
    	if (cuh.isEmpty(params[field+suffix])){
    		return null
    	}
      	  if (phone==null){
      		  phone=new ContactPhone()
      	  }
      	  return cuh.doPhone(phone,phone.params[field+suffix],ContactType.findByCd(cd)).save()
    }
    def validatePhone(ContactPhone phone) throws ValidationException{
        return validate(phone)
    }
    def ContactPhone doHomePhone(ContactPhone phone, String suffix,params){
    	return doPhone(HOME,HOMEPHONENUMBER,phone, suffix,params)
    }
    def ContactPhone doCellPhone(ContactPhone phone, String suffix,params){
        return doPhone(MOBILE,CELLPHONENUMBER,phone, suffix,params)
    }
    def ContactPhone doWorkPhone(ContactPhone phone, String suffix,params){
        return doPhone(WORK,WORKPHONENUMBER,phone,suffix,params)
    }

    def handleNull(ec){
    	if (ec!=null && ec.id==null){
    		return null
    	}else{
    		return ec
            }
    }
	
	def getContactTypes(Party party,String contactClassName){
		def cts= ContactType.list()
		return cts
	}
	def getProfilePhoneContactTypes(Party party){
        return getContactTypes(party,"ContactPhone")
	}
	
	def getProfileAddressContactTypes(Party party){
        return getContactTypes(party,"ContactAddress")
	}
	
	def getProfileEmailContactTypes(Party party){
       return getContactTypes(party,"ContactEmail")
	}
	
}