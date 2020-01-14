package domain;

import java.util.ArrayList;

public class BusinessRuleType {
	
	private ArrayList<Operator> deOperators;
	private Category category;
	private String name;
	private String id;
	private String description;
	
	public BusinessRuleType() {
		deOperators = new ArrayList<Operator>();
	}
	public ArrayList<Operator> getDeOperators() {
		return deOperators;
	}
	public void setDeOperators(ArrayList<Operator> deOperators) {
		this.deOperators = deOperators;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
