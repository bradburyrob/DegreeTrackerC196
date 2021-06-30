package C196.net;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TermsActivity extends AppCompatActivity  {

    //References to all controls
    Button btn_ViewAll, btn_Add;
    EditText TermName,termStartDate,termEndDate;
    ToggleButton Active;
    ListView termResults;
    ArrayAdapter termArrayAdapter;
    List<TermModel> allTermsList;
    private static int termsID2CoursesActivity;
    private static String termsName2CoursesActivity;


    public static  final int NEW_ACTIVITY_REQUEST_CODE =1;
    /////////////////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.tracker_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        btn_Add = findViewById(R.id.btn_Add);

        TermName = findViewById(R.id.AssessmentName);
        termStartDate = findViewById(R.id.termStartDate);
        termEndDate = findViewById(R.id.termEndDate);
        termResults = findViewById(R.id.assessmentResults);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataBaseHelper dbHelper = new dataBaseHelper( TermsActivity.this);
        List<TermModel> everyone = dbHelper.getAllTerms();

        showTerms(dbHelper.getAllTerms());


        //Button Listeners
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermModel termModel;
                try {
                    termModel = new TermModel(-1,TermName.getText().toString(), Date.valueOf(termStartDate.getText().toString())  , Date.valueOf(termEndDate.getText().toString()) );
                    System.out.println("Add Button print " + Date.valueOf(termStartDate.getText().toString()) + " " + Date.valueOf(termEndDate.getText().toString()));
                    Toast.makeText(TermsActivity.this, termModel.toString(), Toast.LENGTH_SHORT).show();
                    TermName.setText("");
                    TermName.setHint("Enter Term Name");
                    termStartDate.setText("");
                    termStartDate.setHint("2021-12-12");
                    termEndDate.setText("");
                    termEndDate.setHint("2021-12-12");
                    dbHelper.addOneTerm(termModel);

                }
                catch (Exception e)
                {
                    Toast.makeText(TermsActivity.this, "Error Creating Term, please enter a term name and valid stat/end dates", Toast.LENGTH_LONG).show();
                    termModel = new TermModel(-1,"Dummy Record");
                    System.out.println("Error with Add Button");
                }

                dataBaseHelper dbHelper = new dataBaseHelper( TermsActivity.this);
                showTerms(dbHelper.getAllTerms());
            }
        });
        //---------------------------------


        termResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TermModel clickedCustomer = (TermModel) parent.getItemAtPosition(position);
                Intent intent = new Intent(TermsActivity.this, CoursesActivity.class);

                termsName2CoursesActivity = clickedCustomer.getName().toString();
                termsID2CoursesActivity = clickedCustomer.getId();

                intent.putExtra("termName",termsName2CoursesActivity);
                intent.putExtra("termID",termsID2CoursesActivity);

                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
                System.out.println("Clicking on a Record " + clickedCustomer + "\n" + "Record ID " + clickedCustomer.getId()    );
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermsActivity.this, MainActivity.class);
                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
            }
        });

//----------------End of on Create Method, to fire at startup, code above
    }

    public  static int getTermsID2CoursesActivity(){
        System.out.println("Should be printing out this value = " + termsID2CoursesActivity );
        return termsID2CoursesActivity;
    }

    public   int getTermsID2CoursesActivity2(){
        System.out.println("Should be printing out this value = " + termsID2CoursesActivity );
        return termsID2CoursesActivity;
    }
    public   String getTermsName2CoursesActivity(){
        System.out.println("Should be printing out this value = " + termsName2CoursesActivity );
        return termsName2CoursesActivity;
    }
//////////////////////////////


    private void showTerms(List<TermModel> everyOne) {
        dataBaseHelper dbHelper = new dataBaseHelper( TermsActivity.this);
        termArrayAdapter = new ArrayAdapter<TermModel>(TermsActivity.this, android.R.layout.simple_list_item_1, dbHelper.getAllTerms());
        termResults.setAdapter(termArrayAdapter);
        getTermsID2CoursesActivity();
    }



}