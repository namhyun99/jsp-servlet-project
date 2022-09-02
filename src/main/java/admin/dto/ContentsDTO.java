package admin.dto;

import java.util.Date;

public class ContentsDTO {

	private int c_idx; // 글번호
	private int board_no; // 게시판 고유번호
	private int cate_no; // 카테 고리번호
	private int m_idx; // 작성자 번호
	private String subject; // 제목
	private String content; // 내용
	private int view_cnt; // 조회수
	private String ip; // 아이피
	private String show; // 공개여부
	private String filename; // 파일이름
	private int filesize; // 파일사이즈
	private Date write_date; // 작성일자
	private Date update_date; // 수정일자
	private String ext; // 첨부파일의 확장자, 테이블에는 없음.(필요에 의해 임의추가)
	private int cmt_count; // 댓글 갯수
	private String userid; // 아이디
	private int rn; // row넘버

	// getter, setter ,toString
	public int getC_idx() {
		return c_idx;
	}

	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
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

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
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

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getCmt_count() {
		return cmt_count;
	}

	public void setCmt_count(int cmt_count) {
		this.cmt_count = cmt_count;
	}

	@Override
	public String toString() {
		return "ContentsDTO [c_idx=" + c_idx + ", board_no=" + board_no + ", cate_no=" + cate_no + ", m_idx=" + m_idx
				+ ", subject=" + subject + ", content=" + content + ", view_cnt=" + view_cnt + ", ip=" + ip + ", show="
				+ show + ", filename=" + filename + ", filesize=" + filesize + ", write_date=" + write_date
				+ ", update_date=" + update_date + ", ext=" + ext + ", userid=" + userid + ", rn=" + rn + "]";
	}

}
