package com.daisyPlugin.daisyIntegration.service

import org.outerj.daisy.repository.RepositoryManager
import org.outerj.daisy.repository.Credentials
import org.outerj.daisy.repository.Document
import org.outerj.daisy.repository.Repository
import org.outerj.daisy.repository.query.QueryManager
import org.outerj.daisy.repository.clientimpl.RemoteRepositoryManager
import org.outerx.daisy.x10.SearchResultDocument
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.util.Locale
import org.codehaus.groovy.grails.commons.ConfigurationHolder

import org.springframework.context.ApplicationContext 
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import java.text.*
import grails.plugin.springcache.annotations.Cacheable
import com.daisyPlugin.utility.*

class DaisyService{
	def daisyUtil
	static final String documentName="documentName"
	static final String documentId="documentId"
	static final String versionCreateTime="versionCreateTime"
	static final String separator="/"
	static final String imageExtension=".png"

	/**
	 * Get the content type from a content Identifier
	 * @param contentId
	 * @return
	 */
	@Cacheable("daisyDocumentTypeCache")
	def getDocumentType(contentId){
		return daisyUtil.getDocumentType(contentId)
	}
	/**
	 * get the ContentID from the content name
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@Cacheable("daisyContentCache")
	def getContentIdFromName(name){
		return daisyUtil.getContentIdFromName(name)
	}
	/**
	 * Gets the image from the name of the content
	 * @param id
	 * @param os
	 */
	@Cacheable("daisyImageCache")
	public void doImage(id,os){
			daisyUtil.doImage(id, os)
	}
	@Cacheable("daisyHtmlContentCache")
	def getHtmlContent(contentId){
		return daisyUtil.getHtmlContentFromId(contentId)
	}
    @Cacheable("daisyRepositoryCache")
	def Repository getRepository(){
		return daisyUtil.getRepository()
	}
	@Cacheable("daisyFieldsCache")
	def getFields(contentName, fieldType){
		return daisyUtil.getFields(contentName, fieldType)
	}
	@Cacheable("daisyHtmlContentCache")
	def getHtmlContent(String contentName){
		return daisyUtil.getHtmlContent(contentName)
	}
	@Cacheable("daisyHtmlContentCache")
	def getSpecificHtmlContent(contentId, os){
		daisyUtil.getSpecificHtmlContent(contentId, os)
	}
	def getCmsUser(){
		return daisyUtil.getCmsUser()
	}
	
	def setCmsUser(cmsUser){
		daisyUtil.setCmsUser(cmsUser)
	}
	
	def getCmsPassword(){
		return daisyUtil.getCmsPassword()
	}
	
	def setCmsPassword(cmsPassword){
		daisyUtil.setCmsPassword(cmsPassword)
	}
	
	def getCmsDirectory(){
		return daisyUtil.getCmsDirectory()
	}
	
	def setCmsDirectory(cmsDirectory){
		daisyUtil.setCmsDirectory(cmsDirectory)
	}
	
	def getCmsLanguage(){
		return daisyUtil.getCmsLanguage()
	}
	
	def setCmsLanguage(cmsLanguage){
		daisyUtil.setCmsLanguage(cmsLanguage)
	}
	
	def getCmsAddress(){
		return daisyUtil.getCmsAddress()
	}
	
	def setCmsAddress(cmsAddress){
		daisyUtil.setCmsAddress(cmsAddress)
	}
	
	def setRepository(repository){
		daisyUtil.setRepositry(repository)
	}
}
