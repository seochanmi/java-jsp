package test.friend.dto;

public class FriendDto {
	private int num;
	private String name;
	private String phone;
	//디폴트 생성자
	public FriendDto() {}
	public FriendDto(int num, String name, String phone) {
		super();
		this.num = num;
		this.name = name;
		this.phone = phone;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}