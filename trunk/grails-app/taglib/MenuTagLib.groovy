import org.codehaus.groovy.grails.plugins.web.taglib.*;

class MenuTagLib {
	static namespace = 'menu'
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