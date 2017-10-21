import java.util.ArrayList;

public class Word {
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
        this.frequency = 0;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }
    public void addRule(Rule rule){
        rules.add(rule);
    }
    public ArrayList<Rule> getRulesForWord(){
        return rules;
    }
    private double frequency;
    private  double difficulty;
    private ArrayList<Rule> rules = new ArrayList<>();

    public int getCommonality() {
        return commonality;
    }

    final private int commonality;
    Word(String word, String pronunciation, String definitions, int commonality){
        this.word = word;
        this.pronunciation = pronunciation;
        this.definitions = definitions;
        this.commonality = commonality;

    }
    static void compare(String word, String pro)
    {
        System.out.println("comparing " + word + " to " + pro);
        int i=0;
        int j=0;
        int startI=0;
        int startJ=0;
        int count=0;
        boolean found=false;
        while(i<word.length() && j<pro.length())
        {
            if(pro.charAt(j) == '-') // or just remove in parse
            {
                j++;
            }
            if(word.charAt(i) == pro.charAt(j))
            {
                count++;
                if(found)
                {
                    System.out.println(word.substring(startI,i));
                    System.out.println(pro.substring(startJ,j));
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

                //if(pro.charAt(j)>'z' || pro.charAt(j) < 'a')
                //{
                j++;
                //}
                //else
                //{
                //  i++;
                //}
                if(i==word.length() || j==pro.length())
                {
                    System.out.println(word.substring(startI,word.length()));
                    System.out.println(pro.substring(startJ,pro.length()));
                }
            }
            else //if(found)
            {
                if(pro.charAt(j)>'z' || pro.charAt(j) < 'a')
                {
                    j++;
                }
                else
                {
                    i++;
                }
                if(i==word.length() || j==pro.length())
                {
                    System.out.println(word.substring(startI,word.length()));
                    System.out.println(pro.substring(startJ,pro.length()));
                }
            }
        }
        System.out.println("amount same:" + count);
    }
}
