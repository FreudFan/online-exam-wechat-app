package edu.sandau.activity.myinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.sandau.activity.UserLoginActivity;
import edu.sandau.online_exam.R;

public class InformationActivity extends Activity {
    private TextView real_name;
    private TextView class_id;
    private TextView school;
    private TextView college;
    private TextView major;
    private TextView sex;
    private TextView user_name;
    private TextView email;
    private TextView phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        real_name = (TextView) findViewById(R.id.real_name);
        real_name.setText("姓名:" + UserLoginActivity.loginUser.getRealname());
        class_id = (TextView) findViewById(R.id.class_id);
        class_id.setText("学号:" + UserLoginActivity.loginUser.getClass_id());
        school = (TextView) findViewById(R.id.school);
        school.setText("学校:" + UserLoginActivity.loginUser.getSchool());
        college = (TextView) findViewById(R.id.college);
        college.setText("学院:" + UserLoginActivity.loginUser.getCollege());
        major = (TextView) findViewById(R.id.major);
        major.setText("专业:" + UserLoginActivity.loginUser.getMajor());
        sex = (TextView) findViewById(R.id.sex);
        sex.setText("性别:" + UserLoginActivity.loginUser.getGender());
        user_name = (TextView) findViewById(R.id.user_name);
        user_name.setText("用户名:" + UserLoginActivity.loginUser.getUsername());
        email = (TextView) findViewById(R.id.email);
        email.setText("邮箱:" + UserLoginActivity.loginUser.getEmail());
        phone = (TextView) findViewById(R.id.phone);
        phone.setText("手机号:" + UserLoginActivity.loginUser.getTelephone());

        user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(InformationActivity.this, UpdateUserNameActivity.class);
                startActivity(intent);
                InformationActivity.this.finish();
            }
        });
    }
}


