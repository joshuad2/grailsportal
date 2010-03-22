class UrlMappings {
    static mappings = {
      "/"{
    	  controller = "default"
    	  action = "index"
    	  view = "index"
      }
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
	  "500"(view:'/error')
	  "404"(view:'/error')
	}
}
