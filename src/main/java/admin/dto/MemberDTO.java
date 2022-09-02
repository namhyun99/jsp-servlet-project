package admin.dto;

import java.sql.Date;

public class MemberDTO {

	private int rn; // 열번호
	private int m_idx; // 회원 일련 번호
	private String userid; // 회원 아이디
	private String passwd; // 회원 비밀번호
	private String name; // 회원 이름
	private String email; // 회원 이메일
	private String phone; // 회원 전화번호
	private String profile_img; // 프로필 이미지
	private String consent; // 이용약관 동의여부
	private String privacy; // 개인정보약관 동의여부
	private String authority; // 역할
	private Date join_date; // 가입일시

	// getter, setter ,toString
	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}
	
	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	@Override
	public String toString() {
		return "MemberDTO [rn=" + rn + ", m_idx=" + m_idx + ", userid=" + userid + ", passwd=" + passwd
				+ ", name=" + name + ", email=" + email + ", phone=" + phone + ", profile_img=" + profile_img
				+ ", consent=" + consent + ", privacy=" + privacy + ", authority=" + authority + ", join_date="
				+ join_date + "]";
	}


}
