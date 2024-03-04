package com.longfish.service;


import com.longfish.pojo.User;

public interface SignInService {

    /**
     * 报名比赛操作
     * @param user（只需包含用户名：username和报名比赛的id：copId）
     * @return 是否成功的布尔值
     */
    boolean sign(User user);

    /**
     * 取消报名比赛操作
     * @param user（只需包含用户名：username和取消报名比赛的id：copId）
     * @return 是否成功的布尔值
     */
    boolean unSign(User user);
}
