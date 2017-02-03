package com.jzarsuelo.android.theguardianreader;

/**
 * A {@link News} object contains information related to a single news
 */
public class News {

    /** Section Id of this news */
    private String mSectionId;

    /** Section Name of this news */
    private String mSectionName;

    /** Web title of this news */
    private String mWebTitle;

    /** Web url of this news */
    private String mWebUrl;

    /** Web uri of this news */
    private String mApiUrl;

    public News(){}

    public News(String sectionId, String sectionName, String webTitle, String webUrl, String apiUrl) {
        mSectionId = sectionId;
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mApiUrl = apiUrl;
    }

    public String getSectionId() {
        return mSectionId;
    }

    public void setSectionId(String sectionId) {
        mSectionId = sectionId;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public void setSectionName(String sectionName) {
        mSectionName = sectionName;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public void setWebTitle(String webTitle) {
        mWebTitle = webTitle;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public void setWebUrl(String webUrl) {
        mWebUrl = webUrl;
    }

    public String getApiUrl() {
        return mApiUrl;
    }

    public void setApiUrl(String apiUrl) {
        mApiUrl = apiUrl;
    }
}
