package service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.Employee;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private List<Employee> employees;
	private Map<Integer, Employee> idEmpployeeMap;
	private Map<Employee, List<Employee>> managerEmpMap;

	public EmployeeServiceImpl(List<Employee> employees) {
		this.employees = employees;
		idEmpployeeMap = getIdEmployeeMap();
		managerEmpMap = getManagerEmpMap();
	}

	@Override
	public Map<Employee, List<Employee>> getManagerEmpMap() {
		return employees.stream().filter(e -> Objects.nonNull(e.getManager_id()))
				.collect(Collectors.groupingBy(e -> idEmpployeeMap.get(e.getManager_id())));
	}

	@Override
	public Map<Integer, Employee> getIdEmployeeMap() {
		return employees.stream().collect(Collectors.toMap(Employee::getId, employee -> employee));
	}

	@Override
	public List<Employee> getGratuityEmps() {
		LocalDate fiveYearsOld = LocalDate.now().minusYears(5);

		List<Employee> gratuityEmpList = employees.stream().filter(e -> e.getDoj().isBefore(fiveYearsOld))
				.collect(Collectors.toList());
		return gratuityEmpList;
	}

	@Override
	public List<Employee> getEmpReportingHierarchy() {
		List<Employee> hierarchy = new ArrayList<>();
		Employee headEmp = employees.stream().filter(e -> e.getManager_id() == null).findFirst().get();
		if (headEmp == null) {
			return hierarchy; // Employee not found
		}
		hierarchy.add(headEmp); 
		for(Employee em : employees) {
			if (em.getManager_id() != null && em.getManager_id().equals(headEmp.getId())) {
				hierarchy.add(em);
			}
		}
		return hierarchy;
	}
}
