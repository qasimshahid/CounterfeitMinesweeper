import java.util.Random;
import java.awt.Graphics;

/**
 * @author Qasim Shahid
 * The Minefield class is the heart of the Mine sweeper game. It controls the 2d array that creates all the InfoCells, MineCells, and places them within the array to create the field.
 */
public class Minefield {
	/**
	 * Object[][] field is the 2d array in which all of the InfoCells and MineCells are initialized. 
	 * int numRows is the number of rows in the field[numRows][] array.
	 * int numColumns is the number of columns in the field[][numColumns] array.
	 * int numMines are the number of MineCells that will be created within the array.
	 */
	private Object[][] field;
	private int numRows;
	private int numColumns;
	private int numMines;
	
	/**
	 * The default constructor for the Minefield. It creates a Minefield that is 10x10 wide with 10 mines. 
	 */
	public Minefield() {
		this.numRows = 10;
		this.numColumns = 10;
		this.numMines = 10;
		this.field = new Object[numRows][numColumns];
		mineLaying(numMines);
		addInfoCells();
	}
	
	/**
	 * The parameterized constructor for initializing a Minefield. It creates a Minefield based on the specified parameters.
	 * @param numRows numRows is the number of rows in the field[][] array.
	 * @param numColumns numColumns is the number of columns in the field[][] array.
	 * @param numMines numMines is the number of mines that will be created in the field[][] array.
	 */
	public Minefield(int numRows, int numColumns, int numMines) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numMines = numMines;
		field = new Object[numRows][numColumns];
		mineLaying(numMines);
		addInfoCells();
	}
	
	/**
	 * mineLaying method places MineCells at random locations within the bounds of the field[][] array.
	 * @param numOfMines numOfMines is the number of mines that will be placed in random locations within the field[][] array.
	 */
	public void mineLaying(int numOfMines) {
		Random r = new Random(); // give value to random for debugging
		int a;
		int b;
		for (int i = 0; i < numOfMines; i++) {
			a = r.nextInt(numRows);
			b = r.nextInt(numColumns);
		
			if (!(field[a][b] instanceof MineCell)) {
				field[a][b] = new MineCell(a, b);
			}
			else {
				while (field[a][b] instanceof MineCell) {
					a = r.nextInt(numRows);
					b = r.nextInt(numColumns);
				}
				field[a][b] = new MineCell(a, b);
			}
			
		}
	}
	
	/**
	 * addInfoCells adds InfoCells to the rest of the Minefield field[][] array. Wherever there are no mines, an InfoCell is created. This method also checks for mines adjacent to InfoCells and assigns them with the correct numOfAdjacentMines value.
	 */
	public void addInfoCells() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (field[i][j] == null) {
					int count = 0;
					if (0 <= (j + 1) && (j + 1) <= (numColumns - 1) && field[i][j + 1] instanceof MineCell) { // checks right, block 4
						count = count + 1;
					}
					if (0 <= (j - 1) && (j - 1) <= (numColumns - 1) && field[i][j - 1] instanceof MineCell) { // checks left, block 8
						count = count + 1;
					}
					if (0 <= (i + 1) && (i + 1) <= (numRows - 1) && field[i + 1][j] instanceof MineCell) { // checks bottom, block 6
						count = count + 1;
					}
					if (0 <= (i - 1) && (i - 1) <= (numRows - 1) && field[i - 1][j] instanceof MineCell) { // checks top, block 2
						count = count + 1;
					}
					if (0 <= (i + 1) && (i + 1) <= (numRows - 1) && 0 <= (j + 1) && (j + 1) <= (numColumns - 1) &&
							field[i + 1][j + 1] instanceof MineCell) { // checks bottom right, block 5
						count = count + 1;
					}
					if (0 <= (i + 1) && (i + 1) <= (numRows - 1) && 0 <= (j - 1) && (j - 1) <= (numColumns - 1) &&
							field[i + 1][j - 1] instanceof MineCell) { // checks bottom left, block 7
						count = count + 1;
					}
					if (0 <= (i - 1) && (i - 1) <= (numRows - 1) && 0 <= (j + 1) && (j + 1) <= (numColumns - 1) &&
							field[i - 1][j + 1] instanceof MineCell) { // checks top right, block 3
						count = count + 1;
					}
					if (0 <= (i - 1) && (i - 1) <= (numRows - 1) && 0 <= (j - 1) && (j - 1) <= (numColumns - 1) &&
							field[i - 1][j - 1] instanceof MineCell) { // checks top left, block 1
						count = count + 1;
					}
					field[i][j] = new InfoCell(i,j,count);
				}
			}
		}
	}
	
	/**
	 * This draws all the cells within the field[][] array to the Board and GUI. 
	 * @param g Graphics component
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (field[i][j] instanceof MineCell) {
					((MineCell) field[i][j]).draw(g);
				}
				else if (field[i][j] instanceof InfoCell) {
					((InfoCell) field[i][j]).draw(g);
				}
			}
		}
	}
	
	/**
	 * This method is used to convert the pixel x,y clicks made by the player into row,col values in the field[][] array.
	 * @param x X is the row of the cell in pixels value. This x is converted to the row location within the field[][] array.
	 * @param y X is the column of the cell in pixels value. This x is converted to the row location within the field[][] array.
	 * @return Returns the Object at the location specified by x and y. 
	 */
	public Object getCellByScreenCoordinates(int x, int y) {
		int a = x - (x % Configuration.CELL_SIZE);
		int b = y - (y % Configuration.CELL_SIZE);
		Object returnObj = new Object();
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (field[i][j] instanceof MineCell) {
					 int HP = ((MineCell) field[i][j]).getHorizontalPosition();
					 int VP = ((MineCell) field[i][j]).getVerticalPosition();
					 if (HP == a && VP == b) {
						 returnObj = field[i][j];
					 }
				}
				
				if (field[i][j] instanceof InfoCell) {
					 int HP = ((InfoCell) field[i][j]).getHorizontalPosition();
					 int VP = ((InfoCell) field[i][j]).getVerticalPosition();
					 if (HP == a && VP == b) {
						 returnObj = field[i][j];		 
					 }
				}
			}
		}
		return returnObj;
	}
	
	/**
	 * This method gets the Object at the specified row and column specified
	 * @param row Row is the row where the Object is.
	 * @param col Col is the column where the Object is.
	 * @return Returns the Object specified by x and y within the field[][] array.
	 */
	public Object getCellByRowCol(int row, int col) {
		return field[row][col];
		
	}
	
	/**
	 * This method sets field[row][col] to the MineCell specified.
	 * @param row Row is the row where the MineCell will be placed in the field[][] array. 
	 * @param col Col is the column where the MineCell will be placed in the field[][] array.
	 * @param cell Cell is the MineCell that will be created at the location specified by x and y. 
	 */
	public void setMineCell(int row, int col, MineCell cell) {
		field[row][col] = cell;
	}
	
	/**
	 * This method sets field[row][col] to the InfoCell specified.
	 * @param row Row is the row where the InfoCell will be placed in the field[][] array. 
	 * @param col Col is the column where the InfoCell will be placed in the field[][] array.
	 * @param cell Cell is the InfoCell that will be created at the location specified by x and y. 
	 */
	public void setInfoCell(int row, int col, InfoCell cell) {
		field[row][col] = cell;
	}
	
	/**
	 * This method counts the number of cells within the field[][] array that have the specified status.
	 * @param status Status is a string that matches one of the statuses specified in the configuration file. 
	 * @return It returns an integer with the number of cells that have the particular status specified.
	 */
	public int countCellsWithStatus(String status) {
		String a;
		int minecount = 0;
		int infocount = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (field[i][j] instanceof MineCell) {
					a = ((MineCell) field[i][j]).getStatus();
					if (a.equals(status)) {
						minecount = minecount + 1;
					}
				}
				if (field[i][j] instanceof InfoCell) {
					a = ((InfoCell) field[i][j]).getStatus();
					if (a.equals(status)) {
						infocount = infocount + 1;
					}
				}
			}
		}
		return minecount + infocount;
	}
	
	/**
	 * This reveals all the mines and incorrectly marked InfoCells marked by the player. This method is used whenever the player loses, to show the locations they guessed incorrectly and to show where the mines were actually located.
	 */
	void revealIncorrectMarkedCells() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if ((field[i][j] instanceof InfoCell) && (((InfoCell) field[i][j]).getStatus().equals(Configuration.STATUS_MARKED))) {
					((InfoCell) field[i][j]).setStatus(Configuration.STATUS_WRONGLY_MARKED);
				}
				if ((field[i][j] instanceof MineCell) && (((MineCell) field[i][j]).getStatus().equals(Configuration.STATUS_COVERED))) {
					((MineCell) field[i][j]).setStatus(Configuration.STATUS_OPENED);  // my own addition, i like to see the mines locations after losing.
				}
			}
		}
	}
	
	public boolean validIndex(int x, int y) {
		boolean bool1 = false;
		boolean bool2 = false;
		
		if (x >= 0 && x < numRows) {
			bool1 = true;
		}
		if (y >= 0 && y < numColumns) {
			bool2 = true;
		}
		return bool1 && bool2;
	}
	
	
	
	public void openCells(int x, int y) {
        if (x < 0 || x > numRows - 1 || y < 0 || y > numColumns - 1) { 
        	return; // check for bounds, if not satisfied we skip.
        }
        
        if (field[x][y] instanceof MineCell) {
        	return;  // skip
        }

           if ( ((InfoCell)field[x][y]).getNumOfAdjacentMines()== 0 && ((InfoCell)field[x][y]).getStatus() != Configuration.STATUS_OPENED) {
               ((InfoCell)field[x][y]).setStatus(Configuration.STATUS_OPENED);
               openCells(x + 1, y); // since the cell has no adjacent mines, we can open all the cells around it.
               openCells(x - 1, y); // bounds will be checked by first statement.
               openCells(x , y - 1);
               openCells(x, y + 1);
               openCells(x + 1, y + 1);
               openCells(x - 1, y - 1);
               openCells(x + 1, y - 1);
               openCells(x - 1, y + 1);
               OpeningThe8Adjacent(field[x][y]); // open the surrounding.
           } else {
               return;
           }
        }
		
	

	
	private void OpeningThe8Adjacent(Object cell) {  // helper method, used to condense code.
		int column;   
		int row;      
		if (cell instanceof InfoCell && ((InfoCell) cell).getNumOfAdjacentMines() == 0) {
			column = ((InfoCell) cell).getHorizontalPosition() / Configuration.CELL_SIZE;
			row = ((InfoCell) cell).getVerticalPosition() / Configuration.CELL_SIZE;
		//	((InfoCell) (cell)).setStatus(Configuration.STATUS_OPENED);
			if  ((0 <= row) && (row <= numRows - 1) && (0 <= column) && (column + 1 <= numColumns - 1)) {
				((InfoCell) field[row][column + 1]).setStatus(Configuration.STATUS_OPENED); // right
			}
			if ( (0 <= row) && (row <= numRows - 1) && (0 <= column - 1) && (column - 1 <= numColumns - 1) ) {
				((InfoCell) field[row][column - 1]).setStatus(Configuration.STATUS_OPENED); // left
			}
			if ( (0 <= row) && (row + 1 <= numRows - 1) && (0 <= column) && (column <= numColumns - 1)) {
				((InfoCell) field[row + 1][column]).setStatus(Configuration.STATUS_OPENED); // bottom
			}
			if ( (0 <= row - 1) && (row - 1 <= numRows -1) && (0 <= column) && (column <= numColumns - 1) ) {
				((InfoCell) field[row - 1][column]).setStatus(Configuration.STATUS_OPENED); // top
			}
			if ( (0 <= row + 1) && (row + 1 <= numRows - 1) && (0 <= column + 1) && (column + 1 <= numColumns - 1) ) {
				((InfoCell) field[row + 1][column + 1]).setStatus(Configuration.STATUS_OPENED); // bottom right 
			}
			if ( (0 <= row - 1) && (row - 1 <= numRows - 1) && (0 <= column - 1) && (column - 1 <= numColumns - 1) ) {
				((InfoCell) field[row - 1][column - 1]).setStatus(Configuration.STATUS_OPENED); // top left
			}	
			if ( (0 <= row - 1) && (row - 1 <= numRows - 1) && (0 <= column + 1) && (column + 1 <= numColumns - 1) )  {
				((InfoCell) field[row - 1][column + 1]).setStatus(Configuration.STATUS_OPENED); // top right
			}
			if ( (0 <= row) && (row + 1 <= numRows - 1) && (0 <= column - 1) && (column - 1 <= numColumns - 1) ) {
				((InfoCell) field[row + 1][column - 1]).setStatus(Configuration.STATUS_OPENED); // bottom left
			}
		}
	}
	
	
}
