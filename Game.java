import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

  class Snake {
	  int xSnake;
	  int ySnake;
	  int direction;
	  
	public Snake(int xSnake, int ySnake, int direction) {
		super();
		this.xSnake = xSnake;
		this.ySnake = ySnake;
		this.direction = direction;
	}

	public int getxSnake() {
		return xSnake;
	}
	public void setxSnake(int xSnake) {
		this.xSnake = xSnake;
	}
	public int getySnake() {
		return ySnake;
	}
	public void setySnake(int ySnake) {
		this.ySnake = ySnake;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
  }
  
  class ChangingPoint {
	  int xChange;
	  int yChange;
	  int direction;
	  
	public ChangingPoint(int xChange, int yChange, int direction) {
		super();
		this.xChange = xChange;
		this.yChange = yChange;
		this.direction = direction;
	}
	
	public int getxChange() {
		return xChange;
	}
	public void setxChange(int xChange) {
		this.xChange = xChange;
	}
	public int getyChange() {
		return yChange;
	}
	public void setyChange(int yChange) {
		this.yChange = yChange;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
}
  class Food {
	  int xPos;
	  int yPos;
	  
	public Food() {
		super();
	}
	
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}	  
  }
  
  class Line {
	  int x1, y1, x2, y2;

	public Line(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
  }

public class Game extends JPanel implements KeyListener, ActionListener {
	int xYýlan = 200;
	int yYýlan = 200;
	Snake snake1 = new Snake(xYýlan,yYýlan,1);
	ArrayList<Snake> snake = new ArrayList<Snake>();
	ArrayList<ChangingPoint> points = new ArrayList<ChangingPoint>();
	ArrayList<Line> lines = new ArrayList<Line>();
	Timer timer = new Timer(120,this);
	Food food = new Food();
	static Random random = new Random();
	int score = 0;
	int length = 3;
	JLabel labelScore;
	JLabel labelLength;
	//direction ==> 1; //5-->up 2-->down  1-->left  3-->right

	
	public Game() {
		setLayout(null);
		labelScore = new JLabel();
		labelScore.setLocation(0,2);
		labelScore.setSize(70,15);
		labelScore.setText("Score : " + score);
		add(labelScore);
	
		
		labelLength = new JLabel();
		labelLength.setLocation(525, 2);
		labelLength.setSize(70, 15);
		labelLength.setText("Length : " + length);
		add(labelLength);
		
		snake.add(snake1);
		snake.add(new Snake(220, 200, 1));
		snake.add(new Snake(240, 200, 1));
		food.setxPos(random.nextInt(500));
		food.setyPos(random.nextInt(500));		
		timer.start();	
		
	}	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.GREEN);
		for (Snake snake : snake) {
			g.fillRect(snake.getxSnake(), snake.getySnake(), 20, 20);
		}
		g.setColor(Color.ORANGE);
		g.fillOval(food.getxPos(), food.getyPos(), 18, 18);
		
		g.setColor(Color.BLACK);
		//Drawing lines 
		for(Snake snake : snake) {
			if(snake.getDirection() == 1) 
				g.drawLine(snake.getxSnake() + 20, snake.getySnake(), snake.getxSnake() + 20, snake.getySnake() + 20);
				else if(snake.getDirection() == 3) 
					g.drawLine(snake.getxSnake() , snake.getySnake(), snake.getxSnake() , snake.getySnake() + 20);
				else if(snake.getDirection() == 5) 
					g.drawLine(snake.getxSnake() , snake.getySnake() + 20, snake.getxSnake() + 20, snake.getySnake() + 20);
				else if(snake.getDirection() == 2) 
					g.drawLine(snake.getxSnake() , snake.getySnake() , snake.getxSnake() + 20, snake.getySnake() );
		}
		
		switch(snake.get(0).getDirection()) {
		case 1:
			g.fillOval(snake.get(0).getxSnake() + 5, snake.get(0).getySnake() + 5, 4, 4);
			break;
		case 3:
			g.fillOval(snake.get(0).getxSnake() + 10, snake.get(0).getySnake() + 5, 4, 4);
			break;
		case 2:
			g.fillOval(snake.get(0).getxSnake() + 5, snake.get(0).getySnake() + 5, 4, 4);
			break;
		case 5:
			g.fillOval(snake.get(0).getxSnake() + 10, snake.get(0).getySnake() + 5, 4, 4);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Snake snakelar : snake) {
			Point pYýlan = new Point(snakelar.getxSnake(), snakelar.getySnake());
			for (ChangingPoint changingPoint : points) {
				Point pChange = new Point(changingPoint.getxChange(), changingPoint.getyChange());
				if(pYýlan.equals(pChange)) 
					snakelar.setDirection(changingPoint.getDirection());
			}
		}
		
		
		for (Snake snake : snake) {
			if(snake.getDirection() == 1)
				snake.setxSnake(snake.getxSnake() - 20);
			else if(snake.getDirection() == 2)
				snake.setySnake(snake.getySnake() + 20);
			else if(snake.getDirection() == 3)
				snake.setxSnake(snake.getxSnake() + 20);
			else if(snake.getDirection() == 5)
				snake.setySnake(snake.getySnake() - 20);	
		}
		
		for(ChangingPoint changingPoint : points) {
			if(new Rectangle(changingPoint.getxChange(), changingPoint.getyChange(), 10, 10).intersects
					(new Rectangle(snake.get(snake.size() - 1).getxSnake(), snake.get(snake.size() - 1).getySnake(), 20, 20))) {
				snake.get(snake.size() - 1).setDirection(changingPoint.getDirection());
				changingPoint.setxChange(1000);
				changingPoint.setyChange(1000);	
			}
		}
		
		
		//optimization
		if(points.size() >= 10 && points.size() % 10 == 0) {
			points.remove(0);
		}
		
		
		
		if(new Rectangle(snake.get(0).getxSnake(), snake.get(0).getySnake(), 20, 20).intersects
				(new Rectangle(food.getxPos(), food.getyPos(), 18, 18)) ) {
			switch(snake.get(snake.size() - 1).getDirection()) {
			 case 1:
				 snake.add(new Snake(snake.get(snake.size() - 1).getxSnake() + 20, snake.get(snake.size() - 1).getySnake(), 1));
				 break;
			 case 2:
				 snake.add(new Snake(snake.get(snake.size() - 1).getxSnake(), snake.get(snake.size() - 1).getySnake() - 20, 2));
				 break;
			 case 3:
				 snake.add(new Snake(snake.get(snake.size() - 1).getxSnake() - 20, snake.get(snake.size() - 1).getySnake(), 3));
				 break;
			 case 5:
				 snake.add(new Snake(snake.get(snake.size() - 1).getxSnake(), snake.get(snake.size() - 1).getySnake() + 20, 5));
				 break;
			}

			int x = random.nextInt(500);
			int y = random.nextInt(500);
			
			//Food check
			boolean control = foodControl(x, y, snake);
			while(control == false) {
				x = random.nextInt(500);
				y = random.nextInt(500);
				control = foodControl(x, y, snake);
			}
			
			food.setxPos(x);
			food.setyPos(y);
			
			
			length++;
			score+=4;
		}
		for(Snake snake : snake) {
			if(snake.getxSnake() <= -20) {
				snake.setxSnake(580);
			}
			else if(snake.getxSnake() >= 580) {
				snake.setxSnake(0);
			}
			else if(snake.getySnake() <= -20) {
				snake.setySnake(580);
			}
			else if(snake.getySnake() >= 580) {
				snake.setySnake(0);
			}
		}
	
		labelScore.setText("Score : " + score);
		labelLength.setText("Length : " + length);
		
		
		for(int i=1; i<snake.size(); i++) {
			if(new Rectangle(snake.get(0).getxSnake(), snake.get(0).getySnake(), 20, 20).intersects
					(new Rectangle(snake.get(i).getxSnake(), snake.get(i).getySnake(), 20, 20))) {
				timer.stop();
				String message = "Game Over!\nYour score is : " + score + "\nLength of the snake is : " + length;
				JOptionPane.showMessageDialog(this, message);
				System.exit(0);
			}
		}
		
			
		repaint();
	}
	
	public static boolean foodControl(int x, int y, ArrayList<Snake> snake) {
		for(Snake a : snake) {
			if(new Rectangle(a.getxSnake(), a.getySnake(), 20, 20).intersects(new Rectangle(x, y, 20, 20))) {
				return false;
			}
		}
		
		return true;	
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		
		if(c == KeyEvent.VK_DOWN && snake.get(0).getDirection() != 5) {
			points.add(new ChangingPoint(snake.get(0).getxSnake(), snake.get(0).getySnake(), 2));
		}
		else if(c == KeyEvent.VK_UP && snake.get(0).getDirection() != 2) {
			points.add(new ChangingPoint(snake.get(0).getxSnake(), snake.get(0).getySnake(), 5));
		}
		else if(c == KeyEvent.VK_LEFT && snake.get(0).getDirection() != 3) {
			points.add(new ChangingPoint(snake.get(0).getxSnake(), snake.get(0).getySnake(), 1));
		}
		else if(c == KeyEvent.VK_RIGHT && snake.get(0).getDirection() != 1) {
			points.add(new ChangingPoint(snake.get(0).getxSnake(), snake.get(0).getySnake(), 3));
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
