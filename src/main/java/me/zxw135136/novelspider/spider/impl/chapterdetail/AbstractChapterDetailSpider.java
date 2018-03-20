package me.zxw135136.novelspider.spider.impl.chapterdetail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.zxw135136.novelspider.bean.ChapterDetail;
import me.zxw135136.novelspider.bean.ChapterSpiderRule;
import me.zxw135136.novelspider.bean.NovelSpiderRule;
import me.zxw135136.novelspider.bean.SiteSpiderRule;
import me.zxw135136.novelspider.exception.SpiderException;
import me.zxw135136.novelspider.spider.AbstractSpider;
import me.zxw135136.novelspider.spider.IChapterDetailSpider;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

/**
 * 抓取小说章节详细内容的类
 * @author 张晓伟
 *
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider{
	
	@Override
	public ChapterDetail getChapterDetail(String url) {
		return getChapterDetail(url, null);
	}

	@Override
	public ChapterDetail getChapterDetail(String url, Integer maxTryTime) {
		
		maxTryTime = maxTryTime == null ? NovelSpiderUtil.MAX_TRY_TIME : maxTryTime;
		
		//网站解析规则
		SiteSpiderRule siteSpiderRule = NovelSpiderUtil.getSpiderRule(url);
		NovelSpiderRule novelSpiderRule = siteSpiderRule.getNovelSpiderRule();
		ChapterSpiderRule chapterSpiderRule = novelSpiderRule.getChapterSpiderRule();
		
		String result = null;
		ChapterDetail chapterDetail = null;
		
		for (int i=1; i<=maxTryTime; i++) {
			try {
				result = super.crawl(url, siteSpiderRule.getCharset());
				result = result.replace("&nbsp;", " ").replace("<br />", "#{line}#").replace("<br/>", "#{line}#");
				Document doc = Jsoup.parse(result);
				doc.setBaseUri(url);
				
				chapterDetail = new ChapterDetail();
				
				//取得章节题目
				String titleSelector = chapterSpiderRule.getDetailTitleSelector();
				String[] splits = titleSelector.split(":");
				splits = parseSelector(splits);
				chapterDetail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
				
				//取得章节内容
				String contentSelector = chapterSpiderRule.getDetailContentSelector();
				chapterDetail.setContent(doc.select(contentSelector).first().text().replace("#{line}#", "\r\n"));
				
				//取得上一章地址
				String prevSelector = chapterSpiderRule.getDetailPrevSelector();
				splits = prevSelector.split(":");
				splits = parseSelector(splits);
				chapterDetail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
				
				//取得下一章地址
				String nextSelector = chapterSpiderRule.getDetailNextSelector();
				splits = nextSelector.split(":");
				splits = parseSelector(splits);
				chapterDetail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
				
				//成功抓取，退出循环
				if (result != null) {
					break;
				}
				
			} catch (Exception e) {
				System.out.println("第[" + i  + "/" + maxTryTime + "]次抓取章节内容页面失败");
			}
		}
		
		if (result != null) {
			return chapterDetail;
		} else {
			throw new SpiderException(url + ",尝试了" + maxTryTime + "次抓取章节内容页面依旧失败了！");
		}
	}
	
	private String[] parseSelector(String[] splits) {
		String[] newSplits = new String[2];
		
		if (splits.length == 1) {
			newSplits[0] = splits[0];
			newSplits[1] = "0";
			return newSplits;
		} else {
			return splits;
		}
		
	}

}
