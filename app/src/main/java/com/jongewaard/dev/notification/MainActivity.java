package com.jongewaard.dev.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

//WIN => CTRL + SHIFT + N -- Find file!
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextTitle)
    EditText editTextTitle;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;
    @BindView(R.id.switchImportance)
    Switch switchImportance;
    @BindView(R.id.buttonSend)
    Button buttondSend;

    private boolean idHighImportance = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //Right after setContentView

    }

    @OnClick(R.id.buttonSend)
    public void click(){

        sendNotification();

    }

    @OnCheckedChanged(R.id.switchImportance)
    public void change(CompoundButton buttonView, boolean isChecked){

        idHighImportance = isChecked;

        Toast.makeText(this, "Working " + idHighImportance, Toast.LENGTH_SHORT).show();

    }


    private void sendNotification(){

        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
    }
}
