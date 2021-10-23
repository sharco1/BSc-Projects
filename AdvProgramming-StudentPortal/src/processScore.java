import java.io.File;
import java.util.*;
import java.io.FileWriter;

public class processScore {
    ArrayList<Score> scores=new ArrayList<>();
    File file;
    Scanner ReadFile;
    Scanner input;
    public processScore(){
        try {
            file = new File("Scores.txt");
            ReadFile = new Scanner(file);
            input = new Scanner(System.in);

            while (ReadFile.hasNext()) {
                int tempclass=ReadFile.nextInt();
                int tempstudent=ReadFile.nextInt();
                String type=ReadFile.next();
                int score = ReadFile.nextInt();
                Score temp=new Score(tempclass,tempstudent,type,score);
                scores.add(temp);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ShowScores(){
        ArrayList<Integer> doneClassID = new ArrayList<Integer>();
        ArrayList<Integer> doneStudentID = new ArrayList<Integer>();
        ArrayList<Integer> project_score = new ArrayList<Integer>();
        ArrayList<Integer> quiz_score = new ArrayList<Integer>();
        ArrayList<Integer> homework_score = new ArrayList<Integer>();

        int classID;
        int studentID;
        processClasses allclass = new processClasses();
        processStudent stu = new processStudent();

        for(int i=0;i<scores.size();i++) {

            doneStudentID.clear();
            project_score.clear();
            quiz_score.clear();
            homework_score.clear();

            classID = scores.get(i).getIDclass();
            if(!doneClassID.contains(classID)){
                doneClassID.add(classID);
                System.out.print("----------------"+allclass.getNameById(classID)+"----------------\n");
                for(int j=0;j<scores.size();j++) {

                    project_score.clear();
                    quiz_score.clear();
                    homework_score.clear();


                    if(scores.get(j).getIDclass() == classID){
                        studentID = scores.get(j).getIDstudent();
                        if(!doneStudentID.contains(studentID)){
                            doneStudentID.add(studentID);
                            System.out.print(stu.getNameById(studentID)+" : ");

                            for(int k=0;k<scores.size();k++) {

                                if(scores.get(k).getIDclass() == classID && scores.get(k).getIDstudent() == studentID) {

                                    if (scores.get(k).getType().equals("project")) {
                                        project_score.add(scores.get(k).getScore());
                                    } else if (scores.get(k).getType().equals("quiz")) {
                                        quiz_score.add(scores.get(k).getScore());
                                    } else if (scores.get(k).getType().equals("homework")) {
                                        homework_score.add(scores.get(k).getScore());
                                    }

                                }

                            }


                            float project_total = 0;
                            for(int k=0;k<project_score.size();k++) {
                                project_total += project_score.get(k);
                            }
                            float project_avg = project_total/project_score.size();

                            float quiz_total = 0;
                            for(int k=0;k<quiz_score.size();k++) {
                                quiz_total += quiz_score.get(k);
                            }
                            float quiz_avg = quiz_total/quiz_score.size();

                            float homework_total = 0;
                            for(int k=0;k<homework_score.size();k++) {
                                homework_total += homework_score.get(k);
                            }
                            float homework_avg = homework_total/homework_score.size();

                            System.out.print(" project avg:"+project_avg);
                            System.out.print(" quiz avg:"+quiz_avg);
                            System.out.print(" homework avg:"+homework_avg);
                            System.out.print(" total avg:"+(project_total+quiz_total+homework_total)/(project_score.size()+quiz_score.size()+homework_score.size())+"\n");

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
            for(int i=0;i<scores.size();i++) {
                fw.write(scores.get(i).getIDclass() + " " + scores.get(i).getIDstudent() + " " + scores.get(i).getType() + " " + scores.get(i).getScore() + "\n");
            }

            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void addnewScore(){
        input=new Scanner(System.in);
        System.out.println("Enter Class ID :\n");
        int classID=input.nextInt();

        processClasses allclass = new processClasses();

        while(!allclass.isExists(classID)){
            System.out.println("Wrong Class ID !\n");
            System.out.println("Enter Class ID :\n");
            classID=input.nextInt();
        }

        System.out.println("Enter Student ID :\n");
        int studentID=input.nextInt();

        processStudent allstudent = new processStudent();

        while(!allstudent.isExists(studentID)){
            System.out.println("Wrong student ID !\n");
            System.out.println("Enter student ID :\n");
            studentID=input.nextInt();
        }


        processSC check = new processSC();
        while(!check.isExist(classID,studentID)){
            System.out.println("Student Not in That Class !\n");

            System.out.println("Enter Class ID :\n");
            classID=input.nextInt();


            while(!allclass.isExists(classID)){
                System.out.println("Wrong Class ID !\n");
                System.out.println("Enter Class ID :\n");
                classID=input.nextInt();
            }

            System.out.println("Enter Student ID :\n");
            studentID=input.nextInt();

            while(!allstudent.isExists(studentID)){
                System.out.println("Wrong student ID !\n");
                System.out.println("Enter student ID :\n");
                studentID=input.nextInt();
            }


            while(!check.isExist(classID,studentID)){
                System.out.println("Student Not in That Class !\n");
                System.out.println("Enter student ID :\n");
                studentID=input.nextInt();
            }


        }


        System.out.println("Enter Type Of Score :\n");
        System.out.println("1.project :\n");
        System.out.println("2.quiz :\n");
        System.out.println("3.homework :\n");
        int scoreid=input.nextInt();

        while(scoreid != 1 && scoreid != 2 && scoreid != 3){
            System.out.println("Wrong type ID !\n");
            System.out.println("Enter Type Of Score :\n");
            System.out.println("1.project :\n");
            System.out.println("2.quiz :\n");
            System.out.println("3.homework :\n");
            scoreid=input.nextInt();
        }

        String type = "";
        if(scoreid == 1){
            type = "project";
        }else if(scoreid == 2){
            type = "quiz";
        }else if(scoreid == 3){
            type = "homework";
        }

        System.out.println("Enter Score :\n");
        int scoreval=input.nextInt();
        while(scoreval > 100){
            System.out.println("Wrong score !\n");
            System.out.println("Enter Score :\n");
            scoreval=input.nextInt();
        }

        Score temp=new Score(classID,studentID,type,scoreval);
        scores.add(temp);
        WriteToFile();
    }

    public int classAvg(int classId){
        int score=0;
        int count=0;
        for(int i=0;i<scores.size();i++) {
            if(classId == scores.get(i).getIDclass()){
                score += scores.get(i).getScore();
                count++;
            }


        }

        if(count == 0)
            return 0;

        return score/count;
    }

    public int StudentAvg(int studentId,int classId){
        int score=0;
        int count=0;
        for(int i=0;i<scores.size();i++) {
            if(classId == scores.get(i).getIDclass() && studentId == scores.get(i).getIDstudent()){
                score += scores.get(i).getScore();
                count++;
            }
        }
        if(count == 0)
            return 0;

        return score/count;

    }


}
