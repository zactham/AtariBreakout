import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Breakout extends JPanel implements KeyListener
{
	Sound sound;
	private int score = 0;
	private JFrame start;

	private InputManager inputManager;

	private final int gameSize = 500;
	private final int scorex = 20; // dont change
	private final int scorey = gameSize-20;

	private Paddle paddle = new Paddle();
	private boolean paddleCollide = false;
	

	private Ball ball = new Ball();

	private int paddleSpeed = 6;

	private Blocks allBlocks = new Blocks();
	private int ballSpeed = 5;
	private boolean blockCollide = false;



	// Constructor
	public Breakout()
	{
		setFocusable(true);
		// Register for mouse events on the panel
		addKeyListener(this);
	}


	public void init(int level)
	{
		sound = new Sound();

		setPreferredSize(new Dimension(gameSize, gameSize));

		inputManager = new InputManager();
		// launch game
		JFrame frame = new JFrame("Sample Frame");
		frame.add(this);
		frame.setTitle("Game Title");
		//setBackground(Color.black);
		JOptionPane.showMessageDialog(start, "Game Instructions");

		paddle.setX(gameSize/2);
		paddle.setY(gameSize-60);
		paddle.createBounds(paddle.getX(), paddle.getY(), paddle.getPaddleWidth(), paddle.getPaddleHeight());

		//TODO make init functions
		ball.setX(paddle.getX()+paddle.getPaddleWidth()/2-ball.getBallSize()/2);
		ball.setY(paddle.getY()-ball.getBallSize()-5);
		ball.createBounds(ball.getX(), ball.getY(), ball.getBallSize(), ball.getBallSize());

		blockSetup();




		//Sets the speed of the game for each mode
		if (level == 1)		// easy
		{

		}

		if (level == 2)		// medium
		{

		}

		if (level == 3)		// hard
		{

		}

		playMusicMain();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerWindow();
		frame.setLocationRelativeTo(TitleScreen.theApp);

		// runs the mainLoop
		ActionListener timerAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainLoop();

			}

		};

		// Frame rate, updates the frame every 15ms --- 60fps
		Timer timer = new Timer(15, timerAction);
		timer.setRepeats(true);
		timer.start();


	}

	public void MainLoop()
	{
		checkKeys();
		updateGame();
		repaint();
	}

	public void updateGame()
	{
		paddle.update();
		ball.update();

		if(blockCollide)
		{
			ball.setX(ball.getX()+ball.getX()/paddle.getX());
			ball.setY(ball.getY()+ballSpeed);
		}
			//moveBallDown();
		else
			blockCollision();
		if(paddleCollide)	
		{
			ball.setX(ball.getX()+ball.getX()/paddle.getX());
			ball.setY(ball.getY()-ballSpeed);
		}
		else
		{
			moveBallDown();
			paddleCollision();
		}
		
		ballOutofBounds();
		

	}

	public void blockSetup()
	{
		for(int r = 0; r < 4; r++)
		{
			for (int c = 0; c<12; c++)
			{
				Blocks b = new Blocks();

				if (c>0)
					b.setX(allBlocks.getBlock(c-1,r+1).getX() + b.getBlockWidth());
				else
					b.setX(0);
				if(r>0)
					b.setY(allBlocks.getBlock(c,r).getY() + b.getBlockHeight());
				else
					b.setY(0);

				b.createBounds(b.getX(), b.getY(), b.getBlockWidth(), b.getBlockHeight());
				allBlocks.addBlock(b,r+1);

			}
		}
	}

	public void blockCollision()
	{
		for(int r = 1; r<5; r++)
		{
			for(int i = 0; i < allBlocks.getTotalBlocks(r); i++)
			{
				if(allBlocks.getBlock(i,r).getBounds().intersects(ball.getBounds()))
				{
					System.out.println("Ball hit block in row: " + r);
					allBlocks.removeBlock(i,r);
					score++;
					blockCollide =  true;
					paddleCollide = false;
					break;
				}
			}
		}
	}
	
	public void ballOutofBounds()
	{
		if(ball.getY()<=0)
			blockCollide = true;
		if(ball.getX()<=0)
		{
			ball.setX(ball.getX()+ballSpeed);
			ball.setY(ball.getY()+ballSpeed);
		}
		
		if(ball.getX()+ball.getBallSize()>=gameSize)
		{
			ball.setX(ball.getX()-ballSpeed);
			ball.setY(ball.getY()+ballSpeed);
		}
		
	}

	public void moveBallDown()
	{
		ball.setY(ball.getY()+ballSpeed);
	}
	//ball.setX(ball.getX()-ballSpeed);
	//ball.setY(ball.getY()-ballSpeed);
	public void moveBallUp()
	{
		ball.setY(ball.getY()-ballSpeed);
	}

	
	//Create a right and left bounds for the paddle, so if it hits left the ball moves up y and left x
	//Do the same for each block
	public void paddleCollision()
	{
		if(ball.getBounds().intersects(paddle.getBounds()))
		{
			System.out.println("Ball hit paddle");
			paddleCollide = true;
			blockCollide = false;
		}
	}

	public void playMusicMain()
	{
		//sound.play("IngameMusic.wav");
	}

	public void playSoundEffect()
	{
		//sound.play("SMACK Sound Effect.wav");
	}

	// Centers the window
	public void centerWindow()
	{
		// gets top level window
		Window window;
		Container c = getParent();
		while (c.getParent() != null)
			c = c.getParent();

		// center window
		if (c instanceof Window)// if it is the top window...
		{
			// centers it
			window = (Window) c;
			window.pack();
			window.setLocationRelativeTo(null);
		}
	}

	//
	//When the game ends
	//
	public void gameEnding()
	{

		sound.stop();

		int result = JOptionPane.showConfirmDialog(this, 
				"Your Score: " + score + " - Play Again?", 
				"Game Over", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.NO_OPTION)
		{
			// no
			System.exit(0);
		}
		else
		{
			// yes, play again
			resetGame();
		}
	}

	private void resetGame()
	{

	}

	public void displayScore(Graphics page)
	{
		//Displays the Score
		page.setColor(Color.black);
		page.setFont(new Font("Comic Sans MS", Font.PLAIN, gameSize/20));
		page.drawString("SCORE: " + Integer.toString(score), scorex, scorey);
	}

	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);		// paint baseclass members too

		page.setColor(Color.white);
		displayScore(page);

		page.setColor(Color.GRAY);
		paddle.draw(page);

		page.setColor(Color.black);
		ball.draw(page);

		for(int r = 1; r<5; r++)
		{
			for(int i = 0; i < allBlocks.getTotalBlocks(r); i++)
			{

				if(r==1)
					page.setColor(Color.red);
				else if(r==2)
					page.setColor(Color.yellow);
				else if(r==3)
					page.setColor(Color.green);
				else if(r==4)
					page.setColor(Color.blue);

				allBlocks.getBlock(i,r).draw(page);
			}
		}
	}

	public int getScore()
	{
		return score;
	}

	public void checkKeys()
	{
		//Pressing the keys

		if (inputManager.getKeyPressed(KeyEvent.VK_LEFT)==true
				&& paddle.getX() > 0)
		{
			paddle.setX(paddle.getX()-paddleSpeed);
		}

		else if(inputManager.getKeyPressed(KeyEvent.VK_RIGHT)
				&& paddle.getX() + paddle.getPaddleWidth() < gameSize)
		{
			paddle.setX(paddle.getX()+paddleSpeed);

		}


	}



	public void keyPressed(KeyEvent arg0) 
	{
		int c = arg0.getKeyCode();
		inputManager.setKeyPressed(c, true);
	}

	public void keyReleased(KeyEvent arg0) 
	{
		int c = arg0.getKeyCode();
		inputManager.setKeyPressed(c, false);
	}



	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

