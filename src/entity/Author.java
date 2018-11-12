package entity;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author zengyaoNPU
 *
 */
public class Author {
	private int id;
	private String name;
	private String description;
	private List<String> isbnList;

	/**
	 * 无参构造函数
	 */
	public Author() {
		isbnList = new ArrayList<String>();
	}

	public Author(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public List<String> getIsbnList() {
		return isbnList;
	}

	public void setIsbnList(List<String> isbnList) {
		this.isbnList = isbnList;
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
