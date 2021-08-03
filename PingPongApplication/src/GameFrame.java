import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private final GamePanel panel;

    public GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Ping Pong");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
