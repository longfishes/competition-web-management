package com.longfish.pojo;

import com.longfish.utils.excel.ExcelImport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* 比赛项目实体类
* @TableName competition
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Competition {

    private Integer id;

    @ExcelImport("比赛名称")
    private String copName;

    @ExcelImport("比赛简介")
    private String info;

    @ExcelImport("主办方")
    private String organizers;

    @ExcelImport(value = "比赛类型", kv = "1-个人赛;2-团体赛")
    private Integer type;

    @ExcelImport("总名额")
    private Integer limitation;

//    @ExcelImport("已报人数")
    private Integer participated;

    @ExcelImport("报名开始日期")
    private String startDate;

    @ExcelImport("报名截止日期")
    private String endDate;

    private Integer copStatus;

    private Boolean sign;


}
