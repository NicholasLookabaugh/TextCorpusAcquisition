package testCases;
import TextCorpusAquisition.HTMLExtraction;
import static org.junit.Assert.*;
import org.junit.Test;

public class HTMLExtractionTests 
{
	@Test
	public void testofExtraction() 
	{
		HTMLExtraction x = new HTMLExtraction("Test");
		x.extract("http://www.karelia.com/support/sandvox/help/z/Site_Title.html", 1);
		assertEquals("Site Title", x.getTitle());
		assertEquals("http://www.karelia.com/support/sandvox/help/z/Site_Title.html", x.getUrl());
		//assertEquals( "", (x.getParagraph()).substring(0, 8));
		assertEquals("Test", x.getSearchTerm());
	}
}
