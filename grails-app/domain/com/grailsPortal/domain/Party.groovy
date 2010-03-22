package com.grailsPortal.domain
class Party implements Serializable{
    String         firstName
    String         middleName
    String         lastName
    String         prefix
    String         suffix
    Date           birthDate
    PartyType      partyType
    static hasMany = [ orderRecordList : OrderRecord,
                       addressList:      PartyContactAddress,
                       phoneList:        PartyContactPhone,
                       emailList:        PartyContactEmail,
                       partyAttrValues:  PartyAttrValue
                     ]
                       
    static constraints = {
        prefix         (size:1..20, blank:true,nullable:true)
        firstName      (size: 1..100, blank: false,nullable:false)
        middleName     (size:1..50, blank:true,nullable:true)
        lastName       (size: 1..100, blank: false,nullable:false)
        suffix         (size:1..20, blank:true,nullable:true)
        birthDate      (nullable:true,blank:true)
        partyType      (nullable:true,blank:true)
        addressList    (nullable:true,blank:true)
        phoneList      (nullable:true,blank:true)
        emailList      (nullable:true,blank:true)
        orderRecordList(nullable:true,blank:true)
        partyAttrValues(nullable:true,blank:true)
    }
    String toString() {
        return firstName+" "+lastName 
    }
}
