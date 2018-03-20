package me.zxw135136.novelspider.bean;

/**
 * 
 * @author 张晓伟
 *
 */
public class SiteSpiderRule {
	
	private String url;
	
	private String title;
	
	private String charset;
	
	private NovelSpiderRule novelSpiderRule;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public NovelSpiderRule getNovelSpiderRule() {
		return novelSpiderRule;
	}

	public void setNovelSpiderRule(NovelSpiderRule novelSpiderRule) {
		this.novelSpiderRule = novelSpiderRule;
	}

	@Override
	public String toString() {
		return "SiteSpiderRule [url=" + url + ", title=" + title + ", charset=" + charset + ", novelSpiderRule="
				+ novelSpiderRule + "]";
	}
	
}
