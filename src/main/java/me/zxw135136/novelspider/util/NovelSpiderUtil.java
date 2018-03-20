package me.zxw135136.novelspider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import me.zxw135136.novelspider.bean.ChapterSpiderRule;
import me.zxw135136.novelspider.bean.NovelSpiderRule;
import me.zxw135136.novelspider.bean.SiteSpiderRule;

/**
 * 解析抓取规则配置文件的工具类
 * @author 张晓伟
 *
 */
public class NovelSpiderUtil {
	
	/** 抓取某一个页面时最大的尝试次数10 */
	public static final int MAX_TRY_TIME = 10;
	
	/**
	 * key:小说网站的url，value:小说网站的具体具体爬取规则（名字、编码、url、小说及章节选择规则）
	 */
	public static final Map<String, SiteSpiderRule> SPIDER_RULES = new HashMap<>();
	
	static {
		init();
	}

	/**
	 * 初始化SITE_SPIDER_RULES，保存Spider-Rule.xml中的信息至SITE_SPIDER_RULES
	 */
	@SuppressWarnings("unchecked")
	private static void init() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(NovelSpiderUtil.class.getClassLoader().getResourceAsStream("conf/Spider-Rule.xml"));
			//得到sites标签
			Element root = doc.getRootElement();

			//得到每一个site标签
			List<Element> sites = root.elements("site");

			for (Element site : sites) {
				
				SiteSpiderRule siteSpiderRule = new SiteSpiderRule();
				
				//得到site标签的属性
				List<Attribute> siteAttrs = site.attributes();

				//将site标签的所有属性放入siteSpiderRules
				if(siteAttrs!=null && siteAttrs.size()>0) {
					
					siteSpiderRule.setUrl(siteAttrs.get(0).getValue());
					siteSpiderRule.setTitle(siteAttrs.get(1).getValue());
					siteSpiderRule.setCharset(siteAttrs.get(2).getValue());
					
				}

				NovelSpiderRule novelSpiderRule = new NovelSpiderRule();

				//得到site下所有子标签
				List<Element> siteSubs = site.elements();
				//遍历所有site的子标签
				for (Element siteSub : siteSubs) {
					//如果是novel标签
					if ("novel".equals(siteSub.getName())){

						List<Element> novelSubs = siteSub.elements();
						//遍历novel下子标签
						for (Element novelSub : novelSubs) {
							
							//如果是novel-selector标签
							if ("novel-selector".equals(novelSub.getName())) {
								
								novelSpiderRule.setNovelSelector(novelSub.getTextTrim());
							
							//如果是novel-next-page-selector标签
							} else if("novel-next-page-selector".equals(novelSub.getName())) {
								
								novelSpiderRule.setNovelNextPageSelector(novelSub.getTextTrim());
								
							//如果是chapter标签
							} else if ("chapter".equals(novelSub.getName())) {	
								
								ChapterSpiderRule chapterSpiderRule = new ChapterSpiderRule();
								List<Element> chapterSubs = novelSub.elements();
								//遍历chapter下所有子标签
								for (Element chapterSub : chapterSubs) {
									if ("list-selector".equals(chapterSub.getName())) {
										
										chapterSpiderRule.setListSelector(chapterSub.getTextTrim());
										
									} else if ("detail-title-selector".equals(chapterSub.getName())) {
										
										chapterSpiderRule.setDetailTitleSelector(chapterSub.getTextTrim());
										
									} else if ("detail-content-selector".equals(chapterSub.getName())) {
										
										chapterSpiderRule.setDetailContentSelector(chapterSub.getTextTrim());
										
									} else if ("detail-prev-selector".equals(chapterSub.getName())) {
										
										chapterSpiderRule.setDetailPrevSelector(chapterSub.getTextTrim());
										
									} else if ("detail-next-selector".equals(chapterSub.getName())) {
										
										chapterSpiderRule.setDetailNextSelector(chapterSub.getTextTrim());
										
									} 
								}
								novelSpiderRule.setChapterSpiderRule(chapterSpiderRule);
							}
							
						}

						siteSpiderRule.setNovelSpiderRule(novelSpiderRule);

					}

				}
				//将siteSpiderRules放入SPIDER_RULES
				//key：site标签的url属性的网站名，value：爬取规则
				SPIDER_RULES.put(siteAttrs.get(0).getValue().split("[.]")[1],siteSpiderRule);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据小说网站的url拿到对应网站的解析规则
	 * @param url
	 * @return
	 */
	public static SiteSpiderRule getSpiderRule(String url) {
		Set<Entry<String, SiteSpiderRule>> entries = SPIDER_RULES.entrySet();

		SiteSpiderRule spiderRule = null;

		for (Map.Entry<String, SiteSpiderRule> entry : entries) {
			if (url.contains(entry.getKey())) {
				spiderRule = entry.getValue();
			}
		}

		if (spiderRule != null) {
			return spiderRule;
		} else {
			throw new RuntimeException("url="+url+":" + "没有支持该url的解析规则");
		}

	}

	
	/**
	 * 获取小说状态
	 * @param status
	 * @return
	 */
	public static int getNovelStatus(String status) {
		if (status.contains("连载")) {
			return 1;
		} else if (status.contains("完结") || status.contains("完成")) {
			return 2;
		} else {
			throw new RuntimeException ("不支持的书籍状态：" + status);
		}
	}
	
	/**
	 * 格式化日期字符串为日期对象
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr, String pattern) throws ParseException {
		if ("MM-dd".equals(pattern)) {
			pattern = "yyyy-MM-dd";
			dateStr = getDateField(Calendar.YEAR) + "-" + dateStr;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		return date;
	}
	
	/**
	 * 获取本时刻的字符量
	 * @param field
	 * @return
	 */
	private static String getDateField(int field) {
		Calendar cal = new GregorianCalendar();
		return cal.get(field) + "";
	}
	
}
