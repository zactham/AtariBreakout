import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Blocks extends GameObject
{
	//These are the values for when the gameSize is 1000
		private int blockX = 60;
		private int blockY = 900;

		private int blockWidth = 30;
		private int blockHeight = 80;
		
		private ArrayList <Blocks> allBlocks = new ArrayList();
		
		public void addBlock(Blocks b)
		{
			allBlocks.add(b);
		}
		
		public Blocks getBlock(int i)
		{
			return allBlocks.get(i);
		}
		
		public int getTotalBlocks()
		{
			return allBlocks.size();
		}

		public void draw (Graphics page)
		{
			super.draw(page);
			
			page.fillRect(getX(), getY(), getBlockWidth(), getBlockHeight());
			
			page.setColor(Color.BLACK);
			page.drawRect(getX(), getY(), getBlockWidth(), getBlockHeight());
			


		}
		
		@Override
		public void update()
		{
			super.update();
			
			updateBounds();
		}
}
