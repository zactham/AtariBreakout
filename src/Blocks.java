import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Blocks 
{
	//These are the values for when the gameSize is 1000
		private int blockX = 60;
		private int blockY = 900;

		private int blockWidth = 30;
		private int blockHeight = 80;
		
		private ArrayList <Blocks> allBlocks = new ArrayList();

		public int getBlockX()
		{
			return blockX;
		}

		public void setBlockX(int x)
		{
			blockX = x;
		}
		
		public void setBlockWidth(int w)
		{
			 blockWidth = w;
		}

		public int getBlockWidth()
		{
			return blockWidth;
		}
		
		public void setBlockHeight(int h)
		{
			 blockHeight = h;
		}

		public int getBlockHeight()
		{
			return blockHeight;
		}


		public int getBlockY()
		{
			return blockY;
		}

		public void setBlockY(int y)
		{
			blockY = y;
		}
		
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
			page.fillRect(getBlockX(), getBlockY(), getBlockWidth(), getBlockHeight());
			
			page.setColor(Color.BLACK);
			page.drawRect(getBlockX(), getBlockY(), getBlockWidth(), getBlockHeight());
			


		}
}
