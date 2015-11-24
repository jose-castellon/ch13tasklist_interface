package com.murach.tasklist;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ShowTaskList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task_list);
        TaskListDB db = new TaskListDB(this);   // load database
        StringBuilder sb = new StringBuilder();     // build string output

        TextView tasklist = (TextView)findViewById(R.id.tv_tasklist);
        DateFormat date = DateFormat.getDateTimeInstance();    // set date format


        // display all the tasks from the database, including the completed dates, if available
        // tasks flagged as hidden will not show up, but will remain in the database.
        ArrayList<Task> tasks = db.getTasks("Personal");
        for (Task t : tasks) {
            sb.append(t.getId() + "| " + t.getName() + "\n" + "Notes: " + t.getNotes() + "\n" + "Completed: " + (t.getCompletedDate().equals("0") ? "No" : date.format(new Date(t.getCompletedDateMillis()))) + "\n\n");
        }

        // display string on UI
        tasklist.setText(sb.toString());
    }

}
