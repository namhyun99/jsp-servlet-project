package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import admin.dto.BoardDTO;
import admin.dto.ContentsDTO;
import mybatis.MybatisManager;

public class BoardDAO {

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
			list = session.selectList("board.getContentsList", map);
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
			result = (int) session.selectOne("board.getContentsCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 공지사항 게시글 리스트 불러오기
	public List<ContentsDTO> getNoticeList(int start, int end, String order, String op, String keyword) {
		List<ContentsDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("op", op);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("board.getNoticeList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 공지사항 갯수
	public int getNoticeCount(String op, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("op", op);
			map.put("keyword", "%" + keyword + "%");
			result = (int) session.selectOne("board.getNoticeCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// faq 게시글 리스트 불러오기
	public List<ContentsDTO> getFaqList(int start, int end, String order, String op, String keyword) {
		List<ContentsDTO> list = null;
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
	public int getFaqCount(String op, String keyword) {
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

	// 게시글 상세보기
	public ContentsDTO detailBoardVeiw(int board_no, int c_idx) {
		ContentsDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("board_no", board_no);
			map.put("c_idx", c_idx);
			dto = session.selectOne("board.boardDetailVeiw", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 게시글 업데이트
	public void updateBoard(ContentsDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.update("board.updateBoard", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파일이름 가져오기
	public String getFileName(int c_idx) {
		String result = "";

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.selectOne("board.getFileName", c_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 게시글 등록하기
	public void writeBoard(ContentsDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.insert("board.writeBoard", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시판 이름 조회
	public String getBoardTitle(int board_no) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("board.getBoardTitle", board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 카테고리 이름 조회
	public String getCategoryTitle(int board_no, int cate_no) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("board_no", board_no);
			map.put("cate_no", cate_no);
			result = session.selectOne("board.getCategoryTitle", board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시판 리스트 가져오기
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectList("board.getBoardList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 작성자 m_idx값 구하기
	public int getM_idx(String userid) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("board.getM_idx", userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시판 글 삭제하기.
	public void deleteBoard(int c_idx) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.delete("board.deleteBoard", c_idx);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		

	}
}
