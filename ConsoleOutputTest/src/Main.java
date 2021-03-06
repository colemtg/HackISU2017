import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //start by making 1000 words and adding them to the "not" pool
        NotPool notPool = new NotPool();
        try {
            URL file = new URL("https://raw.githubusercontent.com/colemtg/HackISU2017/master/words-pros-defs.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.openStream()));
            String line;
            int commonality = 1;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("%");
                Word currWord = new Word(split[0], split[1], split[2], commonality);
                notPool.addWordToPool(currWord);
                commonality++;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        notPool.sortWords();
        //add first 100 and remove 100
        for (int i = 0; i < 100; i++) {
            Pool.addWordToPool( NotPool.getWordsInPool().get(0));
        }

        //Test for changing frequency on the console
        Scanner input =new Scanner(System.in);
        Word word;
        String in;

        System.out.print("Spell word: ");
        for(int i=0; i<1000; i++) {
            System.out.println("current frequency: " + Pool.getCurrentFrequency());
            System.out.println("number of words in the pool: " + Pool.getWordsInPool().size());
            for(int j =0;j<Pool.getWordsInPool().size(); j++ )
            {
                System.out.println("Word: " +Pool.getWordsInPool().get(j).getWord()
                        +" Frequency: " +Pool.getWordsInPool().get(j).getFrequency());
            }
            word = Pool.generateWord();
            System.out.println("The total number of words is: "+Pool.getWordsInPool().size());
            System.out.println("difficulty: " +word.getDifficulty());
            System.out.println(word.getWord());
            in=input.nextLine();
            System.out.println(word.getWord().equals(in));
            Pool.update(word,in.equals(word.getWord()));
        }
    }
}
