package com.xi.proj.controller;

import com.xi.proj.repository.UserRepository;
import com.xi.proj.entity.JsonResponse;
import com.xi.proj.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("")
public class IndexController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/*")
  public String login() {
    return "login";
  }

  //index页面
  @GetMapping("/index")
  public String index() {
    return "index";
  }

  //注册页面
  @GetMapping("/register")
  public String register(){
    return "register";
  }

  //登录页面
  @GetMapping("/success")
  public String success(HttpServletResponse response, HttpSession session){
/*    // 判断session是否存在
    if(存在){
      显示success页面
    }else{
      跳转到login页面
    }*/
    /*Object obj = session.getAttribute("user");
    try {
      if(obj == null){
        //没有登录
        response.sendRedirect("index");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    return "success";
  }

  //注册方法
  @PostMapping("/addregister")
  public String register(HttpServletRequest request){
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String password2 = request.getParameter("password2");
    if (password.equals(password2)){
      UserEntity userEntity = new UserEntity(username,password);
      userRepository.save(userEntity);
      return "index";
    }else {
      return "register";
    }
  }

  //登录方法
  @PostMapping("/addlogin")
  @ResponseBody
  public JsonResponse login(HttpServletRequest request, HttpSession session){
    //session.setAttribute("user",111);

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    UserEntity userEntity = userRepository.findByUsernameAndPassword(username,password);
    UserEntity userEntity1 = userRepository.findByUsername(username);

    if(userEntity1 == null){
      System.out.println("账号错误!");
      return new JsonResponse("USER_NAME_FAIL","用户名错误!",false,"777");

    }else if(userEntity == null){
      System.out.println("密码错误!");
      return new JsonResponse("USER_PASS_FAIL","密码错误!",false,"666");
    }else {
      return new JsonResponse("USER_LOGIN_SUCCESS","登陆成功",true,"200");
    }
  }


}

