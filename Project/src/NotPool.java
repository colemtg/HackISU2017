import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class NotPool {
    private ArrayList<Word> wordsInPool= new ArrayList<>();
    private static HashMap<Rule, ArrayList<Word> > rulesInPool = new HashMap<Rule, ArrayList<Word> >();

    public void addRuleToPool(Rule rule){
        if(rulesInPool.containsKey(rule))
        {
            for(int i=0; i<rule.getWordsInRule().size(); i++)
            rulesInPool.get(rule).add(rule.getWordsInRule().get(i));
        }
        else
        {
            rulesInPool.put(rule,rule.getWordsInRule());
        }
    }
    public ArrayList<Word> getWordsInPool() {
        return wordsInPool;
    }
    static HashMap<Rule,ArrayList<Word>> getRulesInPool() {
        return rulesInPool;
    }
}
