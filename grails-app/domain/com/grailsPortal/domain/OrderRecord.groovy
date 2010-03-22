package com.grailsPortal.domain

class OrderRecord implements Serializable{
    static mapping = {
         table 'order_record'
         grossAmount       column:'gross_amount' 
         orderComment      column:'order_comment' 
         orderDate         column:'order_date' 
         taxAmount         column:'tax_amount' 
         totalAmount       column:'total_amount' 
         orderStatus       column:'order_status' 
         orderRecordType   column:'order_record_type_id'
    }
    String          orderNumber
    Double          grossAmount
    String          orderComment
    Date            orderDate
    Double          taxAmount
    Double          totalAmount
    OrderStatus     orderStatus
    OrderRecordType orderRecordType
    Party           party
	static hasMany=[
	               subscriptions:Subscription,
	               registrationEventOrderRecords:RegistrationEventOrderRecord
	               ]
    static constraints = {
    	orderNumber(nullable: false,blank:false)
        grossAmount(nullable: false,blank:false)
        orderRecordType(nullable:false,blank:false)
		orderStatus(nullable:false,blank:false)
        party(nullable:false,blank:false)
        orderComment(size: 1..1000, blank: false, nullable:true)
        orderDate(nullable: false,blank:false)
        taxAmount(nullable: false,blank:false)
        totalAmount(nullable: false,blank:false)
		subscriptions(nullable:true,blank:true)
		registrationEventOrderRecords(nullable:true,blank:true)
    }
    String toString() {
        return  orderNumber
    }
}
