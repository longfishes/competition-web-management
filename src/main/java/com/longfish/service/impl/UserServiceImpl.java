package com.longfish.service.impl;

import com.github.pagehelper.PageHelper;
import com.longfish.mapper.UserMapper;
import com.longfish.pojo.PageBeanForUser;
import com.longfish.pojo.User;
import com.longfish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getByUserNameAndPassword(user);
    }

    @Override
    public boolean register(User user) {
        List<User> userList = userMapper.getUserByName(user.getUsername());
        if (userList.size() >= 1) return false;

        Integer rows = userMapper.addUser(user);
        return rows.equals(1);
    }

    @Override
    public Integer update(User user) {

        if (user.getPassword() == null)
            return userMapper.updateDetails(user);
        else{
            User u = userMapper.getByUserNameAndPassword(user);
            if (u == null) return 0;
            return userMapper.updateDetails(user);
        }

    }

    @Override
    public Integer insert(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public void delete(List<Integer> ids) {

        userMapper.drop(ids);
    }

    @Override
    public PageBeanForUser list(Integer page, Integer pageSize, Integer id) {

        PageHelper.startPage(page,pageSize);
        List<User> userList = userMapper.list(page, pageSize, id);
        return new PageBeanForUser(userMapper.count(),userList);
    }

}
