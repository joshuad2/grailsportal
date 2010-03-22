package com.grailsPortal.ui.util
import com.grailsPortal.exception.ValidationException
import org.apache.shiro.crypto.hash.Sha1Hash
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
		  user.passwordHash=new Sha1Hash(password).toHex()
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

