package com.yj.WebCrawlTest;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Hello world!
 *
 */
public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "E:/crawler";// 定义爬虫数据存储位置
        int numberOfCrawlers = 7;// 定义了7个爬虫，也就是7个线程

        CrawlConfig config = new CrawlConfig();// 定义爬虫配置
        config.setCrawlStorageFolder(crawlStorageFolder);// 设置爬虫文件存储位置
        config.setMaxDepthOfCrawling(1);// 设置爬虫深度为1，种子本身深度为0
        config.setMaxPagesToFetch(2);// 设置最多爬多少个页面
        // config.setIncludeHttpsPages(true);//启用SSL
        // crawlConfig.setIncludeBinaryContentInCrawling(true);//启用二进制爬取
        // crawlConfig.setPolitenessDelay(politenessDelay);// 设置等待时间，默认大于200ms
        /*
         * 实例化爬虫控制器。
         */
        PageFetcher pageFetcher = new PageFetcher(config);// 实例化页面获取器
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();// 实例化爬虫机器人配置
        // 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件
        // 规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
                pageFetcher);
        // 实例化爬虫控制器
        CrawlController controller = new CrawlController(config, pageFetcher,
                robotstxtServer);

        /*
         * 对于每次抓取，您需要添加一些种子网址。 这些是抓取的第一个URL，然后抓取工具开始跟随这些页面中的链接
         */
        controller.addSeed("https://blog.csdn.net/ly_dengle?t=2");
        // controller.addSeed("http://www.baidu.com/");
        // controller.addSeed("http://www.ics.uci.edu/");

        /**
         * 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
