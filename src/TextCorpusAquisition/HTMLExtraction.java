package TextCorpusAquisition;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLExtraction {
	
	private String url;
	private String title;
	private Document htmlDoc;
	private Element body;

	
		public HTMLExtraction(){
		

				/*	//URL to test
		      String url = "https://en.wikipedia.org/wiki/Web_crawler";   
		      setUrl(url);
		      
		      		//Document with HTML
		      Document htmlDoc = establishConnection(url);

		      		//web page title
		      title =  htmlDoc.title();
		      setTitle(title);
	
		      		//get html body and cleaned body
		      Element body = htmlDoc.body();
		      Elements paragraphs = setBody(body);
		      
		      		//print cleaned body text
		      for (Element paragraph : paragraphs) {
		         System.out.println(paragraph.text());
		      }
		      */
		}
		
				//Connect to web page and extract HTML
		public Document establishConnection(String url){
			
			try {
				htmlDoc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return htmlDoc;
		}
		
		public void extract(String url1) {
			//URL to test
		      String url = url1;   
		      setUrl(url);
		      
		      		//Document with HTML
		      Document htmlDoc = establishConnection(url);

		      		//web page title
		      title =  htmlDoc.title();
		      setTitle(title);
	
		      		//get html body and cleaned body
		      Element body = htmlDoc.body();
		      Elements paragraphs = setBody(body);
		      
		      		//print cleaned body text
		      for (Element paragraph : paragraphs) {
		         System.out.println(paragraph.text());
		      }
		}
		public String getTitle(){
			
			return title;
		}
		
		public void setTitle(String title){
			
			this.title = title;
		}
		
		public Elements setBody(Element body){
			
			Elements paragraphs = body.getElementsByTag("p");
			
			return paragraphs;
		}
		
		public String getUrl(){
			
			return url;
		}
		
		public void setUrl(String url){
			
			this.url = url;
		}

		
		
		
	}
	


