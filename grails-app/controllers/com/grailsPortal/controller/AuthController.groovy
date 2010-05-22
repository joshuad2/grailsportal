package com.grailsPortal.controller


import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.SecurityUtils
import com.grailsPortal.exception.ValidationException
import com.grailsPortal.domain.*

class AuthController {
	def securityService
	def contactUtilService
	def index = { redirect(action: 'redirectLogin',
		params: params)
	}
	def login = {
		return [ username: params.username,
		rememberMe: (params.rememberMe != null),
		targetUri: params.targetUri ]
	}
	def doLogin = {
		return [ username: params.username,
		rememberMe: (params.rememberMe != null),
		targetUri: params.targetUri ]
	}
	def register={
		JsecUser ju=new JsecUser()
		ju.party=new Party()
		String theAction="registerUser"
		try{
			ju=securityService.getRegisteredUser()
			if (ju!=null){
				theAction="updateUser"
			}
		}catch(Exception e){
		}
		return [authInstance:ju,
		userAction:theAction]
	}
	def edit={
		def ju=securityService.getRegisteredUser()
		session.returnController="auth"
		render (model:[authInstance:ju],
		view:'register')
	}
	def registerUser={
		JsecUser.withSession { session ->
			Party userParty
			JsecUser user
			JsecUser ju
			boolean isValidationError=false
			def userName=params.userName
			try{
				ju=securityService.getRegisteredUser()
			}catch (Exception e){
				ju=null;
			}
			if (userName==null ||
			userName=="" ||
			params.firstName==null ||
			params.firstName=="" ||
			params.lastName==null ||
			params.lastName=="" ||
			params.password==null ||
			params.password=="" ||
			params.passwordVerify==null ||
			params.passwordVerify==""){
				flash.message="We are sorry, you"+
				" have to enter a user name, "+
				"First name, Last Name, "+
				"Password and Verify Password"
				render(model:[authInstance:user],view:'register')
				isValidationError=true
			}
			def foundUser=null
			if (ju==null){
				foundUser=JsecUser.findByUsername(userName)
				if (foundUser!=null){
					flash.message="We are sorry this user name is "+
					"already taken, please pick another"
					render(model:[authInstance:user],view:'register')
					isValidationError=true
					session.clear()
				}
			}
			if (params.password!=params.passwordVerify){
				flash.message="We are sorry your passwords do not match,"+
				" please try again"
				render(model:[authInstance:user],view:'register')
				isValidationError=true
			}
			def jsecUser=new JsecUser()
			jsecUser.party=new Party()
			user=contactUtilService.doUser(jsecUser,PartyType.findByName("User"),params)
			if (user.hasErrors() || user.party.hasErrors()){
				flash.message = "ERROR-09:There was a problem "+
				"validating the information, please try again"
				render(model:[authInstance:user,
				authErrorInstance:user.party.errors.allErrors],
				view:"register")
				session.clear()
				return
			}
			if (!isValidationError){
				def userRole=JsecRole.findByName("User")
				try{
					new JsecUserRoleRel(user: user, role: userRole).save(flush:true)
				}catch(Exception e){
				    log.error(e)
					session.clear()
					flash.message="ERROR-11: There was a problem "+
					"saving the User, please try again"
					render(model:[authInstance:user],view:'register')
					return
				}
				if (ju==null){
					flash.message="User Created"
				    user.party.save(flush:true)
					user.save(flush:true)
					render(model:[authInstance:user],view:"register")
				}else{
					flash.message="User Profile Updated"
				}
				if (user.party.id==null){
					flash.message = "ERROR-10:There was a problem"+
					" saving the User, please try again"
					render(model:[authInstance:user],view:'register')
					session.clear()
					return
				}
			}else{
				session.clear()
				render(model:[authInstance:user],view:'register')
			}
		}
	}
	def signIn = {
		def authToken = new UsernamePasswordToken(params.username,
		params.password)
		if (params.rememberMe) {
			authToken.rememberMe = true
		}else {
			authToken.rememberMe=false
		}
		try{
			SecurityUtils.getSubject().login(authToken)
			def targetUri = params.targetUri ?: "/"
			redirect(uri: targetUri)
		}
		catch (AuthenticationException ex){
			flash.message = message(code: "login.failed")
			log.error(ex.printStackTrace())
			def m = [ username: params.username ]
			if (params.rememberMe) {
				m['rememberMe'] = true
			}
			if (params.targetUri) {
				m['targetUri'] = params.targetUri
			}
			redirect(action: 'login', params: m)
		}
	}
	
	def signOut = {
		SecurityUtils.subject?.logout()
		redirect(uri: '/')
	}
	
	def unauthorized = {
		render 'You do not have permission to access this page.'
	}
	
}