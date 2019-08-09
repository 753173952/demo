package com.example.demo.common.interceptors;

/**
 * 用户Token认证
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/8
 */
/*@Slf4j
public class UserTokenInterceptor implements HandlerInterceptor {

    private UserService userService = new UserServiceImpl();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("无token信息，请重新登录！");
        }

        //验证token是否有效
        DecodedJWT decodedJWT = JWTUtils.verifyUserToken(token);
        //验证token是否过期
        if (decodedJWT.getExpiresAt().getTime() < System.currentTimeMillis()) {
            throw new RuntimeException("token信息过期，请重新登录！");
        }

        //验证用户信息是否存在
        String userId = decodedJWT.getClaim("id").asString();
        if (StringUtils.isNotBlank(userId)) {
            throw new RuntimeException("用户信息不存在，请重新登录！");
        }
        UserEnity user = userService.getById(userId);
        if (user == null) {
            throw new RuntimeException("用户信息不存在，请重新登录！");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}*/
