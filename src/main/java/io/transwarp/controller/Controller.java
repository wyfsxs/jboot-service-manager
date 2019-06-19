package io.transwarp.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import io.transwarp.domain.PageBean;
import io.transwarp.domain.TableBean;
import io.transwarp.domain.User;
import io.transwarp.service.CURDService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Function: 主视图层，登录，数据展示
 * @Author: create by wyf
 * @Date: 2019/6/4 16:31
 * @Version 1.0
 */
@RequestMapping("/")
public class Controller extends JbootController {

    @Inject
    public CURDService curdService;
    //google cache 缓存机制
    private static Cache<String, User> cache =
            CacheBuilder.newBuilder().maximumSize(10000)
                    .expireAfterWrite(3, TimeUnit.MINUTES).build();

    public void login() {
        render("login.html");
    }

    //系统主页
    public void index() {
        String username = getPara("username");
        String password = getPara("password");
        //简单校验用户名和密码
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            boolean flag = checkUser(username, password);
            if (flag) {
                setAttr("username", username);
                render("index.html");
            } else {
                setAttr("msg", "用户名或密码错误");
                render("login.html");
                return;
            }
        } else {
            render("login.html");
            return;
        }
    }

    //退出登录
    public void logout() {

        render("login.html");
    }

    //查询所有用户信息
    public void findAllUser() {
        String username = getPara("username");
        List<Record> allUser = curdService.findAllUser(username);
        setAttr("userNum", allUser.size());
        findAllByPage(allUser);
        render("admin-list.html");
    }

    public void userEdit() {
        String username = getPara("username");
        String password = getPara("password");
        String sysname = getPara("sysname");
        String id = getPara("userid");
        User user = toUser(id, sysname, username, password);
        setAttr("user", user);
        render("admin-edit.html");
    }

    //跳转编辑页
    public void tableEdit() {
        String userid = getPara("userid");
        String tableNo = getPara("tableno");
        List<Record> tableById = curdService.findTableById(userid, tableNo);
        setAttr("table", tableById.get(0));
        render("table-edit.html");
    }

    //分页查询封装工具类
    private void findAllByPage(List<Record> list) {
        int pageNum = getParaToInt("page", 1);
        if (pageNum < 1) {
            pageNum = 1;
        }
        int pageSize = 5;
        PageBean pageBean = new PageBean(pageNum, pageSize, list.size(), list);
        Page<Record> userPage = new Page<Record>();
        userPage.setList(pageBean.getList());
        userPage.setPageSize(10);
        userPage.setTotalPage(pageBean.getTotalPage());
        if (list.size() > 0) {
            userPage.setTotalRow(list.get(0).getColumnNames().length);
        }
        setAttr("pageData", userPage);
    }

    public void addUser() {
        render("admin-add.html");
    }

    public void addTable() {
        render("table-add.html");
    }


    //插入table配置信息
    public void insertTable() {

        String userid = getPara("userid");
        String tableNo = getPara("tableno");
        String tableName = getPara("tablename");
        String tableDesc = getPara("tabledesc");
        String className = getPara("classname");
        String driverclassname = getPara("driverclassname");
        String jdbcurl = getPara("jdbcurl");
        String jdbcName = getPara("jdbcname");
        String jdbcPass = getPara("jdbcpass");
        TableBean tableBean = toTableBean(userid, tableNo, tableName, tableDesc, className, driverclassname, jdbcurl, jdbcName, jdbcPass);
        boolean flag = curdService.insertTable(tableBean);
        renderJson(flag);

    }

    //更新表配置信息
    public void updateTable() {
        String userid = getPara("userid");
        String tableNo = getPara("tableno");
        String tableName = getPara("tablename");
        String tableDesc = getPara("tabledesc");
        String className = getPara("classname");
        String driverclassname = getPara("driverclassname");
        String jdbcurl = getPara("jdbcurl");
        String jdbcName = getPara("jdbcname");
        String jdbcPass = getPara("jdbcpass");
        TableBean tableBean = toTableBean(userid, tableNo, tableName, tableDesc, className, driverclassname, jdbcurl, jdbcName, jdbcPass);
        boolean flag = curdService.updateTable(tableBean);
        renderJson(flag);
    }

    //删除表配置信息
    public void deleteTable() {
        String userid = getPara("userid");
        String tableNo = getPara("tableno");
        boolean flag = curdService.deleteTable(userid, tableNo);
        renderJson(flag);
    }

    //将输入信息转table实体
    private TableBean toTableBean(String userid, String tableNo, String tableName, String tableDesc, String className, String driverclassname, String jdbcurl, String jdbcName, String jdbcPass) {
        TableBean tableBean = new TableBean();
        tableBean.setUserid(userid);
        tableBean.setTableno(tableNo);
        tableBean.setTablename(tableName);
        tableBean.setTabledesc(tableDesc);
        tableBean.setClassname(className);
        tableBean.setDriverclassname(driverclassname);
        tableBean.setJdbcurl(jdbcurl);
        tableBean.setJdbcname(jdbcName);
        tableBean.setJdbcpass(jdbcPass);
        return tableBean;
    }

    public void findTables() {
        String username = getPara("username");
        String tableName = getPara("tablename");
        List<Record> tablesByUser = curdService.findTablesByUser(username, tableName);
        setAttr("tabNum", tablesByUser.size());
        setAttr("username", username);
        findAllByPage(tablesByUser);
        render("tables-list.html");
    }

    //欢迎页，主要展示用户当前用户的基本信息
    public void welcome() {
        String username = getPara("username");
        List<Record> tablesByUser = curdService.findTablesByUser(username, null);
        setAttr("username", username);
        if (tablesByUser.size() > 0) {
            setAttr("userId", tablesByUser.get(0).getStr("id"));
            setAttr("tableNum", tablesByUser.size());
            setAttr("sysname", tablesByUser.get(0).getStr("sysname"));
        }
        render("welcome.html");
    }

    public void paginate() {
        int page = getParaToInt("page", 1);
        Page<User> userPage = curdService.paginate(page, 10);
        setAttr("pageData", userPage);
        render("admin-list.html");
    }

    //删除用户
    public void deleteUser() {
        String id = getPara("id");
        boolean flag = curdService.deleteUser(id);
        renderJson(flag);
    }

    //更新用户信息
    public void updateUser() {
        String username = getPara("username");
        String password = getPara("password");
        String sysname = getPara("sysname");
        String id = getPara("userid");
        User user = toUser(id, sysname, username, password);
        boolean flag = curdService.updateUser(user);
        renderJson(flag);

    }

    //新增用户
    public void insertUser() {
        String username = getPara("username");
        String password = getPara("password");
        String sysname = getPara("sysname");
        String id = getPara("userid");
        User user = toUser(id, sysname, username, password);
        boolean flag = curdService.insertUser(user);
        renderJson(flag);
    }

    //校验用户名和密码是否正确
    private boolean checkUser(String username, String password) {

        boolean returnFlag = false;
        User userCache = cache.getIfPresent(username);
        if (userCache != null) {
            if (username.equals(userCache.getUsername()) && password.equals(userCache.getPassword())) {
                setAttr("user", userCache);
                returnFlag = true;
            }
        } else {
            List<Record> user = curdService.findByUserName(username);
            if (user.size() == 1) {
                Record record = user.get(0);
                String usernameQuery = record.getStr("username");
                String passwordQuery = record.getStr("password");
                String sysName = record.getStr("sysName");
                String id = record.getStr("id");
                if (username.equals(usernameQuery) && password.equals(passwordQuery)) {
                    //缓存用户信息，防止重复表单提交，频繁查询数据库
                    User userInfo = toUser(id, sysName, username, password);
                    setAttr("user", userInfo);
                    cache.put(username, userInfo);
                    returnFlag = true;
                }
            }
        }

        return returnFlag;
    }

    //将输入转换成user实体
    private User toUser(String id, String sysName, String username, String password) {
        User user = new User();
        user.setId(id);
        user.setSysname(sysName);
        user.setPassword(password);
        user.setUsername(username);
        return user;
    }
}
