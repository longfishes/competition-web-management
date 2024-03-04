package com.longfish.service.impl;

import com.longfish.mapper.CompetitionMapper;
import com.longfish.mapper.UserMapper;
import com.longfish.pojo.Competition;
import com.longfish.pojo.User;
import com.longfish.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public boolean sign(User user) {

        Competition cop = competitionMapper.getById(user.getCopId());
        if (cop.getParticipated().equals(cop.getLimitation())) return false;

        User sign = userMapper.sign(user);


        List<User> list = userMapper.select(user);
        if (sign != null){
            if (list.size() == 0){

                userMapper.update(user);
                userMapper.record(user);
                return true;
            }

        }
        return false;

    }

    @Override
    public boolean unSign(User user) {

        User sign = userMapper.sign(user);
        List<User> list = userMapper.select(user);

        if (sign != null){
            if (list.size() != 0){

                userMapper.updateDel(user);
                userMapper.unRecord(user);
                return true;
            }

        }
        return false;
    }
}
