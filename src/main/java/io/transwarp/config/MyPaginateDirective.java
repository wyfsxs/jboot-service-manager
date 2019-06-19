package io.transwarp.config;

import io.jboot.web.directive.JbootPaginateDirective;
import io.jboot.web.directive.annotation.JFinalDirective;

/**
 * @Function:
 * @Author: create by wyf
 * @Date: 2019/6/7 18:29
 * @Version 1.0
 */
@JFinalDirective("myPaginate")
public class MyPaginateDirective extends JbootPaginateDirective {

    protected String getPageAttrName() {
        return "pageData"; //这个值要和Controller里setAttr的第一个参数值相同
    }
}
