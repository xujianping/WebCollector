package com.kd.crawlers;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.net.Proxys;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;

/**
 * Created by happy on 2018/5/3.
 */
public class CsdnCrawler extends BreadthCrawler {
//    private static Proxys proxys = null;


    public CsdnCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
//        proxys.add("x.x.x.x" , 8080);
//        proxys.add("y.y.y.y" , 3306);

    }
/*
        可以往next中添加希望后续爬取的任务，任务可以是URL或者CrawlDatum
        爬虫不会重复爬取任务，从2.20版之后，爬虫根据CrawlDatum的key去重，而不是URL
        因此如果希望重复爬取某个URL，只要将CrawlDatum的key设置为一个历史中不存在的值即可
        例如增量爬取，可以使用 爬取时间+URL作为key。

        新版本中，可以直接通过 page.select(css选择器)方法来抽取网页中的信息，等价于
        page.getDoc().select(css选择器)方法，page.getDoc()获取到的是Jsoup中的
        Document对象，细节请参考Jsoup教程
    */

    public void visit(Page page, CrawlDatums netxPage) {

//        System.out.println("URL:"+page.url());
//        System.out.println("Content-Type:"+ page.contentType());
//        System.out.println("Code:"+page.code());
//        System.out.println("-----------------------------");

        if (page.matchUrl("https://blog.csdn.net/.*/article/details/.*")) {
            String title = page.select("h6[class=title-article]").first().text();
            String author = page.select("span[class=time]").first().text();
            String readCount = page.select("span[class=read-count]").first().text();
//            String content=page.select("div[class=markdown_views]").first().text();
            String tagLink = page.select("a[class=tag-link]").first().text();
            System.out.println("---title:" + title + "\tauthor:" + author);
            System.out.println("---tagLink:" + tagLink + "\treadCount:" + readCount);
//            System.out.println("内容："+content);
            try {
                String content =ContentExtractor.getContentByHtml(page.html(), page.url());
                System.out.println("content\t"+content);

                Element contentElement = ContentExtractor.getContentElementByHtml(page.html(), page.url());
                System.out.println("Element\t"+contentElement);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }



}
