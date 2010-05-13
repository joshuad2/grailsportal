package com.daisyPlugin.daisyIntegration.service

import org.outerj.daisy.repository.RepositoryManager
import org.outerj.daisy.repository.Credentials
import org.outerj.daisy.repository.Document
import org.outerj.daisy.repository.Repository
import org.outerj.daisy.repository.query.QueryManager
import org.outerj.daisy.repository.clientimpl.RemoteRepositoryManager
import org.outerx.daisy.x10.SearchResultDocument
import org.springframework.beans.factory.InitializingBean;

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.util.Locale 
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import java.text.*
import com.daisyPlugin.utility.DaisyUtil
import com.daisyPlugin.utility.DaisyUtilImpl
import org.codehaus.groovy.grails.commons.ConfigurationHolder as ch

class DaisyService  implements InitializingBean{
	boolean transactional = false
	RemoteRepositoryManager repositoryManager
	DaisyUtil daisyUtil
	void afterPropertiesSet() {
		def config=ch.config
		def cmsUser=config["daisyPluginCmsUser"]
		def cmsPass=config["daisyPluginCmsPassword"]
		def cmsAddress=config["daisyPluginCmsAddress"]
		def cmsDirectory=config["daisyPluginCmsDirectory"]
		def cmsLanguage=config["daisyPluginCmsLanguage"]
		def namespace=config["applicationNamespace"]
		def daisyCreds= new Credentials(cmsUser,cmsPass)
		repositoryManager=new RemoteRepositoryManager(cmsAddress,daisyCreds)
		daisyUtil=new DaisyUtilImpl();
		daisyUtil.cmsUser=cmsUser
		daisyUtil.cmsPassword=cmsPass
		daisyUtil.cmsDirectory=cmsDirectory
		daisyUtil.cmsLanguage=cmsLanguage
		daisyUtil.namespace=namespace
		daisyUtil.repositoryManager=repositoryManager
	}

	/**
	 * Get the content type from a content Identifier
	 * @param contentId
	 * @return
	 */
	def getDocumentType(contentId){
		return daisyUtil.getDocumentType(contentId)
	}
	/**
	 * get the ContentID from the content name
	 * @param name
	 * @return
	 * @throws Exception
	 */
	def getContentIdFromName(name){
		return daisyUtil.getContentIdFromName(name)
	}
	/**
	 * Gets the image from the name of the content
	 * @param id
	 * @param os
	 */
	public void doImage(id,os){
		daisyUtil.doImage(id, os)
	}
	def getHtmlContent(contentId){
		return daisyUtil.getHtmlContentFromId(contentId)
	}
	
	def Repository getRepository(){
		return daisyUtil.getRepository()
	}
	def getFields(contentName, fieldType){
		return daisyUtil.getFields(contentName, fieldType)
	}
	def String getHtmlContentFromName(contentName){
		return daisyUtil.getHtmlContent(contentName)
	}
	def getSpecificHtmlContent(contentId, os){
		daisyUtil.getSpecificHtmlContent(contentId, os)
	}
	def String getCmsUser(){
		return daisyUtil.getCmsUser()
	}
	
	def setCmsUser(cmsUser){
		daisyUtil.setCmsUser(cmsUser)
	}
	
	def String getCmsPassword(){
		return daisyUtil.getCmsPassword()
	}
	
	def setCmsPassword(cmsPassword){
		daisyUtil.setCmsPassword(cmsPassword)
	}
	
	def String getCmsDirectory(){
		return daisyUtil.getCmsDirectory()
	}
	
	def setCmsDirectory(cmsDirectory){
		daisyUtil.setCmsDirectory(cmsDirectory)
	}
	
	def String getCmsLanguage(){
		return daisyUtil.getCmsLanguage()
	}
	
	def setCmsLanguage(cmsLanguage){
		daisyUtil.setCmsLanguage(cmsLanguage)
	}
	
	def String getCmsAddress(){
		return daisyUtil.getCmsAddress()
	}
	
	def setCmsAddress(cmsAddress){
		daisyUtil.setCmsAddress(cmsAddress)
	}
	
	def setRepository(repository){
		daisyUtil.setRepositry(repository)
	}
}
