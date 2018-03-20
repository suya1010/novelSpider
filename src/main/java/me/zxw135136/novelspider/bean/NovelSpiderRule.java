package me.zxw135136.novelspider.bean;

/**
 * 
 * @author 张晓伟
 *
 */
public class NovelSpiderRule {

	private String novelSelector;
	
	private String novelNextPageSelector;
	
	private ChapterSpiderRule chapterSpiderRule;

	public String getNovelSelector() {
		return novelSelector;
	}

	public void setNovelSelector(String novelSelector) {
		this.novelSelector = novelSelector;
	}

	public String getNovelNextPageSelector() {
		return novelNextPageSelector;
	}

	public void setNovelNextPageSelector(String novelNextPageSelector) {
		this.novelNextPageSelector = novelNextPageSelector;
	}

	public ChapterSpiderRule getChapterSpiderRule() {
		return chapterSpiderRule;
	}

	public void setChapterSpiderRule(ChapterSpiderRule chapterSpiderRule) {
		this.chapterSpiderRule = chapterSpiderRule;
	}

	@Override
	public String toString() {
		return "NovelSpiderRule [novelSelector=" + novelSelector + ", novelNextPageSelector=" + novelNextPageSelector
				+ ", chapterSpiderRule=" + chapterSpiderRule + "]";
	}
	
}
