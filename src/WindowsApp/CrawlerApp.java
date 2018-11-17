package WindowsApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import TextCorpusAquisition.HTMLExtraction;
import GoogleSearch.GoogleCustomSearch;

public class CrawlerApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//HTMLExtraction x = new HTMLExtraction();
		//GoogleCustomSearch y = new GoogleCustomSearch();
		//y.search("freedom");
		//x.extract("https://money.cnn.com/2015/07/27/technology/android-text-hack/index.html");
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
		
		MySearchAmountField searchAmount = new MySearchAmountField();
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
					  Integer.parseInt(searchAmount.getText());
					  //GoogleCustomSearch crawl = new GoogleCustomSearch();
					  //crawl.search(searchTerm.getText());
					  System.out.println(searchAmount.getIntValue());
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

class MySearchAmountField extends JTextField
{
	private static final long serialVersionUID = 1L;
	public void setIntValue(int x)
    {
        setText(""+x);
    }
    public int getIntValue()
    {
        return Integer.parseInt(getText());
    }
}
