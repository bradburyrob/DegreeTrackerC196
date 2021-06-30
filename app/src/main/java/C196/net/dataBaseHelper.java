package C196.net;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;


import androidx.annotation.RequiresApi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.O)
public class dataBaseHelper extends SQLiteOpenHelper {

    TermsActivity newTest = new TermsActivity();
    CoursesActivity newCourseTest = new CoursesActivity();

    public static final String TERM_TABLE = "TERM_TABLE";
    public static final String COLUMN_TERM_NAME = "TERM_NAME";
    public static final String COLUMN_TERM_START = "TERM_START";
    public static final String COLUMN_TERM_END = "TERM_END";
    public static final String COLUMN_ID = "ID";

    public static final String COURSES_TABLE = "COURSES_TABLE";
    public static final String COLUMN_COURSE_ID = "ID";
    public static final String COLUMN_COURSE_NAME = "Course";
    public static final String COLUMN_COURSE_STATUS = "Status";
    public static final String COLUMN_COURSE_NOTES = "Note";
    public static final String COLUMN_COURSE_START = "Course_Start";
    public static final String COLUMN_COURSE_END = "Course_End";
    public static final String COLUMN_COURSE_INSTRUCTOR_NAME = "Inst_Name";
    public static final String COLUMN_COURSE_INSTRUCTOR_PHONE = "Inst_Phone ";
    public static final String COLUMN_COURSE_INSTRUCTOR_EMAIL = "Inst_Email";
    public static final String COLUMN_COURSE_TERM_ID = "TERM_ID";

    public static final String ASSESSMENTS_TABLE = "ASSESSMENTS_TABLE";
    public static final String COLUMN_ASSESSMENTS_NAME = "Assessment";
    public static final String COLUMN_ASSESSMENTS_TYPE = "Type";
    public static final String COLUMN_NOTE_NAME = "Notes";
    public static final String COLUMN_ASSESSMENTS_START = "Assessment_Start";
    public static final String COLUMN_ASSESSMENTS_END = "Assessment_End";
    public static final String COLUMN_ASSESSMENTS_ID = "ID";
    public static final String COLUMN_ASSESSMENTS_COURSE_ID = "Course_ID";


    CoursesActivity termQuery ;



    public dataBaseHelper(Context context) {
        super(context, "Degree_V4.db", null, 1);
    }



    //This method is called the first time the database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {


        String createTermsTableStatement = "CREATE TABLE " + TERM_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TERM_NAME + " TEXT, " +
                COLUMN_TERM_START + " DATE, " +
                COLUMN_TERM_END + " DATE )";

        String createCoursesTableStatement =
                "CREATE TABLE " + COURSES_TABLE + " (" +
                        COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_COURSE_NAME + " TEXT NOT NULL, " +
                        COLUMN_COURSE_STATUS + " TEXT, " +
                        COLUMN_COURSE_NOTES + " TEXT, " +
                        COLUMN_COURSE_START+ " DATE, " +
                        COLUMN_COURSE_END + " DATE, " +
                        COLUMN_COURSE_INSTRUCTOR_NAME + " TEXT ," +
                        COLUMN_COURSE_INSTRUCTOR_PHONE + " TEXT," +
                        COLUMN_COURSE_INSTRUCTOR_EMAIL + " TEXT, " +
                        COLUMN_COURSE_TERM_ID + " INTEGER NOT NULL, " +
                        "FOREIGN KEY("+COLUMN_COURSE_TERM_ID+") REFERENCES "+TERM_TABLE+"("+COLUMN_ID+"))";

