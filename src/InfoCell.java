import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Qasim Shahid
 * The InfoCell class is responsible for creating InfoCell objects. It creates the InfoCells in the Mine Sweeper game, which if clicked, inform the player about adjacent mines, if any.
 * 
 */
public class InfoCell {
	
	/**
	 * int row is the row location of the InfoCell within the MineField field[row][] array.
	 * int column is the column location of the InfoCell within the MineField field[][column] array.
	 * int numOfAdjacentMines is the number of mines that are adjacent to the InfoCell (or are in the vicinity of the InfoCell).
	 * String Status is the current status of the InfoCell.
	 */
	private int row;
	private int column;
	private int numOfAdjacentMines;
	private String Status = Configuration.STATUS_COVERED;
	
	/** 
	 * Constructor for initializing a InfoCell at a specific row, column location with an appropriate value for numOfAdjacentMines based on its location in the MineField.
	 * @param row The row where the InfoCell is located.
	 * @param column The column where the InfoCell is located.
	 * @param numOfAdjacentMines The number of adjacent mines within the InfoCell's vicinity based on the location of the InfoCell within the MineField field[][] array.
	 */
	public InfoCell(int row, int column, int numOfAdjacentMines) {
		this.row = row;
		this.column = column;
		this.numOfAdjacentMines = numOfAdjacentMines;
	}
	
	/**
	 * This method draws the image of the InfoCell on the Board.
	 * @param g Graphics component
	 */
	public void draw(Graphics g) {
		g.drawImage(getImage(), (getHorizontalPosition()), (getVerticalPosition()), null);
	}
	
	/**
	 * Gets the top left horizontal position in pixels.
	 * @return Returns the top left horizontal position in pixels as an int.  
	 */
	public int getHorizontalPosition() {
		int HP = column  * Configuration.CELL_SIZE;
		return HP;
	}
	
	/**
	 * Gets the top left vertical position in pixels.
	 * @return Returns the top left vertical position in pixels as an int.  
	 */
	public int getVerticalPosition() {
		int VP = row * Configuration.CELL_SIZE;
		return VP;
	}
	
	/**
	 * Gets the current status of the InfoCell object.
	 * @return Returns a string with the current status of the InfoCell.
	 */
	public String getStatus() {
		return Status;
	}
	
	/**
	 * Changes the status of the InfoCell object.
	 * @param status The status that will be set. 
	 */
	public void setStatus(String status) {
		if (status.equals(Configuration.STATUS_COVERED) || status.equals(Configuration.STATUS_OPENED) 
				|| status.equals(Configuration.STATUS_MARKED) || status.equals(Configuration.STATUS_WRONGLY_MARKED))  {
			this.Status = status;
		}
	}
	
	/**
	 * This method gets the Image that will be drawn to the Board based on the status of the InfoCell.
	 * @return Returns the image according to the status of InfoCell.
	 */
	public Image getImage() {
		Image returnImg = new ImageIcon("img/covered_cell.png").getImage();
		if (Status.equals(Configuration.STATUS_COVERED)) {
			ImageIcon a = new ImageIcon("img/covered_cell.png");
			returnImg = a.getImage();
		}
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 0) {
			ImageIcon a = new ImageIcon("img/info_0.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_MARKED)) {
			ImageIcon a = new ImageIcon("img/marked_cell.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_WRONGLY_MARKED)) {
			ImageIcon a = new ImageIcon("img/wrong_mark.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 1) {
			ImageIcon a = new ImageIcon("img/info_1.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 2) {
			ImageIcon a = new ImageIcon("img/info_2.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 3) {
			ImageIcon a = new ImageIcon("img/info_3.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 4) {
			ImageIcon a = new ImageIcon("img/info_4.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 5) {
			ImageIcon a = new ImageIcon("img/info_5.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 6) {
			ImageIcon a = new ImageIcon("img/info_6.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 7) {
			ImageIcon a = new ImageIcon("img/info_7.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED) && numOfAdjacentMines == 8) {
			ImageIcon a = new ImageIcon("img/info_8.png");
			returnImg = a.getImage();
		}
		return returnImg;
	}
	
	/**
	 * This method is used to get the numOfAdjacnetMines for an InfoCell.
	 * @return Returns the value of an InfoCell's numOfAdjacentMines as an int.
	 */
	public int getNumOfAdjacentMines() {
		return numOfAdjacentMines;
	}

}
