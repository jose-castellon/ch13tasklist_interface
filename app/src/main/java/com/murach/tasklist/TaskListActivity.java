// CH13 Task List app with added interface
// Jose Castellon
// CIT243

/*
Task list app from ch13.
Users can add new tasks to the database file by entering name, notes and then flag it as completed and/or hidden.
If flagged as completed, the list will show the date of completion of the task.
If flagged as hidden, the task will not show up on the list.
 */

package com.murach.tasklist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class TaskListActivity extends Activity {
    Button addbtn, showbtn;
    CheckBox chk_completed, chk_hidden;
    EditText tf_name, tf_notes;
    String task_name, task_notes, task_complete, task_hidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        addbtn = (Button)findViewById(R.id.btn_add);
        showbtn = (Button)findViewById(R.id.btn_show);
        chk_completed = (CheckBox)findViewById(R.id.chk_completed);
        chk_hidden = (CheckBox)findViewById(R.id.chk_hidden);
        tf_name = (EditText)findViewById(R.id.tf_taskname);
        tf_notes = (EditText)findViewById(R.id.tf_notes);

        // add task button listener
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // obtain task data and insert it into database
                task_name = tf_name.getText().toString();
                task_notes = tf_notes.getText().toString();
                task_complete = chk_completed.isChecked() ? String.valueOf(System.currentTimeMillis()) : "0";
                task_hidden = chk_hidden.isChecked() ? "1" : "0";
                insertTask(task_name, task_notes, task_complete, task_hidden);

                // show toast indicating task was added
                Toast.makeText(TaskListActivity.this, "Task added to the database.", Toast.LENGTH_SHORT).show();

                // clear the text fields and checkboxes to enter another task
                tf_name.setText("");
                tf_notes.setText("");
                chk_completed.setChecked(false);
                chk_hidden.setChecked(false);
            }
        });

        // add show tasks button listener
        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, ShowTaskList.class);
                startActivity(intent);
            }
        });

    }
    // insert task into database method
    public void insertTask(String name, String notes, String completed, String hidden){
        // get db and StringBuilder objects
        TaskListDB db = new TaskListDB(this);

        // insert a task
        Task task = new Task(1, name, notes, completed, hidden);
        db.insertTask(task);

    }
}