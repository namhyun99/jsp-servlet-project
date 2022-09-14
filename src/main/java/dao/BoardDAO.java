package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import dto.CategoryDTO;
import dto.ContentsCommentDTO;
import dto.ContentsDTO;
import dto.InquiryDTO;
import dto.NoticeDTO;
import mybatis.MybatisManager;

public class BoardDAO {

	// 컨텐츠 게시글 리스트 불러오기
	public List<ContentsDTO> getContentsList(int start, int end, String order) {
		List<ContentsDTO> list = null;

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			list = session.selectList("service.getContentsList", map);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ContentsDTO dto : list) {
			String contents = dto.getContent();
			contents = contents.replace("<p>", "").replace("</p>", "").replace("<br>", "");
			dto.setContent(contents);
		}
		
		return list;
	}
	
	// 컨텐츠 갯수 
	public int getContentsCount() {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = (int) session.selectOne("service.getContentsCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 컨텐츠 게시글 정보 가져오기
	public ContentsDTO getDetailContentsView(int c_idx) {
		ContentsDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("service.getDetailContentsView", c_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	// 컨텐츠 댓글 가져오기
	public List<ContentsCommentDTO> getCommentList(int c_idx) {
		List<ContentsCommentDTO> list =null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			list = session.selectList("service.getCommentList", c_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//컨텐츠 댓글 추가하기
	public void addComment(ContentsCommentDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			 session.insert("service.addComment", dto);
			 session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//컨텐츠 댓글 삭제하기
	public void deleteComment(int cmt_idx) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			 session.delete("service.deleteComment", cmt_idx);
			 session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//추천 콘텐츠 가져오기
	public List<ContentsDTO> getOtherContentList() {
		List<ContentsDTO> list =null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			list = session.selectList("service.getOtherContentList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//조회수 증가 처리
	public void plusViewCount(int c_idx, HttpSession count_session) {
		long view_time = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			if(count_session.getAttribute("view_time_"+c_idx) != null) {
				view_time = (long)count_session.getAttribute("view_time_"+c_idx);
			}
			long current_time = System.currentTimeMillis();
			if(current_time - view_time > 5*1000) {
				session.update("service.plusViewCount", c_idx);
				session.commit();
				count_session.setAttribute("view_tiem_"+c_idx, current_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//카테고리 리스트 가져오기
	public List<CategoryDTO> getCateName() {
		List<CategoryDTO> list =null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			list = session.selectList("service.getCateName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//m_idx 조회하기
	public int getM_idx(String userid) {
		int result = -1;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("service.getM_idx", userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//글작성 하기
	public void insertContents(ContentsDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.insert("service.insertContents", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//파일이름 가져오기
	public String getFileName(int c_idx) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("service.getFileName", c_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	//글삭제 하기
	public int deleteContents(int c_idx) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.delete("service.deleteContents", c_idx);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//글수정 하기
	public void updateContents(ContentsDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.update("service.updateContents", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//공지사항 리스트 불러오기
	public List<NoticeDTO> getNoticeList(int start, int end, String keyword) {
		List<NoticeDTO> list = null;

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("service.getNoticeList", map);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//공지사항 갯수
	public int getNoticeCount(String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = (int) session.selectOne("service.getNoticeCount", "%" + keyword + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//공지사항 조회수 증가
	public void plusViewCountNotice(int f_idx, HttpSession count_session) {
		long view_time = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			if(count_session.getAttribute("view_time_"+f_idx) != null) {
				view_time = (long)count_session.getAttribute("view_time_"+f_idx);
			}
			long current_time = System.currentTimeMillis();
			if(current_time - view_time > 5*1000) {
				session.update("service.plusViewCountNotice", f_idx);
				session.commit();
				count_session.setAttribute("view_tiem_"+f_idx, current_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//공지사항 정보 불러오기
	public NoticeDTO getDetailNoticeView(int f_idx) {
		NoticeDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("service.getDetailNoticeView", f_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//1대1 게시글 리스트 불러오기
	public List<InquiryDTO> getInquiryList(int start, int end, String keyword) {
		List<InquiryDTO> list = null;

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("service.getInquiryList", map);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	//1대1 문의 게시글 갯수
	public int getInquiryCount(String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = (int) session.selectOne("service.getInquiryCount", "%" + keyword + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	//1대1 문의 조회수 증가
	public void plusViewCountInquiry(int i_idx, HttpSession count_session) {
		long view_time = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			if(count_session.getAttribute("view_time_"+i_idx) != null) {
				view_time = (long)count_session.getAttribute("view_time_"+i_idx);
			}
			long current_time = System.currentTimeMillis();
			if(current_time - view_time > 5*1000) {
				session.update("service.plusViewCountInquiry", i_idx);
				session.commit();
				count_session.setAttribute("view_tiem_"+i_idx, current_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//1대1 문의 게시글 상세보기
	public InquiryDTO getDetailInquiryView(int i_idx) {
		InquiryDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("service.getDetailInquiryView", i_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	//1대1 문의 등록하기
	public void insertInquiry(InquiryDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.insert("service.insertInquiry", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//검색 리스트
	public List<ContentsDTO> getSearchList(int start, int end, String order, String keyword) {
		List<ContentsDTO> list = null;
		
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("service.getSearchList", map);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//검색 갯수
	public int getSearchCount(String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = (int) session.selectOne("service.getSearchCount", "%"+keyword+"%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
