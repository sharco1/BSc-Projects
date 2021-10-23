import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class processClasses {
    ArrayList<Classes> classesArrayList=new ArrayList<>();
    File file;
    Scanner ReadFile;
    Scanner input;

    public processClasses(){
        try {
            file = new File("Classes.txt");
            ReadFile = new Scanner(file);
            int i = 0;
            input=new Scanner(System.in);

            while (ReadFile.hasNext()) {
                Classes temp = new Classes(ReadFile.next(), ReadFile.nextInt());
                classesArrayList.add(temp);
                i++;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void ShowClasses(){
        System.out.print("ClassName ### ClassID\n");
        System.out.print("---------------------\n");
        for(int i=0;i<classesArrayList.size();i++) {
            if (classesArrayList.get(i).getClassID() != -1)
                classesArrayList.get(i).printClasses();
        }
    }

    private void WriteToFile()
    {
        try {
            FileWriter fw = new FileWriter(file);
            for(int i=0;i<classesArrayList.size();i++) {
                if (classesArrayList.get(i).getClassID() != -1)
                    fw.write(classesArrayList.get(i).getClassName() + " " + classesArrayList.get(i).getClassID() + "\n");
            }
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void RemoveClass()
    {
        int id,check=0;
        System.out.println("\nInput ID you want to delete it's record:");
        id=input.nextInt();
        for(int i=0;i<classesArrayList.size();i++)
        {
            if(classesArrayList.get(i).getClassID() == id)
            {
                classesArrayList.get(i).setClassID(-1);
                break;
            }
        }

        for(int i=0;i<classesArrayList.size();i++)
        {
            if(classesArrayList.get(i).getClassID() == -1)
                check++;
        }

        if(check==0)
        {
            System.out.println("Your ID is wrong! Try Again:");
            id=input.nextInt();
            for(int i=0;i<classesArrayList.size();i++)
            {
                if(classesArrayList.get(i).getClassID() == id)
                {
                    classesArrayList.get(i).setClassID(-1);
                    break;
                }
            }
        }
        WriteToFile();
    }

    public void AddNewClass()
    {
        String name;
        int id;
        System.out.print("Input ID : ");
        id=input.nextInt();
        for(int i=0;i<classesArrayList.size();i++){
            if(id==classesArrayList.get(i).getClassID()){
                System.out.println("The ID already Exist! Try Again:");
                id=input.nextInt();
            }
            else
                continue;
        }
        System.out.print("Input Name : ");
        name=input.next();
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(name+" "+id+"\n");
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    public boolean isExists(int classId){
        for(int i=0;i<classesArrayList.size();i++) {
            if (classId == classesArrayList.get(i).getClassID()) {
                return true;
            }
        }
        return false;
    }

    public void PrintClassInformation(){

    }

    public String getNameById(int classID){
        for(int i=0;i<classesArrayList.size();i++) {
            if (classID == classesArrayList.get(i).getClassID()) {
                return classesArrayList.get(i).getClassName();
            }
        }

        return "NOTFOUND";
    }

}
