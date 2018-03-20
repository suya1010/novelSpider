package me.zxw135136.novelspider.spider.impl.novel;

import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.zxw135136.novelspider.bean.Novel;
import me.zxw135136.novelspider.bean.NovelSpiderRule;
import me.zxw135136.novelspider.bean.SiteSpiderRule;
import me.zxw135136.novelspider.exception.SpiderException;
import me.zxw135136.novelspider.spider.AbstractSpider;
import me.zxw135136.novelspider.spider.INovelSpider;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

/**
 * 一个抽象的小说列表抓取，已经实现了解析tr元素的方法
 * @author 张晓伟
 *
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider{
	
	/**
	 * 下一页的元素
	 */
	protected Element nextPageElement;
	/**
	 * 下一页的url
	 */
	protected String nextPage ;
	
	/**
	 * 默认的抓取方法，最多会尝试 {@link me.zxw135136.novelspider.util.NovelSpiderUtil#MAX_TRY_TIME} 次下载
	 * @param url
	 * @return
	 * @throws SpiderException 
	 */
	protected Elements getTrs(String url) {
		return getTrs(url, null);
	}
	
	/**
	 * 解析url，取得各小说所在的tr元素
	 * @param url
	 * @param maxTryTime 如果为null， 则 默认是 {@link INovelSpider#MAX_TRY_TIME}
	 * @return
	 * @throws SpiderException 
	 */
	protected Elements getTrs(String url, Integer maxTryTime) {
		
		maxTryTime = maxTryTime == null ? NovelSpiderUtil.MAX_TRY_TIME : maxTryTime;
		Elements trs = null;
		
		//网站解析规则
		SiteSpiderRule siteSpiderRule = NovelSpiderUtil.getSpiderRule(url);
		NovelSpiderRule novelSpiderRule = siteSpiderRule.getNovelSpiderRule();
		
		for (int i=1; i<=maxTryTime; i++) {
			try {
				String result;
				result = super.crawl(url, siteSpiderRule.getCharset());
				String novelSelector = novelSpiderRule.getNovelSelector();
				if (novelSelector == null) {
					throw new SpiderException("url=" + url + "目前不支持抓取小说列表");
				}
				Document doc = Jsoup.parse(result);
				doc.setBaseUri(url);
				trs = doc.select(novelSelector);
				
				String nextPageSelector = novelSpiderRule.getNovelNextPageSelector();
				if (nextPageSelector != null) {
					Elements nextPageElements = doc.select(nextPageSelector);
					nextPageElement = nextPageElements == null ? null : nextPageElements.first();
				}
				
				if (nextPageElement != null) {
					nextPage = nextPageElement.absUrl("href");
				} else {
					nextPage = "";
				}
				
				//成功抓取，退出循环
				if (trs != null) {
					break;
				}
			} catch (Exception e) {
				System.out.println("第[" + i  + "/" + maxTryTime + "]次抓取小说列表页面失败");
			}
		}
		
		if (trs != null) {
			return trs;
		} else {
			throw new SpiderException(url + ",尝试了" + maxTryTime + "次抓取小说列表页面依旧失败了！");
		}
		
	}
	
	@Override
	public Iterator<List<Novel>> iterator(String firstPage) {
		nextPage = firstPage;
		return new NovelIterator();
	}
	
	private class NovelIterator implements Iterator<List<Novel>> {

		@Override
		public boolean hasNext() {
			return !nextPage.isEmpty();
		}
		@Override
		public List<Novel> next() {
			System.out.println(nextPage);
			return getNovels(nextPage);
		}
	}

}
