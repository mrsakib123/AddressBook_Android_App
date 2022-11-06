package com.example.Address_Book;

import androidx.appcompat.app.AppCompatActivity ;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Favourite extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper databaseHelper;
    private EditText nameEditText, idEditText,ageEditText,mobileEditText,mailEditText,addressEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        this.setTitle("Add Info");

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        idEditText = (EditText) findViewById(R.id.idEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        mailEditText = (EditText) findViewById(R.id.mailEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        mobileEditText = (EditText) findViewById(R.id.mobileEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);



        saveButton = (Button) findViewById(R.id.saveButtonId);
        saveButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        String id = idEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String mail = mailEditText.getText().toString();
        String mobile = mobileEditText.getText().toString();
        String address = addressEditText.getText().toString();


        if(view.getId() == R.id.saveButtonId){

            if(id.equals("") && name.equals("")){
                Toast.makeText(getApplicationContext(), "Please enter all the data", Toast.LENGTH_SHORT).show();
            }else{

                long rowNumber = databaseHelper.saveData(id, name,age,mail,mobile,address);
                if(rowNumber > -1) {
                    Toast.makeText(getApplicationContext(), "Data is inserted successfully", Toast.LENGTH_SHORT).show();
                    idEditText.setText("");
                    nameEditText.setText("");
                    mailEditText.setText("");
                    ageEditText.setText("");
                    mobileEditText.setText("");
                    addressEditText.setText("");


                } else {
                    Toast.makeText(getApplicationContext(), "Data is not inserted", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }
}