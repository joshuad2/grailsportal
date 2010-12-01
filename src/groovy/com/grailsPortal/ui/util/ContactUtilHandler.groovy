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
package com.grailsPortal.ui.util
import com.grailsPortal.exception.ValidationException
import org.apache.shiro.crypto.hash.Sha256Hash
import com.grailsPortal.domain.*
class ContactUtilHandler {
	    def boolean isEmpty(value){
			if (value==null|| value==""){
				return false
			}
		}
		def doUser(userParty,user,username,firstName,lastName,birthDate,partyType,password){
		  user.username=username
  		  userParty.firstName=firstName
		  userParty.lastName=lastName
	      userParty.birthDate=birthDate
		  userParty.partyType=partyType
		  user.party=userParty
		  user.passwordHash=new Sha256Hash(password).toHex()
		  return user
		  }
		def doAddress(adr, address1, address2, city, state, zipcode, contactType){
	 	    adr.address1=address1
	 	    adr.address2=address2
	 	    adr.city=city
	 	    adr.state=state
	 	    adr.zipcode=zipcode
	 	    adr.contactType=contactType
	 	    return adr
		    }
		def doEmail(email,emailAddress,contactType){
			email.contactType=contactType
			email.emailAddress=emailAddress
			return email
		}
		def doPhone(phone, phoneNumber, contactType){
			phone.phoneNumber=phoneNumber
			phone.contactType=contactType
			return phone
		}
}

