package com.grailsPortal.generator

class PortalScaffoldingGenerator {
	private renderEnumEditor(domainClass,property,domainInstance) {
		if(property.isEnum()) {
			return """"\
			 <g:select from="\${${property.type.name}?.values()}" 
			           value="\${${domainInstance}?.${property.name}}"
			           name="${property.name}" 
			          ${renderNoSelection(property)}>
			</g:select>
			"""
		}
	}
	private renderStringEditor(domainClass, property, cp, domainInstance) {
		if(!cp) {
			return """\
			<input type="text" 
			       name="${property.name}"
			       id="${property.name}"
			       value="\${fieldValue(bean:${domainInstance},field:'${property.name}')}" 
			 />
			"""
		}
		else {
			if("textarea" == cp.widget 
			             || (cp.maxSize > 250 && !cp.password && !cp.inList)) {
				return """\
			     <textarea rows="5" cols="40"
			               name="${property.name}">
				\${fieldValue(bean:${domainInstance}, field:'${property.name}')}
			</textarea>
			"""
			}
			else {
				if(cp.inList) {
					def sb = new StringBuffer('<g:select ')
					sb << "id=\"${property.name}\" name=\"${property.name}\" from="+
					"\"\${${domainInstance}.constraints.${property.name}.inList}\""+
					" value=\"\${${domainInstance}.${property?.name}}\" ${renderNoSelection(property)}>"
					sb << '</g:select>'
					return sb.toString()
				}
				else {
					def sb = new StringBuffer('<input ')
					cp.password ? sb << 'type="password" ' : sb << 'type="text" '
					if(!cp.editable) sb << 'readonly="readonly" '
					if(cp.maxSize) sb << "maxlength=\"${cp.maxSize}\" "
					sb << "id=\"${property.name}\" name=\"${property.name}\" value=\"\${fie"+
					"ldValue(bean:${domainInstance},field:'${property.name}')}\"/>"
					return sb.toString()
				}
			}
		}
	}
	
	private renderByteArrayEditor(domainClass,property) {
		return "<input type=\"file\" id=\"${property.name}\" name=\"${property.name}\" />"
	}
	
	private renderManyToOne(domainClass,property,cp,domainInstance) {
		def valueRoles=cp.getMetaConstraintValue("valueRoles")
		def selectQuery=cp.getMetaConstraintValue("selectQuery")
		def selectValues=cp.getMetaConstraintValue("selectValues")
		def startAllValueRoles = "<shiro:hasAllRoles in=\"['${valueRoles}']\">"
		def endHasAllRoles="</shiro:hasAllRoles>"
		def showValue="""\
		    <div>
		    \${fieldValue(bean:${domainInstance},field:'${property.name}')}
		    </div>
		    <g:hiddenField name="${property.name}" 
		                   value="\${fieldValue(bean:${domainInstance},field:'${property.name}')}" />
		    """
		def startLacksAllValueRoles="""<shiro:lacksAllRoles in="['${valueRoles}']">"""
		def startLacksAllRoles="""<shiro:lacksAllRoles in="['${valueRoles}']">"""
		def endLacksAllRoles="""</shiro:lacksAllRoles>"""
		def showSelect=""
		if (selectQuery==null){
			showSelect="""\
			<g:select optionKey="id" 
			          from="\${${property.type.name}.list()}" 
			          name="${property.name}.id"
			 value="\${${domainInstance}?.${property.name}?.id}"}>
			</g:select>
			"""
		}else{
			showSelect=
			"\n      <g:select optionKey=\"id\" from=\"\${${property.type.name}.findAll(${selectQuery},(${selectValues})}\" name=\"${property.name}.id\""+
			" value=\"\${${domainInstance}?.${property.name}?.id}\""+
			" ${renderNoSelection(property)}></g:select>"
		}
		def buf= new StringBuffer()
		if(property.association) {
			if (valueRoles==null){
				buf<< showSelect //normal
			}else if (valueRoles!=null){
				buf << startAllValueRoles
				buf << showValue
				buf << endHasAllRoles
				buf << startLacksAllRoles
				buf << showSelect
				buf << endLacksAllRoles
			}
			def shiroRole=cp.getMetaConstraintValue("shiroRole")
			def doAdd=cp.getMetaConstraintValue("addOnlyIfShiroRole")
			if (doAdd!=null && shiroRole!=null ){
				buf <<"<shiro:hasAllRoles in=\"['${shiroRole}']\">"
			}
			buf << "\n<div id=\"createThe${property.referencedDomainClass.name}\">"+
			"\n<g:link controller=\"${property.referencedDomainClass.propertyName}\""+
			" id=\"\" action=\"create\"\">Add</g:link>"
			buf << "\n           </div>"
			buf << "\n           "
			if (doAdd!=null && shiroRole!=null ){
				buf << "     </shiro:hasAllRoles>"
			}
		}
		return buf.toString() 
	}
	
