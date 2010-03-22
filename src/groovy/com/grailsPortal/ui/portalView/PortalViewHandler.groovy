package com.grailsPortal.ui.portalView;
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

import com.grailsPortal.domain.*
/**
 * Portal View Handler Class is a handler for the tag library in Portalvew.  All
 * methods in this class must be stateless.  The policy established for these
 * classes expect that there are no transactions, therefore no metods should alter/query
 * values in any of the domain classes that they reference.  Most likely no values will be
 * committed back to the database if they are changed in an instance of this class.
 * Any database value changes should be done via a method in a service class.
 */
public class PortalViewHandler implements Serializable{
	

	
	def doStyle={cssRenderStyle->
		return htmlAttribute(cssRenderStyle,"style")
	}
	def doClass={cssRenderClass->
	    return htmlAttribute(cssRenderClass,"class")	
	}
	def doId={cssRenderId->
	    return htmlAttribute(cssRenderId,"id")	
	}
	def htmlAttribute={variable,text->
		def t=""
		if (variable!=null){
			t+=text+"=\""+cssRenderStyle+"\" "
		}
		return t
	}
	/**
	 * Render the lookup component
	 * @param fieldName
	 * @param cssId
	 * @param cssRenderStyle
	 * @return
	 */
	def renderLookupComponent(fieldName, cssId,cssRenderStyle,cssClass,hashValues,selectedValue){
		def t="<div "+doId(cssId)+doClass(cssClass)+doStyle(cssRenderStyle)+">"
        t+="<select name=\""+fieldName+"\">"
		hashValues.each{
			if (selectedValue==it.value){
			  t+="<option selected=\"selected\" value=\""+it.key+"\">"+it.value+"</option>"
			}else{
			  t+="<option value=\""+it.key+"\">"+it.value+"</option>"
			}
		}
		t+="</select>"
		return t+"</div>"
	}

	/**
	 * Render the date field
	 * @param fieldName
	 * @param cssId
	 * @param cssRenderStyle
	 * @return
	 */
	def renderDateFieldComponent(fieldName, cssId, cssRenderStyle, cssClass, isError, day, month, year){
		return null
	}
	/**
	 * 
	 * @param fieldName
	 * @param cssId
	 * @param cssRenderStyle
	 * @param cssClass
	 * @return
	 */
	def renderMultiLookupComponent(fieldName, cssId, cssRenderStyle, cssClass, isError){
		return null
	}
	/**
	 * 
	 * @param fieldName
	 * @param cssId
	 * @param cssRenderStyle
	 * @param cssClass
	 * @return
	 */
	def renderRadioButtonComponent(fieldName, cssId, cssRenderStyle, cssClass, isError){
		return null
	}
	/**
	 * 
	 * @param fieldName
	 * @param cssId
	 * @param cssRenderStyle
	 * @param cssClass
	 * @return
	 */
	def renderCheckBoxComponent(fieldName, cssId, cssRenderStyle, cssClass, isError){
		return null
	}
	/**
	 * 
	 * @param fieldName
	 * @param cssId
	 * @param cssRenderStyle
	 * @return
	 */
	def renderDisplayFieldComponent(fieldName, cssId, cssRenderStyle, cssClass){
		return null
	}
	/**
	 * Render the text component
	 * @param fieldName
	 * @param cssId
	 * @param cssRenderStyle
	 * @return
	 */
	def renderTextFieldComponent(fieldName, cssId, cssRenderStyle,
	                             cssClass,value,length, isError,labelText){
		def t="<td valign=\"top\" class=\"name\"><label for=\"cd\">"+labelText+"</label></td>"
		t+="<td "+doId(cssId)+doClass(cssClass)+doStyle(cssRenderStyle)+">"
		t+="<input name=\""+fieldName+"\" id=\""+fieldName+"\" value=\""+value+"\">"
		return t+="</td>"
	}
	/**
	 * Display the Edit mode attributes
	 * @param attributeComponents
	 * @param componentGroupName
	 * @param styles
	 * @param value
	 * @return
	 */
	def displayEditAttributes(attributeComponents,componentGroupName,componentGroupValues){
	   def t="\n<TABLE id=\""+componentGroupName+"\">"
	   attributeComponents.each{
		 AttributeComponent ac=it
		
         t+"\n<TR id=\""+componentGroupName+ac.name+"\">"
		 if (it.component.name=="textField"){ 
			t+=renderTextFieldComponent(ac.getName(),ac.getCssId(), ac.getCssStyle(), 
			                            ac.dssClass,componentGroupValues[ac.name],
										ac.getAttribute().getMaximumWidth(),false,
			                            ac.getLabelText())
		 }else
	     if (it.compnent.name=="dateField"){
			
		}else
	    if (it.component.name=="lookup"){
			t+=renderLookupComponent()
		}else
		if (it.component.name=="multiLookup"){
			
		}else
		if (it.component.name=="radioButton"){
			
		}else
	    if (it.component.name=="checkBox"){
			
		}else
	    if (it.component.name=="displayField"){
			
		}
	     t+"</TR>"
		
		}
		return t+"</TABLE>"
	}
	/**
	 * 
	 * @param attrs
	 * @param componentGroupName
	 * @return
	 */
	def displayReadAttributres(attrs,componentGroupName){
	  return null	
	}
	/**
	 * 
	 * @param attrs
	 * @param componentGroupName
	 * @return
	 */
	def displayAddAttribures(attrs,componentGroupName){
	  return null
	}
	/**
	 * 
	 * @param attrs
	 * @param componentGroupName
	 * @param attributeComponents
	 * @return
	 */
	def doPortalComponentGroup(mode,componentGroupName,attributeComponents,componentGroupValues){
	  if (mode==null || attributeComponents==null){
			return null
		}
	  if (mode=="edit"){
			return displayEditAttributes(attributeComponents,componentGroupName,componentGroupValues)
		}
	  
	}

}

