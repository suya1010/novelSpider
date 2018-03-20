package me.zxw135136.novelspider.spider.impl.chapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.zxw135136.novelspider.bean.Chapter;
import me.zxw135136.novelspider.bean.ChapterSpiderRule;
import me.zxw135136.novelspider.bean.NovelSpiderRule;
import me.zxw135136.novelspider.bean.SiteSpiderRule;
import me.zxw135136.novelspider.exception.SpiderException;
import me.zxw135136.novelspider.spider.AbstractSpider;
import me.zxw135136.novelspider.spider.IChapterSpider;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

/**
 * 抓取对应url下所有章节的抽象类
 * @author 张晓伟
 *
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	
	@Override
	public List<Chapter> getChaperts(String url) {
		return getChaperts(url, null);
	}
	
	@Override
	public List<Chapter> getChaperts(String url, Integer maxTryTime) {
		
		maxTryTime = maxTryTime == null ? NovelSpiderUtil.MAX_TRY_TIME : maxTryTime;
		
		//网站解析规则
		SiteSpiderRule siteSpiderRule = NovelSpiderUtil.getSpiderRule(url);
		NovelSpiderRule novelSpiderRule = siteSpiderRule.getNovelSpiderRule();
		ChapterSpiderRule chapterSpiderRule = novelSpiderRule.getChapterSpiderRule();
		
		String result = null;
		ArrayList<Chapter> chapters = null;
		
		for (int i=1; i<=maxTryTime; i++) {
			try {
				//抓取网页所有内容
				
				result = super.crawl(url,siteSpiderRule.getCharset());
				
				//通过Jsoup工具解析返回的页面信息字符串
				Document doc = Jsoup.parse(result);
				
				//设置基本地址，为a.absUrl("href")服务
				doc.setBaseUri(url);
				
				//根据chapter-list-selector内的选择规则选择元素，即为所有的章节元素\
				String listSelector = chapterSpiderRule.getListSelector();
				Elements as = doc.select(listSelector);
				
				chapters = new ArrayList<>();
				
				//遍历elements，封装所有的章节对象
				for (Element a : as) {
					Chapter chapter = new Chapter();
					chapter.setChapterTitle(a.text());
					chapter.setChapterUrl(a.absUrl("href"));
					chapter.setAddDate(new Date());
					chapters.add(chapter);
				}
				
				//成功抓取，退出循环
				if (result != null) {
					break;
				}
				
			} catch (Exception e) {
				System.out.println("第[" + i  + "/" + maxTryTime + "]次抓取章节列表页面失败");
			}
		
		}
		
		if (result != null) {
			return chapters;
		} else {
			throw new SpiderException(url + ",尝试了" + maxTryTime + "次抓取章节列表页面依旧失败了！");
		}
		
	}

}
