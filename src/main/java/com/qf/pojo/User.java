package com.qf.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Administrator 2019/7/15 0015 14:32
 */
@Data
public class User {
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "电话不能为空")
    private String phone;
    @NotBlank(message = "验证码不能为空")
    private String registerCode;
    private Date created;
}
