package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.CategoryDTO;
import dto.ContentsDTO;
import mybatis.MybatisManager;

public class ContentsDAO {

	// 컨텐츠 게시글 리스트 불러오기
	public List<ContentsDTO> getContentsList(int start, int end, String order, String searchkey, String keyword) {
		List<ContentsDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("searchkey", searchkey);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("contents.getContentsList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 컨텐츠 게시글 갯수 확인
	public int getContestsCount(String searchkey, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("keyword", "%" + keyword + "%");
			result = (int) session.selectOne("contents.getContentsCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시글 상세보기
	public ContentsDTO detailContentsVeiw(int c_idx) {
		ContentsDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("contents.boardDetailVeiw", c_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 게시글 업데이트
	public void updateContents(ContentsDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.update("contents.updateBoard", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파일이름 가져오기
	public String getFileName(int c_idx) {
		String result = "";

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.selectOne("contents.getFileName", c_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 게시글 등록하기
	public void writeContents(ContentsDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.insert("contents.writeBoard", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 작성자 m_idx값 구하기
	public int getM_idx(String userid) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("contents.getM_idx", userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시판 글 삭제하기.
	public void deleteContents(int c_idx) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.delete("contents.deleteBoard", c_idx);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		

	}
	
	//카테고리 이름 가져오기
	public List<CategoryDTO> getCateName() {
		List<CategoryDTO> cateList = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			cateList = session.selectList("contents.getCateName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cateList;
	}
}
