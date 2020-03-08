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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.sandau.activity.MeanActivity;
import edu.sandau.entity.Page;
import edu.sandau.online_exam.R;
import edu.sandau.service.WorryTopicService;
import edu.sandau.util.MyClientFact;

public class WorryTopicActivity extends Activity {
    private LinearLayout back_btn;
    private Page page;
    private Handler handler;
    private ListView worryTopicscList;
    private SimpleAdapter simpleAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worry_topic);
        worryTopicscList = findViewById(R.id.worryTopicList);
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
                List<Map<String,Object>> paramsList = new ArrayList<>();
                List<?> rows = page.getRows();
                int i = 1;
                for (Object wt : rows) {
                    Map<String,Object> worryTopicAttr = new HashMap<>();
                    Map<String,Object> map = (Map<String,Object> )wt;
                    worryTopicAttr.put("description",i + "." + map.get("description").toString());
                    List<Map<String,Object>> optionList = (List<Map<String,Object>>)map.get("optionList");
                    StringBuffer sb = new StringBuffer();
                    int count = 0;
                    for (Map<String,Object> option : optionList) {
                        sb.append(option.get("name") + "." + option.get("content"));
                        count++;
                        if(count != optionList.size())
                        {
                            sb.append("\n");
                        }
                    }
                    worryTopicAttr.put("optionList",sb.toString());
                    worryTopicAttr.put("correctkey","正确答案:" + map.get("correctkey").toString());
                    worryTopicAttr.put("worryanswer","错误答案:" + map.get("worryanswer").toString());
                    worryTopicAttr.put("analysis","解析:" + map.get("analysis").toString());
                    worryTopicAttr.put("subjectName","所属学科:" + map.get("subjectName").toString());
                    worryTopicAttr.put("createtime","错题时间:" + map.get("createtime").toString());
                    paramsList.add(worryTopicAttr);
                    i++;
                }
                simpleAdapter = new SimpleAdapter(WorryTopicActivity.this,paramsList,R.layout.worrytopic_item,
                        new String[]{
                                "description","optionList","correctkey","worryanswer","analysis","subjectName","createtime"
                        },
                        new int[]{
                                R.id.description,R.id.optionList,R.id.correctkey,R.id.worryanswer,R.id.analysis,R.id.subjectName,R.id.createtime
                        }
                        );
                worryTopicscList.setAdapter(simpleAdapter);
            }
        };

    }
}
