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
import com.daisyPlugin.utility.DaisyUtil
class DaisyService implements DaisyUtil{
    def daisyUtil
    def daisyDataService
    
    public ArrayList <Hashtable <String, Object>> getImages(String collectionName, String branch, String language) {
      return daisyUtil.getImages(collectionName, branch, language)
    }
	public String getDaisyContentByName(String name){
		return getHtmlContent(name)
	}
    def String getHtmlContentFromId(String contentId){
    	
    	return daisyUtil.getHtmlContentFromId(contentId)
    }
    def ArrayList <Hashtable> getDocuments(String collectionName, String branch, String language){
    	return daisyUtil.getDocuments(collectionName, branch, language )
    }
    def Repository getRepository(){
    	return daisyUtil.getRepository()
    }
    
    def void doImage(String contentId, OutputStream os){
    	daisyUtil.doImage(contentId, os)
    }
    
    def ArrayList <String> getFields(String contentName, String fieldType){
    	return daisyUtil.getFields(contentName, fieldType)
    }
    
    def String getHtmlContent(String contentName){
    	return daisyUtil.getHtmlContent(contentName)
    }
    
    def void getSpecificHtmlContent(String contentId, OutputStream os){
    	daisyUtil.getSpecificHtmlContent(contentId, os)
    }
	def String getCmsUser(){
		return daisyUtil.getCmsUser()
	}

	def void setCmsUser(String cmsUser){
		daisyUtil.setCmsUser(cmsUser)
	}

	def String getCmsPassword(){
		return daisyUtil.getCmsPassword()
	}

    def void setCmsPassword(String cmsPassword){
		daisyUtil.setCmsPassword(cmsPassword)
	}

	def String getCmsDirectory(){
		return daisyUtil.getCmsDirectory()
	}

	def void setCmsDirectory(String cmsDirectory){
	    daisyUtil.setCmsDirectory(cmsDirectory)
	}

	def  String getCmsLanguage(){
		return daisyUtil.getCmsLanguage()
	}

	def void setCmsLanguage(String cmsLanguage){
		daisyUtil.setCmsLanguage(cmsLanguage)
	}

	def  String getCmsAddress(){
		return daisyUtil.getCmsAddress()
	}

	def void setCmsAddress(String cmsAddress){
		daisyUtil.setCmsAddress(cmsAddress)
	}

	def void setRepository(Repository repository){
		daisyUtil.setRepositry(repository)
	}
}
