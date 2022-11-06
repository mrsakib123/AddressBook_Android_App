package com.example.Address_Book;




import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class ListDataActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        this.setTitle("Details");

        listView = (ListView) findViewById(R.id.listViewId);
        databaseHelper = new DatabaseHelper(this);
        loadData();
    }

    public void loadData(){

        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = databaseHelper.showAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data is available in database", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()){
                //listData.add(cursor.getString(1));
                listData.add("Id :"+cursor.getString(0)+"\n"+"Name :"+cursor.getString(1)+"\n"+"Age :"+cursor.getString(2)+"\n"+"Mail :"+cursor.getString(3)+"\n"+"Mobile :"+cursor.getString(4)+"\n"+"Address :"+cursor.getString(5)+"\n"+"\n\n");
            }
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textViewId,listData);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String malue = adapter.getItem(position).toString();
                Toast.makeText(getApplicationContext(),malue, Toast.LENGTH_SHORT).show();




            }
        });




    }




}