package com.daisyPlugin.daisyIntegration.domain


class ContentImage {
	static mapping ={		
		contentName      column:'content_name'
		contentPath      column:'content_path'
	}
    String contentPath
    Content content
    static constraints = {
    	contentPath(nullable:false, blank:false)
    	content(nullable:true, blank:true)
    }
}
