package C196.net;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    public static  final int NEW_ACTIVITY_REQUEST_CODE =1;
    List<CoursesModel> allCoursesList;
    ArrayAdapter  courseArrayAdapter;
    ListView coursesResults;
    Button addCourseBnt,startCourseNotification,endCourseNotification;
    EditText CourseName;
    int TermID;
    private int termSQL;
    private static dataBaseHelper dbHelper;

    private static int courseIDAssessmentActivity,courseTermID2AssessmentActivity;
    private static  String courseName2AssessmentActivity;
    private static  String courseStatus2AssessmentActivity;
    private static Date courseStartDate2AssessmentActivity;
    private static  Date courseEndDate2AssessmentActivity;
    private static  String courseInstructorName2AssessmentActivity;
    private static  String courseInstructorPhone2AssessmentActivity;
    private static  String courseInstructorEmailAssessmentActivity;
    private static  String courseNote2AssessmentActivity;
    private static  int termID;
    int increment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tracker_menu,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dataBaseHelper dbDelete = new dataBaseHelper(this);
        dbDelete.checkIfTermHasCourses(termID);
        if(dbDelete.checkIfTermHasCourses(termID) == 0){

        switch ( (item.getItemId())){
            case R.id.item1:
                Toast.makeText(this,"The this Term will be deleted " , Toast.LENGTH_LONG).show();
                dbDelete.deleteOne(termID);
                Intent intent = new Intent(CoursesActivity.this, TermsActivity.class);
                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
                return true;
                }
        }
        else
        {
            Toast.makeText(this,"Cannot delete a Term with Courses Assigned. Please drop the courses and try again " , Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        coursesResults = findViewById(R.id.assessmentResults);
        CourseName = findViewById(R.id.AssessmentName);
        addCourseBnt = findViewById(R.id.addAssessmentBnt);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBaseHelper dbHelper = new dataBaseHelper( CoursesActivity.this);
        List<CoursesModel> everyone = dbHelper.getAllCourses();
        showCourses(dbHelper.getAllCourses());


        Bundle termsData = getIntent().getExtras();
        String termName = termsData.getString("termName");
        final TextView termNameText = (TextView)  findViewById(R.id.course_TexVView_Field);
        termID = termsData.getInt("termID");

        System.out.println("Selected Term Value " + termName + " " + termID);
        termNameText.setText("Term: "+ termName);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursesActivity.this, TermsActivity.class);
                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
            }
        });
////////////////////////////////////////////////////////////////
        //Button Listeners
        TermsActivity newTest = new TermsActivity();
        newTest.getTermsID2CoursesActivity();
        addCourseBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoursesModel coursesModel;
                try {

                        String initialStatus = "Plan To Take";
                        String initialNote = "You can enter a course note here";
                        String courseInitialVal1 = "0001-01-01";
                        String courseInitialVal2 = "0001-01-01";
                        Date courseStartVal = java.sql.Date.valueOf(courseInitialVal1);
                        Date courseEndVal = java.sql.Date.valueOf(courseInitialVal2);

                        String initialInstructorName = "Staff";
                        String initialInstructorPhone = "000-00-0000";
                        String initialInstructorEmail = "staff@wgu.edu";

                    coursesModel = new CoursesModel(-1, CourseName.getText().toString(),initialStatus ,initialNote,courseStartVal,courseEndVal ,initialInstructorName,initialInstructorPhone ,initialInstructorEmail , newTest.getTermsID2CoursesActivity());
                        System.out.println("Add Button print " + "testDate");
                        Toast.makeText(CoursesActivity.this, coursesModel.toString(), Toast.LENGTH_SHORT).show();
                        CourseName.setText("");
                        CourseName.setHint("Enter Course Name");

                        dbHelper.addOneCourse(coursesModel);


                }
                catch (Exception e)
                {
                    Toast.makeText(CoursesActivity.this, "Error Creating Course, please enter a term name and valid stat/end dates", Toast.LENGTH_LONG).show();
                    coursesModel = new CoursesModel();
                    System.out.println("Error with Add Button");
                }

                dataBaseHelper dbHelper = new dataBaseHelper( CoursesActivity.this);
                showCourses(dbHelper.getAllCourses());
            }
        });

