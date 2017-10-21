

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class drive {
    public static void main(String[] args) {
        Word word = new Word("steam", "stēm","",1);
        for(int i=0; i<word.getRules().size(); i++)
        {
            System.out.println(word.getRules().get(i).getTo());
            System.out.println(word.getRules().get(i).getFrom());
        }
        System.out.println(word.getDifficulty());
    }
}