import java.io.File;
import java.util.Scanner;
/**
 * @author Qasim Shahid
 * The Configuration class is the class responsible for loading all values from the configuration file into the board. 
 * It uses method loadParameters to accomplish this. The method reads the file using a Scanner and assigns values appropriately. 
 */

public class Configuration {
	/** 
	 * int ROWS is number of rows of cells on the board of the game
	 * int COLS is number of columns of cells on the board of the game
	 * int CELL_SIZE is width/height of the square-size cell (in pixels)
	 * int MINES is number of mines hidden in the mine field
	 * int BOARD_WIDTH is the width of the board in pixels. It’s calculated by the formula: number of columns * width of the cell + 1
	 * int BOARD_HEIGHT is the height of the board in pixels. It’s calculated by the formula: number of rows * height of the cell + 1
	 * String STATUS_COVERED is a string that represents the tag we use in the code when a cell is hidden
	 * String STATUS_OPENED is a string that represents the tag we use in the code when a cell is uncovered
	 * String STATUS_MARKED is a string that represents the tag we use in the code when a cell is marked as a mine
	 * All of these fields are static as they only require one copy. 
	 */
	
	public static int ROWS;
	public static int COLS;
	public static int CELL_SIZE;
	public static int MINES;
	public static int BOARD_WIDTH;
	public static int BOARD_HEIGHT;
	public static String STATUS_COVERED;
	public static String STATUS_OPENED;
	public static String STATUS_MARKED;
	public static String STATUS_WRONGLY_MARKED;
	
	/** 
	 * loadParameters takes input from the configuration file and loads the appropriate values into their respective instance variables within the Configuration class.
	 * @exception Exception Exception raised if file is not found.
	 * @param filename The configuration file that will be read by the loadParameters method and sets the layout for the Board. 
	 */

	public static void loadParameters(String filename) {
		try {
			File Config = new File(filename);

			Scanner Reader1 = new Scanner(Config);
			int count1 = 0;
			int count2 = 0;

			while (Reader1.hasNext()) {
				Reader1.next();
				count1 = count1 + 1;
			}

			String[] Input = new String[count1];

			Scanner Reader2 = new Scanner(Config);

			while (Reader2.hasNext()) {
				Input[count2] = Reader2.next();
				count2 = count2 + 1;
			}

			for (int i = 0; i < Input.length; i++) {
				if (Input[i].contains("ROWS")) {
					ROWS = Integer.parseInt(Input[i + 1]);
				} else if (Input[i].contains("COLS")) {
					COLS = Integer.parseInt(Input[i + 1]);
				} else if (Input[i].contains("MINES")) {
					MINES = Integer.parseInt(Input[i + 1]);
				} else if (Input[i].contains("CELL_SIZE")) {
					CELL_SIZE = Integer.parseInt(Input[i + 1]);
				} else if (Input[i].contains("STATUS_COVERED")) {
					STATUS_COVERED = Input[i + 1];
				} else if (Input[i].contains("STATUS_OPENED")) {
					STATUS_OPENED = Input[i + 1];
				} else if (Input[i].contains("STATUS_MARKED")) {
					STATUS_MARKED = Input[i + 1];
				} else if (Input[i].contains("STATUS_WRONGLY_MARKED")) {
					STATUS_WRONGLY_MARKED = Input[i + 1];
				}
			}
			BOARD_WIDTH = COLS * CELL_SIZE + 1;
			BOARD_HEIGHT = ROWS * CELL_SIZE + 1;

			Reader1.close();
			Reader2.close();

		} 
		catch (Exception e) {
			System.out.println("Error! File not found!");
		}
	}

}
