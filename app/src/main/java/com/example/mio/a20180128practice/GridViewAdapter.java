package com.example.mio.a20180128practice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mio on 2018/1/28.
 */

public class GridViewAdapter extends BaseAdapter {
    private List<DataBean> dataList;
    Context context;
    GridViewAdapter(List<DataBean> datas, int page,Context context) {//參數多給一個context
        this.context=context;

        dataList = new ArrayList<>();
        //start end分别代表要显示的数组在总数据List中的开始和结束位置
        int start = page * MainActivity.item_grid_num;
        int end = start + MainActivity.item_grid_num;


        while ((start < datas.size()) && (start < end)) {
            dataList.add(datas.get(start));
            start++;
        }
    }
    @Override
    public int getCount() {
        return dataList.size();
    }
    @Override
    public Object getItem(int i) {
        return dataList.size();
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {
        ViewHolder mHolder;

       // mHolder.linearLayout=itemView.findViewById(R.id.linearLayout);
//        RelativeLayout linearLayout;
//        linearLayout= viewGroup.findViewById(R.id.linearLayout)   ;
//        ViewGroup.LayoutParams params1=linearLayout.getLayoutParams();
//        params1.height=100;
//        params1.width=100;
//        linearLayout.setLayoutParams(params1);

       // RelativeLayout dayBlock1 = itemView.findViewById(R.id.linearLayout);//抓第一排第一格
       // ViewGroup.LayoutParams params1 = dayBlock1.getLayoutParams();
        if (itemView == null) {
            mHolder = new ViewHolder();
            itemView= LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_gridview, viewGroup, false);
            mHolder.iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            mHolder.tv_text = (TextView) itemView.findViewById(R.id.tv_text);

            itemView.setTag(mHolder);
        }
        else {

            mHolder = (ViewHolder) itemView.getTag();
        }
        Log.d("GGGG20180203="+i,String.valueOf(dataList.get(i)));
        DataBean bean = dataList.get(i);
        Log.d("GGGG20180203001="+i,String.valueOf(mHolder));
//        if (bean != null) {
//           // mHolder.iv_img.setImageResource(R.mipmap.group_icon);
//
//            Log.d("GGGG="+i,String.valueOf(mHolder));
//            mHolder.tv_text.setText(bean.name);
//        }
////---------------------------
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        itemView = inflater.inflate(R.layout.test, null);

        itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.test, viewGroup, false);

        AppCompatActivity appCompatActivity=new AppCompatActivity();

        for(int i1=1;i1<43;i1++) {

            String foo = "day_" + i1;
            int resID =  itemView.getResources().getIdentifier(foo, "id", null);
            //上面這行抓不到這個ID，但是在MainActivity裡面抓的到，因為這個表格有放到activiy_main?
            TextView someDateText = (TextView) itemView.findViewById(resID);

            for(int i2=1;i2<4;i2++){
                String foo2 = "day_" + i1+"_"+i2;
                int resID2 =  itemView.getResources().getIdentifier(foo2, "id", null);
                TextView someDateText2 = (TextView) itemView.findViewById(resID2);
            }

        }
//---------------------------------------------

        return itemView;
    }
     static class ViewHolder {
        LinearLayout linearLayout;
         ImageView iv_img;
         TextView tv_text;

//      public ViewHolder(){
//          ArrayList<String> linearLayoutlist;
//          ArrayList<String> textlist;
//
//          linearLayoutlist=new ArrayList<>();
//          textlist=new ArrayList<>();
//
//          for(int i=1;i<7;i++){
//              linearLayoutlist.add("row_"+i);
//              for(int i1=1;i1<8;i1++){
//                  linearLayoutlist.add("row_"+i+"_"+i1);
//              }
//          }
//
//          for(int i1=1;i1<43;i1++) {
//
//              textlist.add("day_" + i1);
//
//              for(int i2=1;i2<4;i2++){
//                  textlist.add( "day_" + i1+"_"+i2);
//              }
//
//          }
//
//          Log.d("textlist","的成員有："+textlist);
//
////          TextView rtextlist[]={};
////          rtextlist[1]=(TextView)1111111;
////          TextView tv = new TextView(this)
////                  tv.getId()
////for(TextView o:textlist ){
////}
//
//
//
//      }


    }

}

