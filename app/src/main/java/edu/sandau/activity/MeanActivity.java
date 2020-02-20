package edu.sandau.activity;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import edu.sandau.activity.myinfo.InformationActivity;
import edu.sandau.activity.myinfo.WorryTopicActivity;
import edu.sandau.online_exam.R;
public class MeanActivity extends Activity {

    private FrameLayout lintonOne;
    private FrameLayout lintonTwo;
    private FrameLayout lintonThree;
    private FrameLayout lintonFour;
    private PagerAdapter adapter;
    private ViewPager mViewPager;
    private List<View> mViews=new ArrayList<View>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mean);
        Intent intent = this.getIntent();
        int item = intent.getIntExtra("item",0);
        initViewPager();

        if(item == 3) {
            mViewPager.setCurrentItem(3);
        }
    }

    private void initViewPager() {
        lintonOne= (FrameLayout) findViewById(R.id.lin_one);
        lintonTwo=(FrameLayout) findViewById(R.id.lin_two);
        lintonThree=(FrameLayout) findViewById(R.id.lin_three);
        lintonFour=(FrameLayout) findViewById(R.id.lin_four);
        lintonOne.setOnClickListener(onClickEvent);
        lintonTwo.setOnClickListener(onClickEvent);
        lintonThree.setOnClickListener(onClickEvent);
        lintonFour.setOnClickListener(onClickEvent);
        LayoutInflater inflater=LayoutInflater.from(this);
        mViewPager=(ViewPager) findViewById(R.id.viewpager);
        View view1 = inflater.inflate(R.layout.fragment_index, null);
        View view2 = inflater.inflate(R.layout.fragment_index, null);
        View view3 = inflater.inflate(R.layout.fragment_index, null);
        View view4 = inflater.inflate(R.layout.fragment_my, null);
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mViews.add(view4);

        adapter=new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);

                container.addView(view);
                return view;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }
            @Override
            public int getCount() {
                return mViews.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };
        mViewPager.setAdapter(adapter);
    }

    private View.OnClickListener onClickEvent  = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.lin_one:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.lin_two:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.lin_three:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.lin_four:
                mViewPager.setCurrentItem(3);
                TextView userName = (TextView)mViewPager.findViewById(R.id.realname);
                userName.setText(UserLoginActivity.loginUser.getRealname());
                userName.setGravity(Gravity.CENTER);
                LinearLayout info = (LinearLayout) mViewPager.findViewById(R.id.info);
                LinearLayout worryTopic = (LinearLayout) mViewPager.findViewById(R.id.worryTopic);
                info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(MeanActivity.this, InformationActivity.class);
                        startActivity(intent);
//                        MeanActivity.this.finish();
                    }
                });
                worryTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(MeanActivity.this, WorryTopicActivity.class);
                        startActivity(intent);
//                        MeanActivity.this.finish();
                    }

                });
                break;
        }
    }
};



}
