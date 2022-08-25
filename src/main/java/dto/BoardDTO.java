package dto;

import java.util.Date;

public class BoardDTO {

	private int board_no;   //게시판 고유번호
	private int cate_no;    //카테고리 고유번호 (외래키)
	private String title;   //게시판 제목
	private String sub_title;  //게시판 설명
	private Date create_date;  //게시판 생성일자
	
	// getter, setter ,toString
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "BoardDTO [board_no=" + board_no + ", cate_no=" + cate_no + ", title=" + title + ", sub_title="
				+ sub_title + ", create_date=" + create_date + "]";
	}

}
