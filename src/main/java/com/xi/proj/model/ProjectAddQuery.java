package com.xi.proj.model;

import java.util.Date;

public class ProjectAddQuery {
  private String name;
  private Date startTime;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }
}
