import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Pool {
    private ArrayList<Word> wordsInPool= new ArrayList<>();
    private static HashMap<Rule, ArrayList<Word> > rulesInPool = new HashMap<Rule, ArrayList<Word> >();
    private double currentFrequency;
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
    public void addWordToPool(Word word){
        wordsInPool.add(word);
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
