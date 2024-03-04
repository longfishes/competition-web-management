package com.longfish.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateMapper {

    /**
     * 将数据库中报名状态字段根据开始与截止日期实时更新调整正确
     * 因为要调整整个数据库中的报名状态，所以此update语句不包含where条件
     */
    void update();

}
