package com.grailsPortal.domain 
class PortalConfigAttrValue {
    static belongsTo = PortalConfig
    static mapping = {
        table 'portal_config_attr_value'
        attrValue    column:'attr_value' 
        portalConfig column:'portal_config_id'
        attribute    column:'attribute_id'
   }
   String    attrValue
   PortalConfig     portalConfig
   Attribute attribute
   static constraints = {
       attrValue(size: 1..200, blank: false)
       portalConfig(blank:false,nullable:false)
       attribute(blank:false, nullable:false)
   }
   String toString() {
       return attrValue 
   }
}
