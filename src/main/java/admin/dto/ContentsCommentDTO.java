package admin.dto;

import java.util.Date;

public class ContentsCommentDTO {

	private int cmt_idx;  //댓글 고유번호
	private int p_idx;  //글 고유번호
	private int m_idx;  //작성자 고유번호
	private String content;  //댓글 내용
	private Date write_date;  //작성일자
	private Date update_date;  //수정일자

	// getter, setter ,toString
	public int getCmt_idx() {
		return cmt_idx;
	}

	public void setCmt_idx(int cmt_idx) {
		this.cmt_idx = cmt_idx;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
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

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	@Override
	public String toString() {
		return "ContentsCommentDTO [cmt_idx=" + cmt_idx + ", p_idx=" + p_idx + ", m_idx=" + m_idx + ", content="
				+ content + ", write_date=" + write_date + ", update_date=" + update_date + "]";
	}

}
