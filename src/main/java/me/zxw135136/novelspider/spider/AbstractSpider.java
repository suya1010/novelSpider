package me.zxw135136.novelspider.spider;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import me.zxw135136.novelspider.util.NovelSpiderHttpGet;

/**
 * 抓取页面类
 * @author 张晓伟
 *
 */
public abstract class AbstractSpider {
	
	/**
	 * 根据url抓取页面所有信息,返回String,需要使用Jsoup解析
	 * @param url 要抓取网页的url
	 * @param charset 要抓取网页的编码格式
	 * @return
	 * @throws Exception 
	 */
	protected String crawl(String url,String charset) throws Exception {
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url));) {
			
			String result = EntityUtils.toString(httpResponse.getEntity(), charset);
			return result;
		} catch (Exception  e){
            throw new RuntimeException(e);
        }
	}
}
