	grails.project.dependency.resolution = {
		    inherits "global" 
		    log "warn" 
		    repositories {
		        grailsHome()
		       mavenCentral()
		       mavenRepo "http://snapshots.repository.codehaus.org" 
		       mavenRepo "http://repository.codehaus.org" 
		       mavenRepo "http://download.java.net/maven/2/" 
		       mavenRepo "http://repository.jboss.com/maven2/"
		       } 
		   dependencies { 
		    	   // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg. 
		    	    runtime 'mysql:mysql-connector-java:5.1.10' 
		    	    }
		       }

