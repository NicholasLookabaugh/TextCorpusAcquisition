package WindowsApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import TextCorpusAquisition.HTMLExtraction;
import GoogleSearch.GoogleCustomSearch;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CrawlerApp {

	private JFrame frame;
	private static AtomicInteger pKey = new AtomicInteger(0);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			//Random stuff for testing purposes
		//ArrayList<String> links = new ArrayList<String>();
		//HTMLExtraction x = new HTMLExtraction();
		//x.extract("https://en.wikipedia.org/wiki/Paul_Revere");
		//GoogleCustomSearch y = new GoogleCustomSearch();
		//links = y.search("freedom as a concept", 1);
		//x.extractMetaList(links);
	    //System.out.println(Arrays.toString(links.toArray()));
	    //x.getMetaData("https://en.wikipedia.org/wiki/Paul_Revere");
		//x.extractList(links);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrawlerApp window = new CrawlerApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	/**
	 * Create the application.
	 */
	public CrawlerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		
		frame = new JFrame("Text Corpus Acquisition");
		frame.setSize(750, 225);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		JButton crawlButton = new JButton("Crawl");
		crawlButton.setBounds(25, 75, 100, 50);
		
		JTextField searchTerm = new JTextField();
		searchTerm.setBounds(150, 75, 300, 50);
		
		JTextField searchAmount = new JTextField();
		searchAmount.setBounds(500, 75, 150, 50);
		
		JTextField searchText = new JTextField("Enter your search term");
		searchText.setBounds(225, 25, 150, 50);
		searchText.setEditable(false);
		searchText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JTextField amountText = new JTextField("Enter the search length");
		amountText.setBounds(512, 25, 150, 50);
		amountText.setEditable(false);
		amountText.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		frame.add(searchText);
		frame.add(amountText);
		frame.add(crawlButton);
		frame.add(searchTerm);
		frame.add(searchAmount);
		
		crawlButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  if(!searchTerm.getText().isEmpty())
			  {
				  try
				  {
					  	// This is ran to make the error of the search amount sooner than later in the code
					  int amount = Integer.parseInt(searchAmount.getText());
					  if(amount < 1)
					  {
						  JOptionPane.showMessageDialog(frame, "A number greater than zero must be inputted in the search length field.", "Error", JOptionPane.ERROR_MESSAGE);
					  }
					  else
					  {
						  ArrayList<String> links = new ArrayList<String>();
					  
						  	// This section generates the list of links
						  GoogleCustomSearch crawler = new GoogleCustomSearch();
						  links = crawler.search(searchTerm.getText(), Integer.parseInt(searchAmount.getText()));
						  System.out.println(Arrays.toString(links.toArray()));

						  	// This section of code uses the list of links to extract the html and xml
						  HTMLExtraction extractor = new HTMLExtraction(searchTerm.getText());
						  for (String link : links)
						  {
							  System.out.println("Extraction Done!!!!");
							  
							  extractor.extract(link, pKey.incrementAndGet());
							  
		
						  }
						  pKey.getAndSet(0);
						  
					  }
					  
				  }
				  catch (NumberFormatException f)
				  {
					  JOptionPane.showMessageDialog(frame, "A number must be inputted in the search length field.", "Error", JOptionPane.ERROR_MESSAGE);
				  }
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(frame, "The search term field cannot be left empty.", "Error", JOptionPane.ERROR_MESSAGE);
			  }
		  }
		  
		});
		
			
		}
	}

