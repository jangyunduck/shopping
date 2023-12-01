package com.study.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.mapper.AttachMapper;
import com.study.mapper.BookMapper;
import com.study.mapper.CartMapper;
import com.study.mapper.MemberrMapper;
import com.study.mapper.OrderMapper;
import com.study.model.AttachImageVO;
import com.study.model.BookVO;
import com.study.model.CartDTO;
import com.study.model.MemberrVO;
import com.study.model.OrderCancelDTO;
import com.study.model.OrderDTO;
import com.study.model.OrderItemDTO;
import com.study.model.OrderPageItemDTO;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private MemberrMapper memberrMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders){
		
		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();		
		
		for(OrderPageItemDTO ord : orders) {
			
			OrderPageItemDTO goodsInfo = orderMapper.getGoodsInfo(ord.getBookId());			

			goodsInfo.setBookCount(ord.getBookCount());	
			
			goodsInfo.initSaleTotal();	
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodsInfo.getBookId());
			
			goodsInfo.setImageList(imageList);
			
			result.add(goodsInfo);			
		}		
		
		return result;
		
	}
	
	@Override
	@Transactional
	public void order(OrderDTO ord) {
		
		

		/* 사용할 데이터가져오기 */
			/* 회원 정보 */
			MemberrVO member = memberrMapper.getMemberInfo(ord.getMemberrId());
			/* 주문 정보 */
			List<OrderItemDTO> ords = new ArrayList<>();
			for(OrderItemDTO oit : ord.getOrders()) {
				OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getBookId());
				// 수량 셋팅
				orderItem.setBookCount(oit.getBookCount());
				// 기본정보 셋팅
				orderItem.initSaleTotal();
				//List객체 추가
				ords.add(orderItem);
			}
			/* OrderDTO 셋팅 */
			ord.setOrders(ords);
			ord.getOrderPriceInfo();
			
		/*DB 주문,주문상품(,배송정보) 넣기*/
			
			/* orderId만들기 및 OrderDTO객체 orderId에 저장 */
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
			String orderId = member.getMemberrId() + format.format(date);
			
			//17
			//18
			//19
			ord.setOrderId(orderId);
			
			/* db넣기 */
			System.out.println("orderMapper.enrollOrder(ord)START!!!!!!");
			orderMapper.enrollOrder(ord);		//vam_order 등록
			
			
			
			
			System.out.println("orderMapper.enrollOrderItemSeq();Start!!!!!!");
			orderId = String.valueOf( ( orderMapper.enrollOrderItemSeq()-1) );
			System.out.println("orderMapper.enrollOrderItemSeq();End!!!!!!"+orderId);
			
			
			
			System.out.println("orderMapper.enrollOrderItem(oit)START!!!!!!");
			for(OrderItemDTO oit : ord.getOrders()) {		//vam_orderItem 등록
				oit.setOrderId(orderId);
				orderMapper.enrollOrderItem(oit);			
			}
			System.out.println("orderMapper.enrollOrderItem(oit)END!!!!!!");
		/* 비용 포인트 변동 적용 */
			
			/* 비용 차감 & 변동 돈(money) Member객체 적용 */
			int calMoney = member.getMoney();
			calMoney -= ord.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			/* 포인트 차감, 포인트 증가 & 변동 포인트(point) Member객체 적용 */
			int calPoint = member.getPoint();
			calPoint = calPoint - ord.getUsePoint() + ord.getOrderSavePoint();	// 기존 포인트 - 사용 포인트 + 획득 포인트
			member.setPoint(calPoint);
				
			/* 변동 돈, 포인트 DB 적용 */
			System.out.println("orderMapper.deductMoney(member);START!!!!!!");
			orderMapper.deductMoney(member);
			
			
			
			
			System.out.println("orderMapper.deductStock(book);START!!!!!!");
		/* 재고 변동 적용 */
			for(OrderItemDTO oit : ord.getOrders()) {
				/* 변동 재고 값 구하기 */
				BookVO book = bookMapper.getGoodsInfo(oit.getBookId());
				book.setBookStock(book.getBookStock() - oit.getBookCount());
				/* 변동 값 DB 적용 */
				orderMapper.deductStock(book);
			}			
			System.out.println("orderMapper.deductStock(book);END!!!!!!");
			
			
			System.out.println("cartMapper.deleteOrderCart(dto);START");
		/* 장바구니 제거 */
			for(OrderItemDTO oit : ord.getOrders()) {
				CartDTO dto = new CartDTO();
				dto.setMemberrId(ord.getMemberrId());
				dto.setBookId(oit.getBookId());
				
				cartMapper.deleteOrderCart(dto);
			}
			System.out.println("cartMapper.deleteOrderCart(dto);;END!!!!!!");
			
	}
	
	/* 주문취소 */
	@Override
	@Transactional
	public void orderCancle(OrderCancelDTO dto) {
		
		
		//취소할 돈
		int calMoney = 0;
		//취소할 포인트
		int calPoint = 0;
		
		
		
		System.out.println(dto.getMemberrId()+"ID 값이 있습니까?");
		/* 주문, 주문상품 객체 */
		/*회원*/
			MemberrVO member = memberrMapper.getMemberInfo(dto.getMemberrId());
		/*주문상품*/
			List<OrderItemDTO> ords = orderMapper.getOrderItemInfo(dto.getOrderId());
			for(OrderItemDTO ord : ords) {
				ord.initSaleTotal();
			}
		/* 주문 */
			OrderDTO orw = orderMapper.getOrder(dto.getOrderId());
			orw.setOrders(ords);
			
			orw.getOrderPriceInfo();
			
	/* 주문상품 취소 DB */
			orderMapper.orderCancle(dto.getOrderId());
			
	/* 돈, 포인트, 재고 변환 */
			/* 돈 */
			
			try {
			calMoney = member.getMoney();		
			} catch (Exception e) {
				System.out.println("null에러 ");
			}
			calMoney += orw.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			
			/* 포인트 */
			
			
			try {
				calPoint = member.getPoint();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("null에러 ");
			}
			calPoint = calPoint + orw.getUsePoint() - orw.getOrderSavePoint();
			member.setPoint(calPoint);
			
			
				/* DB적용 */
				orderMapper.deductMoney(member);
				
			/* 재고 */
			for(OrderItemDTO ord : orw.getOrders()) {
				BookVO book = bookMapper.getGoodsInfo(ord.getBookId());
				book.setBookStock(book.getBookStock() + ord.getBookCount());
				orderMapper.deductStock(book);
			}
		
	}
}