        String createAssessmentsTableStatement =
                "CREATE TABLE " + ASSESSMENTS_TABLE + " (" +
                        COLUMN_ASSESSMENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_ASSESSMENTS_NAME + " TEXT NOT NULL, " +
                        COLUMN_ASSESSMENTS_TYPE + " TEXT, " +
                        COLUMN_NOTE_NAME + " TEXT, " +
                        COLUMN_ASSESSMENTS_START+ " DATE, " +
                        COLUMN_ASSESSMENTS_END + " DATE, " +
                        COLUMN_ASSESSMENTS_COURSE_ID + " INTEGER NOT NULL, " +
                        "FOREIGN KEY("+COLUMN_ASSESSMENTS_COURSE_ID+") REFERENCES "+COURSES_TABLE+"("+COLUMN_COURSE_ID+"))";

        db.execSQL(createTermsTableStatement);
        db.execSQL(createCoursesTableStatement);
        db.execSQL(createAssessmentsTableStatement);

    }


    //This method is called when the database is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addOneTerm(TermModel TermModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TERM_NAME, TermModel.getName());
        cv.put(COLUMN_TERM_START, String.valueOf(TermModel.getStartTerm()));
        cv.put(COLUMN_TERM_END, String.valueOf(TermModel.getEndTerm()));

        long insert = db.insert(TERM_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return  true;
        }

    }
//-------------------------------------------------------------------------------
//This method is called when the database is changed


    public boolean addOneCourse(CoursesModel CoursesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COURSE_NAME, CoursesModel.getCourse());
        cv.put(COLUMN_COURSE_STATUS, CoursesModel.getStatus());
        cv.put(COLUMN_COURSE_NOTES, CoursesModel.getNoteName());
        cv.put(COLUMN_COURSE_START, String.valueOf(CoursesModel.getStartCourse()));
        cv.put(COLUMN_COURSE_END, String.valueOf(CoursesModel.getEndCourse()));
        cv.put(COLUMN_COURSE_INSTRUCTOR_NAME, CoursesModel.getInstructor_name());
        cv.put(COLUMN_COURSE_INSTRUCTOR_PHONE, CoursesModel.getInstructor_Phone());
        cv.put(COLUMN_COURSE_INSTRUCTOR_EMAIL, CoursesModel.getInstructor_email());
        cv.put(COLUMN_COURSE_TERM_ID,newTest.getTermsID2CoursesActivity());


        long insert = db.insert(COURSES_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return  true;
        }

    }
//-------------------------------------------------------------------------------
//This method is called when the database is changed

    public boolean addOneAssessment(AssessmentsModel AssessmentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ASSESSMENTS_NAME, AssessmentModel.getAssessmentName());
        cv.put(COLUMN_ASSESSMENTS_TYPE, AssessmentModel.getAssessmentType());
        cv.put(COLUMN_NOTE_NAME, AssessmentModel.getNoteName());
        cv.put(COLUMN_ASSESSMENTS_START, String.valueOf(AssessmentModel.getAssessmentStart()));
        cv.put(COLUMN_ASSESSMENTS_END, String.valueOf(AssessmentModel.getAssessmentEnd()));
        cv.put(COLUMN_ASSESSMENTS_COURSE_ID, newCourseTest.getCourseIDAssessmentActivity2());

        long insert = db.insert(ASSESSMENTS_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return  true;
        }

    }
    //-------------------------------------------------------------------------------
    public List<TermModel> getAllTerms(){
        List<TermModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TERM_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){

            do{
                int termID = cursor.getInt(0);
                String termName = cursor.getString(1);
                Date termStart = Date.valueOf(cursor.getString(2));
                Date termEnd = Date.valueOf(cursor.getString(3));

                TermModel newTerm = new TermModel(termID, termName, termStart,termEnd);
                returnList.add(newTerm);

            }while (cursor.moveToNext());
        }
        else{
            //failure to add to the list
        }

        return returnList;
    }

