package com.grailsPortal.service.config

import com.grailsPortal.domain.*;
import com.grailsPortal.ui.portalView.PortalViewHandler;
class PortalviewService {
    PortalViewHandler pvh=new PortalViewHandler()
    boolean transactional = false
    // isValidView will return true if all of the fields are valid on the view
    // this assumes that the attributes from the page are the same as the attributes
    // in the database.
    // This method will go through the list of attributes associated with the view and
    // the attributes for any attribute groups.
    // These are conditions by which it will fail
    // - An attribute is not populated in the view that is mandatory.
    // - An attribute is populated in the view, is blank, and is mandatory.
    // - An attribute is a selection field and the value does not match the list of values for
    //   that attribute
  //attribute is mandatory and value is null or empty
    def Boolean isValidView(viewName, attributes){
    	def view=View.findByName(viewName)
    	attributes.each{
    		def attribute=Attribute.findByName(it.key)
    		if (attribute!=null){
    		  def va=ViewAttribute.findByAttribute(attribute)
    		  if (va.mandatory==true && 
    		      (it.value==null || it.value=="")){
    			    return false
    		  }
    	}
    	return true
    }
  }
	/**
	 * Returns the value for a config setup value
	 * @param attribute
	 * @return
	 */
	def configAttrValue(attributeName){
		def attr=Attribute.findByName(attributeName)
		if (attr==null){
			return ""
		}
		def attrVal= PortalConfigAttrValue.find("from PortalConfigAttrValue pcav "+
		                                       " where pcav.portalConfig.id = 1 and " +
		                                     " pcav.attribute=?",attr)
		if (attrVal==null){
			return ""
		}
		return attrVal.attrValue
	}
	/**
	 * Process the header and create the header html from the attributes.
	 * @param attrs
	 * @param body
	 * @return
	 */
	def doHeader(attrs,body){    
		def t="\n<div id=\"casHeading\" style=\"height: "+
		configAttrValue("headerHeight")+
		"; width: "+
		configAttrValue("headerWidth")+";"+
		"background:"+
		configAttrValue("headerBrColor")+";"+
		configAttrValue("headerStyle")+
		";\">\n<br />\n<img alt=\""+
		configAttrValue("logoFileAlt")+
		"\" longdesc=\""+
		configAttrValue("logoLongDescription")+
		"\" src=\"../images/"+
		configAttrValue("logoImageFile")+"\""+
		" style=\"width : "+configAttrValue("logoImageWidth")+";"+
		" height : "+configAttrValue("logoImageHeight")+";"+
		configAttrValue("logoImageStyle")+"\" />\n"+
		"<div id=\"casTitle\" style=\"height: "+configAttrValue("titleHeight")+";"+
		configAttrValue("logoImageStyle")+"\">\n"+
		"\n<div style=\""+configAttrValue("titleTxt1Style")+"\">"+
		configAttrValue("titleTxt1")+"</div>\n"+
		"\n<div style=\""+configAttrValue("titleTxt2Style")+"\">"+
		configAttrValue("titleTxt2")+"</div>\n"
		return t+body+"</div></div>\n"
	}
/**
 * @TODO fix the buldEmailParams method
 */
    def buildEmailParams(params){
    	return params
    }
/**
* @TODO fix the buldPhoneParams method
* 
* 
*/
    def buildPhoneParams(params){
    	return params
    }
	/**
	 * @TODO fix the buldAddressParams method
	 * 
	 * 
	 */
    def buildAddressParams(params){
       return params	
    }
	/**
	 * @TODO fix the buldOtherParams method
	 * 
	 * 
	 */
    def buildOtherParams(params){
    	return parms
    }
	
	/**
	 * Returns all of the component Groups for a view in the order that they need to be in the form or report.
	 */
	def getViewAttributeGroupsForView(viewName){
		def lst= AttributeComponentGroup.findAll("from ViewAttributeComponentGroup as vacg," +
				" AttributeComponentGroup acg, View as v" +
				" where vacg.attributeComponentGroup=acg and " +
				" vacg.view= v and " +
				" v.name=? and " +
				" vacg.active=true and " +
				" acg.active=true " +
				" order by vacg.displayOrder, acg.displayOrder",viewName)
		return lst
	}
	
	def getComponentGroupAttributesByViewAndGroup(viewName,componentGroupName){
		def lst= AttributeComponent.findAll("from ViewAttributeComponentGroup as vacg," +
				" AttributeComponent as ac, " +
				" AttributeComponentGroup acg, View as v " +
				" where " +
				" vacg.attributeComponentGroup=acg and " +
				" vacg.view= v and " +
				" acg.attributeComponent=ac and " +
				" acg.name= ? " +
				" v.name=? and " +
				" vacg.active=true " +
				" and acg.active=true " +
				" order by vacg.displayOrder, acg.displayOrder",componentGroupName,viewName)
		return lst
	}
	/**
	 * Gets the registration attribute values associated
	 * with the viewName.  This method creates a hashTable
	 * that contains a hashtable that contains the viewName, the component Groups, and
	 * the values associated with the attributes.
	 * @param viewName The view to retrieve the information for
	 * @param eventId The identifier for the event that the information is associated with
	 * @return a list
	 */
	def getRegistrationAttributeValues(viewName,registrationEventId){
		def lst=this.getViewAttributeGroupsForView(viewName)
		def grpHash=[:]
		lst.each{
			def atrCompGrps=getComponentGroupAttributesByViewAndGroup(viewName,it.name)
			def attrHash=[:]
			atrCompGrps.each{
				attrHash.put it.name, ""
			}
			grpHash.put  it.name,attrHash
		}
		def retHash=[viewName:grpHash]
	}
	/**
	 * Gets the registration attribute values associated
	 * with the viewName and ComponentGroup.  This method creates a hashTable
	 * that the component attribute and
	 * @param viewName
	 * @param componentGroupName
	 * @param registrationEventId
	 * @return
	 */
	def getRegistrationAttributeValuesForComponent(viewName,componentGroupName,registrationEventId){
		def atrCompGrps=getComponentGroupAttributesByViewAndGroup(viewName,componentGroupName)
		def attrHash=[:]
		atrCompGrps.each{
				attrHash.put it.name, ""
		}
		return attrHash
	}
	/**
	 * Returns the representation associated with that view and Gomponent Group
	 * @param mode
	 * @param viewName
	 * @param componentGroupName
	 * @param attributeComponents
	 * @param attributeValues
	 * @return
	 */
	def renderComponentGroup(mode, viewName, componentGroupName, attributeComponents, attributeValues){
		return pvh.doPortalComponentGroup(mode, componentGroupName, attributeComponents, attributeValues)
	}
}
