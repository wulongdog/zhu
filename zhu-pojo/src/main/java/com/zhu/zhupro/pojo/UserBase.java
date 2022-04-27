package com.zhu.zhupro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@TableName("t_user_base")   // 说明实体表名
@Data   // 生成get和set
@AllArgsConstructor    // 生成全参构造
@NoArgsConstructor     // 生成空构造方法
@ToString
public class UserBase {

  @TableId(type = IdType.AUTO)
  @TableField("id")
  private long id;

  // 创建时间
  private Date createdTime;

  // 邮箱
  private String email;
  // 密码
  private String password;
  // 昵称
  private String name;
  // 生日
  private Date birthday;
  // 地址
  private String address;
  // 电话
  private String phone;
  // 头像
  private String photo;
  // 简介
  private String intro;
  // 兴趣
  private String interests;
  // 性别
  private Boolean gender;
  //关注的数量
  private long subscribeCount;
  //粉丝的数量
  private long fansCount;
  //个人空间uri
  private String profileUrl;
  // 是否关注我（0 ：是  1：否）
  private long followMe;
  // 我是否关注（0 ：是  1：否）
  private long following;
  // 背景
  private String background;
  //关注的问题id的向量
  private String subscriptionIssueId;
  //收藏的回答的id的向量
  private String collectionAnswerId;

}
