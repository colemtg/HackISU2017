import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class NotPool {
    private ArrayList<Word> wordsInPool= new ArrayList<>();
    private static HashMap<Rule, ArrayList<Word> > rulesInPool = new HashMap<>();

    public static void addRuleToPool(Rule rule,Word word){
        if(rulesInPool.containsKey(rule))
        {
            rulesInPool.get(rule).add(word);
        }
        else
        {
            rulesInPool.put(rule,new ArrayList<Word>());
            rulesInPool.get(rule).add(word);
        }
    }
    public void addWordToPool(Word word){
        wordsInPool.add(word);
    }
    public ArrayList<Word> getWordsInPool() {
        return wordsInPool;
    }
    static HashMap<Rule,ArrayList<Word>> getRulesInPool() {
        return rulesInPool;
    }
    public void sortWords(){wordsInPool.sort(Comparator.comparing(Word::getDifficulty));}
}
