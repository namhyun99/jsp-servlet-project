package dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.MemberDTO;
import mybatis.MybatisManager;
import util.BCrypt;

public class MemberDAO {

	// 로그인체크
	public MemberDTO loginCheck(String userid, String dbPasswd) {
		MemberDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("userid", userid);
			map.put("passwd", dbPasswd);
			dto = (MemberDTO) session.selectOne("service.loginCheck", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 비밀번호 체크
	public String passwdCheck(String userid) {
		String result = "";
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			result = session.selectOne("service.passwdCheck", userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	// 아이디 찾기
	public String remindID(MemberDTO dto) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("service.remindID", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 비밀번호 찾기
	public String remindPwd(MemberDTO dto) {
		String result = "";

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("service.remindPwd", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 비밀번호 재설정
	public void updatePasswd(MemberDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			// BCrypt 비밀번호 암호화
			String passwd = BCrypt.hashpw(dto.getPasswd(), BCrypt.gensalt());
			dto.setPasswd(passwd);
			session.update("service.updatePasswd", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원가입 하기
	public void insertMember(MemberDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			// BCrypt 비밀번호 암호화
			String passwd = BCrypt.hashpw(dto.getPasswd(), BCrypt.gensalt());
			dto.setPasswd(passwd);
			session.insert("service.insertMember", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
