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
	
	private Ball ball = new Ball();
	
	private int paddleSpeed = 6;
	
	private Blocks allBlocks = new Blocks();
	
	


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

		paddle.setPaddleWidth(gameSize/13);
		paddle.setPaddleHeight(gameSize/33);
		
		paddle.setPaddleX(gameSize/2);
		paddle.setPaddleY(gameSize-60);
		
		
		ball.setBallSize(gameSize/50);
		ball.setBallX(paddle.getPaddleX()+paddle.getPaddleWidth()/2-ball.getBallSize()/2);
		ball.setBallY(paddle.getPaddleY()-ball.getBallSize()-5);
		
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
		repaint();
	}

	public void blockSetup()
	{
		for(int r = 1; r < 5; r++)
		{
			for (int c = 0; c<12; c++)
			{
				Blocks b = new Blocks();
				b.setBlockHeight(gameSize/50);
				b.setBlockWidth(gameSize/12);
				b.setBlockX(c*12);
				b.setBlockY(r*(gameSize/4));
				allBlocks.addBlock(b);
			}
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
		
		
		for(int i = 0; i < allBlocks.getTotalBlocks(); i++)
		{
			
			if(i<12)
				page.setColor(Color.red);
			else if(i<24)
				page.setColor(Color.yellow);
			else if(i<36)
				page.setColor(Color.green);
			else if(i<48)
				page.setColor(Color.blue);
			
			allBlocks.getBlock(i).draw(page);
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
				&& paddle.getPaddleX() > 0)
		{
			paddle.setPaddleX(paddle.getPaddleX()-paddleSpeed);
		}

		else if(inputManager.getKeyPressed(KeyEvent.VK_RIGHT)
				&& paddle.getPaddleX() + paddle.getPaddleWidth() < gameSize)
		{
			paddle.setPaddleX(paddle.getPaddleX()+paddleSpeed);

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

