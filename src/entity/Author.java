package entity;

/**
 * 表示数据库中的author属性
 * 
 * @author zengyaoNPU
 * @date 2018-11-12 23:02:00
 *
 */
public class Author {
	private int id;
	private String name;
	private String description;

	/**
	 * 无参构造函数
	 */
	public Author() {
	}

	/**
	 * 对author进行初始化
	 * 
	 * @param id
	 * @param name
	 * @param description
	 */
	public Author(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
