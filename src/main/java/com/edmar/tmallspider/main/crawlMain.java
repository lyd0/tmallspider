package com.edmar.tmallspider.main;

import com.edmar.tmallspider.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class crawlMain {

    public static void main(String[] args) throws Exception{
        List goodsIds = crawlAllGoods("https://canon.tmall.com/i/asynSearch.htm?mid=w-14873283718-0&pageNo=9");
        System.out.println(goodsIds);
        System.out.println("size: "+goodsIds.size());




    }


    



    public static List crawlAllGoods(String url) throws Exception{
        List goodsIds = new ArrayList();

        Document doc = HttpUtils.fetchDoc(url);
        System.out.println("=====================");
        //去掉多余斜杠
        String str = doc.toString();
        str = str.replaceAll("\\\\&quot;", "");
        doc = Jsoup.parse(str);
        System.out.println("=====================");
//		System.out.println(doc);
        Elements liList = doc.select("dl.item");

        for (Element item : liList) {
            // 商品ID
            String id = item.attr("data-id");
            goodsIds.add(id);
            System.out.println("商品ID："+id);
            // 商品名称
            String name = item.select("dd.detail").select("a").text();
            System.out.println("商品名称："+name);
            // 商品价格
            String price = item.select("span.c-price").text();
            System.out.println("商品价格："+price);
            // 商品网址
            String goodsUrl = item.select("a.J_TGoldData").attr("href");
            System.out.println("商品网址："+goodsUrl);
            // 商品图片网址
//			String imgUrl = item.select("div.productImg-wrap.").select("a").select("img").attr("data-ks-lazyload");
//			System.out.println("商品图片网址："+imgUrl);
            System.out.println("------------------------------------");
        }
        return goodsIds;
    }
}
