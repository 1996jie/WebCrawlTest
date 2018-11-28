/**
 * YangJie
 * 2018年11月21日下午9:09:15
 */
package com.yj.WebCrawlTest;

import java.util.Set;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * <p>
 * MyCrawler
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author YangJie
 * @data 2018年11月21日下午9:09:15
 * @version 1.0
 */
public class MyCrawler extends WebCrawler {
    /**
     * 正则表达式匹配指定的后缀文件
     */
    private final static Pattern FILTERS = Pattern
            .compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");

    /**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     * 第一个参数referringPage封装了当前爬取的页面信息 第二个参数url封装了当前爬取的页面url信息
     * 在这个例子中，我们指定爬虫忽略具有css，js，git，...扩展名的url，只接受以“http://www.ics.uci.edu/”开头的url。
     * 在这种情况下，我们不需要referringPage参数来做出决定。
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();// 得到小写的url
        return !FILTERS.matcher(href).matches() // 正则匹配，过滤掉我们不需要的后缀文件
                && href.startsWith("https://blog.csdn.net/");// 只接受以“http://www.ics.uci.edu/”开头的url
    }

    /**
     * 当一个页面被提取并准备好被你的程序处理时，这个函数被调用。
     * 
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();// 获取url
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {// 判断是否是html数据
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();// 强制类型转换，获取html数据对象
            String text = htmlParseData.getText();// 获取页面纯文本（无html标签）
            String html = htmlParseData.getHtml();// 获取页面Html
            Set<WebURL> links = htmlParseData.getOutgoingUrls();// 获取页面输出链接

            System.out.println("纯文本长度: " + text.length());
            System.out.println("html长度: " + html.length());
            System.out.println("链接个数 " + links.size());
        }
    }
    
    // 以下内容为crawler4j框架示例，来自项目源码
    // public void visit(Page page) {
    // int docid = page.getWebURL().getDocid();
    // String url = page.getWebURL().getURL();
    // String domain = page.getWebURL().getDomain();
    // String path = page.getWebURL().getPath();
    // String subDomain = page.getWebURL().getSubDomain();
    // String parentUrl = page.getWebURL().getParentUrl();
    // String anchor = page.getWebURL().getAnchor();
    //
    // logger.debug("Docid: {}", docid);
    // logger.info("URL: {}", url);
    // logger.debug("Domain: '{}'", domain);
    // logger.debug("Sub-domain: '{}'", subDomain);
    // logger.debug("Path: '{}'", path);
    // logger.debug("Parent page: {}", parentUrl);
    // logger.debug("Anchor text: {}", anchor);
    //
    // if (page.getParseData() instanceof HtmlParseData) {
    // HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
    // String text = htmlParseData.getText();
    // String html = htmlParseData.getHtml();
    // Set<WebURL> links = htmlParseData.getOutgoingUrls();
    // System.out.println(text);
    // logger.debug("Text length: {}", text.length());
    // logger.debug("Html length: {}", html.length());
    // logger.debug("Number of outgoing links: {}", links.size());
    // }
    //
    // Header[] responseHeaders = page.getFetchResponseHeaders();
    // if (responseHeaders != null) {
    // logger.debug("Response headers:");
    // for (Header header : responseHeaders) {
    // logger.debug("\t{}: {}", header.getName(), header.getValue());
    // }
    // }
    //
    // logger.debug("=============");
    // }
}
