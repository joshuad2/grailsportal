package com.grailsPortal.domain
class Payment implements Serializable{
    static mapping = {
         table 'payment'
         paymentAmount   column:'payment_amount' 
         paymentDate     column:'payment_date' 
         subscription    column:'subscription_id'
         party           column:'party_id'
         paymentMethod   column:'payment_method_id'
         paymentType     column:'payment_type_id'
         inventoryOrder  column:'inventory_order_id'
         displayOrder    column:'displayOrder'
    }
    Double             paymentAmount
    Integer            displayOrder
    Date               paymentDate
    Subscription       subscription
    Party              party
    PaymentMethodType  paymentMethod
    PaymentType        paymentType
    InventoryOrder     inventoryOrder
    static constraints = {
    	displayOrder(nullable:false)
        paymentAmount(nullable: false)
        paymentDate(nullable: false)
        subscription(nullable:false)
        party(nullable:false)
        paymentMethod(nullable:false)
        paymentType(nullable:false)
        inventoryOrder(nullable:false)
    }
    String toString() {
        return paymentDate+"-"+paymentAmount 
    }
}
