package com.example.tasksmanagers;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class To_Do extends AppCompatActivity {


    private TextView mtitle,mdescription,mduedate;
    private Spinner mtask_type;
    final List<Todo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to__do);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mtitle = findViewById(R.id.title);
        mdescription  = findViewById(R.id.description);
        mduedate = findViewById(R.id.duedate);
        mtask_type = findViewById(R.id.task_type);





    }

    public void submit(View v) {
        String title = mtitle.getText().toString();
        String descrip = mdescription.getText().toString();
        String duedate = mduedate.getText().toString();
        Spinner mySpinner = (Spinner) findViewById(R.id.task_type);
        String task_type = mySpinner.getSelectedItem().toString();
        Todo todo = new Todo();
        todo.setDescrip(descrip);
        todo.setDuedate(duedate);
        todo.setTask_type(task_type);
        todo.setTitle(title);
        list.add(todo);
    }

    public class Todo {
        String title;
        String descrip;
        String duedate;
        String task_type;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescp() {
            return descrip;
        }

        public void setDescrip(String descrip) {
            this.descrip = descrip;
        }

        public String getDuedate() {
            return duedate;
        }

        public void setDuedate(String duedate) {
            this.duedate = duedate;
        }

        public String getTask_type() {
            return task_type;
        }

        public void setTask_type(String task_type) {
            this.task_type = task_type;
        }


    }








}
