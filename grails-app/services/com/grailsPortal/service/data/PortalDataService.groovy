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
package com.grailsPortal.service.data
import com.grailsPortal.domain.attribute.Attribute;
import com.grailsPortal.domain.attribute.AttributeComponentGroup;
import com.grailsPortal.domain.attribute.AttributeComponent;
import com.grailsPortal.domain.attribute.AttributeDataType;
import com.grailsPortal.domain.attribute.Component;
import com.grailsPortal.domain.attribute.AttributeLookupValue;
import com.grailsPortal.domain.attribute.AttributeType;
import com.grailsPortal.domain.portalConfig.PortalConfig;
import com.grailsPortal.domain.portalConfig.PortalConfigAttrValue;
import com.grailsPortal.domain.portalConfig.PortalConfigBusinessProcess;
import com.grailsPortal.domain.portalConfig.State;
import com.grailsPortal.domain.portalConfig.View;
import com.grailsPortal.domain.portalConfig.ViewAttributeComponentGroup;
import org.codehaus.groovy.grails.commons.GrailsClass;

import org.apache.shiro.crypto.hash.Sha256Hash
import org.codehaus.groovy.grails.commons.*
import grails.util.Environment
import org.codehaus.groovy.grails.commons.GrailsClass;
import com.grailsPortal.domain.*;
import com.grailsPortal.domain.menu.*
/**
 * PortalDataService handles the creation of the intial data into 
 * the database.  Most of the data is defined in String arrays and Hashtables
 * that are in the beginning of this class.
 * @author Joshua Davis
 *
 */
