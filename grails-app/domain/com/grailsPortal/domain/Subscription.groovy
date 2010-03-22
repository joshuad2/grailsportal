package com.grailsPortal.domain 
class Subscription implements Serializable{
    static mapping = {
         table 'subscription'
         active column:'active' 
         endDate column:'end_date' 
         paidInFull column:'paid_in_full' 
         renewDate column:'renew_date' 
         startDate column:'start_date' 
         totalAmount column:'total_amount' 
         totalPaid column:'total_paid' 
         orderRecord column:'order_record_id'
    }
    String active
    Date endDate
    String paidInFull
    Date renewDate
    Date startDate
    Double totalAmount
    Double totalPaid
    OrderRecord orderRecord
    static constraints = {
        active(size: 1..255, blank: false,nullable:false)
        endDate(nullable: false)
        paidInFull(size: 1..255, blank: false,nullable:false)
        renewDate(nullable: false)
        startDate(nullable: false)
        totalAmount(nullable: false)
        totalPaid(nullable: false)
        orderRecord(nullable:false)
    }
    String toString() {
        return startDate+" "+endDate
    }
}
