package io.transwarp.service;

import com.jfinal.aop.Singleton;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.aop.annotation.Bean;
import io.jboot.service.JbootServiceBase;
import io.transwarp.domain.TableBean;
import io.transwarp.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Function: 查询数据库操作
 * @Author: create by wyf
 * @Date: 2019/6/5 23:01
 * @Version 1.0
 */
@Bean
@Singleton(value = false)
public class CURDService extends JbootServiceBase<User> {

    //通过用户名查询用户
    public List<Record> findByUserName(String username) {
        List<Record> records = Db.find("select sys_no as id, sys_name as sysname, user_name as username,user_pass as password from dataproviderservice.petro_server_sys_orc_tmp where user_name = '" + username + "'");
        return records;
    }

    //增加用户
    public boolean insertUser(User user) {
        String sql = "insert into dataproviderservice.petro_server_sys_orc_tmp(sys_no,sys_name,user_name,user_pass) values('" + user.getId() + "','" + user.getSysname() + "','" + user.getUsername() + "','" + user.getPassword() + "')";
        int update = Db.update(sql);
        if (update == 1) {
            return true;
        }
        return false;
    }

    public Page<User> paginate(int page, int size) {
        return DAO.paginate(page, size);
    }

    //查询所有用户
    public List<Record> findAllUser(String username) {

        String sql = "select sys_no as id, sys_name as sysname, user_name as username,user_pass as password from dataproviderservice.petro_server_sys_orc_tmp where 1 = 1 ";
        if (StringUtils.isNotBlank(username)) {
            sql += " and user_name like '%" + username + "%'";
        }
        return Db.find(sql);
    }

    //删除用户信息
    public boolean deleteUser(String id) {

        String sql = " DELETE FROM dataproviderservice.petro_server_sys_orc_tmp where sys_no = '" + id + "'";

        int delete = Db.delete(sql);

        if (delete == 1) {
            return true;
        }
        return false;
    }

    //更新用户
    public boolean updateUser(User user) {

        String sql = "update dataproviderservice.petro_server_sys_orc_tmp set ";

        if (StringUtils.isNotEmpty(user.getUsername())) {
            sql += " user_name = '" + user.getUsername() + "',";
        }
        if (StringUtils.isNotEmpty(user.getPassword())) {
            sql += " user_pass = '" + user.getPassword() + "',";
        }
        if (StringUtils.isNotEmpty(user.getSysname())) {
            sql += " sys_name = '" + user.getSysname() + "'";
        }
        if (StringUtils.isNotEmpty(user.getId())) {
            sql += " where sys_no = '" + user.getId() + "'";
        }
        int update = Db.update(sql);
        if (update == 1) {
            return true;
        }
        return false;
    }

    //根据用户名和用户查询用户表
    public List<Record> findTablesByUser(String username, String tableName) {

        String sql = "SELECT\n" +
                "\tt1.sys_no AS id,\n" +
                "\tt1.sys_name AS sysname,\n" +
                "\tt1.user_name AS username,\n" +
                "\tt1.user_pass AS password,\n" +
                "\tt2.table_no AS tableno,\n" +
                "\tt2.table_name AS tablename,\n" +
                "\tt2.table_desc AS tabledesc,\n" +
                "\tt2.class_name AS classname,\n" +
                "\tt2.driverclassname AS driverclassname,\n" +
                "\tt2.jdbcurl AS jdbcurl,\n" +
                "\tt2.user_name AS jdbcname,\n" +
                "\tt2.user_pass AS jdbcpass \n" +
                "FROM\n" +
                "\tdataproviderservice.petro_server_sys_orc_tmp t1\n" +
                "\tLEFT JOIN dataproviderservice.petro_server_table_orc_tmp t2 ON t1.sys_no = t2.sys_no \n" +
                "WHERE\n" +
                "\tt1.user_name = '" + username + "'";

        if (StringUtils.isNotEmpty(tableName)) {
            sql += " and t2.table_name like '%" + tableName + "%'";
        }
        return Db.find(sql);
    }

