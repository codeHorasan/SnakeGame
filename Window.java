import javax.swing.JFrame;

public class Window extends JFrame {
	public static void main(String[] args) {
		Window window = new Window();
		window.setResizable(false);
		window.setSize(600, 600);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setTitle("Snake");
		Game game = new Game();
		game.addKeyListener(game);
		game.setFocusable(true);
		window.add(game);		
		window.setVisible(true);
	}

}
