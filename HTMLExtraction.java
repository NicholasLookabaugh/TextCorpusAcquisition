package TextCorpusAquisition;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class HTMLExtraction {
	
	
		public HTMLExtraction(){
		

		
		      String url = "https://en.wikipedia.org/wiki/Web_crawler";
		      Document document = null;
			try {
				document = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   //   System.out.println(document.body());
		      
		      
	
		      Element body = document.body();
		      Elements paragraphs = body.getElementsByTag("p");
		      
		      
		      for (Element paragraph : paragraphs) {
		         System.out.println(paragraph.text());
		      }
		}  
		

	}
	


