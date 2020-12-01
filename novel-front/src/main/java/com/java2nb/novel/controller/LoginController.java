package com.java2nb.novel.controller;

import com.java2nb.novel.core.utils.MD5Util;
import com.java2nb.novel.core.utils.RandomValidateCodeUtil;
import com.java2nb.novel.service.UserService;
import com.java2nb.novel.vo.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author shaotian
 * @create 2020-11-29 10:57
 */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/config")
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    String login() {
        return "user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password, String verify, HttpServletRequest request, HttpSession session) {

        try {
            //从session中获取随机数
            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOM_CODE_KEY);
            if (StringUtils.isBlank(verify)) {
                return R.error("请输入验证码");
            }
            if (random.equals(verify)) {
            } else {
                return R.error("请输入正确的验证码");
            }

            boolean user = userService.selectUser(username, MD5Util.MD5Encode(password, Charsets.UTF_8.name()));

            if (!user) {
                return R.error("请输入正确的用户密码");
            }

        } catch (Exception e) {
            return R.error("验证码校验失败");
        }

        session.setAttribute("username", username);
        session.setMaxInactiveInterval(24 * 60 * 60);
        return R.ok();

    }

    /**
     * 生成验证码
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        //输出验证码图片方法
        randomValidateCode.getRandcode(request, response);
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.MD5Encode("ww564654", Charsets.UTF_8.name()));
    }

}
