package com.xi.proj.entity;

public class JsonResponse {
  private String data;
  private String message;
  private boolean status;
  private String statusCode;

  public JsonResponse(String data, String message, boolean status, String statusCode) {
    this.data = data;
    this.message = message;
    this.status = status;
    this.statusCode = statusCode;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }
}
