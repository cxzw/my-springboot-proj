package com.xi.proj.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class ProjectRecieve {
  private Integer id;
  private String projname;
  private String projdate;
  private MultipartFile image;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getProjname() {
    return projname;
  }

  public void setProjname(String projname) {
    this.projname = projname;
  }

  public String getProjdate() {
    return projdate;
  }

  public void setProjdate(String projdate) {
    this.projdate = projdate;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  public ProjectRecieve(){}
  public ProjectRecieve(String projname, String projdate, MultipartFile image) {
    this.projname = projname;
    this.projdate = projdate;
    this.image = image;
  }
  public ProjectRecieve(Integer id, String projname, String projdate, MultipartFile image) {
    this.id = id;
    this.projname = projname;
    this.projdate = projdate;
    this.image = image;
  }
}
