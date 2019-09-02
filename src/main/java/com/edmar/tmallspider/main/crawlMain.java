package com.edmar.tmallspider.main;

import com.edmar.tmallspider.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class crawlMain {

    public static void main(String[] args) throws Exception{
//        List goodsIds = crawlAllGoods("https://canon.tmall.com/i/asynSearch.htm?mid=w-14873283718-0&pageNo=9");
//        System.out.println(goodsIds);
//        System.out.println("size: "+goodsIds.size());

//        crawlDetails("https://detail.tmall.com/item.htm?id=531672637569");
        crawlRate();


    }

    public static void crawlRate() throws Exception{
        String url = "https://rate.tmall.com/list_detail_rate.htm?itemId=531672637569&spuId=572300838&sellerId=2134802284&order=3&currentPage=4&append=0&content=1&tagId=&posi=&picture=&groupId=&ua=098%23E1hv09vnvPOvUvCkvvvvvjiPRFqOzjnHR2qZljnEPmPUgjYWRLqwQj3WPsqOgjiR9phvHnQGOTcAzYswzVor7%2FYSMmMw9HuCdphvmpvWPBTYSp2y18wCvvpvvUmm2QhvCvvvMM%2FEvpvVmvvC9jXPuphvmvvv9b77wBMtKphv8vvvvvCvpvvvvvvmLhCv22gvvUnvphvpgvvv96CvpCvOvvm2phCvhC9EvpCW2U1NvvwTD7zOaB46NB3r1WCl5trgon2I1WQXVjHa0f0DyBvOJ1kHsX7v5C6AxYjxAfyprj6OfaClMCuOw6bI6nF9eBO0747B9Wma%2BphCvvOvCvvvphvtvpvhvvvvvUwCvvpvCvvvdphvmpvWKmXLZQvCRLyCvvpvvvvv&needFold=0&_ksTS=1562678660153_4636&callback=jsonp4567";
        Document doc = HttpUtils.fetchDoc(url);

        String str = doc.body().text();
        str = str.replace("jsonp4567(","");
        str = str.substring(0, str.length()-1);
        JSONObject root = new JSONObject(str);
        System.out.println(root);
        JSONObject rateDetail = new JSONObject(root.get("rateDetail").toString());
        System.out.println(rateDetail);
        String rateList = rateDetail.get("rateList").toString();
        System.out.println(rateList);
        System.out.println(rateList.length());
    }
    public static void crawlDetails(String url) {
//        Document doc = HttpUtils.fetchDoc(url);
//        Elements
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
