package io.transwarp.domain;

import io.jboot.db.model.JbootModel;

import java.io.Serializable;

/**
 * @Function: 用户实体
 * @Author: create by wyf
 * @Date: 2019/6/5 23:55
 * @Version 1.0
 */
public class User extends JbootModel<User> implements Serializable {

    private String id;
    private String sysname;
    private String username;
    private String password;


    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
