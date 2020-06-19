package com.vg.demo.controller;

import com.vg.demo.service.OrderService;
import com.vg.mvc.annotation.Controller;
import com.vg.mvc.annotation.Qualifier;
import com.vg.mvc.annotation.RequestMapping;
import com.vg.mvc.annotation.RequestParamter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Qualifier("orderServiceImpl")
	private OrderService orderService;

	@RequestMapping("/m1")
	public void m1(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("I am in m1");
	}

	@RequestMapping("/m2")
	public void m2(@RequestParamter("parm") String reqpram){
		System.out.println("I am in m2," + reqpram);
	}
}
