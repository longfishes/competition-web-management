package com.longfish.controller;

import com.longfish.pojo.PageBeanForUser;
import com.longfish.pojo.Result;
import com.longfish.pojo.User;
import com.longfish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 修改用户信息操作
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user){

        Integer i = userService.update(user);
        return i != 0 ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 新增用户操作
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody User user){

        Integer i = userService.insert(user);
        return i == 1 ? Result.success() : Result.error("添加失败");
    }

    /**
     * 删除用户操作
     */
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){

        userService.delete(ids);
        return  Result.success();
    }

    /**
     * 查询用户操作
     */
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "8") Integer pageSize,
                       Integer id){

        PageBeanForUser pg = userService.list(page, pageSize, id);
        return id != null ? Result.success(pg.getRows().get(0)) : Result.success(pg);
    }

}
