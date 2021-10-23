import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

public class processCheck {
    ArrayList<Check> checkArrayList=new ArrayList<>();
    File file;
    Scanner ReadFile;
    Scanner input;
    public processCheck(){
        try {
            file = new File("CheckList.txt");
            ReadFile = new Scanner(file);
            int i = 0;
            input=new Scanner(System.in);

            while (ReadFile.hasNext()) {
                int tempclass=ReadFile.nextInt();
                int tempperson=ReadFile.nextInt();
                int tempsession=ReadFile.nextInt();
                boolean status = ReadFile.nextBoolean();
                Check temp=new Check(tempclass,tempperson,tempsession,status);
                checkArrayList.add(temp);
                i++;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void ShowRecords(){
        System.out.print("ClassId ### StudentId ### SessionNumber ### Status\n");
        System.out.print("--------------------------------------------------\n");
        for(int i=0;i<checkArrayList.size();i++)
                checkArrayList.get(i).printCheck();
    }

    public void ShowByName(){
        ArrayList<Integer> doneClassID = new ArrayList<Integer>();
        ArrayList<Integer> doneStudentID = new ArrayList<Integer>();

        int classID;
        int studentID;
        processClasses allclass = new processClasses();
        processStudent stu = new processStudent();

        for(int i=0;i<checkArrayList.size();i++) {

            doneStudentID.clear();


            classID = checkArrayList.get(i).getIdclass();
            if(!doneClassID.contains(classID)){
                doneClassID.add(classID);
                System.out.print("----------------"+allclass.getNameById(classID)+"----------------\n");
                for(int j=0;j<checkArrayList.size();j++) {

                    if(checkArrayList.get(j).getIdclass() == classID){
                        studentID = checkArrayList.get(j).getIdperson();
                        if(!doneStudentID.contains(studentID)){
                            doneStudentID.add(studentID);
                            System.out.print(stu.getNameById(studentID)+" : \n");

                            for(int k=0;k<checkArrayList.size();k++) {

                                if(checkArrayList.get(k).getIdclass() == classID && checkArrayList.get(k).getIdperson() == studentID) {

                                    if (checkArrayList.get(k).getStatus()) {
                                        System.out.print("Session "+checkArrayList.get(k).getIdsession()+"    Present\n");
                                    }else{
                                        System.out.print("Session "+checkArrayList.get(k).getIdsession()+"    Absent\n");
                                    }

                                }

                            }

                        }


                    }
                }
            }
        }
    }

    private void WriteToFile()
    {
        try {
            FileWriter fw = new FileWriter(file);
            for(int i=0;i<checkArrayList.size();i++) {
                    fw.write(checkArrayList.get(i).getIdclass() + " " + checkArrayList.get(i).getIdperson() + " " + checkArrayList.get(i).getIdsession() + " "+ checkArrayList.get(i).getStatus() + "\n");
            }

            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void add(){
        input=new Scanner(System.in);
        int id;
        int sess;
        System.out.print("Input CLASS ID : ");
        id=input.nextInt();

        processClasses classes = new processClasses();
        if(!classes.isExists(id)){
            System.out.print("Wrong ID!\n");
            this.add();
            return;
        }

        System.out.print("Input SESSION NUMBER : ");
        sess=input.nextInt();

        processSession Session = new processSession();
        while(!Session.isExists(sess,id)){
            System.out.print("Wrong NUMBER!\n");
            System.out.print("Input SESSION NUMBER : ");
            sess=input.nextInt();
        }

        processStudent student = new processStudent();

        for (int i = 0; i < student.studentArrayList.size(); i++) {
            System.out.println("\n"+"Enter the State of "+student.studentArrayList.get(i).getName()+" "+student.studentArrayList.get(i).getFamily()+" :");
            boolean temp = input.nextBoolean();
            Check newcheck = new Check(id,student.studentArrayList.get(i).getID(),sess,temp);
            checkArrayList.add(newcheck);
        }
        WriteToFile();
    }



    public void ShowByRemove(){
        ArrayList<Integer> doneClassID = new ArrayList<Integer>();
        ArrayList<Integer> doneStudentID = new ArrayList<Integer>();

        int classID;
        int studentID;
        processClasses allclass = new processClasses();
        processStudent stu = new processStudent();

        for(int i=0;i<checkArrayList.size();i++) {

            doneStudentID.clear();


            classID = checkArrayList.get(i).getIdclass();
            if(!doneClassID.contains(classID)){
                doneClassID.add(classID);
                System.out.print("----------------"+allclass.getNameById(classID)+"----------------\n");
                for(int j=0;j<checkArrayList.size();j++) {

                    if(checkArrayList.get(j).getIdclass() == classID){
                        studentID = checkArrayList.get(j).getIdperson();
                        if(!doneStudentID.contains(studentID)){
                            doneStudentID.add(studentID);
                            System.out.print(stu.getNameById(studentID)+" : \n");
                            int numberOfAbsent = 0;
                            for(int k=0;k<checkArrayList.size();k++) {

                                if(checkArrayList.get(k).getIdclass() == classID && checkArrayList.get(k).getIdperson() == studentID) {

                                    if (checkArrayList.get(k).getStatus()) {

                                    }else{
                                        numberOfAbsent++;
                                    }

                                }

                            }

                            if(numberOfAbsent >= 5)
                            System.out.print("REMOVED\n");
                            else
                            System.out.print("NOT REMOVED\n");

                        }


                    }
                }
            }
        }
    }

}
