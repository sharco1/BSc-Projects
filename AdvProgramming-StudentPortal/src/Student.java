public class Student {
    private String Name;
    private String Family;
    private int ID;
    private String CIds;
    public Student(){
        setInfo("Error!", "Error!",0,"");
    }
    public Student(String name,String family,int id,String classIds){
        setInfo(name,family,id,classIds);
    }

    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }
    public void setFamily(String family) {
        Family = family;
    }
    public String getFamily() {
        return Family;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    public void setClassIds(String ClassIds) {
        CIds = ClassIds;
    }
    public String getClassIds() {
        return CIds;
    }

    public void setInfo(String name,String family,int id,String classIds){
        setName(name);
        setFamily(family);
        setID(id);
        setClassIds(classIds);
    }

    public void printStudent(){
        System.out.println(getName()+"\t"+getFamily()+"\t"+getID()+"\t"+getClassIds());
    }
}
