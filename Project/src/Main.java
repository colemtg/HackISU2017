import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        //start by making 1000 words and adding them to the "not" pool
        NotPool notPool = new NotPool();
        try {
            FileReader file = new FileReader("words-and-pros.txt");
            BufferedReader bufferedReader = new BufferedReader(file);
            String line;
            int commonality=1;
            while ((line=bufferedReader.readLine())!=null) {
                String[] split = line.split(" ");
                Word currWord = new Word(split[0],split[1],"",commonality );
                notPool.addWordToPool(currWord);
                commonality++;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        notPool.sortWords();
        ArrayList<Word> testList = new ArrayList<Word>();
        testList = notPool.getWordsInPool();
        for (int i=0;i<100; i++){
            System.out.println(testList.get(i).getWord()+" "+testList.get(i).getDifficulty());
        }
    }
}
