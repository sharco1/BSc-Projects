public class Student_Classes {
        private int studentID;
        private int classID;
        public Student_Classes(int cId,int stId)
        {
            setStudent_ClassesInfo(cId,stId);
        }

        public void setClassID(int classID) {
            this.classID = classID;
        }
        public int getClassID() {
            return classID;
        }
        public void setStudentID(int studentID) {
            this.studentID = studentID;
        }
        public int getStudentID() {
            return studentID;
        }


        public void setStudent_ClassesInfo(int cId,int stId){
            setClassID(cId);
            setStudentID(stId);

        }

        public void printStudent_Classes(){
            System.out.println(getClassID()+"\t"+getStudentID());
    }
}
