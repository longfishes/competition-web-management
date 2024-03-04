package com.longfish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户的分页查询数据传输实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageBeanForUser {

    private Integer total;//总记录数
    private List<User> rows;//记录列表
}
