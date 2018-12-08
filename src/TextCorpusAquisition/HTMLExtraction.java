package TextCorpusAquisition;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HTMLExtraction {
	
	private String url;
	private String title;
	private Document htmlDoc;
	private Elements paragraph;
	private String searchTerm;
	private int wordCount;
	private String dateTime;
	private int pKey;
	private String Xml;
	
    private final String username = "TextCorpusProgram";
    private final String password = "Pa$$word!";
    private final String connectionUrl = "jdbc:sqlserver://";
    private final String serverName = "68.1.83.163\\NGL4";
    private final String portNumber = "51487";
    private final String databaseName = "TextCorpusData";
    
    

	
		public HTMLExtraction(String searchTerm)
		{
			this.setSearchTerm(searchTerm);
			this.deleteOldSql();
			this.deleteOldFiles();
		}
		
				//Connect to web page and extract HTML
		public Document establishConnection(String url){
			
			try {
				htmlDoc = Jsoup.connect(url).get();
			} catch (IOException e) {
				System.out.println("Connection Error");
			}
			
			return htmlDoc;
		}
		
		public void setInformation(String url, int pKey)
		{
			this.setUrl(url);
		    this.setPKey(pKey);
		    	// Date and time of connection
		    this.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		    try
		    {
		    		//Document with HTML
		    	this.setHTMLDoc(Jsoup.connect(url).userAgent("Mozilla").timeout(0).get());
		    		//web page title
		    	this.setTitle(htmlDoc.title());
	      			// Sets the metadata XML with the necessary information
		    	this.setXml();
			}
			catch(Exception e) {
				System.out.println("Extraction Error");
			}
		}
			
			// This sets all the objects for the website and uses it for the extraction of the raw HTML, extracted text, and metadata XML
		public void extract(String url, int pKey) {
					// Sets the necessary information
			  this.setInformation(url, pKey);
			  		// Extracts the raw HTML
		      this.extractHtml();
		      		// Extracts the extracted text
		      this.extractText();
		      		// Exports the metadata XML into your file system
		      this.exportXml();
		      		// Imports all the information of the website into the database
		      this.importIntoDatabase();
		}
			
			// Extracts the raw HTML from the web source and exports it into your file system
		public void extractHtml()
		{
			//Extracting the HTML
			final File f = new File("Crawl Results/" + searchTerm + "/" + pKey + "/" + pKey + ".html");
			      
			try 
			{
				FileUtils.writeStringToFile(f, htmlDoc.outerHtml());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
			// Extracts the text from the web source and exports it into your file system
		public void extractText() 
		{
				//Extracting the text
			final File f = new File("Crawl Results/" + searchTerm + "/" + pKey + "/" + pKey + ".txt");
			String output = "";
			
				//get html body and cleaned body
			Element body = htmlDoc.body();
			Elements paragraphs = setBody(body);
			setParagraph(paragraphs);
			
				//add cleaned body text to output
			for (Element paragraph : paragraphs) 
			{
				output += paragraph.text();
			}
			
				//get word count
			Pattern patt = Pattern.compile("[a-zA-Z]+");
			Matcher match = patt.matcher(paragraphs.text().toString());
			wordCount = 0;
			while(match.find())
			{
				wordCount++;
			}
			setWordCount(wordCount);
		      
			try 
			{
				FileUtils.writeStringToFile(f, output);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void setHTMLDoc(Document htmlDoc)
		{
			this.htmlDoc = htmlDoc;
		}
		
		public Document getHTMLDoc()
		{
			return this.htmlDoc;
		}
		
		public String getTitle(){
			
			return this.title;
		}
		
		public void setTitle(String title){
			
			this.title = title;
		}
		
		public Elements setBody(Element body){
			
			Elements paragraphs = body.getElementsByTag("p");
			
			return paragraphs;
		}
		
		public void setParagraph(Elements paragraph)
		{
			this.paragraph = paragraph;
		}
		
		public Elements getParagraph()
		{
			return this.paragraph;
		}
		
		public void setDateTime(String dateTime)
		{
			this.dateTime = dateTime;
		}
		
		public String getDateTime()
		{
			return this.dateTime;
		}
		
		public void setSearchTerm(String searchTerm)
		{
			this.searchTerm = searchTerm;
		}
		
		public String getSearchTerm()
		{
			return this.searchTerm;
		}
		
		public String getUrl(){
			
			return this.url;
		}
		
		public void setUrl(String url){
			
			this.url = url;
		}
		
		public void setWordCount(int wordCount)
		{
			this.wordCount = wordCount;
		}
		
		public int getWordCount()
		{
			return this.wordCount;
		}
		
		public void setPKey(int pKey)
		{
			this.pKey = pKey;
		}

		public int getPKey()
		{
			return this.pKey;
		}
		
		public String getXml(){
			
			return Xml;
		}
		
		private String getConnectionUrl()
		{
			return connectionUrl + serverName + ":" + portNumber + ";DatabaseName=" + databaseName;
		}
		
			// Imports all the necessary data into the database with the associated information
		public void importIntoDatabase()
		{
		    try
		    {
		    	Connection conn = DriverManager.getConnection(getConnectionUrl(), username, password);
		    	Statement st = conn.createStatement();
		    	String SQL_WebPageInfo = ("INSERT INTO [TextCorpusData].[WebPageInfo] VALUES(" + pKey + ", '" + getSearchTerm() + "', '" + getUrl() + "', N'" + getTitle().replace("\'", "\'\'") + "', '" + getDateTime() + "', " + getWordCount() + ");");
		        st.executeUpdate(SQL_WebPageInfo);

    			//Fix the closing ' for SQL insert
		    	String htmlFix = getHTMLDoc().toString().replace("\'", "\'\'");
		    	
		        String SQL_PageMetaData = ("INSERT INTO [TextCorpusData].[PageMetadata] VALUES (" + pKey + ", N'" + htmlFix + "', N'" + getXml().replace("\'", "\'\'") + "');"); 
		        st.executeUpdate(SQL_PageMetaData);

				Pattern patt = Pattern.compile("[a-zA-Z]+");
				Matcher match = patt.matcher(getParagraph().text().toString());

				while (match.find()) 
				{ 
		            String SQL_WordsExtracted = ("INSERT INTO [TextCorpusData].[WordsExtracted] VALUES (" + pKey + ", '" + match.group() + "');"); 
			        st.executeUpdate(SQL_WordsExtracted);
				}
				
				
				System.out.println("SQL Statement Completed!!!");
		    }
		    // Handle any errors that may have occurred.
		    catch (SQLException e) 
		    {
		        System.out.println("Error in connecting to the Sql Server");
		        e.printStackTrace();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }

		}
		
			// Sets up the basic XML string with the according information
		public void setXml()
		{
			String exportXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			exportXml += "<Website>\n";
			exportXml += " <Title>" + this.getTitle() + "</Title>\n";
			exportXml += " <TimeAccessed>" + this.getDateTime() + "</TimeAccessed>\n";
			exportXml += " <Link>" + this.getUrl() + "</Link>\n";
			exportXml += " <SearchTerm>" + this.getSearchTerm() + "</SearchTerm>\n";
			exportXml += "</Website>";
			
			this.Xml = exportXml;
		}
		
			// Exports the metadata XML into your system's file system
		public void exportXml()
		{
			final File f = new File("Crawl Results/" + searchTerm + "/" + pKey + "/" + pKey + ".xml");
		      
			try 
			{
				FileUtils.writeStringToFile(f, this.getXml());
			} 
			catch (IOException e) 
			{
				System.out.println("Error in making xml file");
				e.printStackTrace();
			}
		}
		
			// Deletes old files from your system such as the raw HTML, extracted text, and metadata XML
		public void deleteOldFiles()
		{
			try 
			{
				FileUtils.deleteDirectory(new File("Crawl Results"));
			} 
			catch (IOException e) 
			{
				System.out.println("Directory not there");
			}
		}
		
			// Deletes old crawls from the database
		public void deleteOldSql()
		{
			try
		    {
		    	Connection conn = DriverManager.getConnection(getConnectionUrl(), username, password);
		    	Statement st = conn.createStatement();
		    	st.execute("DELETE FROM [TextCorpusData].[PageMetadata]");
		    	st.execute("DELETE FROM [TextCorpusData].[WordsExtracted]");
		    	st.execute("DELETE FROM [TextCorpusData].[WebPageInfo]");
		    }
			catch (SQLException e) 
		    {
		        System.out.println("Error in connecting to the Sql Server");
		        e.printStackTrace();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		}
	}
