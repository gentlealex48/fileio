package com.kkaty.fileio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textmsg;
    TextView tvTextResults;
    static final int READ_BLOCK_SIZE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textmsg = findViewById(R.id.editText1);
        tvTextResults = findViewById(R.id.textView2);
    }
    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
       try {
           FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
           OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
           outputWriter.write(textmsg.getText().toString());
           outputWriter.close();

           //display file saved message
           Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT). show();



       } catch (Exception e) {
           Log.e("TAG", "WrtteBtn:", e);
       }
    }

    //Read Text from file
    public void ReadBtn(View v) {

        try{
            FileInputStream fileIn =openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            String readstring;

            while ((charRead = InputRead.read(inputBuffer)) > 0){
                readstring = String.copyValueOf(inputBuffer,0,charRead);
                s += readstring;
            }

            InputRead.close();
            tvTextResults.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    }
