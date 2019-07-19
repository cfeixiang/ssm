package com.qf.controller;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.util.SendSMSUtil;
import com.qf.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.qf.constant.SsmConstant.*;

/**
 * Administrator 2019/7/15 0015 10:28
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SendSMSUtil sendSMSUtil;

    //跳转
    @GetMapping("/register-ui")
    public String registerUI(){
        return "/user/register";
    }

    //1.json数据需要反序列pojo对象
    // jsonlib              比较古老的json工具.        第三方依赖过多.        当POJO类过于复杂时,序列化可能会出现问题.
    // jackson              spring默认使用的工具.      三个依赖.              当POJO类过于复杂时,序列化可能会出现问题.
    // gson                 google的json工具.          一个依赖.
    // jackson和gson和spring framework可以0配置整合.
    // fastJSON             阿里巴巴的json工具.        一个依赖.              当POJO类过于复杂时,序列化可能会出现问题.
    // fastJSON号称自己是最快的json工具.
    // 页面发送json数据时,接收的数据类型 ->   map,pojo类.

    @PostMapping("/check-username")
    @ResponseBody   //不走视图解析器,直接响应  如果返回map或pojo,自动序列化成json
    public ResultVo checkUsername(@RequestBody User user){
        //调用service,判断用户名是否可用
        Integer count=userService.checkUsername(user.getUsername());
        //封装响应数据
       /* Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("msg","成功");
        result.put("data",count);*/
       return new ResultVo(0,"成功",count);
    }
    @PostMapping(value = "/send-sms",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String sendSMS(@RequestParam String phone, HttpSession session){
        //调用工具发短信
        String result = sendSMSUtil.sendSMS(session,phone);
        return result;
    }

    //执行注册
    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes){
        //校验验证码
        String code=(int)session.getAttribute(CODE)+"";
        System.out.println(session);
        if(!user.getRegisterCode().equals(code)){
            redirectAttributes.addAttribute("registerInfo","验证码错误");
            return REDIRECT+REGISTER_UI;
        }
        //检验参数是否合法
        if(bindingResult.hasErrors()){
            String msg=bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addAttribute("registerInfo",msg);
            return REDIRECT+REGISTER_UI;
        }

        Integer count=userService.register(user);

        if(count==1){
            return REDIRECT+LOGIN_UI;
        }else {
            redirectAttributes.addAttribute("registerInfo","服务器爆炸了!!");
            return REDIRECT+REGISTER_UI;
        }
    }
    @GetMapping("/login-ui")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVo login(String username,String password,HttpSession session){
        //校验参数
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return new ResultVo(1,"用户名和密码必填",null);
        }
        //调用service
        User user=userService.login(username,password);

        //
        //如果成功,将信息放入session
        if(user!=null){
            session.setAttribute(USER,user);
            return new ResultVo(0,"成功",null);
        }else {
            //失败
            return new ResultVo(2,"用户名和密码错误",null);
        }
    }
}
