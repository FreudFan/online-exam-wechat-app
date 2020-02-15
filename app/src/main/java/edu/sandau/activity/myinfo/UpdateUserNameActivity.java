package edu.sandau.activity.myinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import edu.sandau.activity.UserLoginActivity;
import edu.sandau.entity.UserInfo;
import edu.sandau.online_exam.R;
import edu.sandau.service.AuthService;
import edu.sandau.util.MyClientFact;
import edu.sandau.service.UserService;

public class UpdateUserNameActivity extends Activity {

    private EditText username;
    private TextView save;
    private TextView btn_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_username);

        username = (EditText)findViewById(R.id.username);
        save = (TextView) findViewById(R.id.save);
        btn_back = (TextView) findViewById(R.id.btn_back);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name = username.getText().toString();
                        UserInfo userInfo = UserLoginActivity.loginUser;
                        userInfo.setUsername(name);
                        UserService us = MyClientFact.getInstance().getUserService();
                        Map<Object,String> map = new HashMap<>();
                        map.put("username",name);
                        us.updateLogin(map);
                        Intent intent = new Intent();
                        intent.setClass(UpdateUserNameActivity.this, InformationActivity.class);
                        startActivity(intent);
                        UpdateUserNameActivity.this.finish();
                    }
                }
                ).start();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(UpdateUserNameActivity.this, InformationActivity.class);
                        startActivity(intent);
                        UpdateUserNameActivity.this.finish();
                    }
                }
                ).start();
            }
        });
    }
}
