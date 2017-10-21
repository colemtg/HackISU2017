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
        //initialize original pool
        ArrayList<Word> totalWords = notPool.getWordsInPool();
        Pool pool = new Pool();
        //add first 100 and remove 100
        for (int i=0; i<100; i++){
            pool.addWordToPool(totalWords.get(0));
            notPool.removeWord(totalWords.get(0));
        }
    }
}
