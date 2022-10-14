import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Qasim Shahid, CS211 Instructors
 * This class is responsible for creating the GUI for the Mine Sweeper game. It also loads the Board with the values of Configuration's instance variables to create the Board.
 */

public class Play
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()  
			{
				public void run()
				{
					Configuration.loadParameters("config1");
					JFrame frame = new JFrame();
					JLabel statusbar = new JLabel("Select a cell");
					frame.add(statusbar, BorderLayout.SOUTH);
					frame.add(new Board(Configuration.ROWS, Configuration.COLS, Configuration.MINES, statusbar));
					frame.setResizable(false);
					frame.pack();
					frame.setTitle("Counterfeit Minesweeper");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
	}
}
