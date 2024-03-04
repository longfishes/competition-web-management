package com.longfish.controller;

import com.longfish.pojo.Result;
import com.longfish.pojo.User;
import com.longfish.service.UserService;
import com.longfish.utils.JwtUtils;
import com.longfish.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){

        User u = userService.login(user);

        if (u != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            u.setJwt(jwt);

            File file = new File("logs\\login\\logins.txt");
            Logger.log("\n用户登录@"+ LocalDateTime.now() +"：username[" +u.getUsername() +"],password["+u.getPassword()+"]",file);
            return Result.success(u);
        }

        return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){

        boolean b = userService.register(user);
        return b ? Result.success() : Result.error("注册失败");
    }
}
