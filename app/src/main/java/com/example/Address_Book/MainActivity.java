package com.example.Address_Book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private ListView listView;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Home");

        drawerLayout = findViewById(R.id.drawerId);
        NavigationView navigationView=findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView = (ListView) findViewById(R.id.listViewId);
        databaseHelper = new DatabaseHelper(this);

        loadData();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
       if(item.getItemId()==R.id.homeMenuId)
       {
           Intent intent4=new Intent(this,MainActivity.class);
           startActivity(intent4);
       }
       else if(item.getItemId()==R.id.settingMenuId)
       {
           Intent intent2=new Intent(this,Setting.class);
           startActivity(intent2);
       }


        else if(item.getItemId()==R.id.favoriteMenuId)
        {
            Intent intent3=new Intent(this,Favourite.class);
            startActivity(intent3);
        }
       else if(item.getItemId()==R.id.aboutMenuId)
       {
           Intent intent3=new Intent(this,About.class);
           startActivity(intent3);
       }
       else if(item.getItemId()==R.id.FaqMenuId)
       {
           Intent intent3=new Intent(this,FAQ.class);
           startActivity(intent3);
       }
       else if(item.getItemId()==R.id.monetizationMenuId)
       {
           Intent intent3=new Intent(this,Monetization.class);
           startActivity(intent3);
       }
       else if(item.getItemId()==R.id.resetMenuId)
       {
           Intent intent3=new Intent(this,Search.class);
           startActivity(intent3);
       }

        return false;
    }

    public void loadData(){

        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = databaseHelper.showAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data is available in database", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()){

                listData.add(cursor.getString(1));
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textViewId,listData);
         listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //value = adapter.getItem(position).toString();

                //Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(intent2);


            }
        });




    }


}