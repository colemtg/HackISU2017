package com.amazon.asksdk.helloworld;

import java.util.ArrayList;

public class Word {
    private final double startingFrequency = 1;
    private double frequency=startingFrequency;
    private  double difficulty;
    private ArrayList<Rule> rules = new ArrayList<>();
    final private int commonality;
    Word(String word, String pronunciation, String definitions, int commonality){
        this.word = word;
        this.pronunciation = pronunciation;
        this.definitions = definitions;
        this.commonality = commonality;
        setDifficulty();
    }
    private double compare()
    {
        int i=0;
        int j=0;
        int startI=0;
        int startJ=0;
        int count=0;
        boolean found=false;
        while(i<word.length() && j<pronunciation.length())
        {
            if(pronunciation.charAt(j) == '-') // or just remove in parse
            {
                j++;
            }
            if(word.charAt(i) == pronunciation.charAt(j))
            {
                count++;
                if(found)
                {
                    addRule(new Rule(word.substring(startI,i),pronunciation.substring(startJ,j)));
                }
                i++;
                j++;
                found=false;
            }
            else if(!found)
            {
                found=true;
                startI=i;
                startJ=j;
                j++;
                if(i==word.length() || j==pronunciation.length())
                {
                    addRule(new Rule(word.substring(startI,word.length()),pronunciation.substring(startJ,pronunciation.length())));
                }
            }
            else //if(found)
            {
                if(pronunciation.charAt(j)>'z' || pronunciation.charAt(j) < 'a')
                {
                    j++;
                }
                else
                {
                    i++;
                }
                if(i==word.length() || j==pronunciation.length())
                {
                    addRule(new Rule(word.substring(startI,word.length()),pronunciation.substring(startJ,pronunciation.length())));
                }
            }
        }
        for(int k=0;k<rules.size(); k++)
        {
            NotPool.addRuleToPool(rules.get(k),this);
        }
        if(count==word.length() && word.length()==pronunciation.length())
        {
            rules.add(new Rule(" ", " "));
            NotPool.addRuleToPool(rules.get(0),this);
        }
        return (double)(word.length()-count)/(double)word.length();
    }
    public String getWord() {
        return word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getDefinitions() {
        return definitions;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    final private String word;
    final private String pronunciation;
    final private String definitions;

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    private void setDifficulty() {
        double lengthDiff = 2.5*((double)word.length()/(double)10);
        double commonDiff = (double)2*Math.pow(1000,(double)commonality/(double)1000)/(double)1000;
        double pronDiff = 5.5*compare();
        this.difficulty = lengthDiff + commonDiff + pronDiff;
    }
    public void addRule(Rule rule){
        rules.add(rule);
    }
    public ArrayList<Rule> getRulesForWord(){
        return rules;
    }
    public int getCommonality() {
        return commonality;
    }

    public double getStartingFrequency() {
        return startingFrequency;
    }
}
