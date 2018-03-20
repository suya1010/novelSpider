package me.zxw135136.novelspider.test;

import org.junit.Test;

import me.zxw135136.novelspider.bean.SiteSpiderRule;
import me.zxw135136.novelspider.util.NovelSpiderUtil;

/**
 * 
 * @author 张晓伟
 *
 */
public class NovelSpiderUtilTest {
	
	@Test
	public void testGetSpiderRule() {
		SiteSpiderRule spiderRule1 = NovelSpiderUtil.getSpiderRule("23wx");
		System.out.println(spiderRule1);
		SiteSpiderRule spiderRule2 = NovelSpiderUtil.getSpiderRule("kanshuzhong");
		System.out.println(spiderRule2);
	}
}
