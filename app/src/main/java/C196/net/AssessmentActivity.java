package C196.net;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AssessmentActivity extends AppCompatActivity {

    //References to all controls
    Button  btn_AddAssessment,updateCourse_bnt,updateAssessment_bnt,addAssessmentBtn,updateAssessmentBtn,deleteAssessmentBtn,shareNotes,startAssessmentNotification,startCourseNotification,endCourseNotification;
    EditText AssessmentName,courseStartDate,courseEndDate,courseInstructorName,courseInstructorPhone,courseInstructorEmail,enterAssessmentName,enterAssessmentStart,enterAssessmentEnd,courseName,courseNote;
    ListView termResults,assessmentResults;
    ArrayAdapter  AssessmentArrayAdapter;
    String termsName2CoursesActivity;
    RadioGroup courseStatusSelection,assessmentType;
    TextView courseName_Identifier;
    CoursesModel coursesModel;


    ArrayAdapter termArrayAdapter;
    int  termsID2CoursesActivity;
    public static  final int NEW_ACTIVITY_REQUEST_CODE =1;
    private static  int termID,radioTypeId, radioCourseStatus,radioId;
    private static TermsActivity newTermsInstance;
    private static AssessmentsModel assessmentsModel;
    dataBaseHelper dbHelper;
    private static int courseIDV;
    private  static String courseStatus,courseInstructorNameV,courseInstructorPhoneV,courseInstructorEmailV, assessmentTypeCheck,courseStatusChecked,courseNameV,courseNoteV;
    private static Date courseStartDateV,courseEndDateV;
    private static RadioButton selectedCourseStatus;
    private static RadioButton selectedAssessmentType;
    private static int i;
    private NotificationManagerCompat notificationManagerCompat;
    Long date;
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
        switch ( (item.getItemId())){
            case R.id.item1:
                Toast.makeText(this,"Delete button was pressed, trying to DELETE Course "+ courseName +" whit ID  " + courseIDV, Toast.LENGTH_LONG).show();
                System.out.println("from Activity when delete clicked, trying to delete term with ID of " + "deleteCourse");
                dataBaseHelper dbDelete = new dataBaseHelper(this);
                dbDelete.deleteOneCourse(courseIDV);


                Intent intent = new Intent(AssessmentActivity.this, CoursesActivity.class);
                intent.putExtra("termName", termsName2CoursesActivity);
                intent.putExtra("termID", termsID2CoursesActivity);

                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        dbHelper = new dataBaseHelper(this);

        updateCourse_bnt        = findViewById(R.id.updateCourse_bnt);
        updateAssessment_bnt    = findViewById(R.id.deleteAssessmentBtn);
        courseInstructorName    = findViewById(R.id.courseInstructorName);
        courseName              = findViewById(R.id.courseName_Identifier);
        courseNote              = findViewById(R.id.courseNote);
        courseInstructorPhone   = findViewById(R.id.courseInstructorPhone);
        courseInstructorEmail   = findViewById(R.id.courseInstructorEmail);
        courseStartDate         = findViewById(R.id.courseStartDate);
        courseEndDate           = findViewById(R.id.courseEndDate);
        startCourseNotification = findViewById(R.id.startCourseNotification);
        endCourseNotification   = findViewById(R.id.endCourseNotification);

        enterAssessmentName     = findViewById(R.id.enterAssessmentName);
        enterAssessmentStart    = findViewById(R.id.enterAssessmentStart);
        enterAssessmentEnd      = findViewById(R.id.enterAssessmentEnd);
        addAssessmentBtn        = findViewById(R.id.addAssessmentBtn);
        updateAssessmentBtn     = findViewById(R.id.updateAssessmentBtn);
        deleteAssessmentBtn     = findViewById(R.id.deleteAssessmentBtn);
        assessmentResults       = findViewById(R.id.assessmentResults);
        courseStatusSelection   = findViewById(R.id.courseStatusSelection);
        assessmentType          = findViewById(R.id.assessmentType);
        shareNotes              =findViewById(R.id.shareNotes);
        startAssessmentNotification =findViewById(R.id.startAssessmentNotification);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle courseData = getIntent().getExtras();
        courseIDV               = courseData.getInt("courseID");
        courseNameV             = courseData.getString("courseName");
        courseStatus            = courseData.getString("courseStatus");
        courseNoteV             = courseData.getString("courseNote");
        courseStartDateV        = Date.valueOf(courseData.getString("courseStartDate")) ;
        courseEndDateV          = Date.valueOf(courseData.getString("courseEndDate")) ; //courseStartDate
        courseInstructorNameV   = courseData.getString("courseInstructorName");
        courseInstructorPhoneV  = courseData.getString("courseInstructorPhone");
        courseInstructorEmailV  = courseData.getString("courseInstructorEmail");


        final TextView courseName_Identifier = (TextView) findViewById(R.id.course_TexVView_Field);
        termID = courseData.getInt("termID2AssessmentActivity");

        courseName.setText              (courseNameV                , TextView.BufferType.EDITABLE);
        courseNote.setText              (courseNoteV                , TextView.BufferType.EDITABLE);
        courseStartDate.setText         (courseStartDateV.toString(), TextView.BufferType.EDITABLE);
        courseEndDate.setText           (courseEndDateV.toString()  , TextView.BufferType.EDITABLE);
        courseInstructorName.setText    (courseInstructorNameV      , TextView.BufferType.EDITABLE);
        courseInstructorPhone.setText   (courseInstructorPhoneV     , TextView.BufferType.EDITABLE);
        courseInstructorEmail.setText   (courseInstructorEmailV     , TextView.BufferType.EDITABLE);

        enterAssessmentName.setText ("Name"       , TextView.BufferType.EDITABLE);
        enterAssessmentStart.setText("2001-01-01" , TextView.BufferType.EDITABLE);
        enterAssessmentEnd.setText  ("2001-01-01" , TextView.BufferType.EDITABLE);
        //assessmentNote.setText      ("Enter Note" , TextView.BufferType.EDITABLE);


        if(courseStatus.equalsIgnoreCase("Completed") ){
            ((RadioButton)findViewById(R.id.completed)).setChecked(true);
            System.out.println("complete checked");
        }
        else if(courseStatus.equalsIgnoreCase("In Progress")){
            ((RadioButton)findViewById(R.id.InProgress)).setChecked(true);
            System.out.println("In Progress");
        }
        else if(courseStatus.equalsIgnoreCase("Dropped")){
            ((RadioButton)findViewById(R.id.dropped)).setChecked(true);
            System.out.println("Dropped");
        }
        else if(courseStatus.equalsIgnoreCase("Plan To Take")){
            ((RadioButton)findViewById(R.id.plan_To_Take)).setChecked(true);
            System.out.println("Plan To Take");
        }
        else
            System.out.println("Not catching any value..weird , print courseStatus " + courseStatus);

        System.out.println("Assessment Activity Course ID is " + courseIDV + " Course Name " + courseName+ " Course Status " + courseStatus +" " +" Course Note " + courseNote  + " Course Start "+ courseStartDateV+ " Course End " + courseEndDateV + " Course Instructor " + courseInstructorNameV + " Course inst Phone " + courseInstructorPhoneV + " Course Inst Email " + courseInstructorEmailV + " Term ID is " + termID);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                newTermsInstance = new TermsActivity();
                termsName2CoursesActivity   = newTermsInstance.getTermsName2CoursesActivity();
                termsID2CoursesActivity     = newTermsInstance.getTermsID2CoursesActivity2();
                Intent intent = new Intent(AssessmentActivity.this, CoursesActivity.class);
                intent.putExtra("termName", termsName2CoursesActivity);
                intent.putExtra("termID", termsID2CoursesActivity);

                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);

            }
        });

        dataBaseHelper dbHelper = new dataBaseHelper( AssessmentActivity.this);
        List<AssessmentsModel> everyone = dbHelper.getAllAssessments();
        showAssessments(dbHelper.getAllAssessments());
