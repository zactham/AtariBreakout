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



	private ArrayList <Blocks> rowOne = new ArrayList();
	private ArrayList <Blocks> rowTwo = new ArrayList();
	private ArrayList <Blocks> rowThree = new ArrayList();
	private ArrayList <Blocks> rowFour = new ArrayList();

	public void addBlock(Blocks b, int row)
	{
		if(row == 1)
			rowOne.add(b);
		if(row == 2)
			rowTwo.add(b);
		if(row == 3)
			rowThree.add(b);
		if(row == 4)
			rowFour.add(b);
	}

	public Blocks getBlock(int b, int row)
	{
		if(row == 1)
			return rowOne.get(b);
		if(row == 2)
			return rowTwo.get(b);
		if(row == 3)
			return rowThree.get(b);
		else
			return rowFour.get(b);

	}

	public int getTotalBlocks(int row)
	{
		if(row == 1)
			return rowOne.size();
		if(row == 2)
			return rowTwo.size();
		if(row == 3)
			return rowThree.size();
		if(row == 4)
			return rowFour.size();

		return 0;
	}

	public void draw (Graphics page)
	{
		super.draw(page);

		page.fillRect(getX(), getY(), getBlockWidth(), getBlockHeight());

		page.setColor(Color.BLACK);
		page.drawRect(getX(), getY(), getBlockWidth(), getBlockHeight());



	}

	public void removeBlock(int b, int row)
	{
		if(row == 1)
			rowOne.remove(b);
		if(row == 2)
			rowTwo.remove(b);
		if(row == 3)
			rowThree.remove(b);
		if(row == 4)
			rowFour.remove(b);
	}

	@Override
	public void update()
	{
		super.update();

		updateBounds();
	}
}
