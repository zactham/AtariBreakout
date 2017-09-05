import java.awt.Graphics;

public class Paddle extends GameObject
{
	

	public void draw (Graphics page)
	{
		page.fillRect(getX(), getY(), getPaddleWidth(), getPaddleHeight());


	}

}
