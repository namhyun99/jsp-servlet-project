package dto;

public class CategoryDTO {

	private int cate_no;
	private String cate_name;

	public int getCate_no() {
		return cate_no;
	}

	public void setCate_no(int cate_no) {
		this.cate_no = cate_no;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	@Override
	public String toString() {
		return "CategoryDTO [cate_no=" + cate_no + ", cate_name=" + cate_name + "]";
	}

}
