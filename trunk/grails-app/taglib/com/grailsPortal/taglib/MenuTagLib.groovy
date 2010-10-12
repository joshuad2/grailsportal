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
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
class MenuTagLib {
	static namespace = 'menu'
	static final String SVC_NAME="portalMenuService"
	def getPortalMenuService(){
		def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
		def pms=ctx.getBean(SVC_NAME)
		return pms
	}
	
	def portalMenu={attrs->
		def menuType=attrs["menuType"]
		def portalMenuService=getPortalMenuService()
		def pmConfig=portalMenuService.getPortalMenu(attrs.portalConfigId)
		String preMenu=""
		String postMenu=""
		def t=""
		pmConfig.portalMenus.each{
			def pm=it
			//check to see if it is an admin menu and then apply the security
			if (pm.portalMenuType.menuTypeName==menuType){				
				def body=""
				pm.subMenus.each{
					def sm=it
					if (sm.isAjax=="Y"){
						body+=menu.simpleSubItem(["id":sm.theController+"Link"],
						g.remoteLink(["controller":sm.theController,
							"action":sm.theAction,
							"update":sm.theController+"s"],
						sm.text))
					}
					else{
						body+=
						menu.subItem(["text":sm.text,"controller":sm.theController,"action":sm.theAction])
					}
				}
				t+=
				menu.mainItem(["itemLabel":pm.itemLabel,"text":pm.itemText],body)
			}
		}		            
		out << t
	}
	
	def menuSetup={attrs->
		def portalMenuService=getPortalMenuService()
		def pcId=attrs["portalConfigId"]
		if (pcId==null){
			pcId="1"
		}
		def pmConfig=portalMenuService.getPortalMenu(pcId)
		def menuName=attrs["name"]
		def position=attrs["position"]
		def hideDelay=attrs["hideDelay"]
		def lazyLoad=attrs["lazyload"]
		if (hideDelay==null || hideDelay==""){
			hideDelay=pmConfig.hideDelay
		}
		if (position==null || position==""){
			position=pmConfig.position
		}
		if (menuName==null || menuName==""){
			menuName=pmConfig.menuName
		}
		if (lazyLoad==null || lazyLoad==""){
			if (pmConfig.lazyLoad=="Y"){
				lazyLoad="true"
			}else{
				lazyLoad="false"
			}
		}
		def t='<script type="text/javascript">'+
		'YAHOO.util.Event.onContentReady("basicmenu",function ()'+
		' {var oMenu = new YAHOO.widget.Menu("basicmenu",{'+  
		' position: "'+position+'",'+  
		' hidedelay: \"'+ hideDelay+'\",'+  
		' lazyload: \"'+lazyLoad+'\" });'+
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
		def portalMenuService=getPortalMenuService()
		def portalConfigId=attrs["portalConfigId"]
		if (portalConfigId==null){
			portalConfigId="1"
		}
		def pmConfig=portalMenuService.getPortalMenu(portalConfigId)
		def style=attrs["style"]
		if (style==null){
			style=pmConfig["style"]
			if (style==null){
				style=""
			}
		}
		def width=attrs["width"]
		if (width==null){
			width=pmConfig["width"]
		}
		def height=attrs["height"]
		if (height==null){
			height=pmConfig["height"]
		}
		def margin=attrs["margin"]
		if (margin==null){
			margin=pmConfig["margin"]
		}
		def borderStyle=attrs["borderStyle"]
		if (borderStyle==null){
			borderStyle=pmConfig["borderStyle"]
			if (borderStyle==null){
				borderStyle=""
			}
		}
		def borderWidth=attrs["borderWidth"]
		if (borderWidth==null){
			borderWidth=pmConfig["borderWidth"]
		}
		def borderHeight=["borderHeight"]
		if (borderHeight==null){
			borderHeight=pmConfig["borderHeight"]
		}
		def t="<div id=\"menuContainer\" style=\"width: "+width+";height: "+height+"; margin:"+margin+";"+style+"\">\n"+
		"<div id=\"basicmenu\" class=\"yuimenu\">\n"+
		"<div class=\"bd\" style=\"width: "+borderWidth+"; height: "+height+";"+"\">\n"
		def u="</div>\n</div>\n</div>\n"
		out << t+body()+u
	}
	
	def mainItem={attrs, body ->
		def itemLabel=attrs["itemLabel"]
		def text=attrs["text"]
		def t="<li class=\"yuimenuitem\">\n"+
		"<a class=\"yuimenuitemlabel\" href=\"#"+itemLabel+"\">"+text+"</a>\n"+
		"<div id=\""+itemLabel+"\" class=\"yuimenu\">\n"+
		"<div class=\"bd\" style=\"width: 200px\">\n"+
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
			id=java.util.UUID.randomUUID().toString();
		}
		def t="<li class=\"yuimenuitem\" id=\""+id+"\">\n"+
		"<a href=\""+lib.createLink(attrs)+"\">"+text+"</a>\n"+
		"     </li>\n"
		out << t
	}
	def simpleSubItem={attrs,body->
		def id=attrs["id"]
		if (id=="" || id==null){
			id=java.util.UUID.randomUUID().toString();
		}
		def t="<li class=\"yuimenuitem\" id=\""+id+"\">\n"
		def u="</li>"
		out << t+body()+u
	}
}
