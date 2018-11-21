package entity;

import java.util.ArrayList;

/**
 * 
 * @author zengyaoNPU
 *
 */
public class Reader {

	private int id;
	private String name;
	private String password;
	private String email;
	private String state; // 表示账号状态 ，“blockade”为被锁定，且不可登入；“unlock”为正常状
	private ArrayList<Cart> cartList;// 借阅车
	private ArrayList<BorrowItem> borrowHistory;// 当前借阅

	public Reader() {
		cartList = new ArrayList<Cart>();
		borrowHistory = new ArrayList<BorrowItem>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(ArrayList<Cart> cartList) {
		this.cartList = cartList;
	}

	public ArrayList<BorrowItem> getBorrowHistory() {
		return borrowHistory;
	}

	public void setBorrowHistory(ArrayList<BorrowItem> borrowHistory) {
		this.borrowHistory = borrowHistory;
	}

	@Override
	/**
	 * @author zengyaoNPU
	 */
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", state="
				+ state + ", cartList=" + cartList + ", borrowHistory=" + borrowHistory + "]";
	}

}
