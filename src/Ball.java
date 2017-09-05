import java.awt.Graphics;

public class Ball extends GameObject
{
	public void draw (Graphics page)
	{
		page.fillRect(getX(), getY(), getBallSize(), getBallSize());
	}
}
