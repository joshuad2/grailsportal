package com.daisyPlugin.utility;

import java.io.OutputStream;
import java.util.ArrayList;

import org.outerj.daisy.repository.Repository;

public interface DaisyUtil {
	/**
	 * Get the content type from a content Identifier
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	public String getDocumentType(String contentId) throws Exception;
	/**
	 * get the ContentID from the content name
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getContentIdFromName(String name) throws Exception;
	/**
	 * get the hteml content from the ID
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	public String getHtmlContentFromId(String contentId)throws Exception;
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
	 * Write the image to the outputstream
	 * @param name
	 * @param os
	 * @throws Exception
	 */
	public abstract void doImageFromName(String name, OutputStream os) throws Exception;
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