class PortalDataService {
    static profiled=true
    boolean transactional = true
    def portalConfigName="West Orlando Arts Foundation"
    def attributeNames=["headerText"]
    def headerTexts=["West Orlando Arts Foundation"]
    def curs=["Child","Parent","Purchaser","Admin"]
    def attrTypes=["PortalConfig","PortalFieldValue"]
    def orderRecordTypes=["Inventory","Workshop","Class Card","Registration"]
    def contactTypes=["Home","Work","Mobile"]
    def partyTypes=["Organization","User","Employee","Volunteer","Child","Parent","Contact"]
    def partyRoles=["Primary Parent","Secondary Parent","Emergency","Pickup","Registrant","Doctor","Other"]
    def paymentMethodTypes=["VISA","MC","AMEX","Discover","PayPal","Cash","Check"]
    def salesChannels=["On-Line"]
    def paymentTypes=["One-Time","Recurring","Monthly","Weekly","Deposit"]
    def productTypes=["Inventory","Classes"]
    def relationshipTypes=["Father","Mother","Sister","Brother","Grandmother","Grandfather","Guardian","Cousin","Friend Of Family"]     
    def responsibilities=["Parent","Child","Guardian"]
    def roles=["Entered","NonEntered"]
    def salesChannelTypes=["On-Line","Off-Line"]
    def products=["After School","2010 Week1",
		          "2010 Week2","2010 Week3","2010 Week3",
		          "2010 Week4","2010 Week 5"]
    def configAttrValues = ["headerWidth":"1000px",
                            "headerHeight":"150px",
							"headerStyle":"",
                            "headerBrColor":"#99CCFF",
                            "logoImageFile":"logo.png",
                            "logoImageWidth":"100px",
                            "logoImageHeight":"93px",
                            "logoImageStyle":"float:left",
                            "logoImageAlt":"West Orlando Arts Foundation",
                            "logoLongDesc":"West Orlando Arts Foundation",
                            "titleHeight":"125px",
                            "titleTxt1Style":"color: #0070C0;font-family:'Cooper Black';font-size:28pt",
                            "titleTxt2Style":"float: left",
                            "titleTxt1":"West Orlando Arts Foundation",
                            "titleTxt2":"Gotha, FL (555) 555-5555"
                            ]
    def attributeDataTypes=["String","Integer","Date"]
    def attributes=["registrantBirthDate",
					"registrantGrade",
	                "doctorAllergies"]
    def components=["textField","dateField",
	                "lookup","multiLookup",
	                "radioButton","checkBox",
	                 "displayField"]
    def views=["registrantView","parent1","parent2",
	           "emergencyContact1","emergencyContact2",
			   "pickupContact1","pickupContact2","doctorContact","review"]
    def portalConfigBusinessProcess=["Registration"]
	def attributeValues=["registrantGrade":["Kindergarten","First Grade","Second Grade",
	                                        "Third Grade","Fourth Grade","Fifth Grade",
	                                        "Sixth Grade","Seventh Grade","Eigth Grade"]
	                 ]
	def attributeComponentGroups=["registrantInfo":["registrantBirthDate":"dateField",
	                                                "registrantGrade":"lookup"],
	                                     "address":[],
										   "email":[],
										   "phone":[],
									       "partyViewInfo":[]
			                            ]
	def viewAttributeComponentGroups=[
	                                "registrantView":["registrantInfo","address"],
	                                "parent1":["partyViewInfo","address","email","phone"],									
									"parent2":["partyViewInfo","address","email","phone"],
									"emergencyContact1":["partyViewInfo","address","email","phone"],
									"emergencyContact2":["partyViewInfo","address","email","phone"],
									"pickupContact1":["partyViewInfo","address","email","phone"],
									"pickupContact2":["partyViewInfo","address","email","phone"],
									"doctorContact":["partyViewInfo","phone"]
			                        ]
	def states=["FL":"Florida","AL":"Alabama","GA":"Georgia","SC":"South Carolina","NC":"North Carolina","TX":"Texas","TN":"Tennessee","LA":"Louisiana","KY":"Kentucky"]
    def orderStatuses=["IA":"Inactive","IP":"In Process","PIF":"Paid In Full","PP":"Partial Payment","WT":"Waitlisted","LT":"Late Payment Due"]
	def menus=["Admin":["registrationEvent":"Manage Registrations",
	                    "user":"Manage",
						"campOps":"Operations"],
	           "Setup":["product":"Products",
			            "partyType":"Party Types",
			            "contactType":"Contact Types",
			            "paymentType":"Payment Types",
			            "relationshipType":"Relationship Types",
			            "responsibility":"Responsibilities",
			            "role":"Roles",
			            "salesChannel":"Sales Channels",
			            "salesChannelType":"Sales Channel Type",
			            "attributeAjax":"attributeTypeLink"
			            ],
	"Menu":["portalMenuType":"Menu Types",
	"portalMenuConfiguration":"Portal Menu Configuration",
	"portalMenu":"Portal Menus"
	],
"Config":["portalConfig":"Portal",
	"view":"Portal Views",
	"state":"States"
	]
	          ]
	JsecUser userResults
    JsecRole roleResults
    JsecRole userRoleResults
    Party partyResults
    Party adminParty
    JsecRole adminRole
    JsecRole results 
	
	
	def initializeMenu(portalConfig,menus){
		intializePortalMenuTypes()
	   def config=initializeMenuConfig(portalConfig)
	   def adminMenu=initializePortalMenu(config,menus)
	}
	def intializePortalMenuTypes(){
		PortalMenuType pmt=new PortalMenuType()
		pmt.menuTypeDesc="Admin Menu"
		pmt.menuTypeName="admin"
		pmt.save(flush:true)
		PortalMenuType pmt1=new PortalMenuType()
        pmt1.menuTypeDesc="User Menu"
        pmt1.menuTypeName="user"
        pmt1.save(flush:true)
	}
	/**
	 * Initialize the menu
	 * @return
	 */
	def initializeMenuConfig(portalConfig){
	   def menuConfig= new PortalMenuConfiguration()
	   menuConfig.hideDelay="750"
	   menuConfig.borderHeight="800px"
	   menuConfig.borderWidth="100px"
	   menuConfig.height="400px"
	   menuConfig.margin="10px"
	   menuConfig.width="100px"
	   menuConfig.position="static"
	   menuConfig.isActive="Y"
	   menuConfig.lazyLoad="N"
	   menuConfig.menuName="Main Menu"
	   menuConfig.portalConfig=portalConfig
	   menuConfig.sequence=1
       menuConfig.save(flush:true)
	   return menuConfig
	}
	def initializePortalMenu(menuConfig,menus){
	   int x=1
	   menus.each{
		PortalMenu pm1=new PortalMenu();
		pm1.configuration=menuConfig
		pm1.isActive="Y"
		pm1.sequence=x++
		pm1.itemLabel=it.key
		pm1.itemText=it.key
		if (it.key.startsWith("Admin")){
		  pm1.portalMenuType=PortalMenuType.findByMenuTypeName("admin")
		}else{
		  pm1.portalMenuType=PortalMenuType.findByMenuTypeName("user")
 	    }
		pm1.save(flush:true)
		def subMenus=it.value
		int i=1
		subMenus.each{
			def action="index"
			def psm=new PortalSubMenu()
			if (it.key.endsWith("Ajax")){
				action=it.key.substring(1,it.key.length()-4)
				psm.isAjax="Y"
			}else{
			  psm.isAjax="N"
		    }
			psm.sequence=i++
			psm.portalMenuType=PortalMenuType.findByMenuTypeName("user")
			psm.theAction=action
			psm.mainMenu=pm1
			psm.text=it.value
			psm.theController=it.key
			psm.showSpinner="N"
			psm.showAdmin="N"
			psm.showAnonymous="N"
			psm.validate()
			if (psm.hasErrors()){
				log.error("Couldn't add subMenu "+it.key+" "+it.value)
				log.error(psm.errors.allErrors)
			}
			psm.save(flush:true)
		}
	   }
	}
    def Party initializeAdmin(partyResults){
        if (partyResults==null){
          	 try{
          	    adminParty= new Party(firstName:"Admin",lastName:"Admin",birthDate:new Date())
               adminParty.save(flush:true)
			   adminParty.validate()
			   if (adminParty.hasErrors()){
				  log.error("Couldn't add admin account")
				  log.error(adminParty.errors.allErrors)
	           }
   	       	}catch(Exception e){
          		 log.error(e.printStackTrace());
          	 }
   	       	
          	if (adminParty==null){
          		log.error("Couldn't add Party")
          	}else{
          		log.error("Added Admin Party "+adminParty.id)
          	}
           }else{
          	 adminParty=partyResults
           }
        return adminParty
    }
    def void initializeUserRoles(userRoleResults){
        if (roleResults==null){
            adminRole = new JsecRole(name: "Administrator").save()
            adminRole.save(flush:true)
            log.error("Added admin role")
          }
          if (userRoleResults==null){
         	 def userRole = new JsecRole(name:"User").save()
         	 userRole.save(flush:true)
         	 log.error("Added user role")
          }
    }
    def void initializeUsers(userResults){
        if (userResults!=null){
          	 if(Environment.current==Environment.DEVELOPMENT) {
          	   userResults.passwordHash=new Sha256Hash("admin").toHex();
          	   userResults.save(flush:true)
                 if (userResults!=null){
              	 log.error("updated admin user for DEVELOPMENT ENVIRONMENT")
                   }
                 else{
              	 log.error("unable to update admin user")
                 }
          	 }
           }else{
               def adminUser = new JsecUser(username: "admin", 
			                                passwordHash: new Sha256Hash("admin").toHex(),
			                                party:adminParty,
					                        active:true
					                        ).save(flush:true)
               new JsecUserRoleRel(user: adminUser, role: adminRole).save(flush:true)
               if (adminUser!=null){
              	 log.error("added admin user")
                 }
               else{
              	 log.error("unable to Add admin user")
               }
             }
    }


