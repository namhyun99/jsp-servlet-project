package admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import admin.dto.BoardDTO;
import mybatis.MybatisManager;

public class BoardDAO {
	
	//게시판 리스트 불러오기
	public List<BoardDTO> getBoardList(){
		List<BoardDTO> boradList = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			boradList = session.selectList("board.getBoardList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boradList;
	}
	
}
