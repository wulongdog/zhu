package com.zhu.zhupro.vo.invo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data   // 生成get和set
@AllArgsConstructor    // 生成全参构造
@NoArgsConstructor     // 生成空构造方法
@ToString              // 生成toString方法
public class UserRegisterVo {

    private String email;
    private String password;
    private Boolean gender;
    private String captcha;
}
