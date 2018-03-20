package me.zxw135136.novelspider.bean;

import java.util.Date;

/**
 * 
 * @author 张晓伟
 *
 */
public class Novel {
    private Integer novelId;

    private String novelName;

    private String novelAuthor;

    private String novelUrl;

    private String novelType;

    private String lastUpdateChapterUrl;

    private Date lastUpdateDate;

    private Integer novelStatus;

    private Date addDate;

    private Integer siteId;

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName == null ? null : novelName.trim();
    }

    public String getNovelAuthor() {
        return novelAuthor;
    }

    public void setNovelAuthor(String novelAuthor) {
        this.novelAuthor = novelAuthor == null ? null : novelAuthor.trim();
    }

    public String getNovelUrl() {
        return novelUrl;
    }

    public void setNovelUrl(String novelUrl) {
        this.novelUrl = novelUrl == null ? null : novelUrl.trim();
    }

    public String getNovelType() {
        return novelType;
    }

    public void setNovelType(String novelType) {
        this.novelType = novelType == null ? null : novelType.trim();
    }

    public String getLastUpdateChapterUrl() {
        return lastUpdateChapterUrl;
    }

    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl == null ? null : lastUpdateChapterUrl.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getNovelStatus() {
        return novelStatus;
    }

    public void setNovelStatus(Integer novelStatus) {
        this.novelStatus = novelStatus;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

	@Override
	public String toString() {
		return "Novel [novelId=" + novelId + ", novelName=" + novelName + ", novelAuthor=" + novelAuthor + ", novelUrl="
				+ novelUrl + ", novelType=" + novelType + ", lastUpdateChapterUrl=" + lastUpdateChapterUrl
				+ ", lastUpdateDate=" + lastUpdateDate + ", novelStatus=" + novelStatus + ", addDate=" + addDate
				+ ", siteId=" + siteId + "]";
	}
    
}