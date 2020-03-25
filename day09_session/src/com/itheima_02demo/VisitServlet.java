package com.itheima_02demo;

import com.itheima_01cookie.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@WebServlet(urlPatterns = "/visit")
public class VisitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //本次的访问时间
        Cookie cookie = new Cookie("visitTime", System.currentTimeMillis() + "");
        cookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie);

        response.setContentType("text/html;charset=utf-8");
        String visitTime = CookieUtils.getCookieValue(request, "visitTime");
        if (visitTime == null || "".equals(visitTime)) {
            response.getWriter().print("你是第一次访问");
        } else {
            //visitTime是上次的访问时间,是毫秒值.要显示到浏览器页面上的是:xxxx年xx月xx日 xx:xx:xx.
            //1.将visitTime毫秒值转换成Date对象
            Date date = new Date(Long.getLong(visitTime));
            //2.格式化Date对象,转换成yyyy-MM-dd-HH:mm:ss格式的字符串
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            response.getWriter().print("你上次的访问时间是:" + format.format(date));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}