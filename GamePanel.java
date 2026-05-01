package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, Runnable {

    GameController controller;
    MainFrame frame;
    Thread t;

    public GamePanel(MainFrame frame, String name){
        this.frame = frame;
        controller = new GameController(name);

        setFocusable(true);
        addKeyListener(this);

        setBackground(ThemeManager.bg);

        t = new Thread(this);
        t.start();
    }

    public void run(){
        while(true){
            controller.update();
            repaint();

            try{
                Thread.sleep(Math.max(60, controller.state.speed));
            }catch(Exception e){}
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // ✅ APPLY THEME EVERY FRAME
        setBackground(ThemeManager.bg);

        int i=0;
        for(Point p:controller.snake.getBody()){
            g.setColor(i==0?ThemeManager.head:ThemeManager.body);
            g.fillRoundRect(p.x,p.y,25,25,10,10);
            i++;
        }

        // food
        g.setColor(Color.RED);
        g.fillOval(controller.food.x,controller.food.y,25,25);

        // power
        if(controller.power!=null){
            g.setColor(Color.CYAN);
            g.fillOval(controller.power.x,controller.power.y,25,25);
        }

        // text
        g.setColor(Color.WHITE);
        g.drawString("Player: "+controller.state.playerName,10,20);
        g.drawString("Score: "+controller.state.score,10,40);
        g.drawString("Level: "+controller.state.level,10,60);

        if(controller.state.gameOver){
            g.drawString("GAME OVER (R=Restart, M=Menu)",200,300);
        }
    }

    public void keyPressed(KeyEvent e){

        GameState s = controller.state;

        switch(e.getKeyCode()){

            // ✅ MOVE CONTROLS (with reverse block)
            case KeyEvent.VK_UP:
                if(s.direction != GameState.Direction.DOWN){
                    s.direction = GameState.Direction.UP;
                }
                break;

            case KeyEvent.VK_DOWN:
                if(s.direction != GameState.Direction.UP){
                    s.direction = GameState.Direction.DOWN;
                }
                break;

            case KeyEvent.VK_LEFT:
                if(s.direction != GameState.Direction.RIGHT){
                    s.direction = GameState.Direction.LEFT;
                }
                break;

            case KeyEvent.VK_RIGHT:
                if(s.direction != GameState.Direction.LEFT){
                    s.direction = GameState.Direction.RIGHT;
                }
                break;

            // ✅ PAUSE / RESUME
            case KeyEvent.VK_P:
                s.paused = !s.paused;
                break;

            // ✅ RESTART GAME
            case KeyEvent.VK_R:
                controller.init();
                break;

            // ✅ BACK TO MENU
            case KeyEvent.VK_M:
                frame.backToMenu();
                break;
        }
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}