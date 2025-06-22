package domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private Integer id;
    private String name;
    private String city;
    private String state;
    private String category;
    private Integer manager_id;
    private BigDecimal salary;
    private LocalDate doj;
    
	public Employee(Integer id, String name, String city, String state, String category, Integer manager_id,
			BigDecimal salary, LocalDate doj) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.category = category;
		this.manager_id = manager_id;
		this.salary = salary;
		this.doj = doj;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + ", category="
				+ category + ", manager_id=" + manager_id + ", salary=" + salary + ", doj=" + doj + "]";
	}
    
}
