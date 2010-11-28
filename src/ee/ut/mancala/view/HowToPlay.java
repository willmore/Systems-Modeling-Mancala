package ee.ut.mancala.view;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
			FileInputStream fstream = new FileInputStream(
					"HowToPlayInstructions.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
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
		close = new JButton("Close");
		display = new JTextArea();
		this.add(close);
		this.add(display);
	}

}
