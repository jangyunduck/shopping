package com.study.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.mapper.AdminMapper;
import com.study.model.AttachImageVO;
import com.study.model.BookVO;
import com.study.model.CateVO;
import com.study.model.Criteria;
import com.study.model.OrderDTO;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	@Autowired
	private AdminMapper adminMapper;
	
	/*상품등록*/
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(service)bookEnroll...Start1", book);
		
		adminMapper.bookEnroll(book);
		
		if(book.getImageList() == null || book.getImageList().size() <= 0) {
			log.info("bookEnroll()...ERR!");
			return;
		}
		
		
		log.info("bookEnroll()..2222");
		
		book.getImageList().forEach(attach ->{
			
			attach.setBookId(book.getBookId());
			adminMapper.imageEnroll(attach);
			
		});
		
		
		log.info("bookEnroll()..333333");
		
		
		
	}
	
	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList........");
		
		return adminMapper.cateList();
	}
	
	
	/* 상품 리스트 */
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList()..........");
		return adminMapper.goodsGetList(cri);
	}

	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal().........");
		return adminMapper.goodsGetTotal(cri);
	}	
	
	/* 상품 조회 페이지 */
	@Override
	public BookVO goodsGetDetail(int bookId) {
		
		log.info("(service)bookGetDetail......." + bookId);
		
		return adminMapper.goodsGetDetail(bookId);
	}	
	
	/* 상품 정보 수정 */
	@Transactional
	@Override
	public int goodsModify(BookVO vo) {
		
		int result = adminMapper.goodsModify(vo);
		
		System.out.println("goodsModifyStart!!!!!!!!!!!!!!!!!!!0011");
		
		System.out.println(vo.getBookId());
		
		
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
			
			adminMapper.deleteImageAll(vo.getBookId());
			
			vo.getImageList().forEach(attach -> {
				
				attach.setBookId(vo.getBookId());
				adminMapper.imageEnroll(attach);
				
			});
			
		}
		
		return result;
		
	}
	
	/* 상품 정보 삭제 */
	@Override
	@Transactional
	public int goodsDelete(int bookId) {

		log.info("goodsDelete..........");
		
		adminMapper.deleteImageAll(bookId);
		
		return adminMapper.goodsDelete(bookId);
	}	
	
	/* 지정 상품 이미지 정보 얻기 */
	@Override
	public List<AttachImageVO> getAttachInfo(int bookId) {
		
		log.info("getAttachInfo........");
		
		return adminMapper.getAttachInfo(bookId);
	}
	
	
	
	/* 주문 상품 리스트 */
	@Override
	public List<OrderDTO> getOrderList(Criteria cri) {
		return adminMapper.getOrderList(cri);
	}
	
	/* 주문 총 갯수 */
	@Override
	public int getOrderTotal(Criteria cri) {
		return adminMapper.getOrderTotal(cri);
	}
	

	
}
