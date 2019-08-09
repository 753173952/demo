package com.example.demo.enity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@TableName(value = "user")
@Validated
public class UserEnity extends Model<UserEnity> {
    /*** 用户Id*/
    @TableId
    private Long id;
    /*** 用户名*/
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /*** 用户密码*/
    @NotBlank(message = "用户密码不能为空")
    private String userPassword;
    /*** 用户年龄*/
    private Integer age;
    /*** 用户邮箱*/
    private String email;
    /*** 是否删除 0正常 1删除*/
    @TableLogic
    private Integer isDelete;
    /*** 版本号(主要是用作mybatis中的乐观锁使用)*/
    @Version
    private Integer version;
}
