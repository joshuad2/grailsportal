package com.daisyPlugin.daisyIntegration.service
import org.outerj.daisy.repository.Credentials
import org.outerj.daisy.repository.clientimpl.RemoteRepositoryManager
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.daisyPlugin.utility.DaisyUtilImpl
import com.daisyPlugin.utility.DaisyUtil
class DaisyRepositoryUtilService {
    boolean transactional = false
    RemoteRepositoryManager repositoryManager
	DaisyUtil daisyUtil
    def initializeRepsitory() {
		def cmsUser=ConfigurationHolder.configuration.daisyPlugin.cmsUser
		def cmsPass=ConfigurationHolder.configuration.daisyPlugin.cmsPassword
		def cmsAddress=ConfigurationHolder.configuration.daisyPlugin.cmsAddress
		def cmsDirectory=ConfigurationHolder.configuration.daisyPlugin.cmsDirectory
		def cmsLanguage=ConfigurationHolder.configuration.daisyPlugin.cmsLanguage
		def namespace=ConfigurationHolder.configuration.application.namespace
		def daisyCreds= new Credentials(cmsUser,cmsPassword)
		repositoryManager=new RemoteRepositoryManager(cmsAddress,daisyCreds)
		daisyUtil=new DaisyUtilImpl(cmsUser,cmsPassword,cmsDirectory,cmsLanguage,namespace,repositoryManager)
    }
}