	private renderManyToMany(domainClass,property,cp,domainInstance) {
		def sw = new StringWriter()
		def pw = new PrintWriter(sw)
		def shiroRole=cp.getMetaConstraintValue("shiroRole")
		def doAdd=cp.getMetaConstraintValue("addOnlyIfShiroRole")
		def listQuery=cp.getMetaConstraintValue("listQuery")
		def listQueryParams=cp.getMetaConstraintValue("listQueryParams")
		if (doAdd!=null && shiroRole!=null ){
			pw.println "<shiro:hasAllRoles in=\"['${shiroRole}']\">"
		}
		pw.println "<g:select name=\"${property.name}\""
		if (listQuery!=null && listQueryParams==null){
			pw.println("from=\"\${${property.referencedDomainClass.fullName}.findByAll(${listQuery})}\"")
		}else{
			if (listQuery!=null && listQueryParams!=null){
				pw.println("from=\"\${${property.referencedDomainClass.fullName}."+
				"findByAll(${listQuery},${listQueryParams})}\"")
			}
			else{
				pw.println "from=\"\${${property.referencedDomainClass.fullName}.list()}\""
			}
			pw.println "size=\"5\" multiple=\"yes\" optionKey=\"id\""
			pw.println "value=\"\${${domainInstance}?.${property.name}}\" />"
			if (doAdd!=null && shiroRole!=null ){
				pw.println "</shiro:hasAllRoles>"
			}
			return sw.toString()         
		}
	}
	
	private renderOneToMany(domainClass,property,domainInstance) {
		def sw = new StringWriter()
		def pw = new PrintWriter(sw)
		pw.println()
		def t="""\
		<ul> 
		  <g:each var="${property.name[0]}" in="\${${domainInstance}.${property.name}}">
		  <li>
		    <div id="editThe${property.referencedDomainClass.shortName}"> 
		      <g:link controller="${property.referencedDomainClass.shortName}" 
						    action="show" 
							id="\${${property.name[0]}.id}"\
							>
									\${${property.name[0]}?.encodeAsHTML()}
		      </g:link>
		     </div>
		   </li>
		 </g:each>
		</ul>
		<g:link controller="${property.referencedDomainClass.propertyName}" 
		        params="['${domainClass.propertyName}.id':${domainInstance}?.id]" 
		        action="create">
		           Add
	    </g:link>
		"""
		pw.println(t)
		return sw.toString()
	}
	
