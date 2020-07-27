package com.vg.demo.controller;

import com.vg.demo.service.UserService;
import com.vg.mvc.annotation.Controller;
import com.vg.mvc.annotation.Qualifier;

@Controller("userRest")
public class UserController {

	@Qualifier("userSer")
	private UserService userService;
	
}
