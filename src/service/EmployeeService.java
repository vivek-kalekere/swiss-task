package service;

import java.util.List;
import java.util.Map;

import domain.Employee;

public interface EmployeeService {

    public Map<Integer, Employee> getIdEmployeeMap();

    public List<Employee> getGratuityEmps();
    
    public Map<Employee, List<Employee>> getManagerEmpMap();
    
    public List<Employee> getEmpReportingHierarchy();
}
