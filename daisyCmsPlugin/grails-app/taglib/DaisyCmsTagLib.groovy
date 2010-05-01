
public class DaisyCmsTagLib{
		static namespace = 'daisyCms'

		def img={attrs->
		 def menuName=attrs["name"]
         def id=attrs["id"]
		 def style=attrs["style"]
	     def t='<img src="/daisyController/contentHandler?name='+name+'" id="'+id+'" style="'+style+'" />' 
	     out << t
		} 
}	
