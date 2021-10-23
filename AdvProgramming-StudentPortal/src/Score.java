import java.util.ArrayList;

public class Score {
    int IDclass;
    int IDstudent;
    String type;
    int score;
    public Score(int idclass, int idstudent,String type,int score){
        setIDclass(idclass);
        setIDstudent(idstudent);
        setScore(score);
        setType(type);
    }

    public void setIDclass(int IDclass) {
        this.IDclass = IDclass;
    }
    public int getIDclass() {
        return IDclass;
    }
    public void setIDstudent(int IDstudent) {
        this.IDstudent = IDstudent;
    }
    public int getIDstudent() {
        return IDstudent;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() { return score; }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() { return type; }


    public void printScore(){
        System.out.print(getIDclass()+" "+getIDstudent()+" "+getType()+" "+getScore()+"\n");
    }
}
