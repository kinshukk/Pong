 

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class Racquet{
    int x;
    int xa = 0;
    int length, y, height, width;
    int left, right;
    int stop;

    Game game;

    //constructor
    public Racquet(Game game, int y, int width, int height, int left, int right, int stop){
        this.game = game;
        this.y = y;
        this.width = width;
        this.height = height;
        this.left = left;
        this.right = right;
        this.x = 150;
        this.stop = stop;
    }

    //moves the racquet left or right if more space is left
    public void move(){
        if(this.x + this.xa > 0 && this.x + this.xa < game.getWidth()-this.width)
            this.x = this.x + this.xa;
    }

    //paints the racquet
    public void paint(Graphics2D g){
        g.fillRect(x, this.y, this.width, this.height);
    }

    //empty, detects the key release
    public void keyReleased(KeyEvent e){
    }

    //detects the key presses
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == this.left)
            this.xa = -3;
        if(e.getKeyCode() == this.right)
            this.xa = 3;
        if(e.getKeyCode() == this.stop)
            this.xa = 0;
    }

    //get the bounds of the racquet
    public Rectangle getBounds(){
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    //get the top y-coordinate
    public int getTopY(){
        return this.y;
    }

    //get the bottom y-coordinate
    public int getBottomY(){
        return this.y;
    }

}