package com.xingzhe.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.xingzhe.common.dao.redis.UserLoginCache;
import com.xingzhe.common.domain.User;
import com.xingzhe.common.service.UserService;
import com.xingzhe.framework.util.CookieUtil;
import com.xingzhe.framework.util.MD5Util;
import com.xingzhe.framework.util.UuidUtil;

/**
 * 登录退出模块
 *
 * @author LuTang
 */
@Controller
@RequestMapping(value = "/common")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserLoginCache userLoginCache;
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/login.html" , method = RequestMethod.POST)
    public Object login(HttpServletResponse response, HttpServletRequest request,RedirectAttributes attr) {
        // 获取所需要的参数
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String plantFrom = request.getParameter("plantFrom");

        // 对参数的验证
        if (StringUtils.isBlank(plantFrom)) {
            plantFrom = "WEB_FROM";
        }

        // 将所有字符转成小写
        userName = userName.toLowerCase();
        //System.out.println(MD5Util.getInstance().md5s((userName + password).getBytes()));
        // 获取用户
        List<User> list = userService.getUserByName(userName);
        if (list != null && list.size() != 0) {
            password = MD5Util.getInstance().md5s((userName + password).getBytes());
            log.debug(password);
            // 对用户名和密码的验证 验证通过加入到cookie中
            if (list.get(0).getPassword().equals(password)) {
                CookieUtil.getInstance().addCookie(response, "userName", userName);
                CookieUtil.getInstance().addCookie(response, "platFrom", plantFrom);
                String isSystem = null;
                if (list.get(0).getIsSystem() == 0) {
                    isSystem = "IS_SYSTEM";
                } else {
                    isSystem = "NO_SYSTEM";
                }
                CookieUtil.getInstance().addCookie(response, "isSystem", isSystem);
                String uuid = UuidUtil.getUUid();
                CookieUtil.getInstance().addCookie(response, "uuid", uuid);
                String acessToken = userLoginCache.putAcessToken(userName, plantFrom, uuid);
                CookieUtil.getInstance().addCookie(response, "acessToken", acessToken);
                //重定向  详情 http://blog.sina.com.cn/s/blog_9cd9dc7101016abw.html
                attr.addFlashAttribute("userName", userName);
                return new ModelAndView(new RedirectView("../index.html"));
            } else {
                return "resourse/jsp/common/login";
            }
        } else {
            return "resourse/jsp/common/login";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/logout.html")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        String userName = CookieUtil.getInstance().getCookieValueByName(request, "userName");
        delToKenAndCooike(response, request, userName);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/update/password.html")
    public String update(HttpServletResponse response, HttpServletRequest request) {

        String userName = CookieUtil.getInstance().getCookieValueByName(request, "userName");
        String password = request.getParameter("password");
        if (StringUtils.isNotBlank(password)) {
            password = MD5Util.getInstance().md5s((userName + password).getBytes());
            boolean b = userService.updatePassword(password, userName);
            if (b) {
                delToKenAndCooike(response, request, userName);

                return "OK";
            } else {
                return "ERROR";
            }
        } else {
            return "ERROR";
        }
    }

    private void delToKenAndCooike(HttpServletResponse response, HttpServletRequest request, String userName) {
        String platFrom = CookieUtil.getInstance().getCookieValueByName(request, "platFrom");
        // 将Cookie的值删除
        CookieUtil.getInstance().delCookie(response, "acessToken");
        CookieUtil.getInstance().delCookie(response, "platFrom");
        CookieUtil.getInstance().delCookie(response, "userName");
        CookieUtil.getInstance().delCookie(response, "uuid");
        CookieUtil.getInstance().delCookie(response, "isSystem");

        // 删除缓存
        if (!StringUtils.isBlank(userName)) {
            if (StringUtils.isBlank(platFrom)) {
                platFrom = "WEB_FROM";
            }
            userLoginCache.delAcessToken(userName, platFrom);
        }
	}

}
