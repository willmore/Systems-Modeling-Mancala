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
import ee.ut.mancala.Status;
import ee.ut.mancala.Store;
import ee.ut.mancala.Turn;
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
				final Game game = new Game("", "");

				game.getTurn().addPropertyChangeListener(Turn.PROPERTY_IS,
						new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent arg0) {
								Turn turn = (Turn) arg0.getSource();
								Player player = turn.getIs();
								ArrayList<Player> players = new ArrayList<Player>(
										game.getPlayer());
								int y = players.indexOf(player);
								application.getGameBoard().switchTurn(y);
							}
						});

				// wiring game status
				game.addPropertyChangeListener(Game.PROPERTY_STATUS,
						new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent arg0) {
								if (arg0.getNewValue().equals(Status.GAME_OVER)) {
									GameRecord mostRecent = game.getHistory()
											.getMostRecent();
									for (Score score : mostRecent.getScore()) {
										Player player = score.getPlayer();
										application.getGameOver().addText(
												player.getName() + " got "
														+ score.getPoints());
									}
									application.showGameOver();
								} else if (arg0.getNewValue().equals(
										Status.PLAYING)) {
									application.showGameBoard();
								}
							}
						});

				// Adding listeners to the buttons
				for (int x = 0; x < application.getGameBoard().getBoardWidth(); x++) {
					for (int y = 0; y < application.getGameBoard()
							.getBoardHeight(); y++) {

						System.out.println("wiring " + x + ":" + y);
						application.getGameBoard().getHouseButton(x, y)
								.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent arg0) {
										int[] pos = application.getGameBoard()
												.getHousePosition(
														arg0.getSource());
										Player player = (Player) game
												.getPlayer().toArray()[pos[Y]];
										if (pos[Y] == 0)
											pos[X] = Math.abs(pos[X] - 5);
										House house = player.getHouses()
												.getFromHouse(pos[X]);
										player.play(house);
										System.out.println("hit cell " + pos[X]
												+ ":" + pos[Y]);
									}
								});
					}
				}

				// initializing model

				// TODO this is a hardcoded game initialization, must implement
				// as story.
				/*
				 * Player p1 = new Player("Alice"); Player p2 = new
				 * Player("Bob");
				 * 
				 * Houses h1 = new Houses(); p1.setHouses(h1); for (int i = 0; i
				 * < 6; i++) { h1.addToHouse(new House()); }
				 * 
				 * Houses h2 = new Houses(); p2.setHouses(h2); for (int i = 0; i
				 * < 6; i++) { h2.addToHouse(new House()); }
				 * Game.getInstance().addToPlayer(p1);
				 * Game.getInstance().addToPlayer(p2);
				 */

				// Wire house properties
				int y = 0;
				for (Player p : game.getPlayer()) {
					System.out.println("New player "
							+ p.getHouses().getHouse().size());
					int x = 0;

					// wire player
					p.addPropertyChangeListener(Player.PROPERTY_NAME,
							new PropertyChangeListener() {

								@Override
								public void propertyChange(
										PropertyChangeEvent arg0) {
									Player player = (Player) arg0.getSource();
									ArrayList<Player> players = new ArrayList<Player>(
											game.getPlayer());
									int y = players.indexOf(player);
									application.getGameBoard().setPlayerName(y,
											(String) arg0.getNewValue());
								}
							});

					// wire store
					Store store = p.getOwns();
					store.addPropertyChangeListener(
							SeedContainer.PROPERTY_SEED_COUNT,
							new PropertyChangeListener() {

								@Override
								public void propertyChange(
										PropertyChangeEvent arg0) {
									if (arg0.getSource().getClass()
											.equals(Store.class)) {
										System.out
												.println("store change with the value "
														+ String.valueOf(arg0
																.getNewValue()));

										Store store = (Store) arg0.getSource();
										Player player = store.getPlayer();
										ArrayList<Player> players = new ArrayList<Player>(
												game.getPlayer());
										int y = players.indexOf(player);
										if (y >= 0) {
											application
													.getGameBoard()
													.setStoreSeeds(
															y,
															String.valueOf(arg0
																	.getNewValue()));
										}
									}
								}
							});

					// wire houses
					for (House h : p.getHouses().getHouse()) {
						h.addPropertyChangeListener(
								SeedContainer.PROPERTY_SEED_COUNT,
								new PropertyChangeListener() {

									@Override
									public void propertyChange(
											PropertyChangeEvent arg0) {
										if (arg0.getSource().getClass()
												.equals(House.class)) {
											System.out.println("seed change with the value "
													+ String.valueOf(arg0
															.getNewValue()));

											House house = (House) arg0
													.getSource();

											int x = house.getHouses()
													.getHouse().indexOf(house);

											Player player = house.getHouses()
													.getOwner();

											ArrayList<Player> players = new ArrayList<Player>(
													game.getPlayer());
											int y = players.indexOf(player);
											if (y >= 0) {
												if (y == 0)
													x = Math.abs(x - 5);
												application
														.getGameBoard()
														.setHouseSeeds(
																x,
																y,
																String.valueOf(arg0
																		.getNewValue()));
											}
										}
									}
								});

						// System.out.println("wiring " + x + ":" + y);
						// application.getGameBoard().getHouseButton(x, y)
						// .addActionListener(new ActionListener() {
						//
						// @Override
						// public void actionPerformed(ActionEvent arg0) {
						//
						// int[] pos = application.getGameBoard()
						// .getHousePosition(
						// arg0.getSource());
						// Player player = (Player) Game
						// .getInstance().getPlayer()
						// .toArray()[pos[Y]];
						// House house = player.getHouses()
						// .getFromHouse(pos[X]);
						// player.play(house);
						// System.out.println("hit cell " + pos[X]
						// + ":" + pos[Y]);
						// }
						// });

						x++;
					}
					y++;
				}

				// Wire start game button
				application.getPlayerEntry().getSubmitButton()
						.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// application.hidePlayerEntry();
								PlayerEntry playerEntry = application
										.getPlayerEntry();
								// Get player names
								// Start new game
								ArrayList<Player> players = new ArrayList<Player>(
										game.getPlayer());
								players.get(0).setName(
										playerEntry.getPlayerOneName());
								players.get(1).setName(
										playerEntry.getPlayerTwoName());
								application.getGameBoard().switchTurn(players.indexOf(game.getTurn().getIs()));
								application.showGameBoard();
								application.refresh();
							}
						});

				// show history
				application.getShowHistoryMenuItem().addActionListener(
						new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// application.hidePlayerEntry();
								// hide previous one
								boolean existingGames = false;
								if (game.getHistory() != null) {
									GameRecord record = game.getHistory()
											.getMostRecent();
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

				// wire close button in Show History
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
