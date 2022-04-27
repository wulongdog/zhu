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

@TableName("t_issue_base")   // 说明实体表名
@Data   // 生成get和set
@AllArgsConstructor    // 生成全参构造
@NoArgsConstructor     // 生成空构造方法
@ToString
public class IssueBase {

  //
  @TableId(type = IdType.AUTO)
  @TableField("id")
  private long id;
  //提出问题的用户id
  private long userId;
  //提出问题时间
  private Date createdTime;
  // 最后编辑的时间
  private Date lastEditTime;
  //浏览次数
  private long browCount;
  //文本id
  private long contentId;
  //点赞数量
  private long likeCount;
  //关注数量
  private long subscriptionCount;
  //评论数量
  private long commentCount;
  //热度
  private long hot;
}
