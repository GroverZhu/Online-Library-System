package entity;

/**
 * 出版社实体类
 * 
 * @author zengyaoNPU
 * @date 2018-11-12 23:01:00
 */
public class Publisher {
	private int id;
	private String name;
	private String description;

	/**
	 * 无参构造函数
	 */
	public Publisher() {

	}

	public Publisher(int id, String name, String description) {
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
		return "Publisher [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