    //插入table的配置信息
    public boolean insertTable(TableBean tableBean) {

        String sql = "INSERT INTO DATAPROVIDERSERVICE.PETRO_SERVER_TABLE_ORC_TMP\n" +
                "  (SYS_NO,\n" +
                "   TABLE_NO,\n" +
                "   TABLE_NAME,\n" +
                "   TABLE_DESC,\n" +
                "   CLASS_NAME,\n" +
                "   DRIVERCLASSNAME,\n" +
                "   JDBCURL,\n" +
                "   USER_NAME,\n" +
                "   USER_PASS)\n" +
                "VALUES\n" +
                "  ('" + tableBean.getUserid() + "'," +
                "  '" + tableBean.getTableno() + "'," +
                "  '" + tableBean.getTablename() + "'," +
                "  '" + tableBean.getTabledesc() + "'," +
                "  '" + tableBean.getClassname() + "'," +
                "  '" + tableBean.getDriverclassname() + "'," +
                "  '" + tableBean.getJdbcurl() + "'," +
                "  '" + tableBean.getJdbcname() + "'," +
                "   '" + tableBean.getJdbcpass() + "')";
        int update = Db.update(sql);

        if (update == 1) {
            return true;
        }
        return false;
    }

    //更新table的配置信息
    public boolean updateTable(TableBean tableBean) {

        String sql = "update DATAPROVIDERSERVICE.PETRO_SERVER_TABLE_ORC_TMP set ";

        if (StringUtils.isNotEmpty(tableBean.getTablename())) {
            sql += " TABLE_NAME = '" + tableBean.getTablename() + "',";
        }
        if (StringUtils.isNotEmpty(tableBean.getTabledesc())) {
            sql += " TABLE_DESC = '" + tableBean.getTabledesc() + "',";
        }
        if (StringUtils.isNotEmpty(tableBean.getClassname())) {
            sql += " CLASS_NAME = '" + tableBean.getClassname() + "',";
        }
        if (StringUtils.isNotEmpty(tableBean.getDriverclassname())) {
            sql += " DRIVERCLASSNAME = '" + tableBean.getDriverclassname() + "',";
        }
        if (StringUtils.isNotEmpty(tableBean.getJdbcurl())) {
            sql += " JDBCURL = '" + tableBean.getJdbcurl() + "',";
        }
        if (StringUtils.isNotEmpty(tableBean.getJdbcname())) {
            sql += " USER_NAME = '" + tableBean.getJdbcname() + "',";
        }
        if (StringUtils.isNotEmpty(tableBean.getJdbcpass())) {
            sql += " USER_PASS = '" + tableBean.getJdbcpass() + "'";
        }
        if (StringUtils.isNotEmpty(tableBean.getUserid())) {
            sql += " where sys_no = '" + tableBean.getUserid() + "'";
        }
        if (StringUtils.isNotEmpty(tableBean.getTableno())) {
            sql += " and TABLE_NO = '" + tableBean.getTableno() + "'";
        }

        int update = Db.update(sql);

        if (update == 1) {
            return true;
        }
        return false;
    }

    //删除表配置信息
    public boolean deleteTable(String userid, String tableNo) {

        String sql = " DELETE FROM DATAPROVIDERSERVICE.PETRO_SERVER_TABLE_ORC_TMP where sys_no = '" + userid + "' and table_no = '" + tableNo + "'";

        int delete = Db.delete(sql);

        if (delete == 1) {
            return true;
        }
        return false;
    }

    public List<Record> findTableById(String userid, String tableNo) {

        String sql = "SELECT\n" +
                "\tt2.sys_no AS userid,\n" +
                "\tt2.table_no AS tableno,\n" +
                "\tt2.table_name AS tablename,\n" +
                "\tt2.table_desc AS tabledesc,\n" +
                "\tt2.class_name AS classname,\n" +
                "\tt2.driverclassname AS driverclassname,\n" +
                "\tt2.jdbcurl AS jdbcurl,\n" +
                "\tt2.user_name AS jdbcname,\n" +
                "\tt2.user_pass AS jdbcpass \n" +
                "FROM\n" +
                "\tDATAPROVIDERSERVICE.PETRO_SERVER_TABLE_ORC_TMP t2 \n" +
                "WHERE\n" +
                "\tsys_no = '" + userid + "' \n" +
                "\tAND TABLE_NO = '" + tableNo + "'";
        return Db.find(sql);
    }
}
