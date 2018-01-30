package com.example.mio.a20180128practice;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

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

    int feb_days;//2月天數
    int DAY_2000_1_1 = 5;//1999/12/31是星期五
    int day_after_2000_1_1=0;
    Calendar calendar;
    int start_Day_of_the_week;//該月份的第一天是星期幾
    int Days_After_2000_1_1;//從2000/1/1開始之後過了幾天
    int[] MONTH_LENGTH_LIST ={ 31, feb_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar=new GregorianCalendar();
         year=calendar.get(Calendar.YEAR) ;
         month=calendar.get(Calendar.MONTH)+1 ;//0~11補正1~12
         day=calendar.get(Calendar.DAY_OF_MONTH) ;
        Log.d("究竟是幾號呢",String.valueOf(day));
        start_Day_of_the_week=get_Day_of_the_week(year,month,day);//設為輸入的該年月的一號是星期幾
        Days_After_2000_1_1=get_Days_After_2000_1_1(year,month,day);//設為輸入的年月日是從2000/1/1開始之後過了幾天


        initViews();
       // initDatas();
        setCalendar(year,month,day);
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


    public void setCalendar(int year,int month,int day)
    {
        if (dataList.size() > 0) {            dataList.clear();        }
        if (gridList.size() > 0) {            gridList.clear();        }
        Log.d( "MyLog" , "開始setCalendar" );
        Log.d( "MyLog" , "月份為"+month+"，年份為"+year );
        //一直加月份的天數，直到指定的月份
//        for(int i=0; i<monthGot-1; ++i)
//        {
//            daysAfter2016_1_1 += MONTH_LENGTH_LIST[i];
//        }
//        Log.d( "MyLog" , "在"+yearGot+"年"+(monthGot-1)+"月底時，過了"+daysAfter2016_1_1+"天");

       // int whatDay = (get_Days_After_2000_1_1(year,month,day) +DAY_2000_1_1 )%7;//求輸入的該年月的一號是星期幾
        int whatDay = start_Day_of_the_week+1;//求輸入的該年月的一號是星期幾
        Log.d( "SetDayLog" , year+"年"+(month)+"月初，是禮拜"+whatDay);
        Log.d( "SetDayLog" , year+"年"+(month)+"月初，共過了"+Days_After_2000_1_1);

        int textDateCount = 1 + whatDay;//星期幾+1=>(甚麼意思?)=>解：因為禮拜整除=0，所以+1把範圍從0~6變成1~7
        int textDateCountLast = whatDay;//設為禮拜幾的前面幾格
//以下有三個步驟，(步驟一跟二對調)設定當月日期、上個月日期(最後幾天)、下個月日期(最初幾天)

        //設定上個月的日期，1月會有問題
        //步驟二，設定上個最後月幾天的日期(步驟一跟二對調)
        // 設定一月的上個最後月幾天的日期時，切換到去年12月的時候會發生問題，所以要把1月的情況特地挑出來寫
        // 會發生的問題是....?
        if(month==1)//如果輸入的月份是1月
        {
            for (int i = MONTH_LENGTH_LIST[11]; 1 > 0; i--)//令i等於12月的天數，然後無窮迴圈到執行break
            {
                DataBean bean = new DataBean();
                bean.name = String.valueOf(i);
                dataList.add(bean);
                Log.d( "MyLog" , "第" + (i + 1) + "条数据");
                if (textDateCountLast > 0)//迴圈跑出有幾個上個月的天數(透過本月1號是星期幾來找出)。不能大於本月的起始星期
                {
                    String foo = "day_" + textDateCountLast;
                    Log.d("MyLog", "使用ID為" + foo);
                    int resID = getResources().getIdentifier(foo, "id", getPackageName());
                    Log.d("MyLog", "使用ID為" + resID);
                    Log.d("MyLog", "使用ID為" + R.id.day_1);
                    TextView someDateText = (TextView) findViewById(resID);
                    TextView tv1;
                    tv1=(TextView)findViewById(R.id.day_1);
                   // someDateText.setText(Integer.toString(i));//日期設定為12月的第i天(31、30、29、28、27...

                    Log.d("MyLog", "使用TextView為" +someDateText);
                    Log.d("MyLog", "使用TextView為" +tv1);
                    Log.d("MyLog", "使用I為" +i);
                    someDateText.setText(String.valueOf(31));//日期設定為12月的第i天(31、30、29、28、27...

                    textDateCountLast--;//每執行一次就-1，最後一個執行的是本星期的起始點
                }
                else
                {
                    break;
                }
            }
        }
        else
        {
            for (int i = MONTH_LENGTH_LIST[month - 2]; 1 > 0; i--)//如果輸入的月份不是1月，則跑一個上個月的迴圈(二月跑一月，三月跑二月)
            {
                DataBean bean = new DataBean();
                bean.name = String.valueOf(i);
                dataList.add(bean);
                Log.d( "MyLog" , "第" + (i + 1) + "条数据");
                if (textDateCountLast > 0)
                {
                    String foo = "day_" + textDateCountLast;
                    Log.d("MyLog", "使用ID為" + foo);
                    int resID = getResources().getIdentifier(foo, "id", getPackageName());
                    TextView someDateText = (TextView) findViewById(resID);
                   someDateText.setText(Integer.toString(i));
                    textDateCountLast--;
                }
                else
                {
                    break;
                }
            }
        }

        //步驟一，設定當月的日期(步驟一跟二對調)
        for(int i=1; i<=MONTH_LENGTH_LIST[month-1]; ++i)//抓出陣列裡面對應的月份(從0開始所以要-1)，接著從1號開始迴圈到該月份的最後一天
        {

            DataBean bean = new DataBean();
            bean.name = String.valueOf(i);
            dataList.add(bean);
            Log.d( "MyLog" , "第" + (i + 1) + "条数据");

            String foo = "day_" + textDateCount;//設定一個字串(起始日的星期幾)=DateText+星期幾
            Log.d( "MyLog" , "使用ID為"+foo);
                    int resID = getResources().getIdentifier(foo , "id" , getPackageName());
                      TextView someDateText = (TextView) findViewById(resID);//找到TextView
            someDateText.setText(Integer.toString(i));//設定TextView
            Log.d( "來猜第二次吧" , "使用ID為"+day);
            Log.d( "來猜第二次吧" , "使用ID為"+day);
            if(i==day){
                someDateText.setTextColor(0xffff0000);
            }
            textDateCount++;//往下一個DAY
        }
//步驟三：設定下個月日期(最初幾天)
        //(刪)設定下個月的日期，12月會有問題
        //(刪)int i = whatDay + MONTH_LENGTH_LIST[monthGot-1]+1;
        //(刪)Log.d( "MyLog" , "下個月的開始dateBlock編號為"+i);
        // (刪)在12月設定下個月的日期時會有問題，所以要把12月的情況特地挑出來寫
        //(刪) 會發生的問題是....?
        int newMonthDate = 1;
        for(int i = whatDay + MONTH_LENGTH_LIST[month-1]+1; i<=42; ++i){
        // MONTH_LENGTH_LIST[monthGot-1]這個是陣列裡面第X個月，由於陣列從0開始，所以要求自己的月份要-1
        // /本月起始日的星期+本月天數，最後再+1=42格裡面剩下幾個是下個月初的格子
        //i的起始值為本月月初是星期幾，加上本月天數可算出本月結束是星期幾，
            DataBean bean = new DataBean();
            bean.name = String.valueOf(newMonthDate);
            dataList.add(bean);
            Log.d( "MyLog" , "第" + (i + 1) + "条数据");
            String foo = "day_" + i;
            Log.d( "MyLog" , "使用ID為"+foo);
            int resID = getResources().getIdentifier(foo , "id" , getPackageName());
            TextView someDateText = (TextView) findViewById(resID);
            someDateText.setText(Integer.toString(newMonthDate));
            newMonthDate++;
        }
        for (int i = 0; i < 30; i++) {
            GridView gridView = new GridView(this);
            GridViewAdapter adapter = new GridViewAdapter(dataList, i);
            gridView.setNumColumns(number_columns);
            gridView.setAdapter(adapter);
            gridList.add(gridView);
        }
        mAdapter.add(gridList);//把每一個客製的list(gridview)丟進去mAdapter物件，之後再用viewPager讀出來全部的東西

        Log.d( "MyLdsdssdog" ,String.valueOf(mAdapter));
    }

    public int get_Day_of_the_week(int year,int month,int day){//求某日期是星期幾

        for (int i = 2000; i < year ; i++) {//迴圈次數等於參數的年距離2000年有幾年，進而求出要加幾天
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//判斷這一年要不要潤
                DAY_2000_1_1 = (DAY_2000_1_1 + 366) % 7;//潤的話這年就是+366天，接著取7餘數判斷出是星期幾
            } else {
                DAY_2000_1_1 = (DAY_2000_1_1 + 365) % 7;////不潤的話這年就是+365天，接著取7餘數判斷出是星期幾
            }
            //每次回圈決定下一年的  是星期幾
        }
        int Day_of_the_week=(DAY_2000_1_1)%7;
        Log.d("GFDHDFGHGJGH",String.valueOf(Day_of_the_week));
        return Day_of_the_week;
    }

    public int get_Days_After_2000_1_1(int year,int month,int day){//求某日期是第幾周
        int week;

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//求那年的二月有幾天
            feb_days = 29;

        } else {
            feb_days = 28;

        }

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
//        day_after_2000_1_1= day_after_2000_1_1 % 365;
//        day_after_2000_1_1=day_after_2000_1_1 /7;
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
