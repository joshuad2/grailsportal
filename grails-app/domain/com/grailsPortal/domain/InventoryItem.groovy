package com.grailsPortal.domain
class InventoryItem implements Serializable{
    static mapping = {
         table 'inventory_item'
         cd                  column:'cd' 
         contenturipath      column:'contenturipath' 
         dsc                 column:'dsc' 
         imageuripath        column:'imageuripath' 
         name                column:'name' 
         numberOfItemInStock column:'number_of_item_in_stock' 
         retailCost          column:'retail_cost' 
         warningInvLevel     column:'warning_inv_level' 
         wholesaleCost       column:'wholesale_cost' 
    }
    java.lang.String  cd
    java.lang.String  contenturipath
    java.lang.String  dsc
    java.lang.String  imageuripath
    java.lang.String  name
    java.lang.Integer numberOfItemInStock
    java.lang.Double  retailCost
    java.lang.Integer warningInvLevel
    java.lang.Double  wholesaleCost
    static constraints = {
        cd(size: 1..20, blank: false)
        contenturipath(size: 1..255, blank: false)
        dsc(size: 1..200, blank: false)
        imageuripath(size: 1..255, blank: false)
        name(size: 1..100, blank: false)
        numberOfItemInStock(nullable: false)
        retailCost(nullable: false)
        warningInvLevel(nullable: false)
        wholesaleCost(nullable: false)
    }
    String toString() {
        return name 
    }
}
