package com.example.Address_Book;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity  implements View.OnClickListener {

    private DatabaseHelper databaseHelper;

    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.setTitle("Setting");

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        resetButton = (Button) findViewById(R.id.resetButtonId);


        resetButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.resetButtonId) {

            Boolean isUpdated = databaseHelper.deleteAllData();

            if(isUpdated == true){
                Toast.makeText(getApplicationContext(), "data is updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "data is not updated", Toast.LENGTH_LONG).show();

            }

        }
    }
}