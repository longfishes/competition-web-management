package com.longfish.controller;

import com.longfish.pojo.Competition;
import com.longfish.pojo.Result;
import com.longfish.service.CompetitionService;
import com.longfish.utils.excel.ExcelUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@RestController
public class ImportAndExportController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * 从 excel 文件导入新增比赛
     */
    @PostMapping("/import")
    public Result importUser(@RequestPart("file")MultipartFile file) throws Exception {

        List<Competition> competitionList = ExcelUtils.readMultipartFile(file, Competition.class);
        if (competitionList.size() == 0)
            return Result.error("导入失败");

        competitionList.forEach(competitionService::add);

        return Result.success("导入成功!");

    }


    /**
     * 导出比赛信息到 excel
     */
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {

        String path = competitionService.export();
        File file = new File(path);

        ServletOutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);

        response.setHeader("Content-Disposition","attachment;filename=" +
                "Competitions"
                + ".xlsx");

        byte[] buffer = new byte[1024*8];
        int len = fis.read(buffer);

        while (len != -1){
            for (int i = 0; i < len; i++) {
                os.write(buffer[i]);
            }
            len = fis.read(buffer);
        }


        os.flush();
        os.close();
        fis.close();

        return Result.success("下载成功!");
    }

}
