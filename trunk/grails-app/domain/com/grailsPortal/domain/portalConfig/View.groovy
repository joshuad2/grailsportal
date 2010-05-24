package com.grailsPortal.domain.portalConfig

class View implements Serializable{

	String name
	String viewName
	String actionName
	String dsc
	String title
	String template
	String rawHtmlHeader
	String rawHtmlStartBody
	String rawHtmlEndBody
	Integer displayOrder
	SortedSet viewAttributes
	PortalConfigBusinessProcess portalConfigBusinessProcess
	static hasMany=[viewAttributes:ViewAttribute,
	                viewAttributeComponentGroups:ViewAttributeComponentGroup]
    static constraints = {
       name(size:1..100,nullable:false,blank:false)
       viewName(size:1..200,nullable:false,blank:false)
       actionName(size:1..100,nullable:true,blank:true)      
       title(size:1..100, nullable:false,blank:false)
       portalConfigBusinessProcess(nullable:false,blank:false)
       displayOrder(range:1..200,nullable:false,blank:false)
       template(size:1..100, nullable:true,blank:true)
	   dsc(size:1..200,nullable:true,blank:true)
       rawHtmlHeader(size:1..400,nullable:true,blank:true)
       rawHtmlStartBody(size:1..200,nullable:true,blank:true)
       rawHtmlEndBody(size:1..200,nullable:true,blank:true)
       viewAttributes(nullable:true,blank:true)
       viewAttributeComponentGroups(nullable:true,blank:true)
    }
	String toString() {
        return name 
    }
}
