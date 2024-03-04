package com.longfish.controller;

import com.longfish.pojo.Result;
import com.longfish.pojo.User;
import com.longfish.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/sign")
    public Result sign(@RequestBody User user){
        boolean b = signInService.sign(user);

        if (b)
            return Result.success();
        return Result.error("报名失败");
    }

    @PostMapping("/unSign")
    public Result unSign(@RequestBody User user){
        boolean b = signInService.unSign(user);

        if (b)
            return Result.success();

        return Result.error("取消报名失败");
    }
}
