package ee.ut.mancala.view;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;

public class GameBoard extends JPanel {

	private static int HOUSE_HEIGHT = 40;
	private static int HOUSE_WIDTH = 40;

	private static int STORE_HEIGHT = 40;
	private static int STORE_WIDTH = 40;

	private static int BOARD_HOUSE_HEIGHT = 2;
	private static int BOARD_HOUSE_WIDTH = 6;
	private static int X = 0;
	private static int Y = 1;

	private final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 9);
	private final JButton[][] houseButtons = new JButton[BOARD_HOUSE_WIDTH][BOARD_HOUSE_HEIGHT];

	private JLabel leftStore;
	private JLabel rightStore;
	private JLabel playerNameOne;
	private JLabel playerNameTwo;
	private JLabel turnPlayerOne;
	private JLabel turnPlayerTwo;
	private static Color turnOn = Color.GREEN;
	private static Color turnOff = Color.RED;
	private static String turnOnText = "Go";
	private static String turnOffText = "Wait";
	
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public GameBoard() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setSize(400, 400);
		this.setLayout(new GridBagLayout());
		this.initializeHouseButtons();
		this.initializeStores();
		this.initializePlayerNames();
		this.initializeTurnLights();
	}

	/**
	 * Updates the GUI to show which player owns the turn
	 * @param player - The Index of the player. Domain: 0|1
	 */
	public void switchTurn(int player) {
		if (player == 0) {
			turnPlayerOne.setForeground(turnOn);
			turnPlayerOne.setText(turnOnText);
			turnPlayerTwo.setForeground(turnOff);
			turnPlayerTwo.setText(turnOffText);
		} else if (player == 1) {
			turnPlayerOne.setForeground(turnOff);
			turnPlayerOne.setText(turnOffText);
			turnPlayerTwo.setForeground(turnOn);
			turnPlayerTwo.setText(turnOnText);			
		}
	}

	/**
	 * This method initialize the turn sign
	 */
	private void initializeTurnLights() {
		
		turnPlayerOne = new JLabel("");
		turnPlayerTwo = new JLabel("");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		this.add(turnPlayerOne,c);
		c.gridy=0;
		c.gridx=9;
		this.add(turnPlayerTwo,c);
	}

	/**
	 * This method initialize the player labels
	 */
	private void initializePlayerNames() {
		playerNameOne = new JLabel("");
		playerNameTwo = new JLabel("");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;		
		this.add(playerNameOne,c);
		c.gridy = 1;
		c.gridx=9;
		this.add(playerNameTwo,c);		
	}

	/**
	 * This method initializes the Stores
	 */
	private void initializeStores() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;

		this.leftStore = new JLabel();
		this.leftStore
				.setPreferredSize(new Dimension(STORE_WIDTH, STORE_HEIGHT));
		this.leftStore.setText("0");
		this.leftStore.setName("resultLabel");
		this.add(leftStore, c);

		c.gridx = 8;
		this.rightStore = new JLabel();
		this.rightStore.setText("0");
		this.rightStore.setPreferredSize(new Dimension(STORE_WIDTH,
				STORE_HEIGHT));
		this.rightStore.setName("resultLabel");
		this.add(rightStore, c);

	}
	/**
	 * This method initialize the buttons that represents the houses in the GUI
	 */
	private void initializeHouseButtons() {

		final int baseX = 2;
		final int baseY = 0;

		for (int x = 0; x < BOARD_HOUSE_WIDTH; x++) {
			for (int y = 0; y < BOARD_HOUSE_HEIGHT; y++) {
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = baseX + x;
				c.gridy = baseY + y;

				JButton b = createHouseButton();

				this.add(b, c);
				houseButtons[x][y] = b;
			}
		}
	}

	/**
	 * Return the button in the location x,y where y = row (0|1) and x = column (0..6)
	 * @param x - column
	 * @param y - row
	 * @return
	 */
	public JButton getHouseButton(int x, int y) {
		return houseButtons[x][y];
	}

	/**
	 * return the position of the given house ohouse
	 * @param ohouse - house whose location is returned. 
	 * @return
	 */
	public int[] getHousePosition(Object ohouse) {
		JButton house = (JButton) ohouse;
		int[] pos = new int[2];
		for (int x = 0; x < BOARD_HOUSE_WIDTH; x++) {
			for (int y = 0; y < BOARD_HOUSE_HEIGHT; y++) {
				if (houseButtons[x][y].equals(house)) {
					pos[X] = x;
					pos[Y] = y;
					break;
				}
			}
		}
		return pos;
	}

	/**
	 * Set the number of seeds shown in the store
	 * @param player - The owner of the house
	 * @param value - The number to be shown
	 */
	public void setStoreSeeds(int player, String value) {
		if (player == 0)
			leftStore.setText(value);
		else if (player == 1)
			rightStore.setText(value);
	}

	/**
	 * Set the number of seeds shown in the houses
	 * @param x - column where the house is located
	 * @param y - row where the house is located
	 * @param value - value to be shown
	 */
	public void setHouseSeeds(int x, int y, String value) {
		JButton house = getHouseButton(x, y);
		house.setText(value);
	}

	/**
	 * Set the name of the player in the given index
	 * @param player - number of player. Domain = 0|1
	 * @param name - new name to be set
	 */
	public void setPlayerName(int player, String name)
	{
		if (player == 0)
			playerNameOne.setText(name);
		else if (player == 1)
			playerNameTwo.setText(name);
	}
	
	/**
	 * Returns a new House Button
	 * @return
	 */
	private JButton createHouseButton() {

		JButton houseButton = new JButton();
		houseButton.setPreferredSize(new Dimension(HOUSE_WIDTH, HOUSE_HEIGHT));
		houseButton.setText("3");
		houseButton.setFont(font);
		return houseButton;
	}

	/**
	 * Returns the height of the board
	 * @return
	 */
	public int getBoardHeight() {
		return BOARD_HOUSE_HEIGHT;
	}

	/**
	 * Return the width of the board
	 * @return
	 */
	public int getBoardWidth() {
		return BOARD_HOUSE_WIDTH;
	}

	/**
	 * Initialize the main window and shows it
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("GridBagLayoutDemo");

		frame.add(new GameBoard());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

} // @jve:decl-index=0:visual-constraint="10,10"
