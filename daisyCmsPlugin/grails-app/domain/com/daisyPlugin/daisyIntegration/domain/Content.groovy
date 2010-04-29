package com.daisyPlugin.daisyIntegration.domain

class Content {
	static mapping ={	
	    daisyContentId         column:'daisy_content_id'
	    documentName           column:'document_name'
	    daisyLanguage          column:'daisy_language'
	    daisyBranch            column:'daisy_branch'
	    isExpired              column:'is_expired'
	    lastRetrieved          column:'last_retrieved'
	    firstRetrieved         column:'first_retrieved'
	}
    String daisyContentId
    String documentName
    String daisyBranch
    String daisyLanguage
    Boolean isExpired
    Date lastRetrieved
    Date firstRetrieved
    static constraints = {
    	daisyContentId(size:1..100,blank:false, nullable:false)
    	documentName(size:1..40,blank:false, nullable:false)
        daisyBranch(size:1..40,blank:false, nullable:false)
        daisyLanguage(size:1..40, blank:false, nullable:false)
    	isExpired(nullable:true, blank:true)
    	lastRetrieved(nullable:false)
    	firstRetrieved(nullable:false)
    }
	
	public String toString(){
		return id+":"+daisyContentId+":"+documentName+":"+daisyLanguage+":"+daisyBranch
	}
}
