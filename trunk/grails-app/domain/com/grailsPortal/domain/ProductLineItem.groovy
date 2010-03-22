package com.grailsPortal.domain
class ProductLineItem implements Serializable{
    static mapping = {
         table 'product_line_item'
         dsc column:'dsc' 
         grossCost column:'gross_cost' 
         isTaxable column:'is_taxable' 
         netCost column:'net_cost' 
         retailAmount column:'retail_amount' 
         wholesaleAmount column:'wholesale_amount' 
         product column:'product_id'
    }
    String  dsc
    Integer lineItemNumber
    Double  grossCost
    String  isTaxable
    Double  netCost
    Double  retailAmount
    Double  wholesaleAmount
    Product product
    
    static constraints = {
        dsc(size: 1..200, blank: false)
        grossCost(nullable: false)
        lineItemNumber(range:1..200)
        isTaxable(size: 1..255, blank: false)
        netCost(nullable: false)
        retailAmount(nullable: false)
        wholesaleAmount(nullable: false)
        product()
    }
    String toString() {
        return  lineItemNumber+"-"+dsc
    }
}
