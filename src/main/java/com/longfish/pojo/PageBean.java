package com.longfish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 比赛的分页查询数据传输实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageBean {

    private Long total;//总记录数
    private List<Competition> rows;//记录列表

}
