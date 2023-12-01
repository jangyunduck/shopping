package com.study.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.AdminMapper;
import com.study.mapper.AttachMapper;
import com.study.model.AttachImageVO;

@Service
public class AttachServiceImpl implements AttachService{

	private static final Logger log = LoggerFactory.getLogger(AttachServiceImpl.class);
	
	@Autowired
	private AttachMapper attachMapper;
	
	

	
	
	/* 이미지 데이터 반환 */
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {
		
		log.info("getAttachList.........001@@@"+bookId);
		
		System.out.println("######test" + bookId);
		
		
		//List<AttachImageVO> vo = attachMapper.getAttachList(bookId);
		
		
		
		
		//if(vo==null && vo.size()==0 ) {
		//	log.info("getAttachList.........빈값 입니다001. ");
		//}else {
		//	log.info("getAttachList.........빈값 입니다002. ");
		//}
		
		//return vo;
		//밑에거 추가해봄
		return attachMapper.getAttachList(bookId);
	}

	
	
	
	
	@Override
	public AttachImageVO getAttach(int bookId) {
		// TODO Auto-generated method stub
		
		
		
		AttachImageVO vo = new AttachImageVO();
		vo.setBookId(171);
		
		
		
		return vo;
	}
	
}
