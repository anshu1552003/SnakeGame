package snake;

import java.awt.*;
import java.util.*;

public class GameController {

    Snake snake;
    Point food;
    Point power;

    GameState state;
    Random rand = new Random();

    public GameController(String name){
        state = new GameState(name);
        init();
    }

    void init(){
        snake = new Snake(300,300);

        state.reset(); // ✅ restart fix

        spawnFood();
        spawnPower();
    }

    void spawnFood(){
        Point p;
        do {
            p = new Point(rand.nextInt(24)*25, rand.nextInt(24)*25);
        } while (snake.getBody().contains(p)); // ✅ avoid snake body

        food = p;
    }

    void spawnPower(){
        Point p;
        do {
            p = new Point(rand.nextInt(24)*25, rand.nextInt(24)*25);
        } while (snake.getBody().contains(p)); // ✅ avoid snake body

        power = p;
    }

    void update(){

        if(state.paused || state.gameOver) return;

        Point head = snake.getHead();
        Point newHead = new Point(head);

        switch(state.direction){
            case UP -> newHead.y-=25;
            case DOWN -> newHead.y+=25;
            case LEFT -> newHead.x-=25;
            case RIGHT -> newHead.x+=25;
        }

        // wall collision
        if(newHead.x<0||newHead.x>=600||newHead.y<0||newHead.y>=600){
            gameOver();
            return;
        }

        boolean grow = newHead.equals(food);

        snake.move(newHead,grow);

        if(grow){
            state.score+=10;
            spawnFood();
        }

        if(power!=null && newHead.equals(power)){
            state.speed = Math.max(60, state.speed = Math.max(80, state.speed - 20));
            power=null;
        }

        if(snake.selfCollision()){
            gameOver();
        }
    }

    void gameOver(){
        state.gameOver = true;
        ScoreManager.save(state.playerName,state.score);
    }
}