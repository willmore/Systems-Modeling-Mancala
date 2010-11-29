package ee.ut.mancala.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameOver extends JPanel {

	private JButton close;
	private JButton playAgain;
	private JLabel display;

	/**
	 * Default constructor
	 */
	public GameOver() {
		super();
		initialize();
	}

	/**
	 * Initialize the controls and this
	 */
	private void initialize() {
		this.setLayout(new GridBagLayout());
		close = new JButton("Close");
		playAgain = new JButton("Play Again");
		display = new JLabel();
		display.setForeground(Color.BLACK);
		GridBagConstraints c = new GridBagConstraints();
		//c.gridheight = 2;
		//c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(display, c);
		c.gridy = 1;
		this.add(close, c);
		c.gridy = 2;
		this.add(playAgain, c);
	}

	/**
	 * Set a text in the display (TextArea)
	 * 
	 * @param text
	 */
	public void addText(String text) {
		display.setText(display.getText() + "  " + text);
	}

	public JButton getClose() {
		return close;
	}

	public JButton getPlayAgain() {
		return playAgain;
	}
}
