package prakhar_squared_mayank.moodler;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import prakhar_squared_mayank.moodler.R;
import prakhar_squared_mayank.moodler.models.Assignment;


public class AssignmentDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);
        Intent intent = getIntent();
        Assignment assign= (Assignment) intent.getSerializableExtra("assign");
        //int assignId=intent.getIntExtra("assignmentId");

        TextView courTitle=(TextView) findViewById(R.id.CourseCode);
       courTitle.setText(assign.getCourseCode().toUpperCase());
        TextView assignTitle=(TextView) findViewById(R.id.AssignTitle);
        assignTitle.setText(assign.getName());
        TextView StartDate=(TextView) findViewById(R.id.StartDate);
        StartDate.setText("Created On: " + assign.getStartDate());
        TextView EndDate=(TextView) findViewById(R.id.EndDate);
        EndDate.setText("Deadline: " + assign.getEndDate());
        TextView Description=(TextView) findViewById(R.id.CourseAssignmentDescription);
        Description.setText(assign.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assignment_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
