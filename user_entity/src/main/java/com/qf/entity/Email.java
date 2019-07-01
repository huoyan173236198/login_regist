package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 1.0
 * @user 焰
 * @date 2019/6/29 11:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {
    private String url;
    private String email_name;
    private Integer code;
}
