import java.awt.Graphics;
import java.awt.Rectangle;
public class GameObject 
{
	
	private Rectangle bounds = null;
	private int x = 0;
	private int y = 0;
	
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
}
