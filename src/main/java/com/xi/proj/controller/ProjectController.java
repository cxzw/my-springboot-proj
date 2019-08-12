package com.xi.proj.controller;


import com.xi.proj.entity.JsonResponse;
import com.xi.proj.entity.ProjectEntity;
import com.xi.proj.entity.ProjectRecieve;
import com.xi.proj.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProjectController {
  @Autowired
  private ProjectRepository projectRepository;

  @GetMapping("/creatproject")
  public String creatproject(){
    return "creatproject";
  }

  @PostMapping("/addProject")
  @ResponseBody
  public JsonResponse addProject(ProjectRecieve projectRecieve){

    if (projectRecieve.getProjname() == null){
      return new JsonResponse("ADD_FAIL","名称添加失败!",false,"888");
    }
    if (projectRecieve.getProjdate() == null) {
      return new JsonResponse("ADD_FAIL", "日期添加失败!", false, "888");
    }if (projectRecieve.getImage() == null){
      return new JsonResponse("ADD_FAIL", "图片添加失败!", false, "888");
    }{
      ProjectEntity projectEntity = new ProjectEntity();

      //加入名称
      projectEntity.setProjname(projectRecieve.getProjname());

      //处理图片名称

      //获取图片后缀
      String exName = projectRecieve.getImage().getOriginalFilename().substring(projectRecieve.getImage().getOriginalFilename().lastIndexOf("."));
      //给图片重命名
      String fileName = UUID.randomUUID().toString().replaceAll("-", "") + exName;

      StringBuilder realPath = new StringBuilder("D:\\proj\\src\\main\\resources\\static\\img\\");
      StringBuilder urlPath = new StringBuilder("/img/");

      realPath.append(fileName);
      urlPath.append(fileName);

      String filePath = realPath.toString();
      String fileUrlPath = urlPath.toString();
      //保存图片
      File file = new File(filePath);
      if(!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }

      try {
        projectRecieve.getImage().transferTo(file);
      } catch (IllegalStateException | IOException e) {
        e.printStackTrace();
      }

      //加入图片路径
      projectEntity.setProjimg(fileUrlPath);

      //字符串转date
      String sdate = projectRecieve.getProjdate();
      Date date = new Date();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
        date = simpleDateFormat.parse(sdate);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      //加入时间
      projectEntity.setProjdate(date);
      //储存
      projectRepository.save(projectEntity);
      return new JsonResponse("ADD_SUCCESS","添加成功!",true,"999");
    }
  }

  @PostMapping("/showProject")
  @ResponseBody
  public List<ProjectEntity> showProject(){
    List<ProjectEntity> projectEntityList = projectRepository.findAll();

    return projectEntityList;
  }

  @PostMapping("/deleteProject")
  @ResponseBody
  public boolean deleteProject(HttpServletRequest request){
    String id = request.getParameter("id");
    int projId = Integer.valueOf(id);
    Boolean isexist = projectRepository.findById(projId).isPresent();

    if (isexist == true){
      projectRepository.deleteById(projId);
    }else {
      return false;
    }
    return true;
  }

  @GetMapping("/updateproject")
  public String updateProject(){
    return "updateproject";
  }

  @PostMapping("/updataProject")
  @ResponseBody
  public JsonResponse updataProject(ProjectRecieve projectRecieve){

    int id = projectRecieve.getId();
    String sid = projectRecieve.getId().toString();
    String projname = projectRecieve.getProjname();

    //Timestamp projdate = Timestamp.valueOf(sprojdate);

    if (projname == ""){
      return new JsonResponse("UPDATA_FAIL","修改失败!用户名有误!",false,"111");
    }
    Optional<ProjectEntity> projectEntityOptional  = projectRepository.findById(id);
    if(!projectEntityOptional.isPresent()){
      return new JsonResponse("UPDATA_FAIL","修改失败!id不存在!",false,"111");
    }
    ProjectEntity projectEntity = new ProjectEntity();
    //加入id
    projectEntity.setId(projectRecieve.getId());
    //加入名称
    projectEntity.setProjname(projectRecieve.getProjname());
    //加入日期
    String sdate = projectRecieve.getProjdate();
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      date = simpleDateFormat.parse(sdate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    projectEntity.setProjdate(date);

    //处理图片名称

    //获取图片后缀
    String exName = projectRecieve.getImage().getOriginalFilename().substring(projectRecieve.getImage().getOriginalFilename().lastIndexOf("."));
    //给图片重命名
    String fileName = sid.replaceAll("-", "") + exName;

    StringBuilder realPath = new StringBuilder("D:\\proj\\src\\main\\resources\\static\\img\\");
    StringBuilder urlPath = new StringBuilder("/img/");

    realPath.append(fileName);
    urlPath.append(fileName);

    String filePath = realPath.toString();
    String fileUrlPath = urlPath.toString();
    //保存图片
    File file = new File(filePath);
    if(!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
    try {
      projectRecieve.getImage().transferTo(file);
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
    }

    //加入图片路径
    projectEntity.setProjimg(fileUrlPath);

    projectRepository.save(projectEntity);
    return new JsonResponse("UPDATA_SUCCESS","修改成功!",true,"222");

    // 验证数据

    // 更新项目
  }

  @PostMapping("/updateshowProject")
  @ResponseBody
  public ProjectEntity updateshowProject(Integer id){
    Optional<ProjectEntity> projectEntityOptional = projectRepository.findById(id);
    ProjectEntity projectEntity = projectEntityOptional.get();

    return projectEntity;
  }


/*
  @GetMapping("/img")
  public String index() {
    return "img";
  }

  @RequestMapping("uploadimg")
  @ResponseBody
  public Object saveImage(MultipartFile image,HttpServletRequest request) {
    String id = request.getParameter("id");

    if(null == image) {
      return "false";
    }

    //获取图片后缀
    String exName = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
    //给图片重命名
    String fileName = id.replaceAll("-", "") + exName;

    StringBuilder realPath = new StringBuilder("D:\\proj\\src\\main\\resources\\static\\img\\");
    StringBuilder urlPath = new StringBuilder("/img/");

    realPath.append(fileName);
    urlPath.append(fileName);

    String filePath = realPath.toString();
    String fileUrlPath = urlPath.toString();
    //保存图片
    File file = new File(filePath);
    if(!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }

    try {
      image.transferTo(file);
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
      return "false";
    }
    return "yes";
  }*/

}

