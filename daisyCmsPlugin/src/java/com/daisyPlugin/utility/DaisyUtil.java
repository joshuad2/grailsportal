package com.daisyPlugin.utility;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import org.outerj.daisy.repository.Repository;

public interface DaisyUtil {
	public String getHtmlContentFromId(String contentId)throws Exception;
	/**
	 */
	public ArrayList <Hashtable <String, Object>> getImages(String collectionName, String branch, String language) throws Exception;
	/**
	 */
	public ArrayList <Hashtable <String, Object>> getDocuments(String collectionName, String branch, String language) throws Exception;
   
	/**
	 * Get the repository
	 * @return
	 * @throws Exception
	 */
	public abstract Repository getRepository() throws Exception;

	/**
	 * Do image
	 * @param contentId
	 * @param os
	 * @throws Exception
	 */
	public abstract void doImage(String contentId, OutputStream os)
			throws Exception;

	/**
	 * Gets the fields for a document name and the field Type
	 * @param contentName
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public abstract ArrayList<String> getFields(String contentName,
			String fieldType) throws Exception;

	/**
	 * Get the html content
	 * @param contentName
	 * @return
	 * @throws Exception
	 */
	public abstract String getHtmlContent(String contentName) throws Exception;

	/**
	 * Get the specific html content
	 * @param contentId
	 * @param os
	 * @throws Exception
	 */
	public abstract void getSpecificHtmlContent(String contentId,
			OutputStream os) throws Exception;

	public abstract String getCmsUser();

	public abstract void setCmsUser(String cmsUser);

	public abstract String getCmsPassword();

	public abstract void setCmsPassword(String cmsPassword);

	public abstract String getCmsDirectory();

	public abstract void setCmsDirectory(String cmsDirectory);

	public abstract String getCmsLanguage();

	public abstract void setCmsLanguage(String cmsLanguage);

	public abstract String getCmsAddress();

	public abstract void setCmsAddress(String cmsAddress);

	public abstract void setRepository(Repository repository);

}