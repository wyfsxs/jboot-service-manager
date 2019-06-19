package io.transwarp.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.handler.Handler;
import io.jboot.aop.jfinal.JfinalHandlers;
import io.jboot.core.listener.JbootAppListenerBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Function: 自定义拦截器
 * @Author: create by wyf
 * @Date: 2019/6/6 15:09
 * @Version 1.0
 */
public class JfinalConfigListener extends JbootAppListenerBase {

    @Override
    public void onHandlerConfig(JfinalHandlers handlers) {
        handlers.add(new ContextPathHandler("ctpath"));
        handlers.add(new Handler(){
            @Override
            public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
                //实现.html请求的判断
                int index = target.lastIndexOf(".html");
                if (index != -1)
                    target = target.substring(0, index);
                nextHandler.handle(target, request, response, isHandled);
            }
        });
    }

    @Override
    public void onConstantConfig(Constants constants) {
        constants.setError401View("/template/error.html");
        constants.setError403View("/template/error.html");
        constants.setError404View("/template/error.html");
        constants.setError500View("/template/error.html");
    }

    @Override
    public void onInterceptorConfig(Interceptors interceptors) {
        interceptors.add(new LoginInterceptor());
    }
}
