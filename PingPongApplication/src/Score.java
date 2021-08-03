import java.awt.*;

public class Score extends Rectangle {
    private static int GAME_WIDTH, GAME_HEIGHT;
    private int player1;
    private int player2;

    public Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
        player1 = 0;
        player2 = 0;
    }

    public void draw(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Consolas", Font.PLAIN, 60));

        graphics.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);

        graphics.drawString(player1 / 10 + String.valueOf(player1 % 10), (GAME_WIDTH/2) - 85, 50);
        graphics.drawString(player2 / 10 + String.valueOf(player2 % 10), (GAME_WIDTH/2) + 20, 50);
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }
}
