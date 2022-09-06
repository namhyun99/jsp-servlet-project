package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.InquiryDTO;
import mybatis.MybatisManager;

public class InquiryDAO {

	// 1대 1 게시글 리스트 불러오기
	public List<InquiryDTO> getInquiryList(int start, int end, String order, String op, String keyword) {
		List<InquiryDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("op", op);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("board.getFaqList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// faq 갯수
	public int getInquiryCount(String op, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("op", op);
			map.put("keyword", "%" + keyword + "%");
			result = (int) session.selectOne("board.getFaqCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
