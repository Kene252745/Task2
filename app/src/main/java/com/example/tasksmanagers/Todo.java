package com.example.tasksmanagers;

import android.content.Intent;

import java.io.Serializable;

public class Todo {

        String title;
        String descrip;
        String duedate;
        String task_type;
        Boolean done;



    public Todo (String title, String desc, String duedate, String task_type) {
        super();
        this.title = title;
        this.descrip = desc;
        this.duedate = duedate;
        this.task_type = task_type;
        this.done = false;


    }
        @Override
        public String toString() {
            return this.title;
        }


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

        public void setStatus(Boolean done) {
            this.done = done;
        }

        public Boolean getStatus() {
            return this.done;
        }


}
