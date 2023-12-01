package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.mapper.AttachMapper;
import com.study.model.AttachImageVO;
import com.study.model.BookVO;
import com.study.model.Criteria;
import com.study.model.PageDTO;
import com.study.service.AdminService;
import com.study.service.AttachService;
import com.study.service.BookService;

@Controller
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
//	@Autowired
//	private AttachMapper attachMapper;
	
	@Autowired
	private AttachService attachService;
	
	//@Autowired
	//private AdminService adminService;
	
	
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/mains", method = RequestMethod.GET)
	public void mainPageGET(Model model) {
	
		
		logger.info("메인 페이지 진입");
		
		model.addAttribute("cate1", bookService.getCateCode1());
		model.addAttribute("cate2", bookService.getCateCode2());
		
	}
	
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		
		logger.info("getImage()....." + fileName);
		File file = new File("c:\\upload\\" + fileName);
		//File file = new File("c:/upload/" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
		
	}
	
	
	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId){
		
		System.out.println( "getAttachList@@@@@@@@@@@@@@@@@@@@" );
		logger.info("getAttachList.........." + bookId);
		
		
		//List<AttachImageVO> vo = attachMapper.getAttachList(bookId);
		//List<AttachImageVO> vo = attachService.getAttachList(bookId);
		
		
		
		
		attachService.getAttach(172);
		
		
		
//		System.out.println( "진입" );
//		List<AttachImageVO> vo= adminService.getAttachInfo(171);
//		System.out.println(vo.get(0).getFileName()+"@@@");
//		System.out.println(vo.get(1).getFileName()+"@@@$$");
//		List<AttachImageVO> vo2=  adminService.getAttachInfo(172);
//		System.out.println(vo2.get(0).getFileName()+"###");
		//AttachImageVO vo = attachService.getAttach(bookId);
		
		
		//logger.info("getAttachList..........good" + vo.getFileName());
		
		//logger.info("getAttachList..........good2" + vo.getUuid());
		
		

			
			
			//System.out.println(  vo.get(0).getFileName()  +"@@@@@@@@@@@@@@@@@@@@##"  );
			//System.out.println(  vo.get(0).getUuid()  +"@@@@@@@@@@@@@@@@@@@@####" );
			
			
	
		
		
		
		return new ResponseEntity<List<AttachImageVO>>(attachService.getAttachList(bookId), HttpStatus.OK);

		
		
		//return new ResponseEntity<List<AttachImageVO>>(adminService.getAttachInfo(bookId), HttpStatus.OK);
		
	}
	
	/* 상품 검색 */
	@GetMapping("/search")//이경로를 잘 맞추어야 나온다.(화면이)
	public String searchGoodsGET(Criteria cri, Model model) {
		
		logger.info("cri : " + cri);
		
		List<BookVO> list = bookService.getGoodsList(cri);
		logger.info("pre list : " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : " + list);
		} else {
			model.addAttribute("listcheck", "empty");
			
			return "search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
		
		model.addAttribute("filter_info", bookService.getCateInfoList(cri));
		
		String[] typeArr = cri.getType().split("");
		
		for(String s : typeArr) {
			if(s.equals("T") || s.equals("A")) {
				model.addAttribute("filter_info", bookService.getCateInfoList(cri));		
			}
		}
		
		return "search";
		
	}
	
	/* 상품 상세 */
	@GetMapping("/goodsDetail/{bookId}")
	public String goodsDetailGET(@PathVariable("bookId")int bookId, Model model) {
		
		logger.info("goodsDetailGET()..........");
		
		model.addAttribute("goodsInfo", bookService.getGoodsInfo(bookId));
		
		return "/goodsDetail";
	}
	
}
