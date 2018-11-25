package XmlMaker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XmlMaker {
	private String title;
	private String timeAccessed;
	private String link;
	private String searchTerm;
	
	public XmlMaker(String title, String timeAccessed, String link, String searchTerm)
	{
		this.setTitle(title);
		this.setTimeAccessed(timeAccessed);
		this.setLink(link);
		this.setSearchTerm(searchTerm);
		this.makeXml();
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTimeAccessed(String timeAccessed)
	{
		this.timeAccessed = timeAccessed;
	}
	
	public String getTimeAccessed()
	{
		return this.timeAccessed;
	}
	
	public void setLink(String link)
	{
		this.link = link;
	}
	
	public String getLink()
	{
		return this.link;
	}
	
	public void setSearchTerm(String searchTerm)
	{
		this.searchTerm = searchTerm;
	}
	
	public String getSearchTerm()
	{
		return this.searchTerm;
	}
	
	public void makeXml()
	{
		String exportXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		exportXml += "<Website>\n";
		exportXml += " <Title>" + this.getTitle() + "</Title>\n";
		exportXml += " <TimeAccessed>" + this.getTimeAccessed() + "</TimeAccessed>\n";
		exportXml += " <Link>" + this.getLink() + "</Link>\n";
		exportXml += " <SearchTerm>" + this.getSearchTerm() + "</SearchTerm>\n";
		exportXml += "</Website>";
		
			// Writes to a xml file named the same as the title of the article
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(this.getTitle() + ".xml"));
			writer.write(exportXml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(exportXml);
	}
}
