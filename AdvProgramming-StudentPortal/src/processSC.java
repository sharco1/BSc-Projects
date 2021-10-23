import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class processSC {
    ArrayList<Student_Classes> scArrayList = new ArrayList<>();
    File file;
    Scanner ReadFile;
    Scanner input;

    public processSC() {
        try {
            file = new File("students_classes.txt");
            ReadFile = new Scanner(file);
            int i = 0;
            input = new Scanner(System.in);

            while (ReadFile.hasNext()) {
                Student_Classes temp = new Student_Classes(ReadFile.nextInt(), ReadFile.nextInt());
                scArrayList.add(temp);
                i++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ShowSC() {
        for (int i = 0; i < scArrayList.size(); i++) {
            if (scArrayList.get(i).getStudentID() != -1)
                scArrayList.get(i).printStudent_Classes();
        }
    }

    public void ShowSC_0() {
        for (int i = 0; i < scArrayList.size(); i++) {
            if (scArrayList.get(i).getStudentID() != -1)
                scArrayList.get(i).printStudent_Classes();
        }
    }

    public void ShowSC_1() {
        for (int i = 0; i < scArrayList.size(); i++) {
            if (scArrayList.get(i).getStudentID() != -1)
                scArrayList.get(i).printStudent_Classes();
        }
    }

    private void WriteToFile() {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < scArrayList.size(); i++) {
                if (scArrayList.get(i).getStudentID() != -1)
                    fw.write(scArrayList.get(i).getClassID() + " " + scArrayList.get(i).getStudentID() + "\n");
            }
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void RemoveRecordst() {
        int id,classId, check = 0;
        System.out.println("\nInput Student's ID you want to delete it's record:");
        id = input.nextInt();

        System.out.println("\nInput Class's ID you want to delete Student From:");
        classId = input.nextInt();

        for (int i = 0; i < scArrayList.size(); i++) {
            if (scArrayList.get(i).getStudentID() == id && scArrayList.get(i).getClassID() == classId) {
                scArrayList.get(i).setStudentID(-1);
                break;
            }
        }

        for (int i = 0; i < scArrayList.size(); i++) {
            if (scArrayList.get(i).getStudentID() == -1)
                check++;
        }

        if (check == 0) {
            System.out.println("Your ID is wrong! Try Again:");
            id = input.nextInt();
            for (int i = 0; i < scArrayList.size(); i++) {
                if (scArrayList.get(i).getStudentID() == id && scArrayList.get(i).getClassID() == classId) {
                    scArrayList.get(i).setStudentID(-1);
                    break;
                }
            }
        }
        WriteToFile();
    }

    public void AddNewRecordst() {
        int idst, idcl;
        boolean state;
        System.out.print("Input Student's ID : ");
        idst = input.nextInt();

        System.out.print("Input Class's ID : ");
        idcl = input.nextInt();

        for (int i = 0; i < scArrayList.size(); i++) {
            while (idst == scArrayList.get(i).getStudentID() && idcl == scArrayList.get(i).getClassID()) {
                System.out.println("The ID already Exist! Try Again:");
                System.out.print("Input Student's ID : ");
                idst = input.nextInt();

                System.out.print("Input Class's ID : ");
                idcl = input.nextInt();
            }
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(idcl + " " + idst + "\n");
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getAllClassIds(int studentId){

        String allIds = "";
        for (int i = 0; i < scArrayList.size(); i++) {
            if (studentId == scArrayList.get(i).getStudentID()) {
                allIds = allIds + scArrayList.get(i).getClassID() + "-";
            }

        }

        return allIds;

    }


    public boolean isExist(int classId,int studentId) {

        for (int i = 0; i < scArrayList.size(); i++) {
            if (studentId == scArrayList.get(i).getStudentID() && classId == scArrayList.get(i).getClassID()) {
                return true;
            }
        }

        return  false;
    }

    public int studentNumber(int classId) {
        int number = 0;
        for (int i = 0; i < scArrayList.size(); i++) {
            if (classId == scArrayList.get(i).getClassID()) {
                number++;
            }
        }

        return  number;
    }


    public int NumberOfStudentAboveAvg(int classId,int classAvg) {
        int number = 0;
        processScore allscore = new processScore();
        for (int i = 0; i < scArrayList.size(); i++) {
            if (classAvg <= allscore.StudentAvg(scArrayList.get(i).getStudentID(),classId) && scArrayList.get(i).getClassID() == classId) {
                number++;
            }
        }

        return  number;
    }


    public int ShowStudentAboveAvg(int classId,int classAvg) {
        int number = 0;
        processScore allscore = new processScore();
        processStudent stu = new processStudent();
        for (int i = 0; i < scArrayList.size(); i++) {
            int stuAvg = allscore.StudentAvg(scArrayList.get(i).getStudentID(),classId);
            if (classAvg <= stuAvg && scArrayList.get(i).getClassID() == classId) {
                System.out.print(stu.getNameById(scArrayList.get(i).getStudentID()) + "      "+stuAvg+"\n");
            }
        }

        return  number;
    }


}
