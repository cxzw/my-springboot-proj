package com.xi.proj.entity;

public class JsonProject {
  private int id;
  private String projname;

  public JsonProject(int id, String projname) {
    this.id = id;
    this.projname = projname;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProjname() {
    return projname;
  }

  public void setProjname(String projname) {
    this.projname = projname;
  }
}
