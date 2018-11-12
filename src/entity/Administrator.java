package entity;

/**
 * 表示数据库中的表librarian各个属性
 * 
 * @author GroverZhu
 * @date 2018-11-12 22:45:00
 */
public class Administrator {
	private int id;
	private String name;
	private String password;

	public Administrator() {
	}

	/**
	 * 初始化administrator的属性
	 * 
	 * @param id
	 * @param name
	 * @param password
	 */
	public Administrator(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
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

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
