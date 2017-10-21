import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class Pool {
    private ArrayList<Word> wordsInPool= new ArrayList<>();
    private HashSet<Rule> rulesInPool = new HashSet<Rule>();

    public ArrayList<Word> getWordsInPool() {
        return wordsInPool;
    }

    public HashSet<Rule> getRulesInPool() {
        return rulesInPool;
    }

    public double getCurrentFrequency() {
        return currentFrequency;
    }

    public void setCurrentFrequency(double currentFrequency) {
        this.currentFrequency = currentFrequency;
    }

    private double currentFrequency;

    public void addRuleToPool(Rule rule){
        rulesInPool.add(rule);
    }
    public Word generateWord(){
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
}
