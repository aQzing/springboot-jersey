package com.example.form;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/form")
public class FormParam {
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getMulParam(@javax.ws.rs.FormParam("username")String name,
			@javax.ws.rs.FormParam("password")String pw) {
		return "用户名："+name+"    密码："+pw;
	}
}
