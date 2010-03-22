package com.grailsPortal.service.config
import java.util.HashMap;

import com.grailsPortal.domain.*
class PortalConfigurationService {

    boolean transactional = true
    def String portalId
    PortalConfig pc
    HashMap <String, String>attributeMap
    
    def init(String portalName){
    	def pc=PortalConfig.findByName(portalName)
        def attrValues=PortalConfigAttrValue.findAllByPortalConfig(pc)
        attributeMap= Collections.synchronizedMap(new HashMap<String,String>(10))
        attrValues.each{
        	attributeMap.put(it.attribute.name,it.attrValue)
        }
    }
    
    def portalName() {
      if (attributeMap==null){
    	  initializePortal()
      }
      return pc.name
    }
   
    def portalDesc(){
    	return pc.description
    }

    def attrValue(attributeName){
    	def retVal=attributeMap.get(attributeName)
    	if (retVal==null){
    		return ""
    	}else{
    		return retVal
    	}
    }
    
}
