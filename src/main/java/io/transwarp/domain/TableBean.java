package io.transwarp.domain;

import java.io.Serializable;

/**
 * @Function: 库表配置信息
 * @Author: create by wyf
 * @Date: 2019/6/8 15:21
 * @Version 1.0
 */
public class TableBean implements Serializable {

    private String userid;
    private String tableno;
    private String tablename;
    private String tabledesc;
    private String classname;
    private String driverclassname;
    private String jdbcurl;
    private String jdbcname;
    private String jdbcpass;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTableno() {
        return tableno;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getTabledesc() {
        return tabledesc;
    }

    public void setTabledesc(String tabledesc) {
        this.tabledesc = tabledesc;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDriverclassname() {
        return driverclassname;
    }

    public void setDriverclassname(String driverclassname) {
        this.driverclassname = driverclassname;
    }

    public String getJdbcurl() {
        return jdbcurl;
    }

    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }

    public String getJdbcname() {
        return jdbcname;
    }

    public void setJdbcname(String jdbcname) {
        this.jdbcname = jdbcname;
    }

    public String getJdbcpass() {
        return jdbcpass;
    }

    public void setJdbcpass(String jdbcpass) {
        this.jdbcpass = jdbcpass;
    }

    @Override
    public String toString() {
        return "TableBean{" +
                "userid='" + userid + '\'' +
                ", tableno='" + tableno + '\'' +
                ", tablename='" + tablename + '\'' +
                ", tabledesc='" + tabledesc + '\'' +
                ", classname='" + classname + '\'' +
                ", driverclassname='" + driverclassname + '\'' +
                ", jdbcurl='" + jdbcurl + '\'' +
                ", jdbcname='" + jdbcname + '\'' +
                ", jdbcpass='" + jdbcpass + '\'' +
                '}';
    }
}
