package com.example.mio.a20180128practice;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    public static int item_grid_num = 42;//每一页中GridView中item的数量
    public static int number_columns = 7;//gridview一行展示的数目
    private ViewPager view_pager;
    private ViewPagerAdapter mAdapter;
    private List<DataBean> dataList;
    private List<GridView> gridList = new ArrayList<>();

    Calendar calendar;
    int start_Day_of_the_week;
    int Days_After_2000_1_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar=new GregorianCalendar();
        int year=calendar.get(Calendar.YEAR) ;
        int month=calendar.get(Calendar.MONTH) ;
        int day=calendar.get(Calendar.DAY_OF_MONTH) ;
        start_Day_of_the_week=get_Day_of_the_week(year,month,day);//設為輸入的該年月的一號是星期幾
        Days_After_2000_1_1=get_Days_After_2000_1_1(year,month,day);//設為輸入的年月日是從2000/1/1開始之後過了幾天

        initViews();
        initDatas();

     //  view_pager.setCurrentItem(16);
        Log.d("HHG12=",String.valueOf(mAdapter.getCount()));
        Log.d("HHG12=",String.valueOf(view_pager.getCurrentItem()));
    }


    private void initViews() {
        //初始化ViewPager
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new ViewPagerAdapter();

        view_pager.setAdapter(mAdapter);




        dataList = new ArrayList<>();
        //圆点指示器
//        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
//        indicator.setVisibility(View.VISIBLE);
//        indicator.setViewPager(view_pager);
    }

    private void initDatas() {
        if (dataList.size() > 0) {
            dataList.clear();
        }
        if (gridList.size() > 0) {
            gridList.clear();
        }
        //初始化数据
        for (int i = 0; i < 420; i++) {
            DataBean bean = new DataBean();
            bean.name = "第" + (i + 1) + "条数据";
            dataList.add(bean);
        }
        //计算viewpager一共显示几页
        int pageSize = dataList.size() % item_grid_num == 0
                ? dataList.size() / item_grid_num
                : dataList.size() / item_grid_num + 1;
        for (int i = 0; i < 30; i++) {
            GridView gridView = new GridView(this);
            GridViewAdapter adapter = new GridViewAdapter(dataList, i);
            gridView.setNumColumns(number_columns);
            gridView.setAdapter(adapter);
            gridList.add(gridView);

        }
        mAdapter.add(gridList);//把每一個客製的list(gridview)丟進去mAdapter物件，之後再用viewPager讀出來全部的東西
        view_pager.setCurrentItem(8);
//        作者：_小马快跑_
//        链接：https://www.jianshu.com/p/bf0a63afd722
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

//----------------------------------------------------------------------------------------------

    int feb_days;//2月天數
    int DAY_2000_1_1 = 5;//1999/12/31是星期五
    int day_after_2000_1_1=0;


    public int get_Day_of_the_week(int year,int month,int day){//求某日期是星期幾

        for (int i = 2000; i < year ; ++i) {//迴圈次數等於參數的年距離2000年有幾年，進而求出要加幾天
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//判斷這一年要不要潤
                DAY_2000_1_1 = (DAY_2000_1_1 + 366) % 7;//潤的話這年就是+366天，接著取7餘數判斷出是星期幾
            } else {
                DAY_2000_1_1 = (DAY_2000_1_1 + 365) % 7;////不潤的話這年就是+365天，接著取7餘數判斷出是星期幾
            }
            //每次回圈決定下一年的  是星期幾
        }
        DAY_2000_1_1=(DAY_2000_1_1+day)%7;
        return DAY_2000_1_1;
    }

    public int get_Days_After_2000_1_1(int year,int month,int day){//求某日期是第幾周
        int week;

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//求那年的二月有幾天
            feb_days = 29;

        } else {
            feb_days = 28;

        }
        int[] MONTH_LENGTH_LIST ={ 31, feb_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //int[] space ={0, first_space, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for(int i = 2000; i < year; i++)//得到年分
        {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                day_after_2000_1_1 += 366;
            } else {
                day_after_2000_1_1 += 365;
            }
        }

        for(int i=0; i<month-1; i++){//得到月份的開頭
            day_after_2000_1_1 += MONTH_LENGTH_LIST[i];
        }

//        if((month)==1)
//        {
//            Log.d( "SetDayLog" , "在"+(year-1)+"年"+(12)+"月底時，過了"+day_after_2000_1_1+"天");
//        }
//        else
//        {
//            Log.d("SetDayLog", "在" + year + "年" + (month) + "月底時，過了" + day_after_2000_1_1 + "天");
//        }
        day_after_2000_1_1= day_after_2000_1_1 % 365;
        day_after_2000_1_1=day_after_2000_1_1 /7;
        week=day_after_2000_1_1;
        return week;
    }






//    public int getdate(int year,int month) {
//        int yy, mm, dd, date;
//        int first_space=6;//第一個月的第一個星期有幾天空白
//        int feb_days;//2月天數
//
//        int DAY_2000_1_1 = 6;//2000/1/1是星期六
//        int day_after_2000_1_1=0;
//
//        boolean isLeapYear;
//
//
//
//
//            for (int i = 2000; i < year ; i++) {//迴圈次數等於參數的年距離2000年有幾年，進而求出要加幾天
//                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//判斷這一年要不要潤
//                    DAY_2000_1_1 = (DAY_2000_1_1 + 366) % 7;//潤的話這年就是+366天，接著取7餘數判斷出是星期幾
//                } else {
//                    DAY_2000_1_1 = (DAY_2000_1_1 + 365) % 7;////不潤的話這年就是+365天，接著取7餘數判斷出是星期幾
//                }
//                //每次回圈決定下一年的  是星期幾
//            }
//
//            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//求那年的二月有幾天
//                feb_days = 29;
//                isLeapYear = true;
//            } else {
//                feb_days = 28;
//                isLeapYear = false;
//            }
//            int[] MONTH_LENGTH_LIST ={ 31, feb_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//            //int[] space ={0, first_space, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//
//            for(int i = 2000; i < year; i++)//得到年分
//            {
//                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
//                    day_after_2000_1_1 += 366;
//                } else {
//                    day_after_2000_1_1 += 365;
//                }
//            }
//
//            for(int i=1; i<month; i++){//得到月份的開頭
//                day_after_2000_1_1 += MONTH_LENGTH_LIST[i];
//            }
//
//            if((month)==1)
//            {
//                Log.d( "SetDayLog" , "在"+(year-1)+"年"+(12)+"月底時，過了"+day_after_2000_1_1+"天");
//            }
//            else
//            {
//                Log.d("SetDayLog", "在" + year + "年" + (month) + "月底時，過了" + day_after_2000_1_1 + "天");
//            }
//
//
//
//
//
//        return date;
//    }
}