//----------------------------------------------------------------------------------
        ////////////////////////////////////////////////////////////////
        //Button Listeners
        AssessmentActivity newTest = new AssessmentActivity();

        addAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String assessmentName    = "Initial";
                    String assessmentType   = "Objective";
                    String assessmentNotes   = "TESTTTTT";
                    String courseInitialVal1 = "0001-01-01";
                    String courseInitialVal2 = "0001-01-01";
                    java.util.Date courseStartVal = java.sql.Date.valueOf(courseInitialVal1);
                    java.util.Date courseEndVal = java.sql.Date.valueOf(courseInitialVal2);

                    assessmentsModel = new AssessmentsModel(-1, enterAssessmentName.getText().toString(),selectedAssessmentType.getText().toString(), "removed" ,Date.valueOf(enterAssessmentStart.getText().toString()),Date.valueOf(enterAssessmentEnd.getText().toString()) ,courseIDV);
                    System.out.println("Add Assessment , course ID  " + courseIDV);
                    Toast.makeText(AssessmentActivity.this, assessmentsModel.toString(), Toast.LENGTH_SHORT).show();
                    dbHelper.addOneAssessment(assessmentsModel);
                    showAssessments(dbHelper.getAllAssessments());

                }
                catch (Exception e)
                {
                    Toast.makeText(AssessmentActivity.this, "Error Creating Course, please enter a term name and valid stat/end dates", Toast.LENGTH_LONG).show();
                    assessmentsModel = new AssessmentsModel();
                    System.out.println("Error with Add Button");
                }

            }
        });

