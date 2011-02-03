package com.grailsPortal.taglib;

import static org.junit.Assert.*;
import grails.converters.JSON;
import org.codehaus.groovy.grails.web.json.*;
import groovy.mock.interceptor.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import com.grailsPortal.taglib.PortalTagLib;
import grails.test.TagLibUnitTestCase;
import com.grailsPortal.domain.*
import com.grailsPortal.service.util.ContactUtilService;

class PortalTagLibTests extends TagLibUnitTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
	}

	def doContactType(){
		ContactType ct=new ContactType()
		ct.id=1
		ct.cd="HOME"
		ct.name="Home"
		ct.dsc="Home"
		ContactType ct1=new ContactType()
		ct1.id=2
		ct1.cd="WORK"
		ct1.name="Work"
		ct1.dsc="Work"
		mockDomain(ContactType,[ct,ct1])
		return [ct,ct1]
	}
	def doPartyType(){
		def pt=new PartyType()
		pt.id=1
		pt.name="Child"
		pt.cd="CHILD"
		pt.dsc="Child"
	    def pt1=new PartyType()
		pt1.id=2
		pt1.name="Parent"
		pt1.cd="PARENT"
		pt1.dsc="Parent"
		mockDomain(PartyType,[pt,pt1])
		return [pt,pt1]
	}
	def doParty(){
		Party party=new Party()
		party.id=1
		party.birthDate=new Date()
		party.firstName="testFirstName"
		party.lastName="testLastName"
		party.middleName="testMiddleName"
		party.prefix="testPrefix"
		party.suffix="testSuffix"
		party.partyType=doPartyType()[0]
		def instances=[party]
		mockDomain(Party,instances)
		return instances
	}
	def doPhones(party,contactType){
		ContactPhone phone = new ContactPhone();
		phone.id=1
		phone.active=true
		phone.areaCode="407"
		phone.contactType=contactType
		phone.internationalCode="1"
		phone.party=party
		phone.phoneNumber="1231234"
		mockDomain(ContactPhone,[phone])
		return [phone]
	}
	public void testFlowPhone() throws Exception {
		doParty()
		doContactType()
		PortalTagLib taglib=new PortalTagLib()
		def attrs=["id":"1",
			       "controller":"registration",
				   "editAction":"editPhone",
				   "createAction":"createPhone",
				   "contactPhoneId":""]
		def test=taglib.flowPhone(attrs)
		System.out.println(test)
	}
	def doMockSetup={service->
		def mock= new MockFor(ContactUtilService)
		mock.demand.getProfilePhoneContactTypes{party->
			return ContactType.list()
		}
		return mock
	}

	public void testEditPhone() throws Exception{
		def party=doParty()
		def contactTypes=doContactType()
		def phones=doPhones(party[0],contactTypes[0])
		
		def attrs=["id":"1",
			"phoneId":"1",
			"controller":"registration",
			"editAction":"editPhone",
			"createAction":"createPhone",
			"contactPhoneId":""]
		mockLogging(ContactUtilService)
		mockLogging(PortalTagLib)
		PortalTagLib.metaClass.getG= new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
		PortalTagLib.metaClass.link={parameters,label->
			def controller=	parameters.controller
			def action=	parameters.action
			def linkId=	parameters.id
			
			return """<a href='${controller}/${action}/id=${linkId}'>${label}</a>"""
		}
		PortalTagLib taglib=new PortalTagLib()
		def contactUtilService = new ContactUtilService()
		taglib.contactUtilService=contactUtilService
        def test=taglib.editPhone(attrs)
        System.out.println(test)
		assert true
	}
	
	public void testDoContactTypesInSelect(){
	  def contactTypes=doContactType()
	  def ptl=new PortalTagLib()
	  def retVal=ptl.doContactTypesInSelect( contactTypes[0] )	
	assert true
	}
	
	public void testDoShowParty(){
		def pt1=new PortalTagLib()
		pt1.doShowParty "TESTFirstName", "TESTLastName"
		assert true
	}
	
	public void testShowParty(){
		doParty()
		def pt1=new PortalTagLib()
		def paramValues=["id":"1"]
		pt1.showParty(paramValues)
		assert true
	}
	
	public void testPhone(){
		def party=doParty()
		def contactType=doContactType()
		doPhones(party[0], contactType[0])
		def pt1=new PortalTagLib()
		def paramValues=["id":"1","editAction":"edit","createAction":"create","controller":"controller"]
		pt1.phone(paramValues)
		assert true
	}
	
	public void testDoFormListField(){
		def pt= new PortalTagLib()
		def contactType=doContactType()
		def retVal=pt.doFormListField("label","labelField","fieldName","field","10",["1":"test"])
		assert true
	}
	
	public void testFormListField(){
		def pt=new PortalTagLib()
		def testJson= ["1":"Test1","2":"Test2","3":"Test3"] as JSON
		
		def attrs=["label":"label",
			      "labelField":"labelField",
				  "fieldName":"fieldName",
				  "instanceName":"instanceName",
				  "field":"field",
				  "fieldLength":"10","listOfValues":testJson.toString()]
		def retVal=pt.formListField(attrs)
		assert true
	}
	
	public void testDoFormInputField(){
		def pt=new PortalTagLib()
		def retVal=pt.doFormInputField("label","labelField","fieldName","field","2","Test")
	    assert true
	}	
	
	public void testFormInputField(){
		def pt=new PortalTagLib()
		def attrs=["label":"label",
			       "labelField":"labelField",
			       "fieldName":"fieldName",
				   "instanceName":"instanceName",
				   "field":"field",
				   "fieldLength":"2",
				   "fieldValue":"Test"]			
		def retVal=pt.formInputField(attrs)
		assert true
	}
	
	public void testDoLinkValue(){
		PortalTagLib.metaClass.getG= new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
		PortalTagLib.metaClass.link={parameters,label->
			def controller=	parameters.controller
			def action=	parameters.action
			def linkId=	parameters.id
			
			return """<a href='${controller}/${action}/id=${linkId}'>${label}</a>"""
		}
		PortalTagLib ptl=new PortalTagLib()
		def retVal=ptl.doLinkValue ("controller", "editAction", "linkId", "linkLabel")
		assert true
	}
	
	public void testLinkValue(){
		PortalTagLib.metaClass.getG= new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
		PortalTagLib.metaClass.link={parameters,label->
			def controller=	parameters.controller
			def action=	parameters.action
			def linkId=	parameters.id
			
			return """<a href='${controller}/${action}/id=${linkId}'>${label}</a>"""
		}
		PortalTagLib ptl=new PortalTagLib()
		def attrs=["controller":"controller","editAction":"editAction","linkId":"linkId","linkLabel":"linkLabel"]
		def retVal=ptl.linkValue(attrs)
		assert true
	}
	
	public void testDoDisplayValue(){
		PortalTagLib ptl=new PortalTagLib()
		def retValue=ptl.doDisplayValue("testValue","testLabel","testFieldName")
		assert true
	}
	
	public void testDisplayValue(){
		PortalTagLib ptl=new PortalTagLib()
		def attrs=["value":"testValue","label":"testLabel","fieldName":"testFieldName"]
		def retValue=ptl.displayValue(attrs)
		assert true
	}
	
	public void testDoFormCheckboxField(){
		PortalTagLib ptl=new PortalTagLib()
		def retVal= ptl.doFormCheckboxField ("testlabel",
			                                 "testlabelField",
											 "testfieldName",
											 "testfield", 
											 "t")
        assert true
	}
	
	public void testFormCheckboxField(){
		PortalTagLib ptl=new PortalTagLib()
		def attrs=["label":"testlabel",
			       "labelField":"testlabelField",
			       "fieldName":"testfieldName",
				   "field":"testfield",
				   "checked":"t"]
		def retVal= ptl.formCheckboxField (attrs)
		assert true

	}
}