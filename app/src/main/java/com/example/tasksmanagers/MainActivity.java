package com.example.tasksmanagers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton madd_main_fab;

    private boolean isOpen;
    final ArrayList<Todo> list = new ArrayList<>();
    private  RecyclerView recyclerView;
    private  MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        madd_main_fab = findViewById(R.id.add_main_fab);

        recyclerView = (RecyclerView) findViewById(R.id.show_list);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);



        list.add(new Todo("Wash Cloth", "Washing my cloth", "12/10/2020", "phone"));
        list.add(new Todo("Eat", "Eat", "12/10/2020", "meeting"));
        list.add(new Todo("Eat", "Eat", "12/10/2020", "todo"));
        list.add(new Todo("Project", "Do project", "12/10/2020", "email"));



        mAdapter = new MyAdapter(list);

        recyclerView.setAdapter(mAdapter);


        isOpen = false;

        madd_main_fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, To_Do.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Todod Found", "This is here" + resultCode);

//        if(resultCode == RESULT_OK && requestCode == 2404) {

            if(data != null) {
                Log.e("Todod Found", "This is here");
                Bundle bundle = data.getExtras();
//                if (bundle != null) {
                    ArrayList<Todo> todos = (ArrayList<Todo>) bundle.getSerializable("todos");
                    for (Todo todo: todos) {
                        list.add(todo);
                        Log.e("Todo", todo.getTitle());
                    }
//                }
//                String value = data.getStringExtra("param");
//            }
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ArrayList<Todo> mDataset;
        private RecyclerViewClickListener mListener;


        MyAdapter(RecyclerViewClickListener listener) {
            mListener = listener;
        }

        public  class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
            private RecyclerViewClickListener mListener;
            @Override
            public void onClick(View view) {
                mListener.onClick(view, getAdapterPosition());
            }
                public TextView title;
                public  TextView dueDate;
                public ImageView todoIcon;
                public View itemRow;


            public MyViewHolder(View v, RecyclerViewClickListener listener) {
                super(v);
                mListener = listener;
                v.setOnClickListener(this);

                title = v.findViewById(R.id.title);
                dueDate = v.findViewById(R.id.due_date);
                todoIcon = v.findViewById(R.id.todo_icon);
                itemRow = v.findViewById(R.id.item_row);
            }
        }

        public MyAdapter(ArrayList<Todo> myDataset) {
            mDataset = myDataset;
        }


        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);

            MyViewHolder vh = new MyViewHolder(v, mListener);
            return vh;
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            String title = mDataset.get(position).getTitle();
            holder.title.setText(title);
            holder.dueDate.setText(mDataset.get(position).getDuedate());
            String taskTYpe = mDataset.get(position).getTask_type();
            Boolean isDone = mDataset.get(position).getStatus();
            holder.itemRow.setTag(mDataset.get(position));

            if(taskTYpe == "email") {
                holder.todoIcon.setImageResource(R.drawable.ic_email_black_24dp);
            } else if ( taskTYpe == "phone") {
                holder.todoIcon.setImageResource(R.drawable.ic_contact_phone_black_24dp);
            } else  if( taskTYpe == "meeting") {
                holder.todoIcon.setImageResource(R.drawable.ic_person_add_black_24dp);
            } else {
                holder.todoIcon.setImageResource(R.drawable.ic_event_black_24dp);
            }

            if(isDone) {
                holder.itemRow.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            holder.itemRow.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    Log.d("I am cicked", "onClick: I am clicked" + position);
                    Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                    Todo todo = (Todo) v.getTag();
                    String title =todo.getTitle();
                    System.out.println("==========Title ======"+todo.getTitle());
                    getIntent().getSerializableExtra(title);


                }
            });

        }


        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

            if(swipeDir == ItemTouchHelper.RIGHT) {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText(MainActivity.this, " Task Deleted", Toast.LENGTH_SHORT).show();
                list.remove(position);
                mAdapter.notifyDataSetChanged();
            } else  {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText(MainActivity.this, " Task Done", Toast.LENGTH_SHORT).show();
                Todo todo = list.get(position);
                todo.setStatus(true);
                list.set(position, todo);
                mAdapter.notifyDataSetChanged();

            }
        }
    };
}
