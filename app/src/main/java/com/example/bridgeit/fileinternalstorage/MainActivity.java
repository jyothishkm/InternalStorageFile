package com.example.bridgeit.fileinternalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText mEditTextFileName,mEditTextData;
    Button mSaveButton,mReadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextFileName = (EditText)findViewById(R.id.editText1);
        mEditTextData = (EditText)findViewById(R.id.editText2);
        mSaveButton = (Button)findViewById(R.id.button1);
        mReadButton = (Button)findViewById(R.id.button2);

        //saving file
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = mEditTextFileName.getText().toString();
                String data = mEditTextData.getText().toString();

                FileOutputStream fos;
                try{
                    fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(getApplicationContext(),fileName + " saved",
                            Toast.LENGTH_LONG).show();

                }catch (FileNotFoundException e) {
e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Performing Action on Read Button
        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String fileName = mEditTextFileName.getText().toString();
                StringBuffer sb = new StringBuffer();
                try{
                    //Attaching BufferedReader to the FileInputStream by the help of InputStreamReader
                    BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
                    String inputString;
                    //Reading data line by line and storing it into the stringbuffer
                    while ((inputString = br.readLine()) != null) {
                        sb.append(inputString + "\n");
                    }
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Displaying data on the toast
                Toast.makeText(getApplicationContext(),sb.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
