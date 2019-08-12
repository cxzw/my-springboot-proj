package com.xi.proj.entity;




import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity(name="project")
public class ProjectEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "projname")
  private String projname;

  @Column(name = "projdate")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date projdate;

  @Column(name = "projimg")
  private String projimg;



  public ProjectEntity(){}

  //新建
  public ProjectEntity(String projname, Date projdate, String projimg) {
    this.projname = projname;
    this.projdate = projdate;
    this.projimg = projimg;
  }

  //修改
  public ProjectEntity(Integer id,String projname, Date projdate, String projimg) {
    this.id = id;
    this.projname = projname;
    this.projdate = projdate;
    this.projimg = projimg;
  }

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

  public Date getProjdate() {
    return projdate;
  }

  public void setProjdate(Date projdate) {
    this.projdate = projdate;
  }

  public String getProjimg() {
    return projimg;
  }

  public void setProjimg(String projimg) {
    this.projimg = projimg;
  }

}
