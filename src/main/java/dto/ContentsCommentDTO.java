package dto;

import java.util.Date;

public class ContentsCommentDTO {

	private int cmt_idx; // 댓글 고유번호
	private int c_idx; // 글 고유번호
	private String writer; // 작성자
	private String content; // 댓글 내용
	private Date write_date; // 작성일자
	private String userid;
	private String profile_img;

	public int getCmt_idx() {
		return cmt_idx;
	}

	public void setCmt_idx(int cmt_idx) {
		this.cmt_idx = cmt_idx;
	}

	public int getC_idx() {
		return c_idx;
	}

	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	@Override
	public String toString() {
		return "ContentsCommentDTO [cmt_idx=" + cmt_idx + ", c_idx=" + c_idx + ", writer=" + writer + ", content="
				+ content + ", write_date=" + write_date + ", userid=" + userid + ", profile_img=" + profile_img + "]";
	}

}
