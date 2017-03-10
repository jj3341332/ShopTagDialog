package com.mrj.shoptagdialog;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mrj.shoptagdialog.tabGround.TagBean;
import com.mrj.shoptagdialog.tabGround.TagContainerLayout;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
/**
 * Author: mrj
 * github:https://github.com/jj3341332
 * blog:http://blog.csdn.net/jj3341332
 * Create Date: 2017/3/8 9:10
 */

public class MainActivity extends AppCompatActivity {


    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EventBus.getDefault().register(this);//订阅事件
        text = (TextView) findViewById(R.id.text);

        Button btn1= (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TagBean> tagBeans=new ArrayList<>();
                tagBeans.add(new TagBean("红色",222,1000));
                tagBeans.add(new TagBean("蓝色",10,0));
                tagBeans.add(new TagBean("紫色",20,555));
                tagBeans.add(new TagBean("黄色",30,0));
                tagBeans.add(new TagBean("青色",40,300));
                tagBeans.add(new TagBean("黑色",50,0));
                tagBeans.add(new TagBean("白色",60,400));

                ShopTagDiglog.Builder builder= new ShopTagDiglog.Builder(MainActivity.this);
                builder
                        .setBanViewColor(new TagContainerLayout.ViewColor())
                        .setDefaultViewColor(new TagContainerLayout.ViewColor(ContextCompat.getColor(MainActivity.this,R.color.backGroundColor),0,ContextCompat.getColor(MainActivity.this,R.color.textColor)))
                        .setClickViewColor(new TagContainerLayout.ViewColor(ContextCompat.getColor(MainActivity.this,R.color.clickBackGroundColor),0,ContextCompat.getColor(MainActivity.this,R.color.clickTextColor)))
                        .setTagBean(tagBeans)
                        .create().show();
            }
        });


        Button btn2= (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TagBean> tagBeans=new ArrayList<>();
                tagBeans.add(new TagBean("30号",222,1000));
                tagBeans.add(new TagBean("31号",10,0));
                tagBeans.add(new TagBean("32号",20,555));
                tagBeans.add(new TagBean("33号",30,0));
                tagBeans.add(new TagBean("34号",40,300));
                tagBeans.add(new TagBean("35号",50,0));
                tagBeans.add(new TagBean("36号",60,400));

                List<TagBean> tagBeans2=new ArrayList<>();
                tagBeans2.add(new TagBean("30号",40,0));
                tagBeans2.add(new TagBean("31号",50,0));
                tagBeans2.add(new TagBean("32号",77,0));
                tagBeans2.add(new TagBean("33号",99,34));
                tagBeans2.add(new TagBean("34号",1000,300));
                tagBeans2.add(new TagBean("35号",555,0));
                tagBeans2.add(new TagBean("36号",4444,111));

                List<TagBean> tagBeans3=new ArrayList<>();
                tagBeans3.add(new TagBean("30号",7,0));
                tagBeans3.add(new TagBean("31号",8,22));
                tagBeans3.add(new TagBean("32号",9,44));
                tagBeans3.add(new TagBean("33号",10,0));
                tagBeans3.add(new TagBean("34号",11,100));
                tagBeans3.add(new TagBean("35号",12,111));
                tagBeans3.add(new TagBean("36号",13,0));



                TagBean tagBean1=new TagBean();
                tagBean1.setTitle("红色");
                tagBean1.setTagBean(tagBeans);

                TagBean tagBean2=new TagBean();
                tagBean2.setTitle("紫色");
                tagBean2.setTagBean(tagBeans2);

                TagBean tagBean3=new TagBean();
                tagBean3.setTitle("蓝色");
                tagBean3.setTagBean(tagBeans3);

                TagBean tagBean4=new TagBean();
                tagBean4.setTitle("金色");
                tagBean4.setTagBean(tagBeans3);

                TagBean tagBean5=new TagBean();
                tagBean5.setTitle("绿色");
                tagBean5.setTagBean(tagBeans2);

                TagBean tagBean6=new TagBean();
                tagBean6.setTitle("黄色");
                tagBean6.setTagBean(tagBeans);



                List<TagBean> listTwo=new ArrayList<>();
                listTwo.add(tagBean1);
                listTwo.add(tagBean2);
                listTwo.add(tagBean3);
                listTwo.add(tagBean4);
                listTwo.add(tagBean5);
                listTwo.add(tagBean6);


                ShopTagDiglog.Builder builder= new ShopTagDiglog.Builder(MainActivity.this);
                builder.setBanViewColor(new TagContainerLayout.ViewColor())
                        .setDefaultViewColor(new TagContainerLayout.ViewColor(ContextCompat.getColor(MainActivity.this,R.color.backGroundColor),0,ContextCompat.getColor(MainActivity.this,R.color.textColor)))
                        .setClickViewColor(new TagContainerLayout.ViewColor(Color.parseColor("#F6A623"),Color.parseColor("#F20942"),ContextCompat.getColor(MainActivity.this,R.color.clickTextColor)))
                        .setTagBean(listTwo)
                        .create().show();
            }
        });





    }

//    @Subscribe
//    public  void onClick(String text){
//        this.text.setText(text);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
