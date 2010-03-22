package com.grailsPortal.service.util
import com.grailsPortal.exception.ValidationException
import org.apache.shiro.crypto.hash.Sha1Hash
import com.grailsPortal.ui.util.ContactUtilHandler
import com.grailsPortal.domain.*
import org.grails.grailsui.DialogTagLib
import org.apache.shiro.crypto.hash.Sha1Hash
class ContactUtilService {
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
			
    boolean transactional = true
    def cuh=new ContactUtilHandler()
		
    def JsecUser doUser(ju,partyType,attrs){
  		      ju.username=attrs.userName
			  ju.party.firstName=attrs.firstName
  		      ju.party.lastName=attrs.lastName
  		      ju.party.birthDate=attrs.birthDate
		      ju.party.partyType=partyType
			  ju.active=true
			  ju.passwordHash=new Sha1Hash(attrs.password).toHex()
			  ju.party.save()
  		      ju.save()
			return ju
		}

    def ContactAddress doAddress(String cd,ContactAddress adr, params){
		if ( cuh.isEmpty(params[ADDRESS1]) &&
		     cuh.isEmpty(params[CITY]) &&
		     cuh.isEmpty(params[STATE]) &&
		     cuh.isEmpty(params[ZIPCODE])){
			return null
		}
    	if (adr==null){
	      adr=new ContactAddress()
    	}
    	return cuh.doAddress(adr,address1,address2,city,state,zipcode,ContactType.findByCd(cd)).save()
		}
    
	def ContactAddress validateAddress(ContactAddress adr)throws ValidationException{
		if (adr==null){
			return null
		}
  	    if(!adr.validate()){
	    	 throw new ValidationException(adr)
	     }
 	    adr.save()
 	    return adr
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
    	if (email==null){
    		return null
    	}
    	   if (!email.validate()){
    		   throw new ValidationException(email)
    	   }
    	   email.save()
    	   return email
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
    	if (phone==null){
    		return null
    	}
    	  if (!phone.validate()){
      		  throw new ValidationException(email)
      	  }
      	  phone.save()
      	  return phone
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
	
	def getProfilePhoneContactTypes(Party party){
		def retArr=[]
		def cts= ContactType.list()
		cts.each{
		  def ct=it
          def pcp = PartyContactPhone.find("from PartyContactPhone where party=? and contactType=?",party,ct)
		  if (pcp==null){
			  retArr.add it
			}
		}
		return retArr
	}
	
	def getProfileAddressContactTypes(Party party){
		def retArr=[]
		def cts= ContactType.list()
		cts.each{
			def ct=it
			def pcp = PartyContactAddress.find("from PartyContactAddress where party=? and contactType=?",party,ct)
			if (pcp==null){
				retArr.add it
			}
		}
		return retArr
	}
	
	def getProfileEmailContactTypes(Party party){
		def retArr=[]
		def cts= ContactType.list()
		cts.each{
			def ct=it
			def pcp = PartyContactAddress.find("from PartyContactEmail where party=? and contactType=?",party,ct)
			if (pcp==null){
				retArr.add it
			}
		}
		return retArr
	}
	

}