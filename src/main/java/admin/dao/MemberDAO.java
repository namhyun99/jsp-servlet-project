package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.MemberDTO;
import mybatis.MybatisManager;
import util.BCrypt;

public class MemberDAO {

	// 로그인 체크
	public String loginCheck(MemberDTO dto) {
		String authority = "";
		SqlSession session = null;

		try {
			session = MybatisManager.getInstance().openSession();
			authority = session.selectOne("member.loginCheck", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return authority;
	}

	// 비밀번호 체크
	public String passwdCheck(String userid) {
		String result = "";
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			result = session.selectOne("member.passwdCheck", userid);
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
			result = session.selectOne("member.remindID", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 비밀번호 찾기
	public String remindPwd(MemberDTO dto) {
		String result = "";

		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("member.remindPwd", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원 멤버 리스트 불러오기
	public List<MemberDTO> getMemberList(String authority, int start, int end, String order, String searchkey,
			String keyword) {
		List<MemberDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("authority", authority);
			map.put("start", start);
			map.put("end", end);
			map.put("order", order);
			map.put("searchkey", searchkey);
			map.put("keyword", "%" + keyword + "%");
			list = session.selectList("member.getMemberList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원가입 하기
	public void joinMember(MemberDTO dto) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			// BCrypt 비밀번호 암호화
			String passwd = BCrypt.hashpw(dto.getPasswd(), BCrypt.gensalt());
			dto.setPasswd(passwd);
			session.insert("member.join", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 멤버 갯수 가져오기
	public int getMemberCount(String authority, String searchkey, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("authority", authority);
			map.put("searchkey", searchkey);
			map.put("keyword", "%" + keyword + "%");
			result = (int) session.selectOne("member.getMemberCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 아이디 중복체크
	public String useridCheck(String userid) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("member.useridCheck", userid);
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
			session.update("member.updatePasswd", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원정보 불러오기
	public MemberDTO detailVeiw(int m_idx) {
		MemberDTO dto = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			dto = session.selectOne("member.detailVeiw", m_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 첨부파일 이름 가져오기
	public String getProfileImg(int m_idx) {
		String result = "";
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("member.getProfileImg", m_idx);
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
			session.update("member.updateMember", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원정보 삭제
	public void deleteMember(int m_idx) {
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.delete("member.deleteMember", m_idx);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
