package self.sunng.springboot.springmvc.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import self.sunng.springboot.springmvc.common.Constant;
import self.sunng.springboot.springmvc.common.TokenManager;
import self.sunng.springboot.springmvc.common.annotations.Authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @SuppressWarnings("Duplicates")
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果方法注明了Authorization，但是验证token失败，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            String token = request.getParameter(Constant.TOKEN_KEY);
            if (TokenManager.isValid(token)) {
                TokenManager.refresh(token);
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }
}
