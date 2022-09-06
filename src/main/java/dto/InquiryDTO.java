package dto;

import java.util.Date;

public class InquiryDTO {
	private int i_idx;
	private int m_idx;
	private String title;
	private String content;
	private int ref;
	private int rs_step;
	private int re_level;
	private String show;
	private Date write_date;
	private Date update_date;
	private String userid;
	private int view_cnt;
	private int rn;

	public int getI_idx() {
		return i_idx;
	}

	public void setI_idx(int i_idx) {
		this.i_idx = i_idx;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRs_step() {
		return rs_step;
	}

	public void setRs_step(int rs_step) {
		this.rs_step = rs_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
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
		return "InquiryDTO [i_idx=" + i_idx + ", m_idx=" + m_idx + ", title=" + title + ", content=" + content
				+ ", ref=" + ref + ", rs_step=" + rs_step + ", re_level=" + re_level + ", show=" + show
				+ ", write_date=" + write_date + ", update_date=" + update_date + ", userid=" + userid + ", view_cnt="
				+ view_cnt + ", rn=" + rn + "]";
	}

}
