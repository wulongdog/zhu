package com.zhu.zhupro.vo.invo;

import com.zhu.zhupro.pojo.UserBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data   // 生成get和set
@AllArgsConstructor    // 生成全参构造
@NoArgsConstructor     // 生成空构造方法
@ToString              // 生成toString方法
public class UserBaseVo {
    //id
    private long id;
    // 昵称
    private String name;
    // 生日
    private Date birthday;
    // 地址
    private String address;
    // 电话
    private String phone;
    // 简介
    private String intro;
    // 兴趣
    private String interests;
    // 性别
    private Boolean gender;

    public UserBase getUserBase(){
        UserBase userBase = new UserBase();
        userBase.setId(id);
        userBase.setName(name);
        userBase.setBirthday(birthday);
        userBase.setAddress(address);
        userBase.setPhone(phone);
        userBase.setIntro(intro);
        userBase.setInterests(interests);
        userBase.setGender(gender);
        return userBase;
    }
}
