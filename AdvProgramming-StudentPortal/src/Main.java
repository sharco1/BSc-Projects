import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        printMainMenu();
    }

    public static void printMainMenu(){
        clearScreen();
        System.out.println("******Main Menu******" );
        System.out.println("1.Manage Class");
        System.out.println("2.Manage Student");
        System.out.println("3.Manage Session");
        System.out.println("4.Manage Attendance");
        System.out.println("5.Manage Score");
        System.out.println("6.Report");
        int input = getInput();
        if(input == 1){
            printClassMenu();
        }else if(input == 2){
            printStudentMenu();
        }else if(input == 3){
            printSessionMenu();
        }else if(input == 4){
            printAttendanceMenu();
        }else if(input == 5){
            printScoreMenu();
        }else if(input == 6){
            printReportMenu();
        }

        printMainMenu();
        return;

    }

    public static void printClassMenu(){
        clearScreen();
        processClasses allclass = new processClasses();
        allclass.ShowClasses();
        System.out.println("******Class Menu******" );
        System.out.println("1.Add Class");
        System.out.println("2.Remove Class");
        System.out.println("3.Print Class Information");
        System.out.println("4.Print Class stat");
        System.out.println("0.Main Menu");
        int input = getInput();
        if(input == 0){
            printMainMenu();
        }else if(input == 1) {
            allclass.AddNewClass();
        }else if(input == 2){
            allclass.RemoveClass();
        }else if(input == 3){
            allclass.PrintClassInformation();
        }else if(input == 4){
            processClassStat classStat = new processClassStat();
            classStat.ShowInfo();
        }


        printClassMenu();
        return;
    }

    public static void printStudentMenu(){
        clearScreen();
        processStudent allStdudent = new processStudent();
        allStdudent.ShowStudents();
        System.out.println("******Student Menu******" );
        System.out.println("1.Add Student");
        System.out.println("2.Remove Student");
        System.out.println("3.Assign Student To Class");
        System.out.println("4.Remove Student From Class");
        System.out.println("0.Main Menu");
        int input = getInput();
        if(input == 0){
            printMainMenu();
        }else if(input == 1) {
            allStdudent.AddNewStudent();
        }else if(input == 2){
            allStdudent.RemoveStudent();
        }else if(input == 3){
            processSC allSC = new processSC();
            allSC.AddNewRecordst();
        }else if(input == 4){
            processSC allSC = new processSC();
            allSC.RemoveRecordst();
        }


        printStudentMenu();
        return;
    }

    public static void printSessionMenu(){
        clearScreen();
        processSession allSession = new processSession();
        allSession.ShowSessions();
        System.out.println("******Session Menu******" );
        System.out.println("1.Add Session");
        System.out.println("0.Main Menu");
        int input = getInput();
        if(input == 0){
            printMainMenu();
        }else if(input == 1) {
            allSession.AddNewSession();
        }


        printSessionMenu();
        return;
    }

    public static void printAttendanceMenu(){
        clearScreen();
        processCheck allCheck = new processCheck();
        allCheck.ShowRecords();
        System.out.println("******Attendance Menu******" );
        System.out.println("1.Add Attendance");
        System.out.println("0.Main Menu");
        int input = getInput();
        if(input == 0){
            printMainMenu();
        }else if(input == 1) {
            allCheck.add();
        }


        printAttendanceMenu();
        return;
    }

    public static void printScoreMenu(){
        clearScreen();
        processScore allScore = new processScore();
        allScore.ShowScores();
        System.out.println("******Score Menu******" );
        System.out.println("1.Add Score");
        System.out.println("0.Main Menu");
        int input = getInput();
        if(input == 0){
            printMainMenu();
        }else if(input == 1) {
            allScore.addnewScore();
        }


        printScoreMenu();
        return;
    }


    public static void printReportMenu(){
        clearScreen();
        processClassStat stat = new processClassStat();
        System.out.println("******Report Menu******" );
        System.out.println("1.Student List + Student ID");
        System.out.println("2.Student List + Student Score");
        System.out.println("3.Student List + Attendance");
        System.out.println("4.Student List + Removed");
        System.out.println("5.Student List + Above Avg");
        System.out.println("0.Main Menu");
        int input = getInput();
        if(input == 0){
            printMainMenu();
        }else if(input == 1) {
            stat.studentWithId();
        }else if(input == 2) {
            stat.studentWithScore();
        }else if(input == 3) {
            stat.studentAtt();
        }else if(input == 4) {
            stat.studentRemove();
        }else if(input == 5) {
            stat.studentAvg();
        }


        printReportMenu();
        return;
    }



    public static void clearScreen() {
        char esc = 27;
        String clear = esc + "[2J";
        System.out.print(clear);

        return;
    }

    public static int getInput(){
        Scanner input;
        input=new Scanner(System.in);
        int inputDigits;
        inputDigits=input.nextInt();
        return inputDigits;
    }
}
