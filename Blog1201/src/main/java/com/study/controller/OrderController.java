package com.study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.model.MemberrVO;
import com.study.model.OrderDTO;
import com.study.model.OrderPageDTO;
import com.study.service.MemberrService;
import com.study.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberrService memberrService;

	@GetMapping("/order/{memberrId}")
	public String orderPgaeGET(@PathVariable("memberrId") String memberrId, OrderPageDTO opd, Model model) {
		
		model.addAttribute("orderList", orderService.getGoodsInfo(opd.getOrders()));
		model.addAttribute("memberInfo", memberrService.getMemberInfo(memberrId));
		
		return "/order";
	}
	
	@PostMapping("/order")
	public String orderPagePost(OrderDTO od, HttpServletRequest request) {
		
		System.out.println(od+"/order  Start!!!!!!!");		
		System.out.println(od.getOrderId()+"/order  Start!!!!!!!");

		
		
		
		orderService.order(od);
		
		System.out.println("/order  End!!!!!!!");
		
		MemberrVO member = new MemberrVO();
		member.setMemberrId(od.getMemberrId());
		
		HttpSession session = request.getSession();
		
		try {
			MemberrVO memberLogin = memberrService.memberLogin(member);
			memberLogin.setMemberrPw("");
			session.setAttribute("member", memberLogin);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "redirect:/memberr/mains";
	}
}
