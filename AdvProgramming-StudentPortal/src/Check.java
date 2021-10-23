import java.util.ArrayList;

public class Check {
    private int idclass;
    private int idperson;
    private int idsession;
    private boolean status;
    public Check(int idclass,int idperson,int idsession,boolean status){
        setIdclass(idclass);
        setIdperson(idperson);
        setIdsession(idsession);
        setStatus(status);
    }

    public void setIdclass(int idclass) {
        this.idclass = idclass;
    }
    public int getIdclass() {
        return idclass;
    }
    public void setIdperson(int idperson) {
        this.idperson = idperson;
    }
    public int getIdperson() {
        return idperson;
    }
    public void setIdsession(int idsession) {
        this.idsession = idsession;
    }
    public int getIdsession() {
        return idsession;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return status;
    }

    public void printCheck(){
        System.out.print(getIdclass()+" "+getIdperson()+" "+getIdsession()+" "+getStatus()+"\n");
    }
}
