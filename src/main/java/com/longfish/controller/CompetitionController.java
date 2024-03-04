package com.longfish.controller;

import com.longfish.pojo.Competition;
import com.longfish.pojo.PageBean;
import com.longfish.pojo.Result;
import com.longfish.pojo.User;
import com.longfish.service.CompetitionService;
import com.longfish.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/competitions")
@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;



    /**
     * 查询比赛操作
     * @param page 1.page页数
     * @param pageSize 2.pageSize每页条数
     * @param copName 3.copName比赛名称
     * @param type 4.type比赛类型（1个人赛，2团体赛）
     * @param isFull 5.isFull是否报满（1未满，2已满）
     * @param date 6.date查询的某一个日期
     * @param order 7.order查询的排序（1报名人数降序，2报名人数升序，3开始日期降序，4截至日期升序）
     * @param username 8.username如果有此参数，则另外查询每条比赛是否已报名
     */
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       String copName,
                       Short type,
                       Short isFull,
                       String date,
                       @RequestParam(defaultValue = "4") Short order,
                       String username){

        if (copName != null) copName = copName.trim();
        PageBean pageBean = competitionService.list(page, pageSize, copName, type, isFull, DateUtil.parseDate(date), order);
        if (username != null){

            for (int i = 0; i < pageBean.getRows().size(); i++) {
                User u = new User();
                u.setUsername(username);
                u.setCopId(pageBean.getRows().get(i).getId());

                pageBean.getRows().get(i).setSign(competitionService.check(u));
            }
        }

        return Result.success(pageBean);
    }

    /**
     * 查询指定用户报名的所有比赛
     */
    @GetMapping("/user")
    public Result userCop(String username){

        List<Competition> competitionList = competitionService.userCop(username);
        return Result.success(competitionList);
    }

    /**
     * 查询是否报名
     */
    @PostMapping("/sign")
    public Result isSigned( @RequestBody User user){

        boolean b = competitionService.check(user);
        if (b)
            return Result.success(1);
        return Result.success(2);
    }

    /**
     * 查询可报比赛数目
     */
    @GetMapping("/avai")
    public Result avai(){
        int count = competitionService.avai();

        return Result.success(count);

    }

    /**
     * 删除比赛操作
     */
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){

        int rows = competitionService.delete(ids);
        if (rows > 0){
            log.info("删除比赛:{id}");
            return Result.success();
        }

        return Result.error("用户不存在");
    }

    /**
     * 新增比赛操作
     */
    @PostMapping
    public Result add(@RequestBody Competition cop){

        cop.setStartDate(cop.getStartDate().substring(0,10));
        cop.setEndDate(cop.getEndDate().substring(0,10));

        log.info("新增比赛:{cop}");
        competitionService.add(cop);
        return Result.success();
    }


    /**
     * 根据id查询比赛信息
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){

        Competition competition = competitionService.getById(id);
        return Result.success(competition);
    }

    /**
     * 修改比赛操作
     */
    @PutMapping
    public Result update(@RequestBody Competition competition){
        competition.setStartDate(competition.getStartDate().substring(0,10));
        competition.setEndDate(competition.getEndDate().substring(0,10));

        competitionService.update(competition);
        return Result.success();
    }

}
