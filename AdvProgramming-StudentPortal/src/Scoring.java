public class Scoring {
    String Type;
    Double Mark;
    public Scoring(String type,double mark){
        setType(type);
        setMark(mark);
    }

    public void setType(String type) {
        Type = type;
    }
    public void setMark(Double mark) {
        Mark = mark;
    }
    public String getType() {
        return Type;
    }
    public Double getMark() {
        return Mark;
    }

    public void printScoring(){
        System.out.print(" "+getType()+":"+getMark());
    }
}
