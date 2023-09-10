package com.jsfcompref;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Login {
  String userid;
  String password;
  public Login() { }
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  // Action Method
  public String loginAction() {
      if (userid.equals("guest") && password.equals("welcome")) {
          return "success";
      } else {
          return "failure";
      }
  }
}
