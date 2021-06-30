package C196.net;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;

public class CoursesModel {

    private int id;
    private String course;
    private String status;
    private String noteName;
    private Date startCourse;
    private Date endCourse;
    private String instructor_name;
    private String instructor_Phone;
    private String instructor_email;
    private int  termID;


    private static int   sqlValue ;

    public CoursesModel(int id, String course, Date startCourse, String instructor_name, String instructor_Phone, String instructor_email, int termID) {
        this.id = id;
        this.course = course;

        this.startCourse = startCourse;
        this.instructor_name = instructor_name;
        this.instructor_Phone = instructor_Phone;
        this.instructor_email = instructor_email;
        this.termID = termID;
    }

    public CoursesModel(int id, String course, String status, String noteName, Date startCourse, Date endCourse, String instructor_name, String instructor_Phone, String instructor_email, int termID) {
        this.id = id;
        this.course = course;
        this.status = status;
        this.noteName = noteName;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.instructor_name = instructor_name;
        this.instructor_Phone = instructor_Phone;
        this.instructor_email = instructor_email;
        this.termID = termID;
    }

    public CoursesModel(int id, String course, Date startCourse, Date endCourse, int termID) {
        this.id = id;
        this.course = course;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.termID = termID;
    }

    public CoursesModel(int id, String course, String status, int termID) {
        this.id = id;
        this.course = course;
        this.status = status;
        this.termID = termID;
    }

    public CoursesModel(int id, String course, String status, Date startCourse, Date endCourse, String instructor_name, String instructor_Phone, String instructor_email, int termID) {
        this.id = id;
        this.course = course;
        this.status = status;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.instructor_name = instructor_name;
        this.instructor_Phone = instructor_Phone;
        this.instructor_email = instructor_email;
        this.termID = termID;
    }

    public CoursesModel(int id, String course, String status, Date startCourse, Date endCourse, int termID) {
        this.id = id;
        this.course = course;
        this.status = status;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.termID = termID;
    }

    public CoursesModel(int id, String course, String instructor_name, String instructor_Phone, String instructor_email, int termID) {
        this.id = id;
        this.course = course;
        this.instructor_name = instructor_name;
        this.instructor_Phone = instructor_Phone;
        this.instructor_email = instructor_email;
        this.termID = termID;
    }

    public CoursesModel(int id, String course, int termID) {
        this.id = id;
        this.course = course;
        this.termID = termID;
    }

    public CoursesModel() {
    }



    ///Print Method
    @Override
    public String toString() {
        return
                "       "+course  ;
           /*     ", status='" + status + '\'' +
                ", instructor_name='" + instructor_name + '\'' +
                ", instructor_Phone='" + instructor_Phone + '\'' +
                ", instructor_email='" + instructor_email + '\'' +
                ", termID=" + termID +
               '}';

            */
    }


    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    public void setInstructor_Phone(String instructor_Phone) {
        this.instructor_Phone = instructor_Phone;
    }

    public void setInstructor_email(String instructor_email) {
        this.instructor_email = instructor_email;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public void setStartCourse(Date startCourse) {
        this.startCourse = startCourse;
    }

    public void setEndCourse(Date endCourse) {
        this.endCourse = endCourse;
    }

    public int getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    public String getNoteName() { return noteName; }

    public String getInstructor_name() {
        return instructor_name;
    }

    public String getInstructor_Phone() {
        return instructor_Phone;
    }

    public String getInstructor_email() {
        return instructor_email;
    }

    public Date getStartCourse() {
        return startCourse;
    }

    public Date getEndCourse() {
        return endCourse;
    }

    public int getTermID() {
        return termID;
    }

    public void setSqlValue(int sqlValue){
        //sqlValue = sqlQuery.getValue();
        //sqlValue = 3;
        this.sqlValue = sqlValue;


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public  int getSqlValue(){
        TermsActivity newTermStuff = new TermsActivity();
        newTermStuff.getTermsID2CoursesActivity();
        sqlValue = Integer.valueOf(String.valueOf(newTermStuff)) ;
        System.out.println("At least some output to know its alive " + sqlValue);
        return sqlValue;
    }

}
