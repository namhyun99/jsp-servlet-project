package dto;

import java.util.Date;

public class ContentsCommentDTO {

	private int cmt_idx; // 댓글 고유번호
	private int c_idx; // 글 고유번호
	private int write; // 작성자
	private String content; // 댓글 내용
	private Date write_date; // 작성일자

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

	public int getWrite() {
		return write;
	}

	public void setWrite(int write) {
		this.write = write;
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

	@Override
	public String toString() {
		return "ContentsCommentDTO [cmt_idx=" + cmt_idx + ", c_idx=" + c_idx + ", write=" + write + ", content="
				+ content + ", write_date=" + write_date + "]";
	}

}
