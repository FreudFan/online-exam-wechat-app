package edu.sandau.activity.myinfo;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.sandau.activity.MeanActivity;
import edu.sandau.entity.Page;
import edu.sandau.online_exam.R;
import edu.sandau.service.ExamRecordService;
import edu.sandau.util.MyClientFact;
public class ExamRecordActivity extends Activity {
    private ImageView back_btn;
    private Page page;
    private ListView listView;
    private Handler handler;
    private SimpleAdapter simpleAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examrecord);
        initContext();
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ExamRecordActivity.this, MeanActivity.class);
                intent.putExtra("item",3);
                startActivity(intent);
                ExamRecordActivity.this.finish();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                ExamRecordService er = MyClientFact.getInstance().getExamRecordService();
                page = er.findMySchedule();
                Message msg = Message.obtain();
                msg.obj = page;
                handler.sendMessage(msg);
            }
        }).start();
        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                List<Map<String,Object>> paramsList = new ArrayList<>();
                List<?> examRecordList = page.getRows();
                int i = 1;
                for (Object examRecord : examRecordList) {
                    Map<String,Object> examRecordAttr = new HashMap<>();
                    Map<String,Object> map = (Map<String,Object> )examRecord;
                    examRecordAttr.put("description",i + "." + map.get("description"));
                    examRecordAttr.put("score","成绩:" + map.get("score"));
                    examRecordAttr.put("typeName","类型:" + map.get("typeName"));
                    Double beginTime = (Double)map.get("beginTime");
                    String beginTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(beginTime.longValue()));
                    examRecordAttr.put("beginTime","开始时间:" + beginTimeStr);
                    Double endTime = (Double)map.get("endTime");
                    String endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(endTime.longValue()));
                    examRecordAttr.put("endTime","结束时间:" + endTimeStr);
                    i++;
                    paramsList.add(examRecordAttr);
                }

                simpleAdapter = new SimpleAdapter(ExamRecordActivity.this,paramsList,R.layout.examrecord_item,
                        new String[]{
                                "description","score","typeName","beginTime","endTime"
                        },
                        new int[]{
                                R.id.description,R.id.score,R.id.typeName,R.id.beginTime,R.id.endTime
                        }
                );

                listView.setAdapter(simpleAdapter);
            }

        };

    }


    private void initContext(){

        back_btn = findViewById(R.id.back_btn);
        listView = findViewById(R.id.examRecordList);
    }
}