//------------------------------------------------------------------------------------------

        coursesResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CoursesModel clickedCustomer = (CoursesModel) parent.getItemAtPosition(position);
                Intent intent = new Intent(CoursesActivity.this, AssessmentActivity.class);

                courseName2AssessmentActivity = clickedCustomer.getCourse().toString();
                courseIDAssessmentActivity = clickedCustomer.getId();
                courseStatus2AssessmentActivity = clickedCustomer.getStatus();
                courseNote2AssessmentActivity = clickedCustomer.getNoteName();
                courseStartDate2AssessmentActivity = clickedCustomer.getStartCourse();
                courseEndDate2AssessmentActivity = clickedCustomer.getEndCourse();
                courseInstructorName2AssessmentActivity = clickedCustomer.getInstructor_name();
                courseInstructorPhone2AssessmentActivity = clickedCustomer.getInstructor_Phone();
                courseInstructorEmailAssessmentActivity = clickedCustomer.getInstructor_email();
                courseStartDate2AssessmentActivity = clickedCustomer.getStartCourse();
                courseEndDate2AssessmentActivity = clickedCustomer.getEndCourse();
                courseTermID2AssessmentActivity = clickedCustomer.getTermID();

                intent.putExtra("courseID", courseIDAssessmentActivity);
                intent.putExtra("courseName",courseName2AssessmentActivity);
                intent.putExtra("courseStatus", courseStatus2AssessmentActivity);
                intent.putExtra("courseNote", courseNote2AssessmentActivity);
                intent.putExtra("courseStartDate",courseStartDate2AssessmentActivity.toString());
                intent.putExtra("courseEndDate",courseEndDate2AssessmentActivity.toString());
                intent.putExtra("courseInstructorName",courseInstructorName2AssessmentActivity);
                intent.putExtra("courseInstructorPhone",courseInstructorPhone2AssessmentActivity);
                intent.putExtra("courseInstructorEmail",courseInstructorEmailAssessmentActivity);
                intent.putExtra("termID2AssessmentActivity",courseTermID2AssessmentActivity);



                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
                System.out.println("   Courses Activity Course ID is " + courseIDAssessmentActivity + " Course Name " + courseName2AssessmentActivity+ " Course Status " + courseStatus2AssessmentActivity+  "Course Note " + courseNote2AssessmentActivity + " Course Start "+ courseStartDate2AssessmentActivity+ " Course End " + courseEndDate2AssessmentActivity + " Course Instructor " + courseInstructorName2AssessmentActivity + " Course inst Phone " + courseInstructorPhone2AssessmentActivity + " Course Inst Email " + courseInstructorEmailAssessmentActivity + " Term ID is "+courseTermID2AssessmentActivity);

                AssessmentsModel temVal = new AssessmentsModel();

                System.out.println("Assessments info " +  temVal.getAssessmentName());
//-----------------------------------------------------------------------------------------


            }
        });

//------------------------------------------------------------------------------------------


    }//-End of onCreate


    //courseIDAssessmentActivity
    public  static int getCourseIDAssessmentActivity(){
        System.out.println("Should be printing out this value = " + courseIDAssessmentActivity );
        return courseIDAssessmentActivity;
    }

    //courseIDAssessmentActivity
    public   int getCourseIDAssessmentActivity2(){
        System.out.println("Should be printing out this value = " + courseIDAssessmentActivity );
        return courseIDAssessmentActivity;
    }
    public  void   setValue(){
        Bundle termsDataSQL = getIntent().getExtras();
        termSQL = termsDataSQL.getInt("termID");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showCourses(List<CoursesModel> everyOne) {
        dataBaseHelper dbHelper = new dataBaseHelper( CoursesActivity.this);

        courseArrayAdapter = new ArrayAdapter<CoursesModel>(CoursesActivity.this, android.R.layout.simple_list_item_1, dbHelper.getAllCourses());
        coursesResults.setAdapter(courseArrayAdapter);

        //int temp4now = getValue();
        System.out.println("Hope this prints " + "temp4now");
    }


    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }



}