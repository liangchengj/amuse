package com.meyoustu.amuse.tomcat;

import com.meyoustu.amuse.tomcat.filt.AntiTheftChainFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/22 14:14
 */
@WebServlet("/main")
@WebFilter("/*")
public class Main
        extends HttpServlet
        implements AntiTheftChainFilter {

    private static final String HTML = "<!--@author Liangcheng Juves-->" +
            "<!--Created at 2020/05/05 00:54-->" +
            "<!Doctype html><html lang=\"en\">" +
            "<head>" +
            "<meta charset=\"UTF-8\">" +
            "<meta name=\"viewport\"content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">" +
            "<meta http-equiv=\"X-UA-Compatible\"content=\"ie=edge\">" +
            "<title>2020/5/5 0:56</title>" +
            "</head>" +
            "<style>*{margin:0px;padding:0px;font-family:\"SF Pro SC\",\"SF Pro Display\",\"SF Pro Icons\",\"PingFang SC\",\"Helvetica Neue\",\"Helvetica\",\"Arial\",sans-serif}body h1{position:absolute;left:50%;top:50%;font-size:100px;transform:translate(-50%,-50%)}</style></head><body><h1>Catalina</h1></body></html>";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        OutputStream os = resp.getOutputStream();
        os.write(HTML.getBytes());
        os.flush();
        os.close();
    }
}