//----------------------------------------------------------------------------------
        //Button Listeners
        setUpdatedCourseValue();
//----------------------------------------------------------------------------------

        assessmentResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        AssessmentsModel clickedCustomer = (AssessmentsModel) parent.getItemAtPosition(position);
                        System.out.println("Assessment Results, trying to display values " + clickedCustomer.getAssessmentName() + " plus assessments ID " + clickedCustomer.getAssessmentID() + " And course ID" + clickedCustomer.getCourseID() + " Start " + clickedCustomer.getAssessmentStart() + " End " + clickedCustomer.getAssessmentEnd() + " Type " + clickedCustomer.getAssessmentType() + " Notes " + clickedCustomer.getNoteName());

                        enterAssessmentName.setText(clickedCustomer.getAssessmentName());
                        enterAssessmentStart.setText(clickedCustomer.getAssessmentStart().toString());
                        enterAssessmentEnd.setText(clickedCustomer.getAssessmentEnd().toString());



                assessmentTypeCheck = clickedCustomer.getAssessmentType();

                        if (assessmentTypeCheck.equalsIgnoreCase("Objective")) {
                            ((RadioButton) findViewById(R.id.assessmentObjectiveType)).setChecked(true);
                            System.out.println("Objective checked " + assessmentTypeCheck);
                            assessmentTypeCheck = "Objective";
                        } else if (assessmentTypeCheck.equalsIgnoreCase("Performance")) {
                            ((RadioButton) findViewById(R.id.assessmentPerformanceType)).setChecked(true);
                            System.out.println("Performance checked " + assessmentTypeCheck);
                            assessmentTypeCheck = "Performance";
                        }

                        deleteAssessmentBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbHelper.deleteOneAssessment(clickedCustomer.getAssessmentID());
                                showAssessments(dbHelper.getAllAssessments());
                                System.out.println("Should be deleting assessment at position " + clickedCustomer.getAssessmentID());
                            }
                        });

                        updateAssessmentBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                radioTypeId = assessmentType.getCheckedRadioButtonId();
                                selectedAssessmentType = findViewById(radioTypeId);
                                assessmentTypeCheck = selectedAssessmentType.getText().toString();
                                dbHelper.updateOneAssessment(clickedCustomer.getAssessmentID(), enterAssessmentName.getText().toString(), assessmentTypeCheck, courseNote.getText().toString() , Date.valueOf(enterAssessmentStart.getText().toString()), Date.valueOf(enterAssessmentEnd.getText().toString()));
                                showAssessments(dbHelper.getAllAssessments());
                                System.out.println("Should be Updating assessment at position " + clickedCustomer.getAssessmentType() + " Lets see if this prints, if it does , we are good to go " + "selectedAssessmentType.getText().toString()" + " this is what I think the current radio selection "  + assessmentTypeCheck);

                            }
                        });




//------------------------------------------------------------------------------------------------------

                startAssessmentNotification.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//-----Build the notification
                        increment = 3;
                        Intent intent=new Intent(AssessmentActivity.this,MyReceiver.class);
                        intent.putExtra("key","The following task is due today : "+clickedCustomer.getAssessmentName());
                        intent.putExtra("channel",App.CHANNEL_3_ID);

                        PendingIntent sender= PendingIntent.getBroadcast(AssessmentActivity.this,++increment,intent,0);
                        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                        Date.valueOf(enterAssessmentEnd.getText().toString()).getTime();
                        alarmManager.set(AlarmManager.RTC_WAKEUP, Date.valueOf(enterAssessmentEnd.getText().toString()).getTime() , sender);

                        System.out.println("Am I being reached ? " + " channel being passed is " + App.CHANNEL_3_ID );
                    }

                });

            }
//------------------------------------------------------------------------------------------------------

        }); //-------------------------End of listener
