import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 * @author Qasim Shahid
 * The Board class is responsible for updating the GUI, determining if the game is over or has been won, adding and removing mines from the status bar, and handling mouse inputs.
 * The class utilizes the Minefield class in order to perform these operations,
 */
public class Board extends JPanel {
	/**
	 * int height is the height of the board, or the number of rows in Minefield.
	 * int width is the width of the board, or the number of columns in Minefield.
	 * int mines is the number of mines on the Minefield. This is used by the status bar to tell the player how many mines are remaining and how many cells the player can mark.
	 * JLabel statusBar is the status bar at the bottom that informs the player if they have won, lost, how many cells they can mark, and if the player is attempting to do an invalid action.
	 * Minefield FieldOfMines is the Minefield that Board will use to perform the various tasks described in the class description. This allows for access to the underlying Minefield array.
	 */
	private static final long serialVersionUID = 1L; // there was an error that wanted me to put this, so i just did. it is not used in the project at all. 
	private int height;
	private int width;
	private int mines;
	private JLabel statusBar;
	private Minefield FieldOfMines;
	
	/**
	 * This is the constructor used to create a new Board with a specific height, width, number of mines. A new Minefield according to those specifications is also created using this constructor.
	 * @param height The height of the board, or the number of rows. 
	 * @param width The width of the board, or the number of columns.
	 * @param mines The number of mines in the Minefield for the board.
	 * @param statusbar The status bar that will inform the player of their actions.
	 */

	public Board(int height, int width, int mines, JLabel statusbar) {
		this.height = height;
		this.width = width;
		this.mines = mines;
		this.statusBar = statusbar;
		this.FieldOfMines = new Minefield(height, width, mines);
		setPreferredSize(new Dimension(Configuration.BOARD_WIDTH, Configuration.BOARD_HEIGHT));
		addMouseListener(new MouseReader(this));
	}

	@Override
	/**
	 * This method is responsible for drawing the board and doing all the GUI work.
	 * @param g Graphics component
	 */
	public void paintComponent(Graphics g) {
		FieldOfMines.draw(g);
	}

	/**
	 * This method gets the Minefield that the Board has been created with.
	 * @return Returns the Minefield that is being used by the board.
 	 */
	public Minefield getMinefield() {
		return FieldOfMines;
	}

