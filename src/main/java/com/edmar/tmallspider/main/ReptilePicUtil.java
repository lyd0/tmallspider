package com.edmar.tmallspider.main;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;

public class ReptilePicUtil {


    /**
     * 获取页面文档字串(等待异步JS执行)
     *
     * @param url 页面URL
     * @return
     * @throws Exception
     */
    public static String getHtmlPageResponse(String url) throws Exception {
        String result = "";

        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        Cookie cookie = new Cookie("taobao.com","enc","gWHku3YsK0BHvnTpwJsGXS6b%2FQPCTwi6T5qiBlyoET%2FI3KT299LTKJhGKntHkx7lid9R7ntARDJx3%2FzjlyqAPQ%3D%3D");
        webClient.getCookieManager().addCookie(cookie);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        webClient.getOptions().setTimeout(30000);//设置“浏览器”的请求超时时间
        webClient.setJavaScriptTimeout(30000);//设置JS执行的超时时间

        HtmlPage page;
        try {
            page = webClient.getPage(url);
        } catch (Exception e) {
            webClient.close();
            throw e;
        }
        webClient.waitForBackgroundJavaScript(30000);//该方法阻塞线程

        result = page.asXml();
        System.out.println(page);
        System.out.println(result);
        webClient.close();

        return result;
    }


    //解析xml获取ImageUrl地址
    public static List<String> getImageUrl(String html,String className){
        List<String> result = new ArrayList<>();
        Document document = Jsoup.parse(html);//获取html文档
        List<Element> infoListEle = document.getElementsByClass(className);//获取元素节点等
        infoListEle.forEach(element -> {
            result.add(element.attr("src"));
        });
        return result;
    }

    public static void main(String[] args) throws Exception {
        String xml = ReptilePicUtil.getHtmlPageResponse("https://canon.tmall.com/i/asynSearch.htm?mid=w-14873283718-0&pageNo=4");
//        System.out.println(JSON.toJSONString(ReptilePicUtil.getImageUrl(xml,"index-logo-src")));
    }

}
