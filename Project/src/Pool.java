import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Pool {
    private static ArrayList<Word> wordsInPool= new ArrayList<>();
    private static HashMap<Integer, ArrayList<Word> > rulesInPool = new HashMap<>();
    private static double currentFrequency;
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
    public static void addRuleToPool(Rule rule,Word word){
        if(rulesInPool.containsKey((rule.getTo()+rule.getFrom()).hashCode()))
        {
            rulesInPool.get((rule.getTo()+rule.getFrom()).hashCode()).add(word);
        }
        else
        {
            rulesInPool.put((rule.getTo()+rule.getFrom()).hashCode(),new ArrayList<Word>());
            rulesInPool.get((rule.getTo()+rule.getFrom()).hashCode()).add(word);
        }
    }
    public void addWordToPool(Word word){
        wordsInPool.add(word);
        currentFrequency = currentFrequency+word.getFrequency();
        for(int i=0; i<word.getRules().size(); i++)
        {
            Pool.addRuleToPool(word.getRules().get(i),word);
        }
    }
    public ArrayList<Word> getWordsInPool() {
        return wordsInPool;
    }
    public double getCurrentFrequency() {
        return currentFrequency;
    }
    public void setCurrentFrequency(double currentFrequency) {
        this.currentFrequency = currentFrequency;
    }
}
