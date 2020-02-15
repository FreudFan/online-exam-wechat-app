package edu.sandau.activity.myinfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import edu.sandau.entity.Option;
import edu.sandau.entity.Page;
import edu.sandau.entity.WorryTopic;
import edu.sandau.online_exam.R;
import edu.sandau.service.WorryTopicService;
import edu.sandau.util.MyClientFact;

public class WorryTopicActivity extends Activity {
    private LinearLayout add;
    private Page page;
    private Handler handler;
    @SuppressLint("HandlerLeak")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worry_topic);
        add = findViewById(R.id.add);
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
                for (Object wt : rows) {
                    Map<String,Object> map = (Map<String,Object> )wt;
                    TextView description = new TextView(WorryTopicActivity.this);
                    description.setText(map.get("description").toString());
                    add.addView(description);
                    List<Map<String,Object>> optionList = (List<Map<String,Object>>)map.get("optionList");
                    for (Map<String,Object> option : optionList) {
                        TextView choose = new TextView(WorryTopicActivity.this);
                        choose.setText(option.get("name") + "." + option.get("content"));
                        add.addView(choose);
                    }
                    TextView correctKey = new TextView(WorryTopicActivity.this);
                    correctKey.setText("正确答案:" + map.get("correctkey").toString());
                    add.addView(correctKey);
                    TextView worryKey = new TextView(WorryTopicActivity.this);
                    worryKey.setText("错误答案:" + map.get("worryanswer").toString());
                    add.addView(worryKey);
                    TextView analysis = new TextView(WorryTopicActivity.this);
                    analysis.setText("解析:" + map.get("analysis").toString());
                    add.addView(analysis);
                    TextView subject = new TextView(WorryTopicActivity.this);
                    subject.setText("所属学科:" + map.get("subject_id").toString());
                    add.addView(subject);
                    TextView createTime = new TextView(WorryTopicActivity.this);
                    createTime.setText("错题时间:" + map.get("createtime").toString());
                    add.addView(createTime);
                }




            }
        };

    }
}
