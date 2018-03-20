package me.zxw135136.novelspider.test;

import java.util.List;

import org.junit.Test;

import me.zxw135136.novelspider.bean.Chapter;
import me.zxw135136.novelspider.factory.ChapterSpiderFactory;
import me.zxw135136.novelspider.spider.IChapterSpider;
import me.zxw135136.novelspider.spider.impl.chapter.DefaultChapterSpider;

/**
 * 
 * @author 张晓伟
 *
 */
public class ChapterSpiderTest {
	
	@Test
	public void test23wxChapterSpider() {
		DefaultChapterSpider defaultChapterSpider = new DefaultChapterSpider();
		List<Chapter> chaperts = defaultChapterSpider.getChaperts("http://www.23wx.cc/du/80/80892/", null);
		System.out.println(chaperts);
	}
	
	@Test
	public void testkanshuzhongChapterSpider() {
		DefaultChapterSpider defaultChapterSpider = new DefaultChapterSpider();
		List<Chapter> chaperts = defaultChapterSpider.getChaperts("http://www.kanshuzhong.com/book/117196/", null);
		System.out.println(chaperts);
	}
	
	@Test
	public void test23wxChapterSpiderFactory() {
		IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider("http://www.23wx.cc/du/80/80892/");
		List<Chapter> chaperts = chapterSpider.getChaperts("http://www.23wx.cc/du/80/80892/", null);
		System.out.println(chaperts);
	}
	
	@Test
	public void testkanshuzhongChapterSpiderFactory() {
		IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider("http://www.kanshuzhong.com/book/774/");
		List<Chapter> chaperts = chapterSpider.getChaperts("http://www.kanshuzhong.com/book/774/", null);
		System.out.println(chaperts);
	}
	
}
