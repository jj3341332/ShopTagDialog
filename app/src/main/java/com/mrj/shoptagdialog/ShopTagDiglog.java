package com.mrj.shoptagdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mrj.shoptagdialog.tabGround.OneTagLabel;
import com.mrj.shoptagdialog.tabGround.TagBean;

import com.mrj.shoptagdialog.tabGround.TagContainerLayout;
import com.mrj.shoptagdialog.tabGround.TagFactory;
import com.mrj.shoptagdialog.tabGround.TagView;
import com.mrj.shoptagdialog.tabGround.TwoTagLabel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.mrj.shoptagdialog.tabGround.TagContainerLayout.*;

/**
 * Author: mrj
 * github:https://github.com/jj3341332
 * blog:http://blog.csdn.net/jj3341332
 * Create Date: 2017/3/7 9:14
 */

public class ShopTagDiglog extends Dialog {

    private ViewColor mBanViewColor=new ViewColor();
    private ViewColor mDefaultViewColor=new ViewColor();
    private ViewColor mClickViewColor=new ViewColor();
    private List<TagBean> mTagBean=null;

    private TagFactory tagFactory;
    private TextView priceTextView;
    private TextView amountTextView;
    private TextView chooseTextView;
    private TagContainerLayout colorTagContainer;
    private TagContainerLayout sizeTagContainer;
    private int colorPosition=-1;
    private int sizePosition=-1;
    private TextView sizeLabel;


    private ShopTagDiglog(Context context) {
        super(context,R.style.ShopTabDialog);
    }

    protected ShopTagDiglog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ShopTagDiglog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

     private void init(){
         initDialog();
         if (mTagBean==null){
             throw new RuntimeException("NullPointer exception!");
         }

         if (mTagBean.get(0).getTagBean()==null) {
             initOneTag();
         }else {
             sizeLabel.setVisibility(View.VISIBLE);
             sizeTagContainer.setVisibility(View.VISIBLE);
             initTwoTag();
         }


     }

