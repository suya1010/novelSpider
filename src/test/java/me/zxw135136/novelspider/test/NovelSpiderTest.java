package me.zxw135136.novelspider.test;

import java.util.List;

import org.junit.Test;

import me.zxw135136.novelspider.bean.Novel;
import me.zxw135136.novelspider.factory.NovelSpiderFactory;
import me.zxw135136.novelspider.spider.INovelSpider;
import me.zxw135136.novelspider.spider.impl.novel.KanShuZhongNovelSpider;

/**
 * 
 * @author 张晓伟
 *
 */
public class NovelSpiderTest {
	
	@Test
	public void testNovelSpider() {
		KanShuZhongNovelSpider kanShuZhongNovelSpider = new KanShuZhongNovelSpider();
		List<Novel> novels = kanShuZhongNovelSpider.getNovels("http://www.kanshuzhong.com/toplist/lastupdate/1/");
		System.out.println(novels);
	}
	
	@Test
	public void testNovelSpiderFactory() {
		INovelSpider novelSpider = NovelSpiderFactory.getNovelSpider("http://www.kanshuzhong.com/toplist/lastupdate/1/");
		List<Novel> novels = novelSpider.getNovels("http://www.kanshuzhong.com/toplist/lastupdate/1/");
		System.out.println(novels);
	}
}