/////////////////////////////////////////
    public List<CoursesModel> getAllCourses(){
        List<CoursesModel> returnCoursesList = new ArrayList<>();

        newTest.getTermsID2CoursesActivity();

        String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE TERM_ID = " + newTest.getTermsID2CoursesActivity();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){

            do{
                int courseID                    = cursor.getInt(0);
                String courseName               = cursor.getString(1);
                String courseStatus             = cursor.getString(2);
                String courseNotes              = cursor.getString(3);
                Date courseStart                = Date.valueOf(cursor.getString(4));
                Date courseEnd                  = Date.valueOf(cursor.getString(5));
                String courseInstructorName     = cursor.getString(6);
                String courseInstructorPhone    = cursor.getString(7);
                String courseInstructorEmail    = cursor.getString(8);
                int termID = cursor.getInt(9);

                CoursesModel newCourse = new CoursesModel(courseID,  courseName, courseStatus,courseNotes, courseStart,courseEnd,courseInstructorName,courseInstructorPhone,courseInstructorEmail,termID);
                returnCoursesList.add(newCourse);

            }while (cursor.moveToNext());
        }
        else{
            //failure to add to the list
        }

        return returnCoursesList;
    }
    /////////////////////////////////////////
    public List<AssessmentsModel> getAllAssessments(){
        List<AssessmentsModel> returnAssessmentList = new ArrayList<>();

        newCourseTest.getCourseIDAssessmentActivity2();

        String queryString = "SELECT * FROM " + ASSESSMENTS_TABLE + " WHERE Course_ID = " +  newCourseTest.getCourseIDAssessmentActivity2();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){

            do{
                int assessmentID            = cursor.getInt(0);
                String assessmentName       = cursor.getString(1);
                String assessmentType       = cursor.getString(2);
                String assessmentNoteName   = cursor.getString(3);
                Date assessmentStart        = Date.valueOf(cursor.getString(4));
                Date assessmentEnd          = Date.valueOf(cursor.getString(5));

                int courseID = cursor.getInt(6);

                AssessmentsModel newAssessment = new AssessmentsModel(assessmentID,  assessmentName,assessmentType, assessmentNoteName ,assessmentStart,assessmentEnd,courseID);//assessmentNoteName, assessmentType,assessmentStart,assessmentEnd,courseID
                returnAssessmentList.add(newAssessment);

                System.out.println(" from the assessments result set  ID " + cursor.getInt(0) + "  name  " + cursor.getString(1) + " Note " +  assessmentNoteName + " Start " + assessmentStart + " end " + assessmentEnd +  " courseID " + cursor.getInt(5));

            }while (cursor.moveToNext());
        }
        else{
            //failure to add to the list
        }

        return returnAssessmentList;
    }

/////////////////////////////////////////

    public boolean deleteOne (int deleteTerm){

        String queryString = "DELETE  FROM " + TERM_TABLE + " WHERE " +  COLUMN_ID + " =  "+ deleteTerm;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        System.out.println("deleteOne method, trying to delete term with ID of " + deleteTerm);

        if(cursor.moveToFirst()){
            System.out.println("deleteOne method TRUE " + deleteTerm);
            return  true;

        }
        else {
            System.out.println("deleteOne method FALSE " + deleteTerm);
            return false;
        }

    }
/////////////////////////////////////////

    public boolean deleteOneCourse (int deleteCourse){

        String queryString = "DELETE  FROM " + COURSES_TABLE + " WHERE " +  COLUMN_ID + " =  "+ deleteCourse;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        System.out.println("deleteOne method, trying to delete term with ID of " + "deleteCourse");

        if(cursor.moveToFirst()){
            System.out.println("deleteOneCourse method TRUE " + deleteCourse);
            return  true;

        }
        else {
            System.out.println("deleteOneCourse method FALSE " + deleteCourse);
            return false;
        }

    }
//-------------------------------------------------------------------------------
/////////////////////////////////////////

    public boolean deleteOneAssessment (int deleteAssessment){

        String queryString = "DELETE  FROM " + ASSESSMENTS_TABLE + " WHERE ID " + " =  "+ deleteAssessment;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        System.out.println("deleteOne method, trying to delete assessment with ID of " + deleteAssessment);

        if(cursor.moveToFirst()){
            System.out.println("deleteOneCourse method TRUE " + deleteAssessment);
            return  true;

        }
        else {
            System.out.println("deleteOneCourse method FALSE " + deleteAssessment);
            return false;
        }

    }
