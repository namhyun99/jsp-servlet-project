package dto;

import java.util.Date;

public class NoticeDTO {

	private int f_idx;
	private int m_idx;
	private String title;
	private String content;
	private Date write_date;
	private Date update_date;
	private String userid;
	private int view_cnt;
	private int rn;

	public int getF_idx() {
		return f_idx;
	}

	public void setF_idx(int f_idx) {
		this.f_idx = f_idx;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	@Override
	public String toString() {
		return "NoticeDTO [f_idx=" + f_idx + ", m_idx=" + m_idx + ", title=" + title + ", content=" + content
				+ ", write_date=" + write_date + ", update_date=" + update_date + ", userid=" + userid + ", view_cnt="
				+ view_cnt + ", rn=" + rn + "]";
	}

}
