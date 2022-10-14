/**
 * DO NOT EDIT ANYTHING IN THIS FILE
*/
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author CS211 Instructors
 */
public class MouseReader extends MouseAdapter
{
	private Board board;

	public MouseReader(Board board)
	{
		this.board = board;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		String button = "";
		if (e.getButton() == MouseEvent.BUTTON1)
			button = "left";
		else if (e.getButton() == MouseEvent.BUTTON3)
			button = "right";
		board.mouseClickOnLocation(e.getX(), e.getY(), button);
	}
}
