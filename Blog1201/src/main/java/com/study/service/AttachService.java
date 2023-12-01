package com.study.service;

import java.util.List;

import com.study.model.AttachImageVO;

public interface AttachService {

	/* 이미지 데이터 반환 */
	public List<AttachImageVO> getAttachList(int bookId);
	
	
	public AttachImageVO getAttach (int bookId);
}
