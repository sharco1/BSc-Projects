public class Classes {
    private String className;
    private int classID;
    public Classes(){
        setClassInfo("Error!",0);
    }
    public Classes(String name,int id){
        setClassInfo(name,id);
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public int getClassID() {
        return classID;
    }
    public void setClassID(int classID) {
        this.classID = classID;
    }
    public String getClassName() {
        return className;
    }

    public void setClassInfo(String name,int id){
        setClassName(name);
        setClassID(id);
    }

    public void printClasses(){
        System.out.println(getClassName()+"\t"+getClassID());
    }
}
