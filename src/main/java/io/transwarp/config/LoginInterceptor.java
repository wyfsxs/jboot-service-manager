package io.transwarp.config;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import javax.servlet.http.HttpSession;

/**
 * @Function:
 * @Author: create by wyf
 * @Date: 2019/6/7 23:10
 * @Version 1.0
 */
public class LoginInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {

        HttpSession session = invocation.getController().getSession();
        if (session == null) {
            invocation.getController().redirect("/");
        } else {
            String nickname = (String) session.getAttribute("nickname");
            if (nickname != null) {
                System.out.println(nickname);
            } else {
                invocation.getController().redirect("/");
            }
        }
        invocation.invoke();
    }
}