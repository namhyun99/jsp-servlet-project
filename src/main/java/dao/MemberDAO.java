package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.BCrypt;
import dto.MemberDTO;
import mybatis.MybatisManager;

public class MemberDAO {

	// 로그인 체크
	public String loginCheck(MemberDTO dto) {
		String roll = "";
		SqlSession session = null;

		try {
			session = MybatisManager.getInstance().openSession();
			roll = session.selectOne("member.loginCheck", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return roll;
	}

	// 아이디 체크
	public String remindID(MemberDTO dto) {
		String result = "";

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("member.remindID", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//비밀번호 체크
	public String remindPwd(MemberDTO dto) {
		String result = "";

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("member.remindPwd", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//회원 리스트 가져오기
	public List<MemberDTO> getUserList(int start, int end, String order, String searchkey, String keyword) {
		List<MemberDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("searchkey", searchkey);
			map.put("keyword", "%"+keyword+"%");
			list = session.selectList("member.getUserList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 회원가입 하기
	public int join(MemberDTO dto) {
		int result = -1;
		
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			//BCrypt 비밀번호 암호화
			String passwd = BCrypt.hashpw(dto.getPasswd(), BCrypt.gensalt());
			dto.setPasswd(passwd);
			result = session.insert("member.join", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//멤버 리스트 가져오기
	public int getMemberCount(String searchkey, String keyword) {
		int result = 0;
		
		try(SqlSession session=MybatisManager.getInstance().openSession()){
			Map<String, Object> map = new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("keyword", "%"+keyword+"%");
			result = session.selectOne("member.getMemberCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
