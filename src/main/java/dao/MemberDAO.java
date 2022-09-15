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

	// 회원정보 불러오기
	public MemberDTO getMemberDetailView(int m_idx) {
		MemberDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("service.getMemberDetailView", m_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 첨부파일 이름 가져오기
	public String getProfileImg(int m_idx) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("service.getProfileImg", m_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원정보 업데이트
	public void updateMember(MemberDTO dto, String dbPasswd) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {

			if (!dto.getPasswd().equals(dbPasswd)) {
				// 기존 비밀번호와 다르면 BCrypt 비밀번호 암호화
				String passwd = BCrypt.hashpw(dto.getPasswd(), BCrypt.gensalt());
				dto.setPasswd(passwd);
			}
			session.update("service.updateMember", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원정보 삭제
	public void deleteMember(int m_idx) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.delete("service.deleteMember", m_idx);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 아이디 중복체크
	public String useridCheck(String userid) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("service.useridCheck", userid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
