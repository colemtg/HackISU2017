public class Rule {


    final private String from;//substring in normal lingo
    final private String to;//substring in pronunciation terms
    final int hashCode;
    Rule(String from, String to){
        this.from = from;
        this.to = to;
        hashCode = (to + from).hashCode();

    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public int getHashCode(){return hashCode;}
}
