package com.grailsPortal.constraint;

import org.codehaus.groovy.grails.validation.AbstractConstraint;
import org.springframework.validation.Errors;

class AddOnlyIfShiroRoleConstraint extends AbstractConstraint {
	
	public static final String ADD_ONLY_IF_SHIRO_ROLE_CONSTRAINT="addOnlyIfShiroRole"
	
    private boolean isShiroRole=false
    public void setParameter(Object constraintParameter) {
        if(!(constraintParameter instanceof Boolean))
        throw new IllegalArgumentException("Parameter for constraint ["
               +ADD_ONLY_IF_SHIRO_ROLE_CONSTRAINT+"] of property ["
               +constraintPropertyName+"] of class ["
               +constraintOwningClass+"] must be a boolean value");
 
        this.isShiroRole = ((Boolean)constraintParameter).booleanValue();
        super.setParameter(constraintParameter);
    }

	public String getName() {
        return ADD_ONLY_IF_SHIRO_ROLE_CONSTRAINT
	}

	boolean supports(Class type) {
		return type != null && String.class.isAssignableFrom(type);
	}
	protected void processValidate(Object target, Object propertyValue, Errors errors) {
	}
	
}
