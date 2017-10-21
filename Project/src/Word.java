import java.util.ArrayList;

public class Word {
    private double frequency;
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
        System.out.println("comparing " + word + " to " + pronunciation);
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
                    System.out.println(word.substring(startI,i));
                    System.out.println(pronunciation.substring(startJ,j));
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
                    System.out.println(word.substring(startI,word.length()));
                    System.out.println(pronunciation.substring(startJ,pronunciation.length()));
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
                    System.out.println(word.substring(startI,word.length()));
                    System.out.println(pronunciation.substring(startJ,pronunciation.length()));
                }
            }
        }
        return (word.length()-count)/word.length();
        //System.out.println("amount same:" + count);
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

    public ArrayList getRules() {
        return rules;
    }

    final private String word;
    final private String pronunciation;
    final private String definitions;

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    private void setDifficulty() {
        double lengthDiff = 1.5*(word.length()/10);
        double commonDiff = (2.5*Math.pow(1000,(double)commonality/1000)/1000);
        double pronDiff = 0.6*compare();
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
}
