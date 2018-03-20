package me.zxw135136.novelspider.test;

import org.junit.Test;

import me.zxw135136.novelspider.bean.ChapterDetail;
import me.zxw135136.novelspider.factory.ChapterDetailSpiderFactory;
import me.zxw135136.novelspider.spider.IChapterDetailSpider;
import me.zxw135136.novelspider.spider.impl.chapterdetail.DefaultChapterDetailSpider;

/**
 * 
 * @author 张晓伟
 *
 */
public class ChapterDetailSpiderTest {
	
	@Test
	public void testChapterDetailSpider() {
		DefaultChapterDetailSpider defaultChapterDetailSpider = new DefaultChapterDetailSpider();
		ChapterDetail chapterDetail = defaultChapterDetailSpider.getChapterDetail("http://www.23wx.cc/du/80/80892/13089785.html", null);
		System.out.println(chapterDetail);
	}
	
	@Test
	public void testkanshuzhongChapterDetailSpider() {
		DefaultChapterDetailSpider defaultChapterDetailSpider = new DefaultChapterDetailSpider();
		ChapterDetail chapterDetail = defaultChapterDetailSpider.getChapterDetail("http://www.kanshuzhong.com/book/117196/27267492.html", null);
		System.out.println(chapterDetail);
	}
	
	@Test
	public void testChapterDetailSpiderFactory() {
		IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider("http://www.23wx.cc/du/80/80892/13089785.html");
		ChapterDetail chapterDetail = chapterDetailSpider.getChapterDetail("http://www.23wx.cc/du/80/80892/13089785.html", null);
		System.out.println(chapterDetail);
	}
	
	@Test
	public void testkanshuzhongChapterDetailSpiderFactory() {
		IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider("http://www.kanshuzhong.com/book/117196/27267492.html");
		ChapterDetail chapterDetail = chapterDetailSpider.getChapterDetail("http://www.kanshuzhong.com/book/117196/27267492.html", null);
		System.out.println(chapterDetail);
	}
}
