import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Qasim Shahid
 * The MineCell class is responsible for creating MineCell objects. It creates the mines in the Mine Sweeper game, which if clicked upon, result in the game being lost.
 */

public class MineCell {
	
	/**
	 * int row is the row location of the MineCell within the MineField field[row][] array.
	 * int column is the column location of the MineCell within the MineField field[][column] array.
	 * String Status is the current status of the MineCell. It can be opened, marked, or covered.
	 */
	private int row; 
	private int column;
	private String Status = Configuration.STATUS_COVERED;
	
	/**
	 * Constructor for initializing a MineCell with a specific row and column location.
	 * @param row The row where the MineCell is located.
	 * @param column The column where the MineCell is located.
	 */
	public MineCell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * This method draws the image of the MineCell on the Board.
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
	 * Gets the current status of the MineCell object.
	 * @return Returns a string with the current status of the MineCell.
	 */
	public String getStatus() {
		return Status;
	}
	
	/**
	 * Changes the status of the MineCell object.
	 * @param status The status that will be set. 
	 */
	public void setStatus(String status) {
	
		if (Status.equals(Configuration.STATUS_COVERED) && status.equals(Configuration.STATUS_OPENED)) {
			this.Status = status;
		}
		
		else if (Status.equals(Configuration.STATUS_COVERED) && status.equals(Configuration.STATUS_MARKED)) {
			this.Status = status;
		}
		
		else if (Status.equals(Configuration.STATUS_MARKED) && status.equals(Configuration.STATUS_OPENED)) {
			this.Status = status;
		}
		
		else if (Status.equals(Configuration.STATUS_MARKED) && status.equals(Configuration.STATUS_COVERED)) {
			this.Status = status;
		}
		
		else if (Status.equals(Configuration.STATUS_OPENED)) {
			this.Status = status;
		}
		
		
	}
	
	/**
	 * This method gets the Image that will be drawn to the Board based on the status of the MineCell.
	 * @return Returns the image according to the status of MineCell.
	 */
	public Image getImage() {
		Image returnImg = new ImageIcon("img/covered_cell.png").getImage();
		if (Status.equals(Configuration.STATUS_COVERED)) {
			ImageIcon a = new ImageIcon("img/covered_cell.png");
			returnImg = a.getImage();
		}
		else if (Status.equals(Configuration.STATUS_OPENED)) {
			ImageIcon a = new ImageIcon("img/mine_cell.png");
			returnImg = a.getImage();
		}
		
		else if (Status.equals(Configuration.STATUS_MARKED)) {
			ImageIcon a = new ImageIcon("img/marked_cell.png");
			returnImg = a.getImage();
		}
		
		return returnImg;
	}

}

