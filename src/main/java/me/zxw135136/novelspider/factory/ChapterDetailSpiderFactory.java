package me.zxw135136.novelspider.factory;

import java.util.Map;
import java.util.Map.Entry;

import me.zxw135136.novelspider.bean.ChapterSpiderRule;
import me.zxw135136.novelspider.bean.NovelSpiderRule;
import me.zxw135136.novelspider.bean.SiteSpiderRule;
import me.zxw135136.novelspider.exception.SpiderException;
import me.zxw135136.novelspider.spider.IChapterDetailSpider;
import me.zxw135136.novelspider.spider.impl.chapterdetail.DefaultChapterDetailSpider;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

import java.util.Set;

/**
 * ChapterDetailSpider工厂类
 * @author 张晓伟
 *
 */
public class ChapterDetailSpiderFactory {
	
	/**
	 * 根据url返回一个对应的实现了IChapterDetailSpider接口的实现类
	 * @param url
	 * @return
	 */
	public static IChapterDetailSpider getChapterDetailSpider(String url) {
		
		IChapterDetailSpider chapterDetailSpider = null;
		
		Set<Entry<String, SiteSpiderRule>> entrySet = NovelSpiderUtil.SPIDER_RULES.entrySet();
		
		for (Map.Entry<String, SiteSpiderRule> entry : entrySet) {
			if (url.contains(entry.getKey())) {
				
				SiteSpiderRule siteSpiderRule = entry.getValue();
				NovelSpiderRule novelSpiderRule = siteSpiderRule.getNovelSpiderRule();
				ChapterSpiderRule chapterSpiderRule = novelSpiderRule.getChapterSpiderRule();
				
				if (chapterSpiderRule.getDetailTitleSelector() == null ) {
					throw new SpiderException("url="+url+":" + "没有支持该url的detail-title-selector规则");
				} else if (chapterSpiderRule.getDetailContentSelector() == null) {
					throw new SpiderException("url="+url+":" + "没有支持该url的detail-content-selector规则");
				} else if (chapterSpiderRule.getDetailPrevSelector() == null) {
					throw new SpiderException("url="+url+":" + "没有支持该url的detail-prev-selector规则");
				} else if (chapterSpiderRule.getDetailNextSelector() == null) {
					throw new SpiderException("url="+url+":" + "没有支持该url的detail-next-selector规则");
				} else {
					chapterDetailSpider = new DefaultChapterDetailSpider();
				}
			}
		}

		if (chapterDetailSpider != null) {
			return chapterDetailSpider;
		} else {
			throw new SpiderException("url="+url+":" + "没有支持该url的ChapterDetailSpider");
		}
	}
}
