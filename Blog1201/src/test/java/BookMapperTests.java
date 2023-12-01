import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.mapper.BookMapper;
import com.study.mapper.OrderMapper;
import com.study.model.BookVO;
import com.study.model.Criteria;

@WebAppConfiguration // 폼버전을 업그레이드 했을때 잘되던 junit테스트가 안된다면 이 어노테이션을 작성해보라
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BookMapperTests {

	@Autowired
	private BookMapper mapper;

	
	@Autowired
	private OrderMapper orderMapper;
	/*
	 * @Test public void getGoodsListTest() {
	 * 
	 * Criteria cri = new Criteria(); // 테스트 키워드 //cri.setKeyword("사진");
	 * System.out.println("cri : " + cri);
	 * 
	 * List<BookVO> list = mapper.getGoodsList(cri); System.out.println("list : " +
	 * list);
	 * 
	 * System.out.println("=========="); int goodsTotal = mapper.goodsGetTotal(cri);
	 * System.out.println("total : " + goodsTotal);
	 * 
	 * }
	 */

	/* 작가 id 리스트 요청 */

	/*
	 * @Test public void getAuthorId() {
	 * 
	 * String keyword = "코";
	 * 
	 * String[] list = mapper.getAuthorIdList(keyword);
	 * 
	 * System.out.println("결과 : " + list.toString());
	 * 
	 * for (String id : list) { System.out.println("개별 결과 : " + id); }
	 * 
	 * }
	 */
	/*
	 * @Test public void getGoodsListTest1() { // Criteria cri = new Criteria();
	 * String type = "A"; String keyword = "코난"; // DB에 등록된 작가 데이터 // String keyword
	 * = "없음"; // DB에 등록되지 않은 작가 데이터 String catecode = "";
	 * 
	 * cri.setType(type); cri.setKeyword(keyword);
	 * cri.setAuthorArr(mapper.getAuthorIdList(keyword)); cri.setCateCode(catecode);
	 * 
	 * List<BookVO> list = mapper.getGoodsList(cri);
	 * 
	 * System.out.println("cri : " + cri); System.out.println("list : " + list);
	 * 
	 * }
	 */

	/*
	 * @Test public void getGoodsListTest2() { Criteria cri = new Criteria(); String
	 * type = "T"; String keyword = "사진"; // 테이블에 등록된 책 제목 데이터 // String keyword =
	 * "없음"; // 테이블에 등록되지 않은 데이터 String catecode = "";
	 * 
	 * cri.setType(type); cri.setKeyword(keyword);
	 * cri.setAuthorArr(mapper.getAuthorIdList(keyword)); cri.setCateCode(catecode);
	 * 
	 * List<BookVO> list = mapper.getGoodsList(cri);
	 * 
	 * System.out.println("cri : " + cri); System.out.println("list : " + list);
	 * 
	 * }
	 */
	/* 검색 (동적 쿼리 적용) - 카테고리 */

	/*
	 * @Test public void getGoodsListTest3() { Criteria cri = new Criteria(); String
	 * type = "C"; String keyword = ""; String catecode = "102001";
	 * 
	 * cri.setType(type); cri.setKeyword(keyword);
	 * cri.setAuthorArr(mapper.getAuthorIdList(keyword)); cri.setCateCode(catecode);
	 * 
	 * List<BookVO> list = mapper.getGoodsList(cri);
	 * 
	 * System.out.println("cri : " + cri); System.out.println("list : " + list); }
	 */
	/*
	 * 검색 (동적 쿼리 적용) - 카테고리 + 작가
	 * 
	 * @Test public void getGoodsListTest4() { Criteria cri = new Criteria(); String
	 * type = "AC"; String keyword = "유홍준"; // 카테고리에 존재하는 작가 //String keyword =
	 * "머스크"; // 카테고리에 존재하지 않는 작가 String catecode = "103002";
	 * 
	 * cri.setType(type); cri.setKeyword(keyword);
	 * cri.setAuthorArr(mapper.getAuthorIdList(keyword)); cri.setCateCode(catecode);
	 * 
	 * List<BookVO> list = mapper.getGoodsList(cri);
	 * 
	 * System.out.println("cri : " + cri); System.out.println("list : " + list);
	 * 
	 * }
	 */

	
	/* 카테고리 리스트 */
	@Test
	public void getCateListTest1() {
		
		Criteria cri = new Criteria();
		
		String type = "TC";
		String keyword = "테스트";
		//String type = "A";
		//String keyword = "유홍준";		

		cri.setType(type);
		cri.setKeyword(keyword);
		//cri.setAuthorArr(mapper.getAuthorIdList(keyword));		
		
		String[] cateList = mapper.getCateList(cri)		;
		for(String codeNum : cateList) {
			System.out.println("codeNum ::::: " + codeNum);
		}
		
		
	}
	
	/* 카테고리 정보 얻기 */	
	@Test
	public void getCateInfoTest1() {
		
		Criteria cri = new Criteria();
		
		String type = "TC";
		String keyword = "테스트";	
		String cateCode="102001";

		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		mapper.getCateInfo(cri);
		
	}
	
	@Test
	public void getGoodsInfo() {
		int bookId = 15;
		BookVO goodsInfo = mapper.getGoodsInfo(bookId);
		System.out.println("===========================");
		System.out.println(goodsInfo);
		System.out.println("===========================");
		
	}
	
	
	@Test
	public void Test() {
		
		

		System.out.println("==========================="+orderMapper.enrollOrderItemSeq());
		
	}

	


	
}
