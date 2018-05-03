package com.kd.example;

import com.kd.crawlers.CsdnCrawler;
import org.springframework.cglib.proxy.Proxy;

import java.net.InetSocketAddress;

/**
 * Created by happy on 2018/5/3.
 */
public class CsdnTest {
    public static void main(String[] args) throws Exception {
        CsdnCrawler crawler = new CsdnCrawler("crawler", true);


        crawler.addSeed("https://so.csdn.net/so/search/s.do?q=webcollector+setUseragent&t=blog&o=&s=&l=");
        crawler.addRegex("https://blog.csdn.net/.*/article/details/.*");

        /*设置保存爬取记录的文件夹*/
//        crawler.setCrawlPath("crawl_hfut");

         /*设置爬虫是否为断点爬取*/
//        crawler.setResumable(false);

//        /*设置代理服务器*/
//        Proxy proxy=new Proxy(Proxy.Type.HTTP, new InetSocketAddress("14.18.16.67",80));
//        crawler.setProxy(proxy);
//        /*设置User-Agent*/
//        crawler.setUseragent("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0");

        /*可以设置每个线程visit的间隔，这里是毫秒*/
        //crawler.setVisitInterval(1000);
        /*可以设置http请求重试的间隔，这里是毫秒*/
        //crawler.setRetryInterval(1000);

        //爬取前多少条
        crawler.getConf().setTopN(10);
        //开启线程数
        crawler.setThreads(10);
        //爬取的深度
        crawler.start(10);


    }
}
