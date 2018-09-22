package com.bwie.month_moin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FlowActivity extends AppCompatActivity {

    private EditText edt;
    private Button but;
    private FlowLayout afl_cotent;
    private LayoutInflater layoutInflater;
    private TextView tv_attr_tag;
    private List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        initView();
    }
    private void initView() {
        //初始化控件
        edt = findViewById(R.id.edt);
        but = findViewById(R.id.but);
        afl_cotent = findViewById(R.id.afl_cotent);
        //加载布局
        layoutInflater = LayoutInflater.from(this);
        //点击事件
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlowActivity.this,"打印",Toast.LENGTH_LONG).show();               //获取edit内容
                String s = edt.getText().toString();
                //加载子布局
                View item = layoutInflater.inflate(R.layout.sub_item, null);
                tv_attr_tag = item.findViewById(R.id.tv_attr_tag);
                list.add(s);
                for (int i = 0; i < list.size(); i++) {
                    tv_attr_tag.setText(list.get(i));
                    tv_attr_tag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(FlowActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                afl_cotent.addView(item);
            }
        });
    }



}
