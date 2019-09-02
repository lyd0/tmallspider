package com.edmar.tmallspider.utils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HttpUtils {
    public static Document fetchDoc(String url) throws Exception {

        Map map = new HashMap();
//        map.put("_m_h5_tk", "76cc1e42c8f5737467b3ed187af77888_1562668375038");
//        map.put("_m_h5_tk_enc", "a16bbaa1400973a109649ab12c824365");
//        map.put("_tb_token_", "315577e53bfd5");
//        map.put("cna", "9+qoFYptTzYCAXWlLmJgElrz");
//        map.put("cookie2", "1d5eb128be2f7ed2ca4df10560600a32");
//        map.put("cq", "ccp%3D1");
//        map.put("csg","ef4d0fcb");

//        map.put("_l_g_", "Ug%3D%3D");
//        map.put("tk_trace", "1");

//        map.put("t", "df897418d7dde3d86a23a9812af14e7c");


//        map.put("uc1", "cookie16=U%2BGCWk%2F74Mx5tgzv3dWpnhjPaQ%3D%3D&cookie21=VT5L2FSpccLuJBreK%2BBd&cookie15=V32FPkk%2Fw0dUvg%3D%3D&existShop=false&pas=0&cookie14=UoTaGqtL3ZSiHA%3D%3D&tag=8&lng=zh_CN");
//        map.put("uc3", "vt3=F8dBy3%2F6%2FsE00IU%2FPyc%3D&id2=Vv7KpAwf4DM7&nk2=D5LbqtATPCDj&lg2=U%2BGCWk%2F75gdr5Q%3D%3D");
//        map.put("tracknick", "l123wms88");
//        map.put("lgc", "l123wms88");
//        map.put("csg", "ef4d0fcb");
//        map.put("skt", "6daec765ab374ae6");
//        map.put("otherx", "e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0");
        map.put("enc", "gWHku3YsK0BHvnTpwJsGXS6b%2FQPCTwi6T5qiBlyoET%2FI3KT299LTKJhGKntHkx7lid9R7ntARDJx3%2FzjlyqAPQ%3D%3D");
//        map.put("swfstore", "__ll%3D-1%26_ato%3D0");
//        map.put("whl", "-1%260%260%260");
//        map.put("cq", "ccp%3D1");
//        map.put("cookie1", "UNjQYR5SnNibApzyxTzgXdb2CBya9Y9mez%2Fp%2BIUL2ww%3D");

        Document doc=null;

        Exception ex =  new Exception();
        for (int i = 0; i <= 3; i++) {
            try {
                long t = System.currentTimeMillis();
                doc = Jsoup.connect(url)
                        .cookies(map)
                        .header("referer","https://detail.tmall.com/item.htm?id=531672637569&sku_properties=5919063:10010&spuId=572300838&sellerId=2134802284&order=3&currentPage=1&append=0&content=1&ua=098")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36").get();
                System.out.println(System.currentTimeMillis()-t);
                return doc;
            } catch (IOException e) {
                ex=e;
            }

        }
        throw ex;
    }
}
