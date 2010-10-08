package com.daisyPlugin.daisy.tag
import com.daisyPlugin.daisyIntegration.service.*;
public class DaisyCmsTagLib{

		static namespace = 'daisyCms'
		def daisyService
		def img={attrs->
         def id=attrs["id"]
		 def style=attrs["style"]
	     def t='<img src="/daisyController/contentHandler?id="'+id+'" style="'+style+'" />' 
	     out << t
		}
	   def simpleText={attrs->
		DaisyService ds=daisyService
		def id=attrs["id"]
		def style=attrs["style"]
		def t='<div style="'+style+'">'+ds.getHtmlContent(id)+'<div/>' 
		out << t
  	}
}	