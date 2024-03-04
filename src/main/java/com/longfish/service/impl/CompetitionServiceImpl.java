package com.longfish.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.longfish.mapper.CompetitionMapper;
import com.longfish.pojo.Competition;
import com.longfish.pojo.PageBean;
import com.longfish.pojo.User;
import com.longfish.service.CompetitionService;
import com.longfish.utils.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public PageBean list(Integer page,
                         Integer pageSize,
                         String copName,
                         Short type,
                         Short isFull,
                         LocalDate date,
                         Short order) {

        PageHelper.startPage(page, pageSize);
        List<Competition> competitionList = competitionMapper.list(page, pageSize, copName, type, isFull, date, order);
        Page<Competition> p = (Page<Competition>) competitionList;

        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public int delete(List<Integer> ids) {
        return competitionMapper.delete(ids);
    }

    @Override
    public int add(Competition cop) {
        return competitionMapper.add(cop);
    }

    @Override
    public Competition getById(Integer id) {
        return competitionMapper.getById(id);
    }

    @Override
    public void update(Competition competition) {
        competitionMapper.update(competition);
    }

    @Override
    public String export() {

        List<Object> head = Arrays.asList("ID", "比赛名称", "比赛简介", "主办方", "类型", "参赛名额（单位：名）",
                "已报名人数", "报名开始时间", "报名结束时间", "状态");

        List<Competition> competitionList = competitionMapper.list(null, competitionMapper.count(), null, null, null, null, Short.valueOf("4"));

        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);

        int i = 0;
        for (Competition competition : competitionList) {
            ArrayList<Object> competitions = new ArrayList<>();

            competitions.add(++i);

            competitions.add(competition.getCopName());
            competitions.add(competition.getInfo());
            competitions.add(competition.getOrganizers());

            if (competition.getType() == 1)
                competitions.add("个人赛");
            else if (competition.getType() == 2)
                competitions.add("团体赛");
            else competitions.add("数据错误或已丢失");

            competitions.add(competition.getLimitation());
            competitions.add(competition.getParticipated());
            competitions.add(competition.getStartDate());
            competitions.add(competition.getEndDate());

            if (competition.getCopStatus() == 1)
                competitions.add("未开始");
            else if (competition.getCopStatus() == 2)
                competitions.add("正在报名");
            else if (competition.getCopStatus() == 3)
                competitions.add("已截止");
            else competitions.add("数据错误或已丢失");

            sheetDataList.add(competitions);

        }

        String path = "src\\main\\resources\\ec\\downloads\\";
        path += "competitions.xlsx";

        ExcelUtils.exportFile(new File(path), sheetDataList);

        return path;

    }

    @Override
    public boolean check(User user) {

        List<User> check = competitionMapper.check(user);
        return check.size() != 0;
    }

    @Override
    public List<Competition> userCop(String username) {
        return competitionMapper.getCopByUsername(username);
    }

    @Override
    public int avai() {
        return competitionMapper.avaiCount();
    }


}



