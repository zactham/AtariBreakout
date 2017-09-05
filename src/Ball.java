import java.awt.Graphics;

public class Ball extends GameObject
{
	public void draw (Graphics page)
	{
		super.draw(page);
		page.fillRect(getX(), getY(), getBallSize(), getBallSize());
	}
	
	@Override
	public void update()
	{
		super.update();
		
		updateBounds();
	}
}
