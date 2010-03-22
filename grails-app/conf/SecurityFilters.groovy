class SecurityFilters {
   def filters = {
        auth(controller: "*", action: "*") {
            before = {
                if (controllerName == "public") return true
                 accessControl { true } 
                }
            }
  user(controller:"user",action:"(create|edit|update|save|delete|list)"){
	   before={
			    accessControl{
			    	role("Administrator")
			    }
		   }
  }
   }
}

 