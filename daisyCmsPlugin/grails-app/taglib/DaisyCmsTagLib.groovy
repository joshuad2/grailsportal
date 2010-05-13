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
		def id=attrs["id"]
		def style=attrs["style"]
		def t='<div style="'+style+'">'+daisyService.getHtmlContent(id)+'<div/>' 
		out << t
  	}
}	