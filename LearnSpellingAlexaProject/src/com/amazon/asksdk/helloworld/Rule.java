package com.amazon.asksdk.helloworld;

public class Rule {

    //public void addWord(Word word){
      //  words.add(word);
   // }
    //public ArrayList<Word> getWordsInRule(){
        //return words;
   // }
    final private String from;//substring in normal lingo
    final private String to;//substring in pronunciation terms
    final int hashCode;
    //private ArrayList<Word> words = new ArrayList<>();
    Rule(String from, String to){
        this.from = from;
        this.to = to;
        hashCode = (to + from).hashCode();

    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public int getHashCode(){return hashCode;}
}
