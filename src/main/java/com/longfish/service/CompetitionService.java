package com.longfish.service;

import com.longfish.pojo.Competition;
import com.longfish.pojo.PageBean;
import com.longfish.pojo.User;

import java.time.LocalDate;
import java.util.List;


public interface CompetitionService {

    /**
     * 查询比赛操作
     * @param page 当前页
     * @param pageSize 每页条数
     * @param copName 比赛名称（模糊查询）
     * @param type 比赛类型（1个人赛 2团体赛）
     * @param isFull 是否已报满（1未报满 2已报满）
     * @param date 某一天的日期
     * @param order 排序方式
     * @return 封装好的PageBean，包含总条数和查询到的所有记录数
     */
    PageBean list(Integer page,
                  Integer pageSize,
                  String copName,
                  Short type,
                  Short isFull,
                  LocalDate date,
                  Short order);

    /**
     * 批量删除操作
     * @param ids 要删除的比赛id的List集合
     * @return 影响的条数，即成功删除的条数
     */
    int delete(List<Integer> ids);

    /**
     * 新增比赛操作
     * @param cop 要新增的比赛，比赛信息通过Competition类封装好
     * @return 影响的条数，新增成功应为1，否则应为0
     */
    int add(Competition cop);


    /**
     * 根据id查询比赛
     * @param id 要查询比赛的id
     * @return 封装好的Competition对象，包含比赛的信息
     */
    Competition getById(Integer id);

    /**
     * 修改比赛信息操作
     * @param competition 包含要修改的用户id：competition.id
     */
    void update(Competition competition);


    /**
     * 导出比赛信息操作
     * @return 文件路径
     */
    String export();

    /**
     * 查询是否报名
     * @param user 包含用户名：username和比赛id：copId
     * @return 是否报名的布尔值
     */
    boolean check(User user);

    /**
     * 查询用户报名的所有比赛
     * @param username 用户名
     * @return 报名的比赛的List集合
     */
    List<Competition> userCop(String username);

    /**
     * 查询可以报名的比赛数目
     * @return 可以报名的比赛数目
     */
    int avai();
}
