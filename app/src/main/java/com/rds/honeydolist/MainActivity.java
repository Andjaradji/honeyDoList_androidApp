package com.rds.honeydolist;

import android.content.Context;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText mInputs;
    private Button mSaveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        buttonClick();

        try {
            if (getStringFile()!= null) {
                mInputs.setText(getStringFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void setUpUI () {
        mInputs = (EditText)findViewById(R.id.inputTextID);
        mSaveButton = (Button)findViewById(R.id.saveButtonID);
    }

    private  void buttonClick () {
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mInputs.getText().toString().equals("")) {
                    saveStringFile(mInputs.getText().toString());
                } else {
                    // do nothing for now
                }
            }
        });
    }

    private void saveStringFile (String inputs) {
        try {
            OutputStreamWriter outputStreamInputs = new OutputStreamWriter(openFileOutput("hasil.txt", Context.MODE_PRIVATE));

            outputStreamInputs.write(inputs);

            outputStreamInputs.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringFile () throws IOException {
        String savedString = "";


        InputStream savedStream = openFileInput("hasil.txt");

        if (savedStream != null) {
            InputStreamReader savedStreamReader = new InputStreamReader(savedStream);
            BufferedReader bufferedReader = new BufferedReader(savedStreamReader);

            String tempText = "";

            StringBuilder stringBuilder = new StringBuilder();

            while ((tempText = bufferedReader.readLine())!= null) {
                stringBuilder.append(tempText);
            }

            savedStream.close();
            savedString = stringBuilder.toString();
        }


        return savedString;
    }
}
