package me.zxw135136.novelspider.factory;

import java.util.Map;
import java.util.Map.Entry;

import me.zxw135136.novelspider.bean.ChapterSpiderRule;
import me.zxw135136.novelspider.bean.NovelSpiderRule;
import me.zxw135136.novelspider.bean.SiteSpiderRule;
import me.zxw135136.novelspider.exception.SpiderException;
import me.zxw135136.novelspider.spider.IChapterSpider;
import me.zxw135136.novelspider.spider.impl.chapter.DefaultChapterSpider;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

import java.util.Set;

/**
 * ChapterSpider工厂类
 * @author 张晓伟
 *
 */
public class ChapterSpiderFactory {
	
	/**
	 * 根据url返回一个对应的实现了IChapterSpider接口的实现类
	 * @param url
	 * @return
	 */
	public static IChapterSpider getChapterSpider(String url) {
		
		IChapterSpider chapterSpider = null;
		
		Set<Entry<String, SiteSpiderRule>> entrySet = NovelSpiderUtil.SPIDER_RULES.entrySet();
		
		for (Map.Entry<String, SiteSpiderRule> entry : entrySet) {
			
			if (url.contains(entry.getKey())) {
				
				SiteSpiderRule siteSpiderRule = entry.getValue();
				NovelSpiderRule novelSpiderRule = siteSpiderRule.getNovelSpiderRule();
				ChapterSpiderRule chapterSpiderRule = novelSpiderRule.getChapterSpiderRule();
				
				if (chapterSpiderRule.getListSelector() == null ) {
					throw new SpiderException("url="+url+":" + "没有支持该url的list-selector规则");
				} else {
					chapterSpider = new DefaultChapterSpider();
				}
			}
			
		}

		if (chapterSpider != null) {
			return chapterSpider;
		} else {
			throw new SpiderException("url="+url+":" + "没有支持该url的ChapterSpider");
		}
		
	}
}