    def void initializeAttributeDataTypes(dataTypes){
    	dataTypes.each{
    		def dt=new AttributeDataType()
    		dt.name=it
    		dt.dsc=it
    		dt.groovyType="java.lang.String"
    		dt.size=100
    		dt.htmlStartTag="<span>"
    		dt.htmlEndTag="</span>"
    		dt.isFromCMS=false
    		dt.cmsContentType="default"
			dt.validate()
			if (!dt.hasErrors()){
    	      dt.save(flush:true)
    	      log.error("initialized AttributeDataTytpe - "+it)
			}else{
				log.error("Couldn't save AttributeDataType -"+it)
			    log.error(dt.errors.allErrors)
			}
    	}
    }
    def void initializePortalConfigBusinessProcess(portalConfigBusinessProcess, portalConfig){
    	portalConfigBusinessProcess.each{
    		def pcbp=new PortalConfigBusinessProcess()
    		pcbp.portalConfig=portalConfig
    		pcbp.name=it
    		pcbp.purpose=it
			pcbp.validate()
			if (!pcbp.hasErrors()){
				pcbp.save(flush:true)
				log.error("initialized PortalConfigBusinessProcess - "+it)
			}else{
				log.error("Couldn't save PortalConfigBusinessProcess -"+it)
				log.error(pcbp.errors.allErrors)
			}
			
    	}
    	
    }
    def void initializeView(views,portalConfigBusinessProcess){
    	int i=1
    	views.each{
    		def view=new View()
    		view.name=it
    		view.viewName=it
    		view.actionName="index"
    	    view.dsc=it
    	    view.title=it
    	    view.portalConfigBusinessProcess=portalConfigBusinessProcess
    	    view.displayOrder=i++
    	    view.template="main"
			view.validate()
			if (!view.hasErrors()){
              view.save(flush:true)
              log.error("initialized view - "+it)
			}else{
				log.error("Could not initialize view - "+it)
			    log.error(view.errors.allErrors)
			}
    	}
    }
    def void initializeComponents(components){
    	components.each{
    		initializeValue(new Component(),"Component",it,it,it,Component.findByName(it))
    	}
    }
	def initializeValue(cls, className, name, cd, dsc,valResults){
		if (valResults==null){
			if (name!=null){
				cls.name=name
			}
			if (cd!=null){
				cls.cd=cd
			}
			if (dsc!=null){
				cls.dsc=dsc
			}
			cls.validate()
			cls.save(flush:true)
			 if (cls.hasErrors()){
				log.error("Couldn't add "+className+" - "+name)
			   log.error(cls.errors.allErrors)
			}else{
			  log.error("Added "+className+" - "+name)
			}
			return cls
		}
	}
	
