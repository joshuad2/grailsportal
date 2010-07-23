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
package com.grailsPortal.domain.attribute

class AttributeDataType implements Serializable{
    static mapping = {
        table 'attributeDataType'
        name            column:'name' 
        dsc             column:'dsc' 
        groovyType      column:'groovy_type'
        size            column:'size_of_type'
        htmlStartTag    column:'html_start_tag'
        htmlEndTag      column:'html_end_tag'
        isFromCMS       column:'is_from_cms'
        cmsContentType  column:'cms_content_type'
   }
	String name
	String dsc
	String groovyType
	String size
	String htmlStartTag
	String htmlEndTag
	Boolean isFromCMS
	String cmsContentType
    static constraints = {
		name(size: 1..40,nullable:false,blank:false)
		dsc(size: 1..100,nullable:false,blank:false)
    	groovyType(size: 1..100,nullable:false,blank:false)
    	size(size: 1..100,nullable:false,blank:false)
    	htmlStartTag(size: 1..200,nullable:false,blank:false)
    	htmlEndTag(size: 1..200,nullable:false,blank:false)
    	isFromCMS(nullable:false,blank:false)
    	cmsContentType(size: 1..200,nullable:false,blank:false)
    }
    String toString() {
        return name 
    }
}
