package com.longfish.mapper;

import com.longfish.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 核验用户名和密码
     * @param user（包含用户名：username和密码：password）
     * @return 查询到的用户
     */
    User getByUserNameAndPassword(User user);


    /**
     * 校验用户的报名情况
     * @param user 包含用户名：username
     * @return 查询到的用户
     */
    @Select("select * from user where username = #{username}")
    User sign(User user);

    /**
     * 报名成功后更新比赛信息
     * @param user 谁报名
     */
    @Update("update competition set participated = participated + 1 where id = #{copId}")
    void update(User user);

    /**
     * 取消报名成功后更新比赛信息
     * @param user 谁取消报名
     */
    @Update("update competition set participated = participated - 1 where id = #{copId}")
    void updateDel(User user);

    /**
     * 记录报名信息
     * @param user 谁报名
     */
    @Insert("insert into signs(username, cop_id, time) values (#{username}, #{copId}, now())")
    void record(User user);

    /**
     * 记录取消报名
     * @param user 谁取消报名
     */
    @Delete("delete from signs where username = #{username} and cop_id = #{copId}")
    void unRecord(User user);

    /**
     * 查询用户报名的所有比赛
     * @param user 包含username：用户名
     * @return 报名的比赛的List集合
     */
    @Select("select * from signs where username = #{username} and cop_id = #{copId}")
    List<User> select(User user);


    /**
     * 新增用户操作
     * @param user 包含封装好的所有要新增的用户信息
     * @return 影响的条数（新增成功应为1，否则应为0）
     */
    Integer addUser(User user);

    /**
     * 修改用户信息操作
     * @param user 包含要修改的用户id：user.id
     * @return 影响的条数（修改成功应为1，否则应为0）
     */
    Integer updateDetails(User user);

    /**
     * 批量删除操作
     * @param ids 要删除的用户id的List集合
     */
    void drop(List<Integer> ids);

    /**
     * 查询所用用户
     * @param page 当前页
     * @param pageSize 每页显示数
     * @param id 如果有次项则为：根据id查询（单条）
     * @return 封装好的PageBean，包含总条数和查询到的所有数据
     */
    List<User> list(Integer page, Integer pageSize, Integer id);

    /**
     * 查询用户总数
     * @return 用户的总数
     */
    @Select("select count(*) from user")
    Integer count();

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 查询到的用户
     */
    List<User> getUserByName(String username);
}
