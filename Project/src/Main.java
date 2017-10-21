import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

    }
}
