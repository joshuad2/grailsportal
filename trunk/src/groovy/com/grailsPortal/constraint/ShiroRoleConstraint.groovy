package com.grailsPortal.constraint;

import org.codehaus.groovy.grails.validation.AbstractConstraint;
import org.springframework.validation.Errors;

class ShiroRoleConstraint extends AbstractConstraint {
   public static final String SHIRO_ROLE_CONSTRAINT="shiroRole"
	@Override
	protected void processValidate(Object target, Object propertyValue,
			Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class type) {
		// TODO Auto-generated method stub
		return false;
	}

}
