package me.zxw135136.novelspider.spider.impl.novel;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;

import me.zxw135136.novelspider.bean.Novel;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

/**
 * 看书中网站的小说列表爬取
 * @author 张晓伟
 *
 */
public class KanShuZhongNovelSpider extends AbstractNovelSpider{
	
	@Override
	public List<Novel> getNovels(String url) {
		return getNovels(url, null);
	}

	@Override
	public List<Novel> getNovels(String url, Integer maxTryTime) {
		List<Novel> novels = new ArrayList<>();
		try {
			
			Elements trs = super.getTrs(url, maxTryTime);
			
			int size = trs.size();
			//从1开始是因为第一个tr不是小说
			//size-1是因为原网站最后一个tr不是小说
			for (int index=1; index<size-1; index++) {
				Elements tds = trs.get(index).getElementsByTag("td");
				Novel novel = new Novel();
				novel.setNovelType(tds.first().text());
				novel.setNovelName(tds.get(1).text());
				novel.setNovelUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				novel.setLastUpdateChapterUrl(tds.get(2).getElementsByTag("a").first().absUrl("href"));
				novel.setNovelAuthor(tds.get(3).text());
				novel.setLastUpdateDate(NovelSpiderUtil.getDate(tds.get(4).text(), "MM-dd"));
				novel.setNovelStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
				novels.add(novel);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return novels;
	}
	
}
