package ee.ut.mancala.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HowToPlay extends JPanel {

	private JButton close;
	private JTextArea display;

	public HowToPlay() {
		super();
		initialize();
	}
	
	/**
	 * Pulls from a txt file the text to be display by the help 
	 */
	public void setText() {
		try {
			// Open the file that is the first
			// command line parameter
			InputStream s = this.getClass().getResourceAsStream("HowToPlayInstructions.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				display.setText(display.getText() + strLine + "\n");
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * initialize the controls 
	 */
	public void initialize() {
		this.setSize(600, 600);
		this.setLayout(new GridBagLayout());
		close = new JButton("Close");
		display = new JTextArea();		
		setText();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		this.add(display);
		c.gridy=1;
		this.add(close);
	}
	
	public JButton getCloseButton()	{
		return close;
	}
}
