

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//http://www.dictionaryapi.com/api/v1/references/collegiate/xml/would?key=782cdb78-2970-44f3-a699-e4c511831106
public class drive {
    public static void main(String[] args) {
        Word word = new Word("steam", "stēm","",991);
        for(int i=0; i<word.getRules().size(); i++)
        {
            System.out.println(word.getRules().get(i).getTo());
            System.out.println(word.getRules().get(i).getFrom());
        }
        System.out.println(word.getDifficulty());
    }
}