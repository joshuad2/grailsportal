package com.grailsPortal.domain 
class OrderRecordLineItem implements Serializable{
    static mapping = {
         table 'order_record_line_item'
         amountPurchased          column:'amount_purchased' 
         lineItemAmount           column:'line_item_amount' 
         lineItemNumber           column:'line_item_number' 
         product                  column:'product_id'
         orderRecord              column:'order_record_id'
         lineItemDate             column:'line_item_date'
    }
    Integer     amountPurchased
    Double      lineItemAmount
    Integer     lineItemNumber
    Product     product
    OrderRecord orderRecord
    Date        lineItemDate
    static constraints = {
        amountPurchased(range:1..100,nullable: false)
        lineItemAmount(nullable: false)
        lineItemNumber(range:1..100,nullable: false)
        product(nullable:false)
        orderRecord(nullable:false)
    }
    String toString() {
        return lineItemNumber+":"+orderRecord+":"+product
    }
}
