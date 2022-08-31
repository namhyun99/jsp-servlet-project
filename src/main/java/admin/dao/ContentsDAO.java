package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import admin.dto.ContentsDTO;
import mybatis.MybatisManager;

public class ContentsDAO {

	// 컨텐츠 게시글 리스트 불러오기
	public List<ContentsDTO> getContentsList(int start, int end, String order, String op, String keyword) {
		List<ContentsDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("op", op);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("contents.getContentsList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 컨텐츠 게시글 갯수 확인
	public int getContestsCount(String op, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("op", op);
			map.put("keyword", "%" + keyword + "%");
			result = (int) session.selectOne("contents.getContentsCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//작성자 조회
	public String getWriter(int m_idx) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("contents.getWriter", m_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
