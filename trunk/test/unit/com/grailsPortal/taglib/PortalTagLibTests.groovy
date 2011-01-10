package com.grailsPortal.taglib;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import com.grailsPortal.taglib.PortalTagLib;
import grails.test.TagLibUnitTestCase;
import com.grailsPortal.domain.*
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
}