//-------------------------------------------------------------------------------
    /////////////////////////////////////////

    public int checkIfTermHasCourses (int isTermEmpty){
        int courseCount;
        String queryString = "select count(*) FROM  " + COURSES_TABLE + " WHERE " +  COLUMN_COURSE_TERM_ID + " =  "+ isTermEmpty;
        //select count(*) FROM COURSES_TABLE WHERE TERM_ID = 1
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        System.out.println("deleteOne method, trying to delete term with ID of " + "deleteCourse" + " Also , this is the passed value for the method " + isTermEmpty);

        if(cursor.moveToFirst()){

                courseCount = cursor.getInt(0);
                System.out.println("deleteOneCourse method TRUE " + isTermEmpty + " Cursor AKA result set " + courseCount  );
        }
        else {
                ////go traversing through loops
                courseCount = cursor.getInt(0);
                System.out.println("deleteOneCourse method FALSE " + isTermEmpty + " Cursor AKA result set " + courseCount  );
        }
        return courseCount;
    }
    //-------------------------------------------------------------------------------
public  boolean updateOneCourse(int ID,String Course ,String Status ,String Notes ,Date Start,Date End ,String Inst_Name,String Inst_Phone ,String Inst_Email) {

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(COLUMN_COURSE_ID, ID);
    cv.put(COLUMN_COURSE_NAME, Course);
    cv.put(COLUMN_COURSE_STATUS, Status);
    cv.put(COLUMN_COURSE_NOTES, Notes);
    cv.put(COLUMN_COURSE_START, String.valueOf(Start));
    cv.put(COLUMN_COURSE_END, String.valueOf(End));
    cv.put(COLUMN_COURSE_INSTRUCTOR_NAME, Inst_Name);
    cv.put(COLUMN_COURSE_INSTRUCTOR_PHONE, Inst_Phone);
    cv.put(COLUMN_COURSE_INSTRUCTOR_EMAIL, Inst_Email);

    long update = db.update(COURSES_TABLE, cv, "ID = ?", new String[]{String.valueOf(ID)});
    System.out.println("I am at the update method in the DB class " + newTest.getTermsID2CoursesActivity());

        return true;
    }

    //-------------------------------------------------------------------------------
    public  boolean updateOneAssessment(int ID,String Name, String Type,String Notes ,Date Start,Date End) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ASSESSMENTS_ID, ID);
        cv.put(COLUMN_ASSESSMENTS_NAME, Name);
        cv.put(COLUMN_ASSESSMENTS_TYPE, Type);
        cv.put(COLUMN_NOTE_NAME, Notes);
        cv.put(COLUMN_ASSESSMENTS_START, String.valueOf(Start));
        cv.put(COLUMN_ASSESSMENTS_END, String.valueOf(End));


        long update = db.update(ASSESSMENTS_TABLE, cv, "ID = ?", new String[]{String.valueOf(ID)});
        System.out.println("I am at the update method in the DB class " + newCourseTest.getCourseIDAssessmentActivity2());

        return true;
    }

/*
    public static final String ASSESSMENTS_TABLE = "ASSESSMENTS_TABLE";
    public static final String COLUMN_ASSESSMENTS_NAME = "Assessment";
    public static final String COLUMN_NOTE_NAME = "Notes";
    public static final String COLUMN_ASSESSMENTS_START = "Assessment_Start";
    public static final String COLUMN_ASSESSMENTS_END = "Assessment_End";
    public static final String COLUMN_ASSESSMENTS_ID = "ID";
    public static final String COLUMN_ASSESSMENTS_COURSE_ID = "Course_ID";
 */
/////////////////////////////////////////


}
