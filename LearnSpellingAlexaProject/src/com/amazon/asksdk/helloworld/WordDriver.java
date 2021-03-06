package com.amazon.asksdk.helloworld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WordDriver
{
    public static void initializeWords()
    {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        notPool.sortWords();
        Pool pool = new Pool();
        //add first 100 and remove 100

        for (int i = 0; i < 100; i++) {
            Pool.addWordToPool( NotPool.getWordsInPool().get(0));
        }

        /*
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
            System.out.println("difficulty: " +word.getDifficulty());
            System.out.println(word.getWord());
            in=input.nextLine();
            System.out.println(word.getWord().equals(in));
            Pool.update(word,in.equals(word.getWord()));
        }
        */

    }
}
