import java.util.ArrayList;

public class Word {
    final private String word;
    final private String pronunciation;
    final private String definitions;
    private double frequency;
    private  double difficulty;
    private ArrayList rules;

    Word(String word, String pronunciation, String definitions){
        this.word = word;
        this.pronunciation = pronunciation;
        this.definitions = definitions;
    }

}
