 

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball{
    private static final int DIAMETER = 30;
    public int x = 150, y = 300, xa = 1, ya = 1;
    private Game game;
    private int speed = 2, counter = 0;
    
    //constructor
    public Ball(Game game){
        this.game = game;
        this.x = (int)(game.getWidth()/2);
    }
    
    void move() {
        //bounces the ball back if it hits the left wall
        if (x + xa < 0)
            xa = speed;
        //bounces the ball back if it hits the right wall
        if (x + xa > game.getWidth() - DIAMETER)
            xa = -speed;
        //on collision with the first racquet, reverse the ball's vertical direction
        if(collision1()){
            ya = -speed;
            y = game.racquet1.getTopY() - DIAMETER;
        }
        //on collision with the second racquet, reverse the ball's vertical direction
        if(collision2()){
            ya = speed;
            y = game.racquet2.getBottomY() + DIAMETER;
        }

        x = x + xa;
        y = y + ya;
    }

    private boolean collision1(){
        return game.racquet1.getBounds().intersects(getBounds());
    }

    private boolean collision2(){
        return game.racquet2.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds(){
        return new Rectangle(this.x, this.y, DIAMETER, DIAMETER);
    }
    //draw the ball onto the screen
    public void paint(Graphics2D g){
        g.fillOval(this.x, this.y, 30, 30);
    }
}
