package com.example.demo.enity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 定时任务实体类·
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("task")
public class TaskEnity {
    private static final long serialVersionUID = 1L;

    /*** 任务id*/
    @TableId
    private Long id;

    /*** spring bean名称*/
    @NotBlank(message = "bean名称不能为空")
    private String jobClassName;

    /*** 方法名*/
    @NotBlank(message = "方法名称不能为空")
    private String ExecuteMethodName;

    /*** 参数*/
    private String jobParams;

    /*** job名称*/
    private String jobName;

    /*** job所属组*/
    private String jobGroup;

    /*** cron表达式*/
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    /*** 任务状态*/
    private Integer jobStatus;

    /*** 备注*/
    private String jobDescription;

    /*** 是否删除*/
    @TableLogic
    private Integer isDelete;

    /*** 创建时间*/
    private Date createTime;

    /*** 更新时间*/
    private Date updateTime;
}
