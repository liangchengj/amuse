package com.meyoustu.amuse.tomcat.filt;

import com.meyoustu.amuse.gous.util.DateFormatter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/22 14:16
 */
public interface AntiTheftChainFilter extends Filter {


    @Override
    default void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String reqUri = ((HttpServletRequest) servletRequest).getRequestURI();

        String[] protectSuffixes = {
                ".js", ".css", ".xml",
                ".json", ".ico", ".png",
                ".jpg", ".svg", ".jpeg",
                ".txt", ".vue", ".apk",
                ".exe", ".jdk", ".xz",
                ".html", ".htm", ".xls",
                ".xtd", ".java", ".class",
                ".cpp", ".c", ".go", ".dart",
                ".py"
        };

        int index = 0;
        for (String suffix : protectSuffixes) {
            index++;
            if (reqUri.endsWith(suffix) ||
                    reqUri.endsWith(suffix + "/") ||
                    (reqUri.contains(suffix) && reqUri.contains("?"))) {
                String headerName = "Referer";
                String referer = ((HttpServletRequest) servletRequest).getHeader(headerName);
                referer = referer == null ?
                        ((HttpServletRequest) servletRequest).getHeader(headerName.toLowerCase()) :
                        referer;

                if (referer != null) {
                    if (((HttpServletRequest) servletRequest).getRequestURL().toString().startsWith(referer)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        jsResp(servletResponse);
                    }
                } else {
                    jsResp(servletResponse);
                }

            } else if (index == protectSuffixes.length) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }


    }


    private void jsResp(ServletResponse servletResponse)
            throws IOException {
        String js = "<!-- @author Liangcheng Juves -->" +
                "<!-- Created at " + DateFormatter.format("yyyy/MM/dd HH:mm") + " -->" +
                "<script type=\"application/javascript\">" +
                "window.onload=function(){window.history.back();" +
                "alert('Sorry, you don\\'t have permission to do this!')}</script>";

        servletResponse.setContentType("text/html;charset=utf-8");
        OutputStream os = servletResponse.getOutputStream();
        os.write(js.getBytes());
        os.flush();
        os.close();
    }
}
