package com.longfish.service;

import com.longfish.pojo.PageBeanForUser;
import com.longfish.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 登录操作
     * @param user（包含用户名：username和密码：password）
     * @return 通过bean：User类封装的此用户的信息和jwt令牌
     */
    User login(User user);

    /**
     * 注册操作
     * @param user（包含用户名：username，密码：password，组织：organization和类型（1工作人员 2选手）：type）
     * @return 是否成功的布尔值
     */
    boolean register(User user);

    /**
     * 修改用户信息操作
     * @param user 包含要修改的用户id：user.id
     * @return 影响的条数（修改成功应为1，否则应为0）
     */
    Integer update(User user);

    /**
     * 新增用户操作
     * @param user 包含封装好的所有要新增的用户信息
     * @return 影响的条数（新增成功应为1，否则应为0）
     */
    Integer insert(User user);

    /**
     * 批量删除操作
     * @param ids 要删除的用户id的List集合
     */
    void delete(List<Integer> ids);

    /**
     * 查询所用用户
     * @param page 当前页
     * @param pageSize 每页显示数
     * @param id 如果有次项则为：根据id查询（单条）
     * @return 封装好的PageBean，包含总条数和查询到的所有数据
     */
    PageBeanForUser list(Integer page, Integer pageSize, Integer id);
}
