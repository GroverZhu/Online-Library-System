package entity;

import java.util.ArrayList;
import java.util.List;

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
	private List<Integer> borrowCart;// 借阅车
	private List<Integer> borrowList;// 当前借阅
	private List<Integer> borrowHistory;// 借阅历史

	public boolean addToBorrowCart(int book_id) {
		if (borrowCart.size() < 10) {
			this.borrowCart.add(book_id);
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteFromBorrowCart(int book_id) {
		if (borrowCart.size() == 0) {
			return false;
		} else {
			if (borrowCart.contains(book_id)) {
				borrowCart.remove(book_id);
				return true;
			} else {
				return false;
			}
		}
	}

	public void addToBorrowList(int book_id) {
		borrowList.add(book_id);
	}

	public boolean deleteFromBorrowList(int book_id) {
		if (borrowList.isEmpty()) {
			return false;
		} else {
			if (borrowList.contains(book_id)) {
				borrowList.remove(book_id);
				return true;
			} else {
				return false;
			}
		}
	}

	public void addToHistory(int book_id) {
		borrowHistory.add(book_id);
	}

	public Reader() {
		borrowCart = new ArrayList<Integer>();
		borrowList = new ArrayList<Integer>();
		borrowHistory = new ArrayList<Integer>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getBorrowList() {
		return borrowList;
	}

	public void setBorrowList(List<Integer> borrowList) {
		this.borrowList = borrowList;
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

	public List<Integer> getBorrowCart() {
		return borrowCart;
	}

	public void setBorrowCart(List<Integer> borrowCart) {
		this.borrowCart = borrowCart;
	}

	public List<Integer> getBorrowHistory() {
		return borrowHistory;
	}

	public void setBorrowHistory(List<Integer> borrowHistory) {
		this.borrowHistory = borrowHistory;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", password=" + password + ", borrowCart.size()="
				+ borrowCart.size() + ", borrowList.size()=" + borrowList.size() + ", borrowHistory.size()="
				+ borrowHistory.size() + "]";
	}

}
