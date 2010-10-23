package com.daisyPlugin.utility;
import org.outerj.daisy.repository.Field;
import org.outerj.daisy.repository.Fields;
import org.outerj.daisy.repository.Part;
import org.outerj.daisy.repository.RepositoryManager;
import org.outerj.daisy.repository.Credentials;
import org.outerj.daisy.repository.Document;
import org.outerj.daisy.repository.Repository;
import org.outerj.daisy.repository.query.QueryManager;
import org.outerx.daisy.x10.SearchResultDocument;
import java.util.LinkedHashMap;
import java.util.HashMap;

import java.io.OutputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.io.*;
public class DaisyUtilImpl implements DaisyUtil {

	private String cmsUser;
	private String cmsPassword;
	private String cmsDirectory;
	private String cmsLanguage;
	private String cmsAddress;
	private Repository repository;
	private RepositoryManager repositoryManager;
	private String namespace;

	/**
	 * Get the content type from a content Identifier
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	public String getDocumentType(String contentId) throws Exception{
	    String query="select documentType where id='"+contentId+"'";
		List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery(query);
		for (SearchResultDocument.SearchResult.Rows.Row row:rows){
			return row.getValueArray(0);
		}
	    return null;
	}
	/**
	 * get the ContentID from the content name
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getContentNameFromId(String id) throws Exception{
	    String query="select name where id='"+id+"'";
		List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery(query);
		for (SearchResultDocument.SearchResult.Rows.Row row:rows){
			return row.getValueArray(0);
		}
	    return null;
	}
	/**
	 * get the ContentID from the content name
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getContentIdFromName(String name) throws Exception{
	    String query="select id where name='"+name+"'";
		List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery(query);
		for (SearchResultDocument.SearchResult.Rows.Row row:rows){
			return row.getValueArray(0);
		}
	    return null;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#convertStreamToString(java.io.InputStream)
	 */
	public String convertStreamToString(InputStream is) {
	      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	      StringBuffer sb = new StringBuffer(); 
	      String line = null;
	      try {
	        while ((line = reader.readLine()) != null) {
	          sb.append(line + "\n");
	    	}
	       } catch (IOException e) {
	    	            e.printStackTrace();
	    	        } finally {
	    	            try {
	    	                is.close();
	    	            } catch (IOException e) {
	    	                e.printStackTrace();
	    	            }
	    	        }
	    	 
	    	        return sb.toString();
	        }
	  
/* (non-Javadoc)
 * @see com.cas.daisy.DaisyUtil#getRepository()
 */
	  public Repository getRepository() throws Exception{
		if (this.repository==null){
	      repository= repositoryManager.getRepository(new Credentials(cmsUser, cmsPassword));
		}
	    return repository;
	  }
	  /* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#writeImage(java.io.OutputStream, java.io.InputStream)
	 */
	  public void writeImage(OutputStream os, InputStream is) throws Exception{
	    	BufferedImage bi = ImageIO.read(is);
	        ImageIO.write(bi, "png", os);
	  }
	  /* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#doImage(java.lang.String, java.io.OutputStream)
	 */
	  public void doImage(String contentId,OutputStream os) throws Exception{
		    Document doc= getRepository().getDocument(contentId,cmsDirectory,cmsLanguage,false);
		    Part pt= doc.getPart("ImageData");
		    writeImage(os,pt.getDataStream());
	  }
	  /**
	   * 
	   */
		public void doImageFromName(String name, OutputStream os) throws Exception{
			List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery("select id where name='"+name+"'");
			doImage(rows.get(0).getValueArray(0),os);
		}
       /**
        * 
        * @param str
        * @return
     * @throws Exception 
        */
	   private String handleDaisyUrl(String str) throws Exception{
		   StringBuffer sb=new StringBuffer();
		   String splitString="<pre class=\"include\">daisy:";
		   String[] result = str.split(splitString);
		   String table="<TABLE>";
           for (int x=0;x<result.length;x++){
        	   if (x % 2==0){ //even
            	   sb.append(result[x]);
        	   }else{
        		   String splitString2="</pre>";
        		   StringBuffer sb2=new StringBuffer();
        		   String result2[]=result[x].split(splitString2);
        		   String daisyId=result2[0];
        		   String docType=getDocumentType(daisyId);
        		   String contentName=this.getContentNameFromId(daisyId);
        		   if (docType.equals("ContactMe")){
        			   sb2.append(table);
        			   HashMap <String,String>fields=this.getAllFields(contentName);
        			   for (String key:fields.keySet()){
        				   sb2.append("<TR><TD>"+key+"</TD><TD>"+fields.get(key)+"</TD></TR>");
        			   }
        		   sb2.append("</TABLE>");
        		   }
        		   for (int y=1;y<result2.length;y++){
        			   sb2.append(result2[y]);
        		   }
               sb.append(sb2);     		   
        	   }   
           }
          str=sb.toString();
		  String str1= str.replaceAll("daisy:","../daisy/contentHandler?id=");
		  str1=str1.replaceAll("-"+this.getNamespace(),"");
		  return str1;
	   }
	   /**
	    * 
	    * @param str
	    * @return
	    */
	   private String handleDaisyData(String str){
		   String str1=str.replaceAll("<html>","");
		   String str2=str1.replaceAll("</html>","");
	       String str3=str2.replaceAll("<body>","");
	       String str4=str3.replaceAll("</body>","");
	       return str4;
	   }
	   /**
	    * 
	    * @param query
	    * @return
	    * @throws Exception
	    */
	  private List <SearchResultDocument.SearchResult.Rows.Row> doDaisyQuery(String query)throws Exception{
			Repository repository =getRepository();
	        QueryManager queryManager = repository.getQueryManager();
	        SearchResultDocument searchresults =
	            queryManager.performQuery(query, Locale.getDefault());
	        return searchresults.getSearchResult().getRows().getRowList();
	  }
	  public LinkedHashMap <String,String> getAllFields(String contentName) throws Exception{
		  List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery("select id where name='"+contentName+"'");
		  Repository repository=getRepository();
		  LinkedHashMap <String,String> arr=new LinkedHashMap<String,String>();
		  for (SearchResultDocument.SearchResult.Rows.Row row: rows){
	        String id=row.getValueArray(0);
		    Document doc= repository.getDocument(id,cmsDirectory,cmsLanguage,false);
		    Fields fields=doc.getFieldsInOrder();
		    Field[] theFields=fields.getArray();
		    for (int x=0;x<theFields.length;x++){
		      Field field=theFields[x];
  		      if (field.isMultiValue()){
  		    	String label=field.getTypeName();
		    	for (String value:(String[])field.getValue() )
		    	  arr.put(label, value);
		    	}
		        else{
		    	  arr.put(field.getTypeName(),(String)field.getValue());
		        }
		    }
	        }
		    return arr;
		    }
	  /* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getFields(java.lang.String, java.lang.String)
	 */
	  public ArrayList <String> getFields(String contentName, String fieldType) throws Exception{
		  List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery("select id where name='"+contentName+"'");
		  Repository repository=getRepository();
		  ArrayList <String> arr=new ArrayList<String>();
		  for (SearchResultDocument.SearchResult.Rows.Row row: rows){
	        String id=row.getValueArray(0);
		    Document doc= repository.getDocument(id,cmsDirectory,cmsLanguage,false);
		    Field field=doc.getField(fieldType);
		    if (field.isMultiValue()){
		    	for (String value: (String[])field.getValue() )
		    	  arr.add(value);
		    	}
		      else{
		    	arr=new ArrayList<String>();
		    	arr.add((String)field.getValue());
		      }
	        }
		    return arr;
		    }
	  public String getHtmlContentFromId(String contentId)throws Exception{
			Document doc= getRepository().getDocument(contentId,cmsDirectory,cmsLanguage,false);
		    Part pt= doc.getPart(13L);
		    String str= convertStreamToString(pt.getDataStream());
		    return handleDaisyUrl(handleDaisyData(str));
	  }
	   /* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getHtmlContent(java.lang.String)
	 */
	   public String getHtmlContent(String contentName) throws Exception{
			List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery("select id where name='"+contentName+"'");
		    return getHtmlContentFromId(rows.get(0).getValueArray(0));
	   }
	   /* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getSpecificHtmlContent(java.lang.String, java.io.OutputStream)
	 */
	   public void getSpecificHtmlContent(String contentId, OutputStream os) throws Exception{		    
		    Document doc= getRepository().getDocument(contentId,cmsDirectory,cmsLanguage,false);
		    Part pt= doc.getPart(13L);
		    String str= convertStreamToString(pt.getDataStream());
		    OutputStreamWriter osw=new OutputStreamWriter(new BufferedOutputStream(os),"UTF-8");
		    osw.write(handleDaisyUrl(handleDaisyData(str)));
	  }
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getCmsUser()
	 */
	public String getCmsUser() {
		return cmsUser;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#setCmsUser(java.lang.String)
	 */
	public void setCmsUser(String cmsUser) {
		this.cmsUser = cmsUser;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getCmsPassword()
	 */
	public String getCmsPassword() {
		return cmsPassword;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#setCmsPassword(java.lang.String)
	 */
	public void setCmsPassword(String cmsPassword) {
		this.cmsPassword = cmsPassword;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getCmsDirectory()
	 */
	public String getCmsDirectory() {
		return cmsDirectory;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#setCmsDirectory(java.lang.String)
	 */
	public void setCmsDirectory(String cmsDirectory) {
		this.cmsDirectory = cmsDirectory;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getCmsLanguage()
	 */
	public String getCmsLanguage() {
		return cmsLanguage;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#setCmsLanguage(java.lang.String)
	 */
	public void setCmsLanguage(String cmsLanguage) {
		this.cmsLanguage = cmsLanguage;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#getCmsAddress()
	 */
	public String getCmsAddress() {
		return cmsAddress;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#setCmsAddress(java.lang.String)
	 */
	public void setCmsAddress(String cmsAddress) {
		this.cmsAddress = cmsAddress;
	}
	/* (non-Javadoc)
	 * @see com.cas.daisy.DaisyUtil#setRepository(org.outerj.daisy.repository.Repository)
	 */
	public void setRepository(Repository repository){
		this.repository=repository;
	}

	public RepositoryManager getRepositoryManager() {
		return repositoryManager;
	}

	public void setRepositoryManager(RepositoryManager repositoryManager) {
		this.repositoryManager = repositoryManager;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}
