package GoogleSearch;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.*;
import org.jsoup.nodes.Element;



public class GoogleCustomSearch {

	public ArrayList<String> search(String term) {
		String query = term;
		ArrayList<String> links =new ArrayList<String>();
		int searchNum = 20;
		final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
		try {
			  
			String searchURL = GOOGLE_SEARCH_URL + "?q="+query+"&num="+ searchNum;
			Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
			Elements results = doc.select("h3.r > a");

			for (Element result : results) {
				String linkHref = result.attr("href");
				links.add(linkHref.substring(7, linkHref.indexOf("&")));
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		return links;
	}

}