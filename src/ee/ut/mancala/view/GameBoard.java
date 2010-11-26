package ee.ut.mancala.view;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
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
	}

	private void initializePlayerNames() {

	}

	private void initializeStores() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;

		this.leftStore = new JLabel();
		this.leftStore
				.setPreferredSize(new Dimension(STORE_WIDTH, STORE_HEIGHT));
		this.leftStore.setText("0");
		this.leftStore.setName("resultLabel");
		this.add(leftStore, c);

		c.gridx = 7;
		this.rightStore = new JLabel();
		this.rightStore.setText("0");
		this.rightStore.setPreferredSize(new Dimension(STORE_WIDTH,
				STORE_HEIGHT));
		this.rightStore.setName("resultLabel");
		this.add(rightStore, c);

	}

	private void initializeHouseButtons() {

		final int baseX = 1;
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

	public JButton getHouseButton(int x, int y) {
		return houseButtons[x][y];
	}

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

	public void setStoreSeeds(int player, String value) {
		if (player == 0)
			leftStore.setText(value);
		else if (player == 1)
			rightStore.setText(value);
	}

	public void setHouseSeeds(int x, int y, String value) {
		JButton house = getHouseButton(x, y);
		house.setText(value);
	}

	private JButton createHouseButton() {

		JButton houseButton = new JButton();
		houseButton.setPreferredSize(new Dimension(HOUSE_WIDTH, HOUSE_HEIGHT));
		houseButton.setText("3");
		houseButton.setFont(font);
		return houseButton;
	}

	public int getBoardHeight() {
		return BOARD_HOUSE_HEIGHT;
	}

	public int getBoardWidth() {
		return BOARD_HOUSE_WIDTH;
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("GridBagLayoutDemo");

		frame.add(new GameBoard());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

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
