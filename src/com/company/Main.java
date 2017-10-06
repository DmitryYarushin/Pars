import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String query = "&inomarka=1&go_search=2";
        int min = 350000;
        int max = 420000;
        String region = "74";
        String min_price = "?minprice=" + min;
        String max_price = "&maxprice="+ max;

        List<Articule> articuleList = new ArrayList<>();
        for (int i =1; i<100; i++) {
            Document doc = Jsoup.connect("http://auto.drom.ru/region" + region + "/page" + i + "/"+ min_price + max_price  + query).get();

            Elements divElements = doc.getElementsByAttributeValue("class", "b-media-cont b-media-cont_modifyMobile_sm");//"b-advItem__content");

            divElements.forEach(divElement -> {
                Element aElement = divElement.child(0);
                String url = aElement.attr("href");
                String title = aElement.child(1).text();

                articuleList.add(new Articule(url, title));
            });
        }
        System.out.println(min_price + max_price + query );
        articuleList.forEach(System.out::println);
    }
}
class Articule {

    private String url;
    private String name;

    public Articule(String url, String name) {
        this.url = url;
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Articule { " +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}




