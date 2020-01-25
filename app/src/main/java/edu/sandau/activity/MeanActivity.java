package edu.sandau.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import edu.sandau.online_exam.R;
public class MeanActivity extends Activity implements View.OnClickListener{

    LinearLayout lintonOne;
    LinearLayout lintonTwo;
    LinearLayout lintonThree;
    LinearLayout lintonFour;
    PagerAdapter adapter;
    private TextView tvmain;
    private TextView tvcontact;
    private TextView tvmy;
    private TextView tvset;
    ViewPager mViewPager;
    private List<View> mViews=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mean);
        initViewPager();

    }

    private void initViewPager() {
        lintonOne= (LinearLayout) findViewById(R.id.lin_one);
        lintonTwo=(LinearLayout) findViewById(R.id.lin_two);
        lintonThree=(LinearLayout) findViewById(R.id.lin_three);
        lintonFour=(LinearLayout) findViewById(R.id.lin_four);
        lintonOne.setOnClickListener(this);
        lintonTwo.setOnClickListener(this);
        lintonThree.setOnClickListener(this);
        lintonFour.setOnClickListener(this);
        tvmain =(TextView)findViewById(R.id.tv_main);
        tvcontact = (TextView)findViewById(R.id.tv_contact);
        tvmy = (TextView) findViewById(R.id.tv_my);
        tvset = (TextView)findViewById(R.id.tv_set);
        LayoutInflater inflater=LayoutInflater.from(this);
        mViewPager=(ViewPager) findViewById(R.id.viewpager);
        View view1 = inflater.inflate(R.layout.fragment_index, null);
        View view2 = inflater.inflate(R.layout.fragment_index, null);
        View view3 = inflater.inflate(R.layout.fragment_my, null);
        View view4 = inflater.inflate(R.layout.fragment_index, null);
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mViews.add(view4);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                resetImg();
//                switch (position){
//                    case 0:
//                        mViewPager.setCurrentItem(0);
//                        tvmain.setTextColor(Color.RED);
//                        break;
//                    case 1:
//                        mViewPager.setCurrentItem(1);
//                        tvcontact.setTextColor(Color.RED);
//                        break;
//                    case 2:
//                        mViewPager.setCurrentItem(2);
//                        tvmy.setTextColor(Color.RED);
//                        break;
//                    case 3:
//                        mViewPager.setCurrentItem(3);
//                        tvset.setTextColor(Color.RED);
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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


    @Override
    public void onClick(View v) {
        resetImg();
        switch(v.getId()){
            case R.id.lin_one:
                tvmain.setTextColor(Color.RED);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.lin_two:
                mViewPager.setCurrentItem(1);
                tvcontact.setTextColor(Color.RED);
                break;
            case R.id.lin_three:
                mViewPager.setCurrentItem(2);
                tvmy.setTextColor(Color.RED);
                new MyInfoView(mViewPager);
                break;
            case R.id.lin_four:
                mViewPager.setCurrentItem(3);
                tvset.setTextColor(Color.RED);
                break;
        }
    }
    private void resetImg() {
        tvmain.setTextColor(Color.BLACK);
        tvcontact.setTextColor(Color.BLACK);
        tvmy.setTextColor(Color.BLACK);
        tvset.setTextColor(Color.BLACK);
    }
}
