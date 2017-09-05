import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject 
{

	protected int x = 0;
	protected int y = 0;
	
	protected static final int gameSize = 500;//TODO change this based on screen size

	protected static final int ballSize = gameSize/50;
	
	protected static final int paddleWidth = gameSize/13;
	protected static final int paddleHeight = gameSize/33;
	
	private Rectangle bounds = null;




	public int getX()
	{
		return x;
	}

	public void setX(int xPos)
	{
		x = xPos;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int yPos)
	{
		y = yPos;
	}

	public int getBallSize()
	{
		return ballSize;
	}
	
	public int getPaddleWidth()
	{
		return paddleWidth;
	}
	
	public int getPaddleHeight()
	{
		return paddleHeight;
	}
	

	public Rectangle getBounds()
	{
		return bounds;
	}

	public void createBounds(int x, int y, int width, int height)
	{
		if(getBounds() == null)
		{
			bounds = new Rectangle(x, y, width, height);

		}
	}

	protected void drawBounds(Graphics page)
	{
		//page.setColor(Color.white);
		//page.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int) bounds.getHeight());

	}

	public void updateBounds()
	{
		if (getBounds() != null)
			bounds.setLocation(x, y);


		//updateBounds();
	}

	public void draw (Graphics page)
	{
		//drawBounds(page);
	}

	public void update()
	{

	}
}
