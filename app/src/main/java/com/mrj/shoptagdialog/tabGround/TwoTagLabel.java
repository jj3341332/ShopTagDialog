package com.mrj.shoptagdialog.tabGround;

import android.view.SoundEffectConstants;

import java.util.List;

import static com.mrj.shoptagdialog.tabGround.TagContainerLayout.*;


/**
 * Author: mrj
 * github:https://github.com/jj3341332
 * blog:http://blog.csdn.net/jj3341332
 * Create Date: 2017/3/8 11:16
 */
public class TwoTagLabel extends   TagFactory<TagBean> {
    private  List<TagBean> mTwoTagBean;
    private  List<TagView> mColorAllChildViews;
    private  List<TagView> mSizeAllChildViews;
    private ViewColor mBanViewColor;
    private ViewColor mDefaultViewColor;
    private ViewColor mClickViewColor;
    private  TwoTagLabel(){}

    public TwoTagLabel(List<TagBean> twoTagBean,List<TagView> colorAllChildViews, List<TagView> sizeAllChildViews, ViewColor banViewColor, ViewColor defaultViewColor, ViewColor clickViewColor) {
        this.mTwoTagBean=twoTagBean;
        this.mColorAllChildViews=colorAllChildViews;
        this.mSizeAllChildViews=sizeAllChildViews;
        this.mBanViewColor=banViewColor;
        this.mDefaultViewColor=defaultViewColor;
        this.mClickViewColor=clickViewColor;
        initTags();

    }

    private void initTags() {
      for (int i=0;i<mColorAllChildViews.size();i++){
          mColorAllChildViews.get(i).setTagViewColor(mDefaultViewColor);
      }
       for (int i=0;i<mSizeAllChildViews.size();i++){
           mSizeAllChildViews.get(i).setTagViewColor(mDefaultViewColor);
       }
    }

    @Override
  public   ClickStatus onColorTagClick(int  position) {
        TagView colorTabview= mColorAllChildViews.get(position);
        if (colorTabview.getEnabled()==true){
            colorTabview.playSoundEffect(SoundEffectConstants.CLICK);
            if (!colorTabview.getIsClick()){
                for (TagView tagView : mColorAllChildViews) {
                    if (tagView.getEnabled()==true) {
                        tagView.setTagViewColor(mDefaultViewColor);
                        tagView.setIsClick(false);

                    }
                }
                setSizeTagColor(position);
                colorTabview.setTagViewColor(mClickViewColor);
                colorTabview.setIsClick(true);
                return ClickStatus.CLICK;
            }else{
                colorTabview.setTagViewColor(mDefaultViewColor);
                colorTabview.setIsClick(false);

                setSizeTagColor(-1);
                return ClickStatus.UNCLICK;
            }
        }
        return ClickStatus.BAN;
    }

    @Override
    public  ClickStatus onSizeTagClick(int position) {
        TagView sizeTabview= mSizeAllChildViews.get(position);
        if (sizeTabview.getEnabled()==true){
            sizeTabview.playSoundEffect(SoundEffectConstants.CLICK);
            if (!sizeTabview.getIsClick()){
                for (TagView tagView : mSizeAllChildViews) {
                    if (tagView.getEnabled()==true) {
                        tagView.setTagViewColor(mDefaultViewColor);
                        tagView.setIsClick(false);
                    }
                }
                setColorTagColor(position);
                sizeTabview.setTagViewColor(mClickViewColor);
                sizeTabview.setIsClick(true);
                return ClickStatus.CLICK;
            }else{
                sizeTabview.setTagViewColor(mDefaultViewColor);
                sizeTabview.setIsClick(false);
                setColorTagColor(-1);
                return ClickStatus.UNCLICK;
            }
        }
        return ClickStatus.BAN;
    }



    @Override
    public  TagBean getClickObject() {
        return null;
    }

    private void setSizeTagColor(int position) {
        if (position==-1){
            for (int i=0;i<mSizeAllChildViews.size();i++){
                if (mSizeAllChildViews.get(i).getEnabled()==false) {
                    mSizeAllChildViews.get(i).setTagViewColor(mDefaultViewColor);
                    mSizeAllChildViews.get(i).setEnabled(true);
                }
            }
            return;
        }

        for (int i=0;i<mTwoTagBean.get(position).getTagBean().size();i++){
            if (mTwoTagBean.get(position).getTagBean().get(i).getAmount()==0){
                mSizeAllChildViews.get(i).setTagViewColor(mBanViewColor);
                mSizeAllChildViews.get(i).setEnabled(false);
            }else { if (!mSizeAllChildViews.get(i).getIsClick()){
                mSizeAllChildViews.get(i).setTagViewColor(mDefaultViewColor);
                mSizeAllChildViews.get(i).setEnabled(true);
            }
            }
        }
    }

    private void setColorTagColor(int position) {
        if (position==-1){
            for (int i=0;i<mColorAllChildViews.size();i++){
                if (mColorAllChildViews.get(i).getEnabled()==false){
                    mColorAllChildViews.get(i).setTagViewColor(mDefaultViewColor);
                    mColorAllChildViews.get(i).setEnabled(true);
                }
            }
            return;
        }

        for(int i=0;i<mTwoTagBean.size();i++){
            if (mTwoTagBean.get(i).getTagBean().get(position).getAmount()==0){
                mColorAllChildViews.get(i).setTagViewColor(mBanViewColor);
                mColorAllChildViews.get(i).setEnabled(false);
            }  else{
                if ( !mColorAllChildViews.get(i).getIsClick()){
                mColorAllChildViews.get(i).setTagViewColor(mDefaultViewColor);
                mColorAllChildViews.get(i).setEnabled(true);
                }

            }

        }

    }
}
