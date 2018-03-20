package me.zxw135136.novelspider.bean;

import java.util.Date;

/**
 * 
 * @author 张晓伟
 *
 */
public class Chapter {
    private Integer chapterId;

    private String chapterTitle;

    private String chapterUrl;

    private Date addDate;

    private Integer novelId;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle == null ? null : chapterTitle.trim();
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl == null ? null : chapterUrl.trim();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

	@Override
	public String toString() {
		return "Chapter [chapterId=" + chapterId + ", chapterTitle=" + chapterTitle + ", chapterUrl=" + chapterUrl
				+ ", addDate=" + addDate + ", novelId=" + novelId + "]";
	}
    
}