	private renderNumberEditor(domainClass,property,cp,domainInstance) {
		if(!cp) {
			if(property.type == Byte.class) {
				return "<g:select from=\"\${-128..127}\" name=\"${property.name}\" "+
				"value=\"\${${domainInstance}?.${property.name}}\"></g:select>"
			}
			else {
				return "<input type=\"text\" id=\"${property.name}\" name=\"${property.name}\" " +
				"value=\"\${fieldValue(bean:${domainInstance},field:'${property.name}')}\" />"
			}
		}
		else {
			if(cp.range) {
				return "<g:select from=\"\${${cp.range.from}..${cp.range.to}}\" "+
				"id=\"${property.name}\" name=\"${property.name}\" "+
				"value=\"\${${domainInstance}?.${property.name}}\" ${renderNoSelection(property)}></g:select>"
			}
			else if(cp.inList) {
				def sb = new StringBuffer('<g:select ')
				sb << "id=\"${property.name}\" name=\"${property.name}\" "+
				"from=\"\${${domainClass.propertyName}.constraints."+
				"${property.name}.inList}\" " +
				"value=\"\${${domainClass.propertyName}.${property?.name}}\" ${renderNoSelection(property)}>"
				sb << '</g:select>'
				return sb.toString()
			}            
			else {
				return "<input type=\"text\" id=\"${property.name}\" "+
				"name=\"${property.name}\" value=\"\${fieldValue(bean:"+
				"${domainInstance},field:'${property.name}')}\" />"
			}
		}
	}
	
	private renderBooleanEditor(domainClass,property,cp,domainInstance) {
		if(!cp) {
			return "<g:checkBox name=\"${property.name}\" value=\"\${${domainInstance}?.${property.name}}\"></g:checkBox>"
		}
		else {
			def buf = new StringBuffer('<g:checkBox ')
			if(cp.widget) buf << "widget=\"${cp.widget}\"";
			
			buf << "name=\"${property.name}\" value=\"\${${domainInstance}?.${property.name}}\" "
			cp.attributes.each { k,v ->
				buf << "${k}=\"${v}\" "
			}
			buf << '></g:checkBox>'
			return buf.toString()
		}
	}
	
	private renderDateEditor(domainClass,property,cp,domainInstance) {
		def precision = property.type == java.sql.Date ? 'day' : 'minute';
		if(!cp) {
			return "<g:datePicker name=\"${property.name}\" value=\"\${${domainInstance}?.${property.name}}\" precision=\"${precision}\"></g:datePicker>"
		}
		else {
			if(!cp.editable) {
				return "\${${domainInstance}?.${property.name}?.toString()}"
			}
			else {
				def buf = new StringBuffer('<g:datePicker ')
				if(cp.widget) buf << "widget=\"${cp.widget}\" "
				if(cp.format) buf << "format=\"${cp.format}\" "
				cp.attributes.each { k,v ->
					buf << "${k}=\"${v}\" "
				}
				buf << "name=\"${property.name}\" value=\"\${${domainInstance}?.${property.name}}\""+
				" precision=\"${precision}\" ${renderNoSelection(property)}></g:datePicker>"
				return buf.toString()
			}
		}
	}
	
	private renderSelectTypeEditor(type,domainClass,property,cp,domainInstance) {
		if(!cp) {
			def buf= new StringBuffer("<g:${type}Select name=\"${property.name}\" value=\"\${${domainInstance}?.${property.name}}\"></g:${type}Select>")
			buf << "<g:link controller=\"${property.name}\" params=\"['${property.id}':${domainInstance}?.id]\" action=\"create\">Add ${property.name}</g:link>"
			return buf
		}
		else {
			def buf = new StringBuffer("<g:${type}Select ")
			if(cp.widget) buf << "widget=\"${cp.widget}\" ";
			cp.attributes.each { k,v ->
				buf << "${k}=\"${v}\" "
			}
			buf << "name=\"${property.name}\" value=\"\${${domainInstance}?.${property.name}}\" ${renderNoSelection(property)}></g:${type}Select>"
			buf << "<g:link controller=\"${property.name}\" params=\"['${property.id}':${domainInstance}?.id]\" action=\"create\">Add ${property.name}</g:link>"
			return buf.toString()
		}
	}
	
	private renderNoSelection(property) {
		if(property.optional) {
			if(property.manyToOne || property.oneToOne) {
				return "noSelection=\"['null':'']\""				
			}
			else {
				return "noSelection=\"['':'']\""
			}
		}
		return ""
	}
}

