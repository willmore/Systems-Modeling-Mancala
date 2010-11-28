package ee.ut.mancala.view;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ee.ut.mancala.History;

public class ShowHistory extends JPanel {

	private static int CLOSE_BUTTON_HEIGHT = 75;
	private static int CLOSE_BUTTON_WIDTH = 75;
	private static int TEXTBOX_HEIGHT = 100;
	private static int TEXTBOX_WIDTH = 100;
	private static String EMPTY = "";
	private static String ENTER = "\n";
	private static String GAME = "Game";
	private static String EMPTY_GAME_RECORDS = "No games played so far";

	private JButton close;
	private JTextArea scoresArea;

	/**
	 * Default constructor. Calls the initialize method
	 */
	public ShowHistory() {
		super();
		initialize();
	}

	/**
	 * initialize the controls and this. Do not call directly
	 */
	private void initialize() {
		this.setSize(400, 400);
		this.setLayout(new GridBagLayout());
		close = new JButton("Close");
		scoresArea = new JTextArea();
		this.add(scoresArea);
		this.add(close);
	}

	/**
	 * Returns the close button
	 * @return
	 */
	public JButton getCloseButton() {
		return close;
	}

	/**
	 * Marks the starting for a new record. A record is the result of one game
	 */
	public void addRecord() {
		if (scoresArea.getText().compareTo(EMPTY) != 0)
			scoresArea.setText(ENTER);
		scoresArea.setText(ENTER + GAME + ENTER);
	}

	/**
	 * Adds the number of seeds that one player collected at the end of a given game
	 * @param player
	 * @param points
	 */
	public void addScore(String player, int points) {
		scoresArea.setText(player + "got " + String.valueOf(points)+"     ");
	}

	/**
	 * Clears the text in the display 
	 */
	public void clearRecords() {
		scoresArea.setText(EMPTY);
	}

	/**
	 * Display the text "No games played so far" when the history is empty.
	 */
	public void addEmptyRecords() {
		scoresArea.setText(EMPTY_GAME_RECORDS);
	}
}
