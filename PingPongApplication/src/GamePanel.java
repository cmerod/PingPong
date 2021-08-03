import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private static final int GAME_WIDTH = 1000, GAME_HEIGHT = GAME_WIDTH * 5/9;
    private static final int PADDLE_WIDTH = 20, PADDLE_HEIGHT = 80;
    private static final int BALL_DIAMETER = 20;
    private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    private Paddle paddle1, paddle2;
    private Ball ball;
    private final Score score;
    private Thread gameThread;

    public GamePanel(){
        newPaddlesAndBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);

        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(this);

        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newPaddlesAndBall(){
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT);

        Random random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), (GAME_HEIGHT/2)-(BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER, random.nextInt(2), random.nextInt(2));
    }

    public void paint(Graphics g){
        Image image = createImage(getWidth(), getHeight());
        Graphics graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics graphics){
        paddle1.draw(graphics);
        paddle2.draw(graphics);
        ball.draw(graphics);
        score.draw(graphics);
    }

    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){
        if(ball.getY() < 0 || ball.getY() > GAME_HEIGHT-BALL_DIAMETER){
            ball.setYVelocity(-ball.getYVelocity());
        }
        if(ball.intersects(paddle1)){
            ball.setXVelocity(Math.abs(ball.getXVelocity())+1);
        }else if(ball.intersects(paddle2)){
            ball.setXVelocity(-(Math.abs(ball.getXVelocity())+1));
        }

        if(paddle1.getY() < 0){
            paddle1.setLocation((int)paddle1.getX(), 0);
        }else if(paddle1.getY() > GAME_HEIGHT-PADDLE_HEIGHT){
            paddle1.setLocation((int)paddle1.getX(), GAME_HEIGHT-PADDLE_HEIGHT);
        }
        if(paddle2.getY() < 0){
            paddle2.setLocation((int)paddle2.getX(), 0);
        }else if(paddle2.getY() > GAME_HEIGHT-PADDLE_HEIGHT){
            paddle2.setLocation((int)paddle2.getX(), GAME_HEIGHT-PADDLE_HEIGHT);
        }

        if(ball.getX() <= 0){
            score.setPlayer2(score.getPlayer2()+1);
            newPaddlesAndBall();
        }else if(ball.getX() >= GAME_WIDTH-BALL_DIAMETER){
            score.setPlayer1(score.getPlayer1()+1);
            newPaddlesAndBall();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ns = 1000000000/60.0;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_UP){
            paddle2.setUpAcceleration(true);
        }else if(event.getKeyCode() == KeyEvent.VK_DOWN){
            paddle2.setDownAcceleration(true);
        }
        if(event.getKeyCode() == KeyEvent.VK_W){
            paddle1.setUpAcceleration(true);
        }else if(event.getKeyCode() == KeyEvent.VK_S){
            paddle1.setDownAcceleration(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_UP){
            paddle2.setUpAcceleration(false);
        }else if(event.getKeyCode() == KeyEvent.VK_DOWN){
            paddle2.setDownAcceleration(false);
        }
        if(event.getKeyCode() == KeyEvent.VK_W){
            paddle1.setUpAcceleration(false);
        }else if(event.getKeyCode() == KeyEvent.VK_S){
            paddle1.setDownAcceleration(false);
        }
    }
}
