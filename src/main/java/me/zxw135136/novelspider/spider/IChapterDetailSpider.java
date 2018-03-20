package me.zxw135136.novelspider.spider;

import me.zxw135136.novelspider.bean.ChapterDetail;

/**
 * 小说章节详细内容爬虫接口
 * @author 张晓伟
 *
 */
public interface IChapterDetailSpider {
	
	/**
	 * 根据章节详细内容所在的url爬取整章内容
	 * @param url
	 * @return
	 */
	public ChapterDetail getChapterDetail(String url);
	
	/**
	 * 根据章节详细内容所在的url爬取整章内容
	 * @param url
	 * @param maxTryTime 如果为null， 则 默认是 {@link me.zxw135136.novelspider.util.NovelSpiderUtil#MAX_TRY_TIME}
	 * @return
	 */
	public ChapterDetail getChapterDetail(String url, Integer maxTryTime);
}
