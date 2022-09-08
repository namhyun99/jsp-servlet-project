package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

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

}
