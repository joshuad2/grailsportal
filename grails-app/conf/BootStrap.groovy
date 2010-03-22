import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

class BootStrap {
     def init = { servletContext ->
     def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
     def pd = ctx.getBean("portalDataService")
     def config=ctx.getBean("portalConfigurationService")
     pd.initializePortal();
     config.init("WEST ORLANDO ARTS FOUNDATION")
    }
     def destroy = {
     }
} 