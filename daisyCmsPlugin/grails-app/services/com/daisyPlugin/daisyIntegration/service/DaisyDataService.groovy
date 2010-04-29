package com.daisyPlugin.daisyIntegration.service

import com.daisyPlugin.utility.DaisyData
import com.daisyPlugin.daisyIntegration.domain.Content;
import com.daisyPlugin.daisyIntegration.domain.ContentHtml;
import com.daisyPlugin.daisyIntegration.domain.ContentImage;
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.springframework.beans.factory.InitializingBean

class DaisyDataService implements DaisyData, InitializingBean{

    boolean transactional = true
    String imagePath
    String daisyBranch
    String daisyLanguage
    
    void afterPropertiesSet()
    {
        this.imagePath     = ConfigurationHolder.config.imagePath
        this.daisyBranch   = ConfigurationHolder.config.wellEssnt.cmsDirectory
        this.daisyLanguage = ConfigurationHolder.config.wellEssnt.cmsLanguage
    }
    def getHtmlTextByDocumentNameAndLanguage(documentName,language){
	  	def contentList=Content.findByDocumentName(documentName,language)
		if (contentList==null){ // do documents
			
		}
	}
	/**
	 * saveHtmlText
	 */
	def void saveHtmlText(String daisyContentId, String htmlText, String documentName, Date versionCreateDate) {
		def ct=doContent(daisyContentId, documentName, daisyBranch, daisyLanguage, versionCreateDate)
		def ch=ContentHtml.findByContent(ct)
		if (ch==null){
			ch=new ContentHtml()
			ch.content=ct
			ch.htmlText=htmlText
			ch.save()
			return;
		}
		if (ct.lastRetrievedDate()<versionCreateDate){
			ch.htmlText=htmlText
			ch.save()
		}
	}
	
    def Content getContentByDaisyContentId(String daisyContentId){
    	return Content.findByDaisyContentId(daisyContentId)
    }
    /**
     * doContent
     */
    def Content doContent(String daisyContentId, String documentName, String daisyBranch, String daisyLanguage, Date versionCreateDate){
        def ct=Content.findByDaisyContentId(daisyContentId)
        if (ct==null){
          ct=new Content()
          ct.daisyContentId=daisyContentId
          ct.documentName=documentName
          ct.daisyLanguage=daisyLanguage
          ct.daisyBranch=daisyBranch
          ct.lastRetrieved=new Date()
          ct.firstRetrieved=new Date()
      	  if (!ct.validate()){
      		log.error("VALIDATE ERROR:"+ct.errors)
      		throw new Exception()
      	  }
        }
        else{
      	  def ch=ContentHtml.findByContent(ct)
      	  if (ct.lastRetrievedDate()<versionCreateDate){
      	        ct.documentName=documentName
      	        ct.daisyLanguage=daisyLanguage
      	        ct.daisyBranch=daisyBranch
      	        ct.lastRetrieved=new Date()
      	  }
        }
    ct.save()
    return ct
    }
    /**
     * saveImage
     */
    def void saveImage(String daisyContentId, String documentName, Date versionCreateDate){
    	def ct=doContent(daisyContentId, documentName, daisyBranch, daisyLanguage, versionCreateDate)
    	def ci=ContentImage.findByContent(ct)
    	if (ci==null){
    		ci=new ContentImage()
    		ci.contentPath=imagePath+"/"+daisyBranch+"/"+daisyLanguage+"/"
    		ci.content=ct;
    		ci.save()
    		return
    	}
    }

}
