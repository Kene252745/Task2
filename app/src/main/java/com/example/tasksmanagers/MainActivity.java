package com.example.tasksmanagers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton madd_main_fab;

    private boolean isOpen;
    final List<Todo> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        madd_main_fab = findViewById(R.id.add_main_fab);

        final ListView listView = findViewById(R.id.listView);
        final TextAdapter adapter  = new TextAdapter();

        adapter.setData(list);
        listView.setAdapter(adapter);





        isOpen = false;

        madd_main_fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, To_Do.class));




            }
        });
    }

    class TextAdapter extends BaseAdapter {

        List<Todo> list = new ArrayList<>();

        void setData(List<Todo> mList) {
            list.clear();
            list.addAll(mList);
            notifyDataSetChanged();
        }

        @Override
        public int getCount(){
            return list.size();
        }
        @Override
        public Object getItem(int position){
            return null;
        }
        @Override
        public long getItemId(int position){
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.item, parent, false);
            TextView textView = rowView.findViewById(R.id.task);
            textView.setText((CharSequence) list.get(position));
            return rowView;


        }
    }
}
