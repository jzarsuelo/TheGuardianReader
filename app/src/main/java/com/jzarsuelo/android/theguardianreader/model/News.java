package com.jzarsuelo.android.theguardianreader.model;

/**
 * A {@link News} object contains information related to a single news
 */
public class News {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

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

    /** Author of the news */
    private String mAuthor;

    /** Date in milliseconds when the news is published */
    private Long mWebPublicationDate;

    public News(){}

    public News(String sectionId, String sectionName, String webTitle, String webUrl, String apiUrl,
                String author, Long webPublicationDate) {
        mSectionId = sectionId;
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mApiUrl = apiUrl;
        mAuthor = author;
        mWebPublicationDate = webPublicationDate;
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

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public Long getWebPublicationDate() {
        return mWebPublicationDate;
    }

    public void setWebPublicationDate(Long webPublicationDate) {
        mWebPublicationDate = webPublicationDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "mSectionId='" + mSectionId + '\'' +
                ", mSectionName='" + mSectionName + '\'' +
                ", mWebTitle='" + mWebTitle + '\'' +
                ", mWebUrl='" + mWebUrl + '\'' +
                ", mApiUrl='" + mApiUrl + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mWebPublicationDate=" + mWebPublicationDate +
                '}';
    }
}
