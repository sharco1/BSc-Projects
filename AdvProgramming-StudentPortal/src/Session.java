public class Session {
    int number;
    String Date;
    String Hour;
    int ID;

    public Session(int id,int number,String date,String hour){
        setSessionInfo(id,number,date,hour);
    }


    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public void setDate(String date) {
        Date = date;
    }
    public String getDate() {
        return Date;
    }
    public void setHour(String hour) {
        Hour = hour;
    }
    public String getHour() {
        return Hour;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }

    public void setSessionInfo(int id,int number,String date,String hour){
        setNumber(number);
        setDate(date);
        setHour(hour);
        setID(id);
    }

    public void printSession(){
        System.out.println(getID()+"\t"+getNumber()+"\t"+getDate()+"\t"+getHour());
    }
}
