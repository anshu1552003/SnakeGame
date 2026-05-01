package snake;

public class GameState {

    enum Direction {UP,DOWN,LEFT,RIGHT}

    int score;
    int level;
    int speed;
    boolean gameOver;
    boolean paused;

    String playerName;
    Direction direction;

    public GameState(String name){
        this.playerName = name;
        reset();
    }

    void reset(){
        score = 0;
        level = 1;
        speed = 140;
        gameOver = false;
        paused = false;
        direction = Direction.RIGHT;
    }
}