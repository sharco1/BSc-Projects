import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class processStudent {
    ArrayList<Student> studentArrayList=new ArrayList<>();
    File file;
    Scanner ReadFile;
    Scanner input;
    processSC studentClass;

    public processStudent(){
        try {
            file = new File("Students.txt");
            ReadFile = new Scanner(file);
            int i = 0;
            int stID;
            input=new Scanner(System.in);

            studentClass=new processSC();

            while (ReadFile.hasNext()) {
                Student temp = new Student(ReadFile.next(), ReadFile.next(), stID = ReadFile.nextInt(),studentClass.getAllClassIds(stID));
                studentArrayList.add(temp);
                i++;
            }



        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void ShowStudents(){
        System.out.print("Name ### Family ### StudentId ### ClassIds(Comma delimiter)\n");
        System.out.print("-----------------------------\n");

        for(int i=0;i<studentArrayList.size();i++) {
            if (studentArrayList.get(i).getID() != -1)
                studentArrayList.get(i).printStudent();
        }
    }

    private void WriteToFile()
    {
        try {
            FileWriter fw = new FileWriter(file);
            for(int i=0;i<studentArrayList.size();i++) {
                if (studentArrayList.get(i).getID() != -1)
                    fw.write(studentArrayList.get(i).getName() + " " + studentArrayList.get(i).getFamily() + " " + studentArrayList.get(i).getID() + "\n");
            }
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void RemoveStudent()
    {
        int id,check=0;
        System.out.println("\nInput ID you want to delete it's record:");
        id=input.nextInt();
        for(int i=0;i<studentArrayList.size();i++)
        {
            if(studentArrayList.get(i).getID() == id)
            {
                studentArrayList.get(i).setID(-1);
                break;
            }
        }

        for(int i=0;i<studentArrayList.size();i++)
        {
            if(studentArrayList.get(i).getID() == -1)
                check++;
        }

        if(check==0)
        {
            System.out.println("Your ID is wrong! Try Again:");
            id=input.nextInt();
            for(int i=0;i<studentArrayList.size();i++)
            {
                if(studentArrayList.get(i).getID() == id)
                {
                    studentArrayList.get(i).setID(-1);
                    break;
                }
            }
        }
        WriteToFile();
    }

    public void AddNewStudent()
    {
        String name,family;
        int id;
        System.out.print("Input ID : ");
        id=input.nextInt();
        for(int i=0;i<studentArrayList.size();i++){
            if(id==studentArrayList.get(i).getID()){
                System.out.println("The ID already Exist! Try Again:");
                id=input.nextInt();
            }
            else
                continue;
        }
        System.out.print("Input Name : ");
        name=input.next();
        System.out.print("Input Family : ");
        family=input.next();
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(name+" "+family+" "+id+"\n");
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isExists(int studentId){
        for(int i=0;i<studentArrayList.size();i++) {
            if (studentId == studentArrayList.get(i).getID()) {
                return true;
            }
        }
        return false;
    }


    public String getNameById(int studentID){
        for(int i=0;i<studentArrayList.size();i++) {
            if (studentID == studentArrayList.get(i).getID()) {
                return studentArrayList.get(i).getName()+" "+studentArrayList.get(i).getFamily();
            }
        }

        return "NOTFOUND";
    }

}
