package me.zxw135136.novelspider.spider;

import java.util.Iterator;
import java.util.List;

import me.zxw135136.novelspider.bean.Novel;

/**
 * 爬取某个站点的小说列表
 * @author 张晓伟
 *
 */
public interface INovelSpider {
	
	/**
	 * 根据url爬取小说实体
	 * @param url
	 * @return
	 */
	public List<Novel> getNovels(String url);
	
	/**
	 * 根据url爬取小说实体
	 * @param url
	 * @param maxTryTime 如果为null， 则 默认是 {@link me.zxw135136.novelspider.util.NovelSpiderUtil#MAX_TRY_TIME}
	 * @return
	 */
	public List<Novel> getNovels(String url, Integer maxTryTime);
	
	/**
	 * 所有小说的迭代器
	 * @param firstPage 第一页小说列表的url
	 * @return
	 */
	public Iterator<List<Novel>> iterator(String firstPage);
}
