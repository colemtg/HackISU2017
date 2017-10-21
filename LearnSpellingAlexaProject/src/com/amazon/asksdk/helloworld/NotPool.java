package com.amazon.asksdk.helloworld;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class NotPool {
    private static ArrayList<Word> wordsInPool= new ArrayList<>();
    private static HashMap<Integer, ArrayList<Word> > rulesInPool = new HashMap<>();

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

    public void addWordToPool(Word word){
        wordsInPool.add(word);
    }
    public static ArrayList<Word> getWordsInPool() {
        return wordsInPool;
    }
    static HashMap<Integer,ArrayList<Word>> getRulesInPool() {
        return rulesInPool;
    }
    public void sortWords(){
        ArrayList<Word> returnList = new ArrayList<>();
        TreeMap<Double,Word> sortMap = new TreeMap<>();
        for(Word word : wordsInPool)
        {
            sortMap.put(word.getDifficulty(),word);
        }
        returnList.addAll(sortMap.values());
        this.wordsInPool =returnList;
    }
    public static void removeWord(Word word){
        wordsInPool.remove(word);
        for (int i=0; i<word.getRules().size(); i++){
            if (rulesInPool.containsKey((word.getRules().get(i).getHashCode()))){
                rulesInPool.get((word.getRules().get(i).getHashCode())).remove(word);
            }
        }
    }
}
