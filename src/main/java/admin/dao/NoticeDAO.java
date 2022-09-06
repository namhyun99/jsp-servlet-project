package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.NoticeDTO;
import mybatis.MybatisManager;

public class NoticeDAO {

	// 공지사항 게시글 리스트 불러오기
	public List<NoticeDTO> getNoticeList(int start, int end, String order, String searchkey, String keyword) {
		List<NoticeDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("searchkey", searchkey);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("notice.getNoticeList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 공지사항 갯수
	public int getNoticeCount(String searchkey, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("keyword", "%" + keyword + "%");
			result = (int) session.selectOne("notice.getNoticeCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public NoticeDTO detailNoticeVeiw(int f_idx) {
		NoticeDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("notice.detailNoticeVeiw", f_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public void updateNotice(NoticeDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.update("notice.updateNotice", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getM_idx(String userid) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("notice.getM_idx", userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void writeNotice(NoticeDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.insert("notice.writeNotice", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteNotice(int f_idx) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.delete("notice.deleteNotice", f_idx);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
