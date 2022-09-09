package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.ContentsCommentDTO;
import dto.ContentsDTO;
import mybatis.MybatisManager;

public class BoardDAO {

	// 컨텐츠 게시글 리스트 불러오기
	public List<ContentsDTO> getContentsList(String order) {
		List<ContentsDTO> list = null;

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			list = session.selectList("service.getContentsList", order);		
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

}