    private void initOneTag() {
        chooseTextView.setText("请选择 颜色分类");
        List<String> titles = new ArrayList<String>();
        for (TagBean tagBean :mTagBean) {
            titles.add(tagBean.getTitle());
        }
        colorTagContainer.setTitles(titles);
        tagFactory=new OneTagLabel(mTagBean,colorTagContainer.getAllChildViews(),mBanViewColor,mDefaultViewColor,mClickViewColor);
        colorTagContainer.setOnTagClickListener(new TagView.OnTagClickListener(){
            @Override
            public void onTagClick(TagView view, int position, String text) {
                TagFactory.ClickStatus clickStatus =tagFactory.onColorTagClick(position);
                if (clickStatus== TagFactory.ClickStatus.CLICK){
                    priceTextView.setText(mTagBean.get(position).getPrice()+"");
                    amountTextView.setText("库存"+mTagBean.get(position).getAmount()+"件");
                    chooseTextView.setText("已选择 "+mTagBean.get(position).getTitle());
                }else if(clickStatus== TagFactory.ClickStatus.UNCLICK) {
                    chooseTextView.setText("请选择 颜色分类");
                }
//                EventBus.getDefault().post(chooseTextView.getText());
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
    }

    private void initTwoTag() {
        chooseTextView.setText("请选择 颜色分类 尺码");
        List<String> colorTitles = new ArrayList<String>();
        List<String> sizeTitles=new ArrayList<String>();
        for (TagBean colorTagBean :mTagBean) {
            colorTitles.add(colorTagBean.getTitle());
        }
        for (TagBean sizeTagBean :mTagBean.get(0).getTagBean()) {
            sizeTitles.add(sizeTagBean.getTitle());
        }

        colorTagContainer.setTitles(colorTitles);
        sizeTagContainer.setTitles(sizeTitles);
        tagFactory=new TwoTagLabel(mTagBean, colorTagContainer.getAllChildViews(), sizeTagContainer.getAllChildViews(),mBanViewColor,mDefaultViewColor,mClickViewColor);
        colorTagContainer.setOnTagClickListener(new TagView.OnTagClickListener(){
            @Override
            public void onTagClick(TagView view, int position, String text) {
                TagFactory.ClickStatus clickStatus =tagFactory.onColorTagClick(position);
                if (clickStatus==TagFactory.ClickStatus.CLICK){
                    colorPosition=position;
                    if (sizePosition==-1){
                        chooseTextView.setText("已选择 "+mTagBean.get(position).getTitle()+" 请选择尺码");
                    }else{
                        priceTextView.setText(mTagBean.get(position).getTagBean().get(sizePosition).getPrice()+"");
                        amountTextView.setText("库存"+mTagBean.get(position).getTagBean().get(sizePosition).getAmount()+"件");
                        chooseTextView.setText("已选择 "+mTagBean.get(position).getTitle()+" "+mTagBean.get(position).getTagBean().get(sizePosition).getTitle());
                    }
                }else if(clickStatus==TagFactory.ClickStatus.UNCLICK){
                    colorPosition=-1;
                    if (sizePosition==-1){
                        chooseTextView.setText("请选择 颜色分类 尺码");
                    }else{
                        chooseTextView.setText("已选择 "+mTagBean.get(position).getTagBean().get(sizePosition).getTitle()+ " 请选择颜色分类");
                    }
                }
//                EventBus.getDefault().post(chooseTextView.getText());
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });


        sizeTagContainer.setOnTagClickListener(new TagView.OnTagClickListener(){
            @Override
            public void onTagClick(TagView view, int position, String text) {
                TagFactory.ClickStatus clickStatus =tagFactory.onSizeTagClick(position);
                if (clickStatus==TagFactory.ClickStatus.CLICK){
                    sizePosition=position;
                    if (colorPosition==-1){
                        chooseTextView.setText("已选择 "+mTagBean.get(0).getTagBean().get(position).getTitle()+" 请选择颜色分类");

                    }else{
                        priceTextView.setText(mTagBean.get(colorPosition).getTagBean().get(position).getPrice()+"");
                        amountTextView.setText("库存"+mTagBean.get(colorPosition).getTagBean().get(position).getAmount()+"件");
                        chooseTextView.setText("已选择 "+mTagBean.get(colorPosition).getTitle()+" "+mTagBean.get(colorPosition).getTagBean().get(position).getTitle());
                    }
                }else if(clickStatus==TagFactory.ClickStatus.UNCLICK){
                    sizePosition=-1;
                    if (colorPosition==-1){
                        chooseTextView.setText("请选择 颜色分类 尺码");
                    }else{
                        chooseTextView.setText("已选择 "+mTagBean.get(colorPosition).getTitle()+ " 请选择尺码");
                    }
                }
//                EventBus.getDefault().post(chooseTextView.getText());
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

    }


    private void initDialog() {
        setContentView(R.layout.shop_tab_dialog);
        //设置返回键可撤销
        setCancelable(true);
        //设置点击非Dialog区可撤销
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        priceTextView = (TextView) findViewById(R.id.price);
        amountTextView = (TextView) findViewById(R.id.amount);
        chooseTextView = (TextView) findViewById(R.id.choose);
        colorTagContainer = (TagContainerLayout) findViewById(R.id.color_tag_container);
        sizeTagContainer = (TagContainerLayout) findViewById(R.id.size_tag_container);
        sizeLabel = (TextView) findViewById(R.id.size_label);
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }


    public static class Builder{
        private ShopTagDiglog shopTagDiglog;
        public Builder(Context context){
            shopTagDiglog=new ShopTagDiglog(context);
        }
        public Builder setBanViewColor(ViewColor viewColor){
            shopTagDiglog.mBanViewColor=viewColor;
            return this;
        }
        public Builder setDefaultViewColor(ViewColor viewColor){
            shopTagDiglog.mDefaultViewColor=viewColor;
            return this;
        }
        public Builder setClickViewColor(ViewColor viewColor){
            shopTagDiglog.mClickViewColor=viewColor;
            return this;
        }
        public Builder setTagBean(List<TagBean> tagBean){
            shopTagDiglog.mTagBean=tagBean;
            return this;
        }



        public ShopTagDiglog create(){
            shopTagDiglog.init();
            return shopTagDiglog;
        }

    }
}
