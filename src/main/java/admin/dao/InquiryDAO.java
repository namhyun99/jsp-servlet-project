package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.InquiryDTO;
import mybatis.MybatisManager;

public class InquiryDAO {

	// 1대 1 게시글 리스트 불러오기
	public List<InquiryDTO> getInquiryList(int start, int end, String order, String keyword) {
		List<InquiryDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("inquiry.getInquiryList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 1대1일 문의 갯수
	public int getInquiryCount(String order, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("order", order);
			map.put("keyword", "%" + keyword + "%");
			result = (int) session.selectOne("inquiry.getInquiryCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//게시글 상세보기
	public InquiryDTO detailInquiryVeiw(int i_idx) {
		InquiryDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("inquiry.detailInquiryVeiw", i_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	//작성자 조회
	public int getM_idx(String userid) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("inquiry.getM_idx", userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//답글의 순서 조정
	public void updateStep(int ref, int re_step) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			InquiryDTO dto = new InquiryDTO();
			dto.setRef(ref);
			dto.setRe_step(re_step);
			session.update("inquiry.updateStep", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//답글 쓰기
	public void writeReply(InquiryDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.insert("inquiry.writeReply", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//게시글 삭제
	public void deleteInquiry(int i_idx) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.delete("inquiry.deleteInquiry", i_idx);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	// 원글 답변여부 업데이트
	public void updateComplete(int i_idx, String complete) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("i_idx", i_idx);
			map.put("complete", complete);		
			session.update("inquiry.updateComplete", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
