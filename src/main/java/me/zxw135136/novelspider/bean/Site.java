package me.zxw135136.novelspider.bean;

import java.util.Date;

/**
 * 
 * @author 张晓伟
 *
 */
public class Site {
    private Integer siteId;

    private String siteName;

    private String siteUrl;

    private Date addDate;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

	@Override
	public String toString() {
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", siteUrl=" + siteUrl + ", addDate=" + addDate
				+ "]";
	}
    
}