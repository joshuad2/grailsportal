package com.grailsPortal.domain 
class PartyAttrValue implements Serializable{
    static mapping = {
         table 'party_attr_value'
         attrValue    column:'attr_value' 
         party        column:'party_id'
         attribute    column:'attribute_id'
    }
    String    attrValue
    Party     party
    Attribute attribute
    Integer   displayOrder
    static constraints = {
        attrValue(size: 1..200, blank: false)
        party()
        attribute()
    }
    String toString() {
        return attrValue 
    }
}
