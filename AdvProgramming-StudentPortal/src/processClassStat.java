import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class processClassStat {

    processClasses allclass;
    processStudent allstudent;
    processSession allsession;
    processScore allscore;
    processCheck allcheck;
    processSC allSC;
    Scanner input;

    public processClassStat(){

        allclass = new processClasses();
        allstudent = new processStudent();
        allsession = new processSession();
        allscore = new processScore();
        allcheck = new processCheck();
        allSC = new processSC();

    }

    public void ShowInfo(){
        for(int i=0;i<allclass.classesArrayList.size();i++) {
            System.out.print("+++++++++++++++++++++++++++++++No "+(i+1)+"+++++++++++++++++++++++++++++++++++\n");
            System.out.print("ClassName: "+allclass.classesArrayList.get(i).getClassName()+"\n");
            int studentNumber = allSC.studentNumber(allclass.classesArrayList.get(i).getClassID());
            System.out.print("NumberOfStudent: "+studentNumber+"    ");
            int classAvg = allscore.classAvg(allclass.classesArrayList.get(i).getClassID());
            System.out.print("classAvg: "+classAvg+"    ");

            if(studentNumber == 0){
                System.out.print("precentOfAboveAvg: 0%");
            }else{
                System.out.print("precentOfAboveAvg: "+((allSC.NumberOfStudentAboveAvg(allclass.classesArrayList.get(i).getClassID(),classAvg)/studentNumber)*100)+"%");
            }

            int numberOfSession = allsession.numberOfSession(allclass.classesArrayList.get(i).getClassID());
            System.out.print("    NoOfSession: "+numberOfSession+"");



            System.out.print("\n");
        }
    }


    public void studentWithId(){
        System.out.print("StudentName ### studentId\n");
        System.out.print("-------------------------\n");
        for(int i=0;i<allstudent.studentArrayList.size();i++) {
            System.out.print(allstudent.studentArrayList.get(i).getName()+" "+allstudent.studentArrayList.get(i).getFamily()+"     "+allstudent.studentArrayList.get(i).getID()+"\n");
        }
        System.out.print("------------end-------------\n");

    }

    public void studentWithScore(){
        allscore.ShowScores();

    }

    public void studentAtt(){
        allcheck.ShowByName();

    }

    public void studentRemove(){
        allcheck.ShowByRemove();

    }

    public void studentAvg(){
        input=new Scanner(System.in);
        int id;
        System.out.print("Input CLASS ID : \n");
        id=input.nextInt();

        while (!allclass.isExists(id)){
            System.out.print("Wrong CLASS ID!\n");
            System.out.print("Input CLASS ID : \n");
            id=input.nextInt();
        }

        int classAvg = allscore.classAvg(id);
        System.out.print("Class Avg Is : "+classAvg+"\n");

        int avg;
        System.out.print("Input Avg For Filter : \n");
        avg=input.nextInt();

        while (avg > 100){
            System.out.print("Wrong Avg!\n");
            System.out.print("Input Avg For Filter : \n");
            avg=input.nextInt();
        }

        allSC.ShowStudentAboveAvg(id,avg);

    }
}
