package ee.ut.mancala.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;

import ee.ut.mancala.Game;
import ee.ut.mancala.House;
import ee.ut.mancala.Houses;
import ee.ut.mancala.Player;
import ee.ut.mancala.SeedContainer;
import ee.ut.mancala.view.MainGui;

public class GameController {

	public void start() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final MainGui application = new MainGui();

				// TODO this is a hardcoded game initialization, must implement
				// as story.
				Player p1 = new Player("Alice");
				Player p2 = new Player("Bob");

				Houses h1 = new Houses();
				p1.setHouses(h1);
				for (int i = 0; i < 6; i++) {
					h1.addToHouse(new House());
				}

				Houses h2 = new Houses();
				p2.setHouses(h2);
				for (int i = 0; i < 6; i++) {
					h2.addToHouse(new House());
				}
				Game.getInstance().addToPlayer(p1);
				Game.getInstance().addToPlayer(p2);

				// Wire house buttons
				int y = 0;
				for (Player p : Game.getInstance().getPlayer()) {
					System.out.println("New player " + p.getHouses().getHouse().size());
					int x = 0;

					for (House h : p.getHouses().getHouse()) {
						h.addPropertyChangeListener(
								SeedContainer.PROPERTY_SEED_COUNT,
								new PropertyChangeListener() {

									@Override
									public void propertyChange(
											PropertyChangeEvent arg0) {
										System.out.println("seed change");
									}

								});

						System.out.println("wiring " + x + ":" + y);
						application.getGameBoard().getHouseButton(x, y)
								.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent arg0) {
										System.out.println("hit cell " + 0
												+ ":" + 0);
									}
								});

						x++;
					}
					y++;
				}

				// Wire start game button
				application.getPlayerEntry().getSubmitButton()
						.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								application.hidePlayerEntry();
								// Get player names
								// Start new game
								application.showGameBoard();
								application.refresh();
							}
						});

				application.setVisible(true);
			}
		});
	}

}
