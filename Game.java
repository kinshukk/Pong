import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


@SuppressWarnings("serial")
public class Game extends JPanel{

    Ball ball = new Ball(this);//create the ball sprite
    Racquet racquet1 = new Racquet(this, 585, 60, 10, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S);//create the first racquet
    Racquet racquet2 = new Racquet(this, 5, 60, 10, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN);//create the second racquet

    /*
    detect key presses using KeyListener interface and the KeyEvent class.
    One Key listener for each racquet
    */
    public Game(){
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){

            }

            @Override
            public void keyReleased(KeyEvent e1){
                racquet1.keyReleased(e1);
            }

            @Override
            public void keyPressed(KeyEvent e1){
                racquet1.keyPressed(e1);
            }
        });
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e2){

            }

            @Override
            public void keyReleased(KeyEvent e2){
                racquet2.keyReleased(e2);
            }

            @Override
            public void keyPressed(KeyEvent e2){
                racquet2.keyPressed(e2);
            }
        });
        setFocusable(true);
    }

    /*
    move functions of all the sprites in one function.
    */
    private void move(){
        ball.move();
        racquet1.move();
        racquet2.move();
    }

    /*
    painting all the sprites onto the screen, every frame
    */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet1.paint(g2d);
        racquet2.paint(g2d);
    }


    public static void main(String[] args)throws InterruptedException{
        int option = 0;
        JFrame frame = new JFrame("Pong");
        Game game = new Game();
        frame.add(game);
        frame.setSize(400, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //painting everything onto the screen, and wait 1 second for the players to get ready
        game.move();
        game.repaint();
        Thread.sleep(2000);

        while(true){
            game.move();
            game.repaint();
            
            //when the ball crosses the second player's racquet, player 1 wins
            if(game.ball.y < game.racquet2.getBottomY()){
                //System.out.println("racquet 1 wins");
                if(JOptionPane.showConfirmDialog(null, "Player 1 Wins!\nDo you want to restart the game?") == 0){
                    game.ball.x = 150; game.ball.y = 300;
                    Thread.sleep(2000);
                }else{
                    System.exit(0);
                }
            }

            //when the ball crosses the first player's racquet, player 2 wins
            if(game.ball.y > game.racquet1.getTopY()){
                //System.out.println("racquet 2 wins");
                if(JOptionPane.showConfirmDialog(null, "Player 2 Wins!\nDo you want to restart the game?") == 0){
                    game.ball.x = 150; game.ball.y = 300;
                    Thread.sleep(2000);
                }else{
                    System.exit(0);
                }
            }
            Thread.sleep(5);//let all the background threads run (paint and move)
        }
    }
}