//------------------------------------------------------------------------------------------------------

        startCourseNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//-----Build the notification
                increment = 1;
                Intent intent=new Intent(AssessmentActivity.this,MyReceiver.class);
                intent.putExtra("key","This course starts today: " + courseName);
                intent.putExtra("channel",App.CHANNEL_1_ID);
                PendingIntent sender= PendingIntent.getBroadcast(AssessmentActivity.this,++increment,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Date.valueOf(courseStartDate.getText().toString()).getTime();
                alarmManager.set(AlarmManager.RTC_WAKEUP, Date.valueOf(courseStartDate.getText().toString()).getTime() , sender);

                System.out.println("Am I being reached ? " + " channel being passed is " + App.CHANNEL_1_ID );
            }

        });
//------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------

        endCourseNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//-----Build the notification
                increment = 2;
                Intent intent=new Intent(AssessmentActivity.this,MyReceiver.class);
                intent.putExtra("key","This course ends today: " + courseName);
                intent.putExtra("channel",App.CHANNEL_2_ID);
                PendingIntent sender= PendingIntent.getBroadcast(AssessmentActivity.this,++increment,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Date.valueOf(courseEndDate.getText().toString()).getTime();
                alarmManager.set(AlarmManager.RTC_WAKEUP, Date.valueOf(courseEndDate.getText().toString()).getTime() , sender);

                System.out.println("Am I being reached ? " + " channel being passed is " + App.CHANNEL_2_ID );
            }

        });
//------------------------------------------------------------------------------------------------------

        shareNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, courseNote.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);


                        /*
                        assessmentTypeCheck = selectedAssessmentType.getText().toString();
                        dbHelper.updateOneAssessment(clickedCustomer.getAssessmentID(), enterAssessmentName.getText().toString(), assessmentTypeCheck, assessmentNote.getText().toString() , Date.valueOf(enterAssessmentStart.getText().toString()), Date.valueOf(enterAssessmentEnd.getText().toString()));
                        showAssessments(dbHelper.getAllAssessments());
                        System.out.println("Should be Updating assessment at position " + clickedCustomer.getAssessmentType() + " Lets see if this prints, if it does , we are good to go " + "selectedAssessmentType.getText().toString()" + " this is what I think the current radio selection "  + assessmentTypeCheck);
                        */
            }
        });

    }//----------On Create End
//------------------------------------------------------------------------------------------------------
    public void getStatus(View v){
        radioId = courseStatusSelection.getCheckedRadioButtonId();
        selectedCourseStatus = findViewById(radioId);

        System.out.println("This is the Status " + selectedCourseStatus.getText() + " button ID " + radioId);
    }
    //------------------------------------------------------------------------------------------------------
    public void getAssessmentType(View v){
        radioTypeId = assessmentType.getCheckedRadioButtonId();
        selectedAssessmentType = findViewById(radioTypeId);

        System.out.println("This is the Status " + selectedAssessmentType.getText() + " button ID " + radioTypeId);
    }

    //------------------------------------------------------------------------------------------------------
    public  void setUpdatedCourseValue(){

        radioId = courseStatusSelection.getCheckedRadioButtonId();
        selectedCourseStatus = findViewById(radioId);
        updateCourse_bnt.setOnClickListener(new View.OnClickListener() {


            @Override

            public  void onClick(View v) {
                boolean isUpdated = dbHelper.updateOneCourse(
                        courseIDV,
                        courseName.getText().toString(),
                        selectedCourseStatus.getText().toString(),
                        courseNote.getText().toString(),
                        Date.valueOf(courseStartDate.getText().toString()) ,
                        Date.valueOf(courseEndDate.getText().toString()),
                        courseInstructorName.getText().toString(),
                        courseInstructorPhone.getText().toString(),
                        courseInstructorEmail.getText().toString());

                if(isUpdated == true){
                    System.out.println("It updated YES Course ID is : " + courseIDV + " this should be the radio value " + courseStatusChecked);
                    Toast.makeText(AssessmentActivity.this, " Class  " + courseName.getText().toString() +" has been updated", Toast.LENGTH_SHORT).show();
                }
                else
                    System.out.println("Not updated SUCKS!");

            }
        });

    }
//------------------------------------------------------------------------------------------------------

    private void showAssessments(List<AssessmentsModel> everyOne) {
        dataBaseHelper dbHelper = new dataBaseHelper( AssessmentActivity.this);

        AssessmentArrayAdapter = new ArrayAdapter<AssessmentsModel>(AssessmentActivity.this, android.R.layout.simple_list_item_1, dbHelper.getAllAssessments());
        assessmentResults.setAdapter(AssessmentArrayAdapter);

        System.out.println("At Show Assessments"  + selectedAssessmentType);
    }
//------------------------------------------------------------------------------------------------------


    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }


}