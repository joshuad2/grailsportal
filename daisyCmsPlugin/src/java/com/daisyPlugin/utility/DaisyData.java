package com.daisyPlugin.utility;
import java.util.Date;
public interface DaisyData{
	public void saveHtmlText(String daisyContentId, String htmlText, String documentName, Date versionCreateDate);
    public void saveImage(String daisyContentId, String documentName, Date versionCreateDate);
}