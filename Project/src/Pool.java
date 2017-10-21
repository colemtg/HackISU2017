import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
}
