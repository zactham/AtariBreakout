import java.awt.Graphics;

public class Paddle extends GameObject
{
	

	public void draw (Graphics page)
	{
		super.draw(page);
		page.fillRect(getX(), getY(), getPaddleWidth(), getPaddleHeight());

	}
	
	@Override
	public void update()
	{
		super.update();
		
		updateBounds();
	}

}