	/**
	 * This method checks if the game has been won or lost and returns true or false if that happens to be the case.
	 * @return Returns true or false based on if the game is over. 
	 */
	public boolean isGameOver() {
		boolean over = false;
		int count = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (FieldOfMines.getCellByRowCol(i, j) instanceof MineCell
						&& ((MineCell) FieldOfMines.getCellByRowCol(i, j)).getStatus()
								.equals(Configuration.STATUS_OPENED)) {
					over = true;
					count = count + 1;
				} else if (GameWon() == true) {
					over = true;
				} else {
					over = false;
				}
			}
		}
		if (count > 0) {
			over = true;
		}
		return over;
	}

	/**
	 * This private helper method is used in order to see if the game has been won. 
	 * @return Returns true if the game has been won.
	 */
	private boolean GameWon() { // helper private method
		boolean won = false;
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (FieldOfMines.getCellByRowCol(i, j) instanceof MineCell
						&& (((MineCell) FieldOfMines.getCellByRowCol(i, j)).getStatus()
								.equals(Configuration.STATUS_COVERED)
								|| ((MineCell) FieldOfMines.getCellByRowCol(i, j)).getStatus()
										.equals(Configuration.STATUS_MARKED))) {
					count1 = count1 + 1;

				}
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (FieldOfMines.getCellByRowCol(i, j) instanceof InfoCell) {
					count2 = count2 + 1;
				}
			}
		}
		if ((count1 == Configuration.MINES) && count2 == FieldOfMines.countCellsWithStatus(Configuration.STATUS_OPENED)) {
			won = true;
		} else {
			won = false;
		}
		return won;
	}
	
	/**
	 * Sets the text of the status bar. Used to change the text.
	 * @param text Text is the string that will be displayed by the status bar once this method has been called.
	 */
	public void setStatusbar(String text) {
		statusBar.setText(text);
	}
	
	/**
	 * Gets the string of text that is being displayed by the status bar. Also controls the text of the status bar when the game is over. If the user wins, it prints that the user has won. If not, it informs the user that they have lost.
	 * @return Returns the current text that is being displayed by the status bar.
	 */
	public String getStatusbar() {
		String a = statusBar.getText();

		if (isGameOver() && GameWon())
			setStatusbar("Game over - You won!");
		else if ((isGameOver() == true && GameWon() == false))
			setStatusbar("Game over - You lost!");

		return a;
	}
	
	/**
	 * If the player marks a cell as a mine, this decrements the number of mines that are displayed by the status bar.
	 * @return Returns true if mines can still be removed without invoking an invalid action. Returns false if removing any more mines is not possible (if number of mines becomes negative).
	 */
	public boolean removeMine() {
		boolean bool = true;
		if (mines > 0) {
			bool = true;
			mines = mines - 1;
			setStatusbar(mines + " mines remaining");
		} else if (mines <= 0) {
			bool = false;
			setStatusbar("Invalid action");
		}
		return bool;
	}
	
	/**
	 * This method performs the opposite function of removeMine(). If a player marks a previously marked cell as now just a normal covered cell, this method increments the number of mines displayed by the status bar.
	 * @return Returns true if mines can still be added without invoking an invalid action. Returns false if adding any more mines is not possible (greater than the mines the Board started with).
	 */
	public boolean addMine() {
		boolean bool = true;
		mines = mines + 1;
		if (mines > Configuration.MINES) {
			bool = false;
			setStatusbar("Invalid action");
		} else if ((mines >= 0) && (mines <= Configuration.MINES)) {
			bool = true;
			setStatusbar(mines + " mines remaining");
		}
		return bool;

	}
	
	/**
	 * This method handles all mouse input. If the user left clicks,
	 * it will uncover the cell that was clicked (including using OpenCells to uncover adjacent cells). It also handles right clicks to mark cells, and unmark if needed.
	 * The method checks to see if GameIsOver on each click, and if it is, it calls the getStatus. If the user clicks a mine, it reveals incorrectly marked locations and where the mines were located.
	 * It repaints the Board after each input to reflect changes visually for the user.  
	 * @param x X is the horizontal coordinate on the Board in pixels. The method getCellByScreenCoordinates returns the location of the cell in the Minefield array.
	 * @param y  Y is the vertical coordinate on the Board in pixels. The method getCellByScreenCoordinates returns the location of the cell in the Minefield array.
	 * @param button Button is read from MouseReader and specifies if the click was a right click, left click, or any other button on the mouse (which does nothing).
	 */
	public void mouseClickOnLocation(int x, int y, String button) {
		Object cell = FieldOfMines.getCellByScreenCoordinates(x, y);

		if (isGameOver() == false && GameWon() == false) {

			if (button.equals("left") && cell instanceof MineCell && ((MineCell) cell).getStatus().equals(Configuration.STATUS_COVERED)) {
				((MineCell) cell).setStatus(Configuration.STATUS_OPENED);
				getMinefield().revealIncorrectMarkedCells();
				getStatusbar();
			}
			
			else if (button.equals("left") && cell instanceof InfoCell && ((InfoCell) cell).getStatus().equals(Configuration.STATUS_COVERED)) {
				if (((InfoCell)cell).getNumOfAdjacentMines() != 0) {
					((InfoCell)cell).setStatus(Configuration.STATUS_OPENED);
				}
				
				int a = getMinefield().countCellsWithStatus(Configuration.STATUS_MARKED);
				if (((InfoCell) cell).getNumOfAdjacentMines() == 0) {
				getMinefield().openCells(((InfoCell)cell).getVerticalPosition() / Configuration.CELL_SIZE, ((InfoCell)cell).getHorizontalPosition() / Configuration.CELL_SIZE);
				}
				int b = getMinefield().countCellsWithStatus(Configuration.STATUS_MARKED);
				
				if (a > b) {
					for (int i = b; i < a; i++)
						addMine();
				}
				if (GameWon()) {
					getStatusbar();
					getMinefield().revealIncorrectMarkedCells();
				}
			
			} 
			else if (button.equals("right") && cell instanceof MineCell && ((MineCell) cell).getStatus().equals(Configuration.STATUS_COVERED) && mines >= 0) {
				if (mines > 0) {
					((MineCell) cell).setStatus(Configuration.STATUS_MARKED);
				}
				removeMine();
			} 
			else if (button.equals("right") && cell instanceof MineCell && ((MineCell) cell).getStatus().equals(Configuration.STATUS_MARKED)
					&& mines < Configuration.MINES) {
				((MineCell) cell).setStatus(Configuration.STATUS_COVERED);
				addMine();
			}
			else if (button.equals("right") && cell instanceof InfoCell && ((InfoCell) cell).getStatus().equals(Configuration.STATUS_COVERED) && mines >= 0) {
				if (mines > 0) {
					((InfoCell) cell).setStatus(Configuration.STATUS_MARKED);
				}
				removeMine();
			}
				else if (button.equals("right") && cell instanceof InfoCell
					&& ((InfoCell) cell).getStatus().equals(Configuration.STATUS_MARKED)
					&& mines < Configuration.MINES) {
				((InfoCell) cell).setStatus(Configuration.STATUS_COVERED);
				addMine();
			}
		}
		else if (GameWon() == true) {
			getStatusbar();
			getMinefield().revealIncorrectMarkedCells();
		}
		repaint();
	}
}
