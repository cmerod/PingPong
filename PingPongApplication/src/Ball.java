import java.awt.*;

public class Ball extends Rectangle {
    private static final int SPEED = 3;
    private int xVelocity;
    private int yVelocity;

    public Ball(int x, int y, int width, int height, int initialXVelocity, int initialYVelocity){
        super(x,y,width,height);

        if(initialXVelocity == 0){
            initialXVelocity = -1;
        }
        if(initialYVelocity == 0){
            initialYVelocity = -1;
        }
        xVelocity = initialXVelocity * SPEED;
        yVelocity = initialYVelocity * SPEED;
    }

    public void draw(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, width, height);
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    public void setYVelocity(int newVelocity){
        yVelocity = newVelocity;
    }

    public void setXVelocity(int newVelocity){
        xVelocity = newVelocity;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }
}
