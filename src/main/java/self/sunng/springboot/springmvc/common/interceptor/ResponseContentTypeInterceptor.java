package self.sunng.springboot.springmvc.common.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunxiaodong on 16/7/30.
 */
public class ResponseContentTypeInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        return true;
    }
}
