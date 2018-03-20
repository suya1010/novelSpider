package me.zxw135136.novelspider.spider;

import java.util.List;

import me.zxw135136.novelspider.bean.Chapter;

/**
 * 小说的章节爬虫接口
 * @author 张晓伟
 *
 */
public interface IChapterSpider {
	
	/**
	 * 根据章节所在的url爬取所有的章节
	 * @param url
	 * @return
	 */
	public List<Chapter> getChaperts(String url);
	
	/**
	 * 根据章节所在的url爬取所有的章节
	 * @param url
	 * @param maxTryTime 如果为null， 则 默认是 {@link me.zxw135136.novelspider.util.NovelSpiderUtil#MAX_TRY_TIME}
	 * @return
	 */
	public List<Chapter> getChaperts(String url, Integer maxTryTime);
}
