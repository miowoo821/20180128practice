package com.example.mio.a20180128practice;

/**
 * Created by mio on 2018/1/29.
 */

public class clendarDAO {

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

    public int get_week_of_the_month(int year,int month,int day){//求某日期是第幾周
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




}
