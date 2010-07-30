package com.grailsPortal.taglib

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
import org.codehaus.groovy.grails.plugins.web.taglib.*;

class MenuTagLib {
	static namespace = 'menu'
    def adminMenu={attrs->
	   /*
	    * 					<menu:mainItem itemLabel="administration" text="Admin">
						<menu:subItem controller="registrationEvent" action="index"
							text="Manage Registrations" />
						<menu:subItem controller="user" action="index" action="index"
							text="Manage Users" />
						<menu:subItem controller="campOps" action="index"
							text="Camp Operations" />
					</menu:mainItem>
					<menu:mainItem itemLabel="setup" text="Setup">
						<menu:subItem controller="product" action="index" text="Products" />
						<menu:subItem controller="contactType" action="index"
							text="Contact Types" />
						<menu:subItem controller="partyType" action="index"
							text="Party Types" />
						<menu:subItem controller="contactType" action="index"
							text="Contact Types" />
						<menu:subItem controller="paymentType" action="index"
							text="Payment Types" />
						<menu:subItem controller="relationshipType" action="index"
							text="Relationship Types" />
						<menu:subItem controller="responsibility" action="index"
							text="Responsibilities" />
						<menu:subItem controller="role" action="index" text="Roles" />
						<menu:subItem controller="salesChannel" action="index"
							text="Sales Channels" />
						<menu:subItem controller="salesChannelType" action="index"
							text="Sales Channel Type" />
						<menu:simpleSubItem id="attributeTypeLink">
							<g:remoteLink controller="attributeType" action="remoteList"
								update="attributeTypes"
								onLoading="showSpinner('attributeTypes');"
								onLoaded="showRegular('attributeTypes');">Attribute Types</g:remoteLink>
						</menu:simpleSubItem>
					</menu:mainItem>
				</shiro:hasAllRoles>	
	    */		            
	}
	def menuSetup={attrs->
	 def menuName=attrs["name"]
	 def position=attrs["position"]
	 def hideDelay=attrs["hideDelay"]
	 def lazyLoad=attrs["lazyload"]
	 if (hideDelay==null || hideDelay==""){
		 hideDelay="750"
	 }
	 if (position==null || position==""){
		 position="static"
	 }
	 if (menuName==null || menuName==""){
		 menuName=new Date().fastTime
	 }
	 if (lazyLoad==null || lazyLoad==""){
		 lazyLoad="true"
	 }
     def t='<script type="text/javascript">'+
           'YAHOO.util.Event.onContentReady("basicmenu",function ()'+
    	   ' {var oMenu = new YAHOO.widget.Menu("basicmenu",{'+  
                                         ' position: "'+position+'",'+  
                                         ' hidedelay: '+ hideDelay+','+  
                                         ' lazyload: '+lazyLoad+' });'+
                                         ' oMenu.render();'+
                                         ' oMenu.show();'+
                                         '});'+
            '</script>'
     out << t
	} 
	def menuBody={attrs, body ->
	  	def style=attrs["style"]
		def id=attrs["id"]
		def t="<body class=\"yui-skin-sam\" "
		if (style!=null){
			t+=" style=\""+style+"\""
		}
		if (id!=null){
			t+=" id=\""+id+"\""
		}
		out << t+">"+body()+"</body>"
	}
	def menuContainer={attrs, body ->
		def style=attrs["style"]
		if (style==null){
			style=""
		}
		def width=attrs["width"]
		if (width==null){
			width="140px"
		}
		def height=attrs["height"]
		if (height==null){
			height="400px"
		}
		def margin=attrs["margin"]
		if (margin==null){
			margin="17px"
		}
		def borderStyle=attrs["borderStyle"]
		if (borderStyle==null){
			borderStyle=""
		}
		def borderWidth=attrs["borderWidth"]
		if (borderWidth==null){
			borderWidth="135px"
		}
		def borderHeight=["borderHeight"]
		if (borderHeight==null){
			borderHeight="400px"
		}
		def t="<div id=\"menuContainer\" style=\"width: "+width+";height: "+height+"; margin:"+margin+";"+style+"\">\n"+
		      "<div id=\"basicmenu\" class=\"yuimenu\">\n"+
		      "<div class=\"bd\" style=\"width: "+borderWidth+"; height: "+height+";"+borderStyle+"\">\n"
		def u="</div>\n</div>\n</div>\n"
		out << t+body()+u
	}
	
	def mainItem={attrs, body ->
	   def itemLabel=attrs["itemLabel"]
	   def text=attrs["text"]
	   def t="<li class=\"yuimenuitem\">\n"+
	         "<a class=\"yuimenuitemlabel\" href=\"#"+itemLabel+"\">"+text+"</a>\n"+
			"<div id=\""+itemLabel+"\" class=\"yuimenu\">\n"+
		     "<div class=\"bd\" style=\"width: 150px\">\n"+
		     "<ul>\n"
		def u="</ul>\n"+
		      "</div>\n"+
			"</div>\n"+
		       "</li>\n"
	    out << t+body()+u
	}
	def subItem={attrs,body ->
		ApplicationTagLib lib=new ApplicationTagLib()
		def text=attrs["text"]
		def id=attrs["id"]
		if (id=="" || id==null){
			id=java.util.UUID.toString();
		}
		def t="<li class=\"yuimenuitem\" id=\""+id+"\">\n"+
		      "<a href=\""+lib.createLink(attrs)+"\">"+text+"</a>\n"+
		"     </li>\n"
		out << t
	}
	def simpleSubItem={attrs,body->
		def id=attrs["id"]
		if (id=="" || id==null){
			id=java.util.UUID.toString();
		}
		def t="<li class=\"yuimenuitem\" id=\""+id+"\">\n"
		def u="</li>"
		out << t+body()+u
	}
}
