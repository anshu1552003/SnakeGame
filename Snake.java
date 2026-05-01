package snake;

import java.awt.*;
import java.util.*;

public class Snake {

    LinkedList<Point> body = new LinkedList<>();

    public Snake(int x,int y){
        // ✅ proper initial body (prevents instant collision)
        body.add(new Point(x,y));
        body.add(new Point(x-25,y));
        body.add(new Point(x-50,y));
    }

    public Point getHead(){
        return body.getFirst();
    }

    public java.util.List<Point> getBody(){
        return body;
    }

    public void move(Point newHead, boolean grow){

        body.addFirst(newHead);

        if(!grow){
            body.removeLast();
        }
    }

    public boolean selfCollision(){
        Point head = getHead();

        for(int i=1;i<body.size();i++){
            if(head.equals(body.get(i))){
                return true;
            }
        }
        return false;
    }
}