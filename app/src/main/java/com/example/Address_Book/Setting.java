package com.example.Address_Book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity implements View.OnClickListener  {

    private DatabaseHelper databaseHelper;
    private EditText nameEditText, idEditText,ageEditText,mobileEditText,mailEditText,addressEditText;
    private Button  updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        this.setTitle("Update Delete");

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        idEditText = (EditText) findViewById(R.id.idEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        mailEditText = (EditText) findViewById(R.id.mailEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        mobileEditText = (EditText) findViewById(R.id.mobileEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);




        updateButton = (Button) findViewById(R.id.updateButtonId);
        deleteButton = (Button) findViewById(R.id.deleteButtonId);



        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);




    }


    @Override
    public void onClick(View view) {

        String id = idEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String mail = mailEditText.getText().toString();
        String mobile = mobileEditText.getText().toString();
        String address = addressEditText.getText().toString();



         if(view.getId() == R.id.updateButtonId){

            Boolean isUpdated = databaseHelper.updateData(id, name,age,mail,mobile,address);

            if(isUpdated == true){
                Toast.makeText(getApplicationContext(), "data is updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "data is not updated", Toast.LENGTH_LONG).show();

            }

        }

        else if(view.getId() == R.id.deleteButtonId) {

            int value = databaseHelper.deleteData(id);

            if (value < 0) {
                Toast.makeText(getApplicationContext(), "data is not deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "data is deleted", Toast.LENGTH_LONG).show();
            }

        }


    }



}
