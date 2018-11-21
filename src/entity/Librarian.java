package entity;

/**
 * 表示数据库中的Librarian
 * 
 * @author GroverZhu
 * @date 2018-11-12 22:49:00
 */
public class Librarian {
	private int id;
	private String name;
	private String password;
	private String state; // 表示账号状态 ，“blockade”为被锁定，且不可登入；“unlock”为正常状态

	public Librarian() {

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

	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            只允许“blockade”与“unlock”
	 */
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Librarian [id=" + id + ", name=" + name + ", password=" + password + ", state=" + state + "]";
	}

}
