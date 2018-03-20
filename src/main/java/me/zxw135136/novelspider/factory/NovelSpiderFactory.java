package me.zxw135136.novelspider.factory;

import java.util.Map;
import java.util.Map.Entry;

import me.zxw135136.novelspider.bean.NovelSpiderRule;
import me.zxw135136.novelspider.bean.SiteSpiderRule;
import me.zxw135136.novelspider.exception.SpiderException;
import me.zxw135136.novelspider.spider.INovelSpider;
import me.zxw135136.novelspider.spider.impl.novel.KanShuZhongNovelSpider;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

import java.util.Set;

/**
 * NovelSpider工厂类
 * @author 张晓伟
 *
 */
public final class NovelSpiderFactory {
	
	private NovelSpiderFactory() {};
	
	public static INovelSpider getNovelSpider(String url) {

		INovelSpider novelSpider = null;
		
		Set<Entry<String, SiteSpiderRule>> entrySet = NovelSpiderUtil.SPIDER_RULES.entrySet();
		
		for (Map.Entry<String, SiteSpiderRule> entry : entrySet) {
			
			if (url.contains(entry.getKey())) {
				
				SiteSpiderRule siteSpiderRule = entry.getValue();
				NovelSpiderRule novelSpiderRule = siteSpiderRule.getNovelSpiderRule();
				
				if (novelSpiderRule.getNovelSelector() == null ) {
					throw new SpiderException("url="+url+":" + "没有支持该url的novel-selector规则");
				} else if (novelSpiderRule.getNovelNextPageSelector() == null) {
					throw new SpiderException("url="+url+":" + "没有支持该url的novel-next-page-selector规则");
				} else {
					if (url.contains("kanshuzhong")) {
						novelSpider = new KanShuZhongNovelSpider();
					}
				}
			}
		}
		
		if (novelSpider != null) {
			return novelSpider;
		} else {
			throw new SpiderException("url="+url+":" + "没有支持该url的NovelSpider");
		}
	}
}
