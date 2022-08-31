package admin.dto;

import java.util.Date;

public class ContentsDTO {

	private int p_idx; // 글번호
	private int board_no; // 게시판 고유번호
	private int cate_no; // 카테고리번호
	private int m_idx; // 작성자 번호
	private String subject; // 제목
	private String content; // 내용
	private int view_cnt; // 조회수
	private String ip; // 아이피
	private String filename; // 파일이름
	private int filesize; // 파일사이즈
	private Date write_date; // 작성일자
	private Date update_date; // 수정일자
	private String userid; // 필요의 한 생성

	// getter, setter ,toString
	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getCate_no() {
		return cate_no;
	}

	public void setCate_no(int cate_no) {
		this.cate_no = cate_no;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "ContentsDTO [p_idx=" + p_idx + ", board_no=" + board_no + ", cate_no=" + cate_no + ", m_idx=" + m_idx
				+ ", subject=" + subject + ", content=" + content + ", view_cnt=" + view_cnt + ", ip=" + ip
				+ ", filename=" + filename + ", filesize=" + filesize + ", write_date=" + write_date + ", update_date="
				+ update_date + ", userid=" + userid + "]";
	}

}
