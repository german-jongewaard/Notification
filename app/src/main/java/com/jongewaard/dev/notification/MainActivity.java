package com.jongewaard.dev.notification;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindString;
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

    @BindString(R.string.switch_notifications_on) String switchTextOn;
    @BindString(R.string.switch_notifications_off) String switchTextOff;

    private boolean isHighImportance = false;
    private NotificationHandler notificationHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); // Right after setContentView
        notificationHandler = new NotificationHandler(this);
    }



    @OnCheckedChanged(R.id.switchImportance)
    public void change(CompoundButton buttonView, boolean isChecked){

        isHighImportance = isChecked;
        switchImportance.setText((isChecked) ? switchTextOn : switchTextOff);
    }


    private void sendNotification(){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            Notification.Builder nb = notificationHandler.createNotification(title, message, isHighImportance);
           /* notificationHandler.getManager().notify(++counter, nb.build());
            notificationHandler.publishNotificationSummaryGroup(isHighImportance);*/
        }
    }
}
