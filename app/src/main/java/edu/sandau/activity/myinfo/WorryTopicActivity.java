package edu.sandau.activity.myinfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;



import java.util.List;
import java.util.Map;

import edu.sandau.activity.MeanActivity;
import edu.sandau.entity.Page;
import edu.sandau.online_exam.R;
import edu.sandau.service.WorryTopicService;
import edu.sandau.util.MyClientFact;

public class WorryTopicActivity extends Activity {
    private LinearLayout topic;
    private RelativeLayout back_btn;
    private Page page;
    private Handler handler;
//    private SearchView search;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worry_topic);
        topic = findViewById(R.id.topic);
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(WorryTopicActivity.this, MeanActivity.class);
                intent.putExtra("item",3);
                startActivity(intent);
                WorryTopicActivity.this.finish();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                WorryTopicService ws = MyClientFact.getInstance().getWorryTopicService();
                 page = ws.showWorryTopic();
                System.out.println(page);
                Message msg = Message.obtain();
                msg.obj = page;
                handler.sendMessage(msg);

            }
        }).start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                List<?> rows = page.getRows();
                int i = 1;
                for (Object wt : rows) {
                    Map<String,Object> map = (Map<String,Object> )wt;
                    TextView description = new TextView(WorryTopicActivity.this);
                    description.setText(i + "." + map.get("description").toString());
                    topic.addView(description);
                    List<Map<String,Object>> optionList = (List<Map<String,Object>>)map.get("optionList");
                    for (Map<String,Object> option : optionList) {
                        TextView choose = new TextView(WorryTopicActivity.this);
                        choose.setText(option.get("name") + "." + option.get("content"));
                        topic.addView(choose);
                    }
                    TextView correctKey = new TextView(WorryTopicActivity.this);
                    correctKey.setText("正确答案:" + map.get("correctkey").toString());
                    topic.addView(correctKey);
                    TextView worryKey = new TextView(WorryTopicActivity.this);
                    worryKey.setText("错误答案:" + map.get("worryanswer").toString());
                    topic.addView(worryKey);
                    TextView analysis = new TextView(WorryTopicActivity.this);
                    analysis.setText("解析:" + map.get("analysis").toString());
                    topic.addView(analysis);
                    TextView subject = new TextView(WorryTopicActivity.this);
                    subject.setText("所属学科:" + map.get("subjectName").toString());
                    topic.addView(subject);
                    TextView createTime = new TextView(WorryTopicActivity.this);
                    createTime.setText("错题时间:" + map.get("createtime").toString());
                    topic.addView(createTime);
                    i++;
                }




            }
        };

    }
}
