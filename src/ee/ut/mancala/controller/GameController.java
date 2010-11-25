package ee.ut.mancala.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import ee.ut.mancala.Game;
import ee.ut.mancala.GameRecord;
import ee.ut.mancala.House;
import ee.ut.mancala.Houses;
import ee.ut.mancala.Player;
import ee.ut.mancala.Score;
import ee.ut.mancala.SeedContainer;
import ee.ut.mancala.view.MainGui;
import ee.ut.mancala.view.PlayerEntry;
import ee.ut.mancala.view.ShowHistory;

public class GameController {

	private static int X = 0;
	private static int Y = 1;

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
					System.out.println("New player "
							+ p.getHouses().getHouse().size());
					int x = 0;

					for (House h : p.getHouses().getHouse()) {
						h.addPropertyChangeListener(
								SeedContainer.PROPERTY_SEED_COUNT,
								new PropertyChangeListener() {

									@Override
									public void propertyChange(
											PropertyChangeEvent arg0) {
										System.out
												.println("seed change with the value "
														+ String.valueOf(arg0
																.getNewValue()));
										House house = (House) arg0.getSource();
										int x = house.getHouses().getHouse()
												.indexOf(house);
										Player player = house.getHouses()
												.getOwner();

										ArrayList<Player> players = new ArrayList<Player>(
												Game.getInstance().getPlayer());
										int y = players.indexOf(player);
										if (y > 0)
											application
													.getGameBoard()
													.setHouseSeeds(
															x,
															y,
															arg0.getNewValue()
																	.toString());

									}
								});

						System.out.println("wiring " + x + ":" + y);
						application.getGameBoard().getHouseButton(x, y)
								.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent arg0) {
										System.out.println("hit cell " + 0
												+ ":" + 0);
										int[] pos = application.getGameBoard()
												.getHousePosition(
														arg0.getSource());
										Player player = (Player) Game
												.getInstance().getPlayer()
												.toArray()[pos[Y]];
										House house = player.getHouses()
												.getFromHouse(pos[X]);
										player.play(house);
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
								//application.hidePlayerEntry();
								PlayerEntry playerEntry = application
										.getPlayerEntry();
								// Get player names
								// Start new game
								Game.getInstance().initializeGame(
										playerEntry.getPlayerOneName(),
										playerEntry.getPlayerTwoName());
								application.showGameBoard();
								application.refresh();
							}
						});

				// show history
				application.getShowHistoryMenuItem().addActionListener(
						new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								//application.hidePlayerEntry();
								// hide previous one
								boolean existingGames = false;
								if (Game.getInstance().getHistory() != null) {
									GameRecord record = Game.getInstance()
											.getHistory().getMostRecent();
									if (record != null) {
										ShowHistory showHistory = application
												.getShowHistory();
										showHistory.clearRecords();
										do {
											showHistory.addRecord();
											for (Score score : record
													.getScore()) {
												showHistory.addScore(score
														.getPlayer().getName(),
														score.getPoints());
											}
										} while ((record = record.getPrevious()) != null);
										existingGames = true;
									}
								}
								if (!existingGames) {
									application.getShowHistory()
											.addEmptyRecords();
								}
								application.showHistory();
							}
						});

				application.getShowHistory().getCloseButton()
						.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {								
								// hide show history
								
								// show previous								
								application.showGameBoard();
							}
						});

				application.setVisible(true);
			}
		});
	}

}
