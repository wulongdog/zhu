package com.zhu.zhupro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data   // 生成get和set
@AllArgsConstructor    // 生成全参构造
@NoArgsConstructor     // 生成空构造方法
@ToString
public class UserDto {

    private long id;
    // 昵称
    private String name;
    // 地址
    private String address;
    // 简介
    private String intro;
    // 性别
    private Boolean gender;
    // 头像
    private String photo;
    // 我是否关注（0 ：是  1：否）
    private long following;
    // 背景
    private String background;
}
