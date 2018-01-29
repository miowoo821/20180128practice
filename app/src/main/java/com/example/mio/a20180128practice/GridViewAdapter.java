package com.example.mio.a20180128practice;

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

    GridViewAdapter(List<DataBean> datas, int page) {
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
        ViewHolder mHolder=new ViewHolder();
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
        Log.d("GGGG="+i,String.valueOf(dataList.get(i)));
        DataBean bean = dataList.get(i);
        if (bean != null) {
           // mHolder.iv_img.setImageResource(R.mipmap.group_icon);
            mHolder.tv_text.setText(bean.name);
        }
        return itemView;
    }
    private class ViewHolder {
        LinearLayout linearLayout;
        private ImageView iv_img;
        private TextView tv_text;
    }

//    作者：_小马快跑_
//    链接：https://www.jianshu.com/p/bf0a63afd722
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}

