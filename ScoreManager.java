package snake;

import java.io.*;

public class ScoreManager {

    static String FILE = "scores.txt";

    public static void save(String name, int score){
        try{
            FileWriter fw = new FileWriter(FILE,true);
            fw.write(name+":"+score+"\n");
            fw.close();
        }catch(Exception e){}
    }
}