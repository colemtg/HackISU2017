package com.amazon.asksdk.helloworld;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class Pool {
    private static ArrayList<Word> wordsInPool= new ArrayList<>();
    private static HashMap<Integer, ArrayList<Word> > rulesInPool = new HashMap<>();
    private static double currentFrequency;


    public static void update(Word word, Boolean bool)
    {
        if(bool)
        {
            for(int i=0; i<word.getRules().size(); i++)
            {
                for(int j=0; j<rulesInPool.get((word.getRules().get(i).getHashCode())).size(); j++)
                {
                    double oldF = rulesInPool.get((word.getRules().get(i).
                            getHashCode())).get(j).getFrequency();

                    double tempF = updateFrequency(rulesInPool.get((word.getRules().get(i).
                            getHashCode())).get(j),bool);

                    rulesInPool.get((word.getRules().get(i).getHashCode())).get(j).setFrequency(tempF);

                    currentFrequency=currentFrequency-(oldF-tempF);
                }
                //limit this in some way
                if(word.getFrequency() < 0.60) //5 incidental instances
                {
                    if (NotPool.getRulesInPool().containsKey(word.getRules().get(i).getHashCode())) //if notPool has rule
                    {
                        if (NotPool.getRulesInPool().get(word.getRules().get(i).getHashCode()).size() != 0) //if word in that rule
                        {
                            addWordToPool(NotPool.getRulesInPool().get(word.getRules().get(i).getHashCode()).get(0));
                        } else if (NotPool.getWordsInPool().size() != 0) {
                            addWordToPool(NotPool.getWordsInPool().get(0));
                        }
                    } else if (NotPool.getWordsInPool().size() != 0) {
                        currentFrequency = currentFrequency + NotPool.getWordsInPool().get(i).getFrequency();
                        addWordToPool(NotPool.getWordsInPool().get(0));
                    }
                }

            }

            double oldF = word.getFrequency();
            double tempF = updateFrequency(word,bool);
            word.setFrequency(tempF);
            currentFrequency=currentFrequency-(oldF-tempF);

        }
        else
        {
            for(int i=0; i<word.getRules().size(); i++)
            {
                for(int j=0; j<rulesInPool.get((word.getRules().get(i).
                        getHashCode())).size(); j++)
                {
                    double oldF = rulesInPool.get((word.getRules().get(i).
                            getHashCode())).get(j).getFrequency();

                    double tempF = updateFrequency(rulesInPool.get((word.getRules().get(i).
                           getHashCode())).get(j),bool);

                    rulesInPool.get((word.getRules().get(i).getHashCode())).get(j).setFrequency(tempF);

                    currentFrequency=currentFrequency+(tempF-oldF);
                }
            }
            double oldF = word.getFrequency();
            double tempF = updateFrequency(word,bool);
            word.setFrequency(tempF);
            currentFrequency=currentFrequency+(tempF-oldF);
        }
    }
    private static double updateFrequency(Word word, Boolean bool)
    {
        double newF;
        int offset=0;
        if(word.getRules().size()==0) offset=1; //in case word some how has no rules
        if(bool)
        {
            newF = word.getFrequency() - word.getFrequency()/((double)((4*(word.getRules().size()+offset))));
        }
        else
        {
            newF=word.getFrequency() + word.getFrequency()/(double)(10*(word.getRules().size()+offset));
        }
        return newF;
    }

    public static Word generateWord(){
        final SecureRandom generator = new SecureRandom();
        double randomNum = generator.nextDouble()*currentFrequency;
        for (int i =0; i<wordsInPool.size();i++){
            Word currWord = wordsInPool.get(i);
            randomNum-=currWord.getFrequency();
            if (randomNum<=0){
                return currWord;
            }
        }
        return null;
    }
    public static void addRuleToPool(Rule rule, Word word){
        if(rulesInPool.containsKey((rule.getHashCode())))
        {
            rulesInPool.get((rule.getHashCode())).add(word);
        }
        else
        {
            rulesInPool.put((rule.getHashCode()),new ArrayList<Word>());
            rulesInPool.get((rule.getHashCode())).add(word);
        }
    }
    //Words added to Pool must be removed from not Pool
    public static void addWordToPool(Word word){
        NotPool.removeWord(word);
        wordsInPool.add(word);
        currentFrequency = currentFrequency+word.getFrequency();
        for(int i=0; i<word.getRules().size(); i++)
        {
            Pool.addRuleToPool(word.getRules().get(i),word);
        }
    }
    public static  ArrayList<Word> getWordsInPool() {
        return wordsInPool;
    }
    public static double getCurrentFrequency() {
        return currentFrequency;
    }
    public void setCurrentFrequency(double currentFrequency) {
        this.currentFrequency = currentFrequency;
    }
}
