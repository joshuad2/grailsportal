package com.daisyPlugin.utility;
import org.outerj.daisy.repository.Field;
import org.outerj.daisy.repository.Part;
import org.outerj.daisy.repository.RepositoryManager;
import org.outerj.daisy.repository.Credentials;
import org.outerj.daisy.repository.Document;
import org.outerj.daisy.repository.Repository;
import org.outerj.daisy.repository.query.QueryManager;
import org.outerx.daisy.x10.SearchResultDocument;



import java.io.OutputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.Locale;
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

	public ArrayList <Hashtable <String, Object>> getDocuments(String collectionName, String branch, String language) throws Exception{
      String query="select id, name, versionCreationTime,retired where InCollection('"+collectionName+"') and " +
      		"documentType = 'SimpleDocument' and branch = '"+branch+"' and language = '"+
      		language+
      		"' and versionState='publish' option include_retired = 'true'";
    
	  List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery(query);
	  ArrayList <Hashtable <String, Object>> arr=new ArrayList<Hashtable<String, Object>>();
	  for (SearchResultDocument.SearchResult.Rows.Row row: rows){
		Hashtable <String, Object> vs=new Hashtable<String, Object>();
		vs.put("documentId",row.getValueArray(0));
		vs.put("documentName",row.getValueArray(1));
		vs.put("versionCreateTime",row.getValueArray(2));
		vs.put("retired",row.getValueArray(3));
		arr.add(vs);
	  }
     return arr;
	 }
	public ArrayList <Hashtable <String, Object>> getImages(String collectionName, String branch, String language) throws Exception{
	      String query="select id, name, versionCreationTime,retired where InCollection('"+collectionName+"') and " +
    		"documentType = 'Image' and branch = '"+branch+"' and language = '"+
    		language+
    		"' and versionState='publish' option include_retired = 'true'";
		  List <SearchResultDocument.SearchResult.Rows.Row> rows=doDaisyQuery(query);
		  ArrayList <Hashtable <String, Object>> arr=new ArrayList<Hashtable<String, Object>>();
		  for (SearchResultDocument.SearchResult.Rows.Row row: rows){
			Hashtable <String, Object> vs=new Hashtable<String, Object>();
			
			vs.put("documentId",row.getValueArray(0));
			vs.put("documentName",row.getValueArray(1));
			vs.put("versionCreateTime",row.getValueArray(2));
			vs.put("retired",row.getValueArray(3));
			arr.add(vs);
		  }
	     return arr;
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
        * @param str
        * @return
        */
	   private String handleDaisyUrl(String str){
		  String str1= str.replaceAll("daisy:","../daisy/contentHandler?name=");
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
