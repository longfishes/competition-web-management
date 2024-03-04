package com.longfish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String password;
    private String organization;
    private String regisTime;
    private String updateTime;
    private Integer copId;
    private Integer type;
    private String jwt;
    private String signature;
    private String newPassword;

}
