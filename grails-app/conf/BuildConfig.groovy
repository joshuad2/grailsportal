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

