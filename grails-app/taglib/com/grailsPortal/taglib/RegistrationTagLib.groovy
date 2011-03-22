package com.grailsPortal.taglib

import com.grailsPortal.taglib.PortalTagLib
import com.grailsPortal.domain.Party
import com.grailsPortal.domain.RegistrationEvent
import com.grailsPortal.domain.RegistrationEventOrderRecord
import com.grailsPortal.domain.RegistrationEventParty
import com.grailsPortal.domain.ProductType
import com.grailsPortal.domain.Product
import com.grailsPortal.domain.OrderRecord
import com.grailsPortal.domain.OrderRecordLineItem
import com.grailsPortal.domain.SalesChannel

class RegistrationTagLib {
	
	static namespace = 'registration'
def doInputRegistrationEventParty(registrationEventId,controller,createAction,value,inputLabel,partyType,needsDOB='false'){
	   def errorVal=""
	   if (value==null){
		   value=new Party()
	   }else{
		 value?.validate()
		 if (value.hasErrors()){
			errorVal+= g.message(code:"Party.input.error",args:['',''])
		 }
	   }
	   def firstNameError=g.hasErrors([bean:value,field:"firstName"],'errors')
	   def lastNameError=g.hasErrors([bean:value,field:"lastName"],'errors')
	   def firstNameInput=doFormInputField("First Name","firstName","firstName","firstName","100",value.firstName,firstNameError)
	   def lastNameInput=doFormInputField("Last Name","lastName","lastName","lastName","100",value.lastName,lastNameError)
	   def birthDateInput=g.datePicker(["name":"birthDate","value":"${value.birthDate}","precision":"day","years":"${1996..2007}"])
	   
	   def url=["action":createAction,"controller":controller]
	   def startRow=" <tr><td>"
	   def endRow="</td></tr>"
	   def label="${startRow}${inputLabel}${endRow}"
	   def str=submitToRemote(["url":url,"update":"phoneSection","value":"Submit Phone"])
	   def dialog="""
				   <div class="dialog">
					  <table>
						<tbody>
						  ${firstNameInput}${lastNameInput}
						  <TR><TD>${birthDateInput}</TD></TR>
						  <TR><TD><input type="hidden" name="registrationEvent" value="${registrationEventId}"/></TD></TR>
						  <TR><TD>${str}</TD></TR>
						</tbody>
					  </table>
				  </div>
				  """
	   def attrs=["action":createAction,"controller":controller]
	   def inputForm="${g.form(attrs,dialog)}"
	   return errorVal+label+startRow+inputForm+endRow
   }
def doDisplayRegistrationEventParty(firstName,lastName,birthDate,controller,editAction,partyId,deleteAction="deleteRemote"){
	PortalTagLib ptl=new PortalTagLib()
    def firstNameDisplay=ptl.doDisplayValue(firstName,"Name:","firstName")
    def lastNameDisplay=ptl.doDisplayValue(lastName,"","lastName")
    def birthDateDisplay=ptl.doDisplayValue(birthDate,"Birth Date:","birthDate")
	def lvValue=ptl.doRemoteLinkValue(controller,editAction,partyId,"Edit","inputEmail")
	def dlValue=ptl.doRemoteLinkValue(controller,deleteAction,partyId,"Delete","emailSection")
	return "${firstNameDisplay}${lastNameDisplay}${lvValue}${dlValue}"
	}
def handleOptionalDisplayValue={bl,value,text,fieldName,portalTagLib->
	if (bl){
		return portalTagLib.doDisplayValue(value,text,fieldName)
	}else{
	return ""
	}
}
def handleOptionalRemoteLink={bl,controller, editAction, linkId, linkLabel,update,portalTagLib,useRow->
	if (bl){
		return portalTagLib.doRemoteLinkValue(controller, editAction, linkId, linkLabel,update,useRow)
	}else{
	return ""
	}
}

def doHandleProduct(controller,productId,lineItemId,name,nameText,description,descriptionText,saleAmount,update="productSection",selectAction="selectProduct",showSelect=false,removeAction="removeProduct", showRemove=false,moreInfoAction="moreInfo", showMoreInfo=false,showCost=true){
	PortalTagLib ptl=new PortalTagLib()
	def selectLinkDisplay
	if (showSelect){
	  selectLinkDisplay=handleOptionalRemoteLink(showSelect,controller,selectAction,productId,"${name} - ${saleAmount}",update,ptl,false)
	}else{
	  selectLinkDisplay=ptl.doDisplayValue ("${name} - ${saleAmount}", "", "selectedProduct",false)
	}

	def removeLinkDisplay=""
	if (showRemove){
	  removeLinkDisplay=handleOptionalRemoteLink(showRemove,controller,removeAction,productId,"remove",update,ptl,false)
	}
	
	def moreInfoLinkDisplay=""
	if (showMoreInfo){
	 moreInfoLinkDisplay=handleOptionalRemoteLink(showMoreInfo,"moreInfoProduct",moreInfoAction,"product","More Info",update,ptl,false)
	}
	return "<tr class=\"prop\">${selectLinkDisplay} ${removeLinkDisplay} ${moreInfoLinkDisplay}</tr>"
}

def doShowProductsNotSelected(controller,regEventId,ecommerceCode,productTypeName,salesChannelName){
    def t=""
	def re=RegistrationEvent.get(regEventId)
	def query="from OrderRecordLineItem as oli,"+ 
	          "  OrderRecord as orec, "+
	          "  RegistrationEventOrderRecord as reor "+
	          "   where oli.orderRecord=orec and "+
	          "     orec=reor.order and "+
	          "     reor.event=? and "+
	          "oli.product=?"
	           
	def pt=ProductType.findByName(productTypeName)
	def sc=SalesChannel.findByName(salesChannelName)
	def products=Product.findAll("from Product where productType=? and salesChannel=?",[pt,sc])
	// this next section tries to eliminate all of the already chosen products
	// becuase in a registration it does not make sense to sign up for the same
	// class twice for the same person
    products.each{
		Product p=it
		def lineItem=OrderRecordLineItem.findAll(query,[re,p])
		if (lineItem.size()==0){//@TODO MAY NEED TO BE FIXED
			t+=doHandleProduct(controller,
				               p.id,
							   "",
							   p.name,
							   "",
							   p.dsc,
							   "",
							   p.getNetSalesAmount(),
						       "productSection",
							   "selectProduct",
							   true,
							   "",
							   false,
							   "",
							   false,
							   true)
							   
		}
	}
	return t
}
def doProduct(isAjax,regEventId,ecommerceCode,productTypeName="Classes",salesChannelName="On-Line",controller="product"){
    RegistrationEvent regEvent=RegistrationEvent.get(regEventId)
	
	    if (regEvent?.registrationFor==null){
		return ""
	}
	def t=""
	 if (!isAjax){
		 t="<TABLE id='productSection' >"
	 }
	 t+="<tr><td style=\"border-style:solid\">Available Camp Sessions</td><td style=\"border-style:solid\">Camp Sessions Selected</td></tr>"
	 t+="<tr><td div style=\"width: 200px; height:100px; overflow:'auto'\"><TABLE id='selectProduct'>"
	 t+=doShowProductsNotSelected(controller,regEventId,ecommerceCode,productTypeName,salesChannelName)
	 t+="</TABLE></TD>"
	 t+="<td><div style=\"width: 250px; height:100px; overflow:'auto'\"><TABLE id='productList' >"
     regEvent.orders.each{
		 def reorList=it
	   reorList.each {
	     RegistrationEventOrderRecord reor=it
	     OrderRecord or=reor.order
	     if (or.orderStatus.name!="deleted"){
	       or.lineItems.each {
		      OrderRecordLineItem oli=it
			  def p=oli.getProduct()
			  t+=doHandleProduct(controller,
				  p.id,
				  oli.id,
				  p.name,
				  "",
				  p.dsc,
				  "",
				  p.getNetSalesAmount(),
				  "productSection",
				  "selectProduct",
				  false,
				  "remove",
				  true,
				  "",
				  false,
				  true)
		    }
	   }
	   }
	}
	 t+="</TABLE></div>"
	 t+="</td></tr>"
   if (!isAjax){
	   t+="</TABLE>"
   }
   return  t
}
def product={attrs->
	def usingAjax=attrs?.isAjax
	def regEventId=attrs.regEventId
	def eCommerceCode=attrs.eCommerceCode
	def productType=attrs?.productType
	def salesChannel=attrs?.salesChannel
	def controller=attrs?.controller
	
	def isAjax=false
	if (usingAjax!=null){
		isAjax=true
	}
	if (productType==null){
		productType="Classes"
	}
	if (salesChannel==null){
		salesChannel="On-Line"
	}
	if (controller==null){
		controller="product"
	}
	out << doProduct(isAjax,regEventId,eCommerceCode,productType,salesChannel,controller)
 }
}