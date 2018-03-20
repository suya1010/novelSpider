package me.zxw135136.novelspider.bean;

/**
 * 
 * @author 张晓伟
 *
 */
public class ChapterSpiderRule {
	
	private String listSelector;
	
	private String detailTitleSelector;
	
	private String detailContentSelector;
	
	private String detailPrevSelector;
	
	private String detailNextSelector;

	public String getListSelector() {
		return listSelector;
	}

	public void setListSelector(String listSelector) {
		this.listSelector = listSelector;
	}

	public String getDetailTitleSelector() {
		return detailTitleSelector;
	}

	public void setDetailTitleSelector(String detailTitleSelector) {
		this.detailTitleSelector = detailTitleSelector;
	}

	public String getDetailContentSelector() {
		return detailContentSelector;
	}

	public void setDetailContentSelector(String detailContentSelector) {
		this.detailContentSelector = detailContentSelector;
	}

	public String getDetailPrevSelector() {
		return detailPrevSelector;
	}

	public void setDetailPrevSelector(String detailPrevSelector) {
		this.detailPrevSelector = detailPrevSelector;
	}

	public String getDetailNextSelector() {
		return detailNextSelector;
	}

	public void setDetailNextSelector(String detailNextSelector) {
		this.detailNextSelector = detailNextSelector;
	}

	@Override
	public String toString() {
		return "ChapterSpiderRule [listSelector=" + listSelector + ", detailTitleSelector=" + detailTitleSelector
				+ ", detailContentSelector=" + detailContentSelector + ", detailPrevSelector=" + detailPrevSelector
				+ ", detailNextSelector=" + detailNextSelector + "]";
	}
	
}