	def void initializeAttributeTypes(attrTypes){
        attrTypes.each{
            initializeValue(new AttributeType(),
				            "AttributeType",it,it,it,
				            AttributeType.findByName(it))
          }
   }
    
   def void initializeContactTypes(contactTypes){
       contactTypes.each{
    	    initializeValue(new ContactType(),"ContactType",it,it,it,ContactType.findByName(it))
            }
   }
   
   def void initializeRelationshipTypes(relationshipTypes){
       relationshipTypes.each{
          	initializeValue(new RelationshipType(),"RelationshipType",it,it,it,RelationshipType.findByName(it))
       }
   }
  
   def void initializeProductTypes(productTypes){
       productTypes.each{
    	   initializeValue(new ProductType(),"ProductType", it, it, it,ProductType.findByName(it) )
          }
   }
   def void initializePaymentTypes(paymentTypes){
       paymentTypes.each{
          	initializeValue(new PaymentType(),"Payment Type", it, it, it,PaymentType.findByName(it))
          }
   }
   def void initializeSalesChannelTypes(salesChannelTypes){
       salesChannelTypes.each{
    	   initializeValue(new SalesChannelType(),"Sales Channel Type",it,it,it,SalesChannelType.findByName(it))
          }
   }
   
   def void initializeSalesChannels(SalesChannels,SalesChannelType channelType){
     salesChannels.each{
		 def qry=SalesChannel.findByName(it)
	   	 SalesChannel salesChannel
		 if (qry!=null){
			 salesChannel=qry
		 }else{
		    salesChannel=new SalesChannel()
		 }
		 salesChannel.dsc=it
		 salesChannel.name=it
		 salesChannel.cd=it
		 salesChannel.salesChannelType=channelType
		 
	   	 salesChannel.save(flush:true)
     }
   }
   def void initializeStates(states){
		states.each{
			initializeValue(new State(),"States",it.value,it.key,it.value,State.findByName(it.value))
		}
	}
	def void initializeOrderStatuses(orderStatuses){
		orderStatuses.each{
			def os=initializeValue(new OrderStatus(),"OrderStatus",it.value,it.key,it.value,OrderStatus.findByName(it.value))
			os.active=true
			os.save(flush:true)
		}
	}
   def void initializeProducts( products, productType,salesChannel){
       products.each{
          	def prod=Product.findByName(it)
          	if (prod==null){
          		def p=new Product()
				p.cd=it
				p.name=it
				p.netCostAmount=175.00
			    p.netSalesAmount=175.00
				p.displayGroup="Painting Camp Ages 10-14"
				p.ecommerceCode="2011Camp"
				p.salesChannel=salesChannel
		        p.productType=productType
				p.save(flush:true)
				 if (p.hasErrors()){
									  log.error("Couldn't add Product "+it)
									  log.error(p.errors.allErrors)
								  }
                  else{
          		  log.error("Added Product - "+it)
          		}
          	}
          }
   }
   def void initializeResponsibilities(responsibilities){
       responsibilities.each{
          		initializeValue(new Responsibility(),"Responsibility",
          				        it,it,it,Responsibility.findByName(it))
          }
   }
   def void initializePartyTypes(partyTypes){
       partyTypes.each{
          		initializeValue(new PartyType(),"Party Type",
          				        it,it,it,PartyType.findByName(it))
          }
   }
   def void initializePartyRoles(partyRoles){
       partyRoles.each{
          		initializeValue(new PartyRole(),"Party Role",
          				        it,it,it,PartyRole.findByName(it))
          }
   }
   def void initializePaymentMethodTypes(paymentMethodTypes){
       paymentMethodTypes.each{
          		initializeValue(new PaymentMethodType(),"Payment Method Type",
          				        it,it,it,PaymentMethodType.findByName(it))
          }
   }
   def void initializeOrderRecordTypes(orderRecordTypes){
       orderRecordTypes.each{
          		initializeValue(new OrderRecordType(),"Order Record Type",
          				        it,it,it,OrderRecordType.findByName(it))
          }
   }
   def PortalConfig initializePortalConfig(){
	   PortalConfig pc=new PortalConfig()
	   pc.name=portalConfigName
	   pc.description="West Orlando Arts Foundation"
	   pc.validate()
	   if (!pc.hasErrors()){
	     pc.save(flush:true)
	     log.error("Added Portal Config")
	   }
	else{
		log.error("Could not add Portal Config")
		log.error(pc.errors.allErrors)
	   }
	   return pc
   }
   def initializePortalConfigAttributes(atrType){
	   configAttrValues.each{
		 Attribute attr=new Attribute()
		 attr.name=it.key
		 attr.dsc=it.key
		 attr.cd=it.key
		 attr.attributeType=atrType
		 attr.validate()
		 if (!attr.hasErrors()){
		   log.error("Added Portal config attribute - "+it.key)
		   attr.save(flush:true)
		 }
		else{
			log.error("Couldn't add config attribute -"+it.key)
			log.error(attr.errors.allErrors)
		}
	    }
   }
  def initializeAttributeValues(attrValues){
     attrValues.each{
	 Integer i=0
     def attribute=Attribute.findByCd(it.key)
	 it.value.each{
	    def aValue=new AttributeLookupValue()
	    aValue.attribute=attribute
	    aValue.displayOrder=i++
	    aValue.lookupValue=it
		aValue.active=true
		aValue.validate()
		if (!aValue.hasErrors()){
			aValue.save(flush:true)
			log.error("Added attribute value "+it)
		}else{
			log.error("Couldn't add attributeValue - "+it)
		    log.error(aValue.errors.allErrors)
		 }
		}
	}
}
   def initializeAttributes(attributes,atrType,attributeDataType){
	   attributes.each{
		 Attribute attr=new Attribute()
		 attr.name=it
		 attr.dsc=it
		 attr.cd=it
		 attr.attributeType=atrType
		 attr.attributeDataType=attributeDataType
		 attr.validate()
		 if (!attr.hasErrors()){
			log.error("Added attribute - "+it)
			attr.save(flush:true)
		}else{
		  log.error("Could not Add attribute - "+it)
		  log.error(attr.errors.allErrors)
		}
	     }
   }
   def initializePortalConfigValues(pc){
	   configAttrValues.each{
		   def pcav= new PortalConfigAttrValue();
		   pcav.attrValue=it.value
		   pcav.portalConfig=pc
		   def attr=Attribute.findByName(it.key)
		   pcav.attribute=attr
		   pcav.validate()
		   if (!pcav.hasErrors()){
			 log.error("Added Portal Config Attribute Value -"+it)
			 pcav.save(flush:true)
		   }else{
			  log.error("Could not add Portal Config Attribute Value - "+it)
		      log.error(pcav.errors.allErrors)
		    }

	   }
   }
   def initializeAttributeComponents(atrList, acg){
	    Integer i=0
		atrList.each{
		  AttributeComponent ac=new AttributeComponent()
		  ac.name=it.key
          ac.component=Component.findByName(it.value)
		  ac.attribute=Attribute.findByName(it.key)
		  ac.labelText=it.key
		  ac.attributeComponentGroup=acg
		  ac.displayOrder=i++
		  ac.active=true
		  ac.cssClass=it.key
		  ac.cssId=it.key
		  ac.cssStyle=it.key
		  ac.labelCssStyle=it.key
		  ac.labelCssClass=it.key
		  ac.labelText=it.value
		  ac.validate()
		  if (!ac.hasErrors()){
			ac.save(flush:true)
			log.error("Added AttributeComponent - "+it.key)
		  }else{
            log.error("Could not add AttributeComponent - "+it.key)	
		    log.error(ac.errors.allErrors)
			}
	    }	
   }
   def initializeAttributeComponentGroups(acg){
	acg.each{
		def atrCg= new AttributeComponentGroup()
		atrCg.name=it.key
		atrCg.dsc=it.key
		atrCg.cd=it.key
		atrCg.active=true
		atrCg.mandatory=true
		atrCg.validate()
		if (!atrCg.hasErrors()){
			atrCg.save(flush:true)
			initializeAttributeComponents(it.value,atrCg)
			log.error("saved "+it.key+" attributeComponentGroup")
		}else{
		  log.error("Could not save "+it.key+" attributeComponentGroup")
		  log.error(atrCg.errors.allErrors)
		}
	}
   }
  def initializeViewAttributeComponentGroups(vacg){
	vacg.each{
		def viewName=it.key
		Integer displayVal=0
		View vw=View.findByName(viewName)
		it.value.each{
		    ViewAttributeComponentGroup vwacg= new ViewAttributeComponentGroup()
	  	    vwacg.name=viewName
		    vwacg.active=true
			vwacg.displayOrder=displayVal
		    vwacg.mandatory=true
			def attrcg=AttributeComponentGroup.findByNameAndActive(it,true)
			vwacg.attributeComponentGroup=attrcg
			vwacg.view=vw
			vwacg.validate()
			if (!vwacg.hasErrors()){
				vwacg.save(flush:true)
				log.error("saved viewAttributeComponentGroup:"+viewName+":"+attrcg.toString())
			}else{
				log.error("could not save viewAttributeComponentGroup "+viewName+":"+attrcg.toString)
			    log.error(vwacg.errors.allErrors)
			}
	       }
        }
    }
    def void initializePortal() {
	  def config = ConfigurationHolder.config
	  if (config.portal.load.defaultData==true){
    	results =     JsecRole.findByName("Administrator")
        userResults=  JsecUser.findByUsername("admin")
        roleResults=  JsecRole.findByName("Administrator")
        userRoleResults=JsecRole.findByName("User")
        partyResults=Party.findByFirstName("Admin")
        adminParty=partyResults
        adminParty=initializeAdmin(partyResults)
        initializeUserRoles(userRoleResults)        
        initializeUsers(userResults)
        initializeAttributeTypes(attrTypes)
        initializePortalConfigAttributes(AttributeType.findByName("PortalConfig"))
        initializeContactTypes(contactTypes)
        initializeRelationshipTypes(relationshipTypes)
        initializeProductTypes(productTypes)
        initializePaymentTypes(paymentTypes)
        initializeSalesChannelTypes(salesChannelTypes)
        def onLine=SalesChannelType.findByName("On-Line")
        initializeSalesChannels(salesChannels, onLine)
		def onLineSc=SalesChannel.findByName("On-Line")
        def classesProductType=ProductType.findByName("Classes")
        initializeProducts(products,classesProductType,onLineSc)
        initializeResponsibilities(responsibilities)
        initializePartyTypes(partyTypes)
        initializePartyRoles(partyRoles)
        initializePaymentMethodTypes(paymentMethodTypes)
        initializeOrderRecordTypes(orderRecordTypes)
        def pc=initializePortalConfig()
        initializePortalConfigBusinessProcess(portalConfigBusinessProcess,pc)
        initializeView(views,PortalConfigBusinessProcess.findByName("Registration"))
        initializeComponents(components)
        initializeAttributeDataTypes(attributeDataTypes)
        initializeAttributes(attributes,AttributeType.findByName("PortalFieldValue"),
			                 AttributeDataType.findByName("String"))
        initializePortalConfigValues(pc)
        initializeAttributeComponentGroups(attributeComponentGroups)
		initializeAttributeValues(attributeValues)
        initializeViewAttributeComponentGroups(viewAttributeComponentGroups)
		initializeOrderStatuses(orderStatuses)
		initializeStates(states)
        initializeMenu(pc,menus)
	  }
    }
}
