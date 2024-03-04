package com.longfish.utils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期类型转换的工具类
 */
public class DateUtil {

    /**
     * Sat Dec 09 2023 00:00:00 GMT 0800 (中国标准时间) -> 2023-12-09
     */
    public static LocalDate parseDate(String date){
        if (date == null || date.equals("null") || date.equals("") || date.equals("undefined"))
            return null;

        Map<String, String> map = new HashMap<>();
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Mar", "03");
        map.put("Apr", "04");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");

        String s = "";
        s += date.substring(11,15);
        s += '-';
        s += map.get(date.substring(4,7));
        s += '-';
        s += date.substring(8,10);
        return LocalDate.parse(s);

    }


}
