package edu.sandau.activity;

import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import edu.sandau.online_exam.R;

public class MyInfoView {
    public MyInfoView(ViewPager viewPager){
        TextView userName = (TextView)viewPager.findViewById(R.id.user_name);
        TextView userSchool = (TextView)viewPager.findViewById(R.id.user_school);
        System.out.println(userName.getText().toString());
        userName.setText(UserLoginActivity.loginUser.getRealname());
        System.out.println(UserLoginActivity.loginUser.getRealname());
        userSchool.setText(UserLoginActivity.loginUser.getOrganization());
    }
}
