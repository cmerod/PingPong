import java.awt.*;

public class Paddle extends Rectangle {
    private static final double GRAVITY = 0.94;
    private int yVelocity;
    private boolean upAcceleration, downAcceleration;

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        yVelocity = 0;
        upAcceleration = false;
        downAcceleration = false;
    }

    public void draw(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, width, height);
    }

    public void move(){
        if(upAcceleration){
            yVelocity -= 2;
        }
        if(downAcceleration){
            yVelocity += 2;
        }
        if(!upAcceleration && !downAcceleration){
            yVelocity *= GRAVITY;
        }

        if(yVelocity < -5){
            yVelocity = -5;
        }else if(yVelocity > 5){
            yVelocity = 5;
        }

        y += yVelocity;
    }

    public void setUpAcceleration(boolean keyPressed){
        upAcceleration = keyPressed;
    }

    public void setDownAcceleration(boolean keyPressed){
        downAcceleration = keyPressed;
    }
}
