package com.grailsPortal.domain 

import com.grailsPortal.domain.attribute.Attribute;

class OrderRecordAttrValue implements Serializable{
    static mapping = {
         table 'order_record_attr_value'
         attrValue   column:'attr_value' 
         orderRecord column:'order_record_id'
         attribute   column:'attribute_id'
    }
    String           attrValue
    OrderRecord      orderRecord
    Attribute        attribute
    static constraints = {
        attrValue(size: 1..255, blank: false)
        orderRecord()
        attribute()
    }
    String toString() {
        return attrValue 
    }
}
