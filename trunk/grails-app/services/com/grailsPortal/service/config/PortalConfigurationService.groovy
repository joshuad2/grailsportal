/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grailsPortal.service.config
import com.grailsPortal.domain.portalConfig.PortalConfig;
import com.grailsPortal.domain.portalConfig.PortalConfigAttrValue;

import java.util.HashMap;

import com.grailsPortal.domain.*
class PortalConfigurationService {
    static profiled=true
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
