import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class processSession {
    ArrayList<Session> sessions=new ArrayList<>();
    File file;
    Scanner ReadFile;
    Scanner input;

    public processSession() {
        try {
            file = new File("Sessions.txt");
            ReadFile = new Scanner(file);
            int i = 0;
            input = new Scanner(System.in);

            while (ReadFile.hasNext()) {
                Session temp = new Session(ReadFile.nextInt(), ReadFile.nextInt(), ReadFile.next(), ReadFile.next());
                sessions.add(temp);
                i++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ShowSessions(){
        System.out.print("ClassId ### SessionNumber ### Date ### Time\n");
        System.out.print("-------------------------------------------\n");
        for(int i=0;i<sessions.size();i++)
                sessions.get(i).printSession();
    }

    private void WriteToFile()
    {
        try {
            FileWriter fw = new FileWriter(file);
            for(int i=0;i<sessions.size();i++) {
                if (sessions.get(i).getID() != -1)
                    fw.write(sessions.get(i).getID() + " " + sessions.get(i).getNumber() + " " + sessions.get(i).getDate()+ " " + sessions.get(i).getHour() + "\n");
            }
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void AddNewSession()
    {
        int id,number;
        String date, hour;
        System.out.print("Input ID : ");
        id=input.nextInt();

        processClasses classes = new processClasses();
        if(!classes.isExists(id)){
            System.out.print("Wrong ID!\n");
            this.AddNewSession();
            return;
        }

        System.out.print("Input Number : ");
        number=input.nextInt();
        System.out.print("Input Date : ");
        date=input.next();
        System.out.print("Input Hour : ");
        hour=input.next();
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(id+" "+number+" "+date+" "+hour+"\n");
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    public boolean isExists(int sessionId,int classId){
        for(int i=0;i<sessions.size();i++) {
            if (sessionId == sessions.get(i).getNumber() && classId == sessions.get(i).getID()) {
                return true;
            }
        }
        return false;
    }

    public int numberOfSession(int classId){
        int count = 0;
        for(int i=0;i<sessions.size();i++) {
            if (classId == sessions.get(i).getID()) {
                count++;
            }
        }
        return count;
    }

}
