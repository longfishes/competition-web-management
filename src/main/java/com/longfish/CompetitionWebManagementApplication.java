package com.longfish;

import com.longfish.mapper.UpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class CompetitionWebManagementApplication {

    @Autowired
    private UpdateMapper updateMapper;

    public static void main(String[] args) {
        SpringApplication.run(CompetitionWebManagementApplication.class, args);
    }

    @PostConstruct
    public void init() {
        updateMapper.update();
    }

    @Scheduled(cron = "0 01 00 * * ?")
    public void update(){
        updateMapper.update();
    }
}
