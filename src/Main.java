import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import domain.Employee;
import service.EmployeeService;
import service.impl.EmployeeServiceImpl;
import service.processor.FileProcessor;
import service.processor.impl.FileReader;

public class Main {
	public static void main(String[] args) {
		System.out.println("Enter file path (csv) for processing: ");
		String filePath = new Scanner(System.in).nextLine();

		FileProcessor fileProcessor = new FileReader();
		List<Employee> employees = fileProcessor.processFile(filePath.trim());
		EmployeeService service = new EmployeeServiceImpl(employees);

		List<Employee> gratuityEmployess = service.getGratuityEmps();
		System.out.println("1) Employees eligible for Gratuity: ");
		gratuityEmployess.forEach(System.out::println);

		System.out.println("-------------------- ");
		System.out.println("2) Employees earning more than Manager: ");
		Map<Employee, List<Employee>> empsSalMoreThanManager = service.getManagerEmpMap();
		for (Map.Entry<Employee, List<Employee>> entry : empsSalMoreThanManager.entrySet()) {
			BigDecimal managerSal = entry.getKey().getSalary();
			List<Employee> empGreaterThanManagerSal = entry.getValue().stream()
					.filter(e -> e.getSalary().compareTo(managerSal) > 0).collect(Collectors.toList());
			empGreaterThanManagerSal.forEach(System.out::println);
		}

		System.out.println("-------------------- ");
		System.out.println("3) Employees reporting JSON: ");
		List<Employee> reportingData = service.getEmpReportingHierarchy();
		Gson gson = new Gson();
	    String jsonResponse = gson.toJson(reportingData);
		System.out.println(jsonResponse);
		
		System.out.println("-------------------- ");
		System.out.println("4) SQL, employee whose salary is nth highest in descending order: ");
		System.out.println("SELECT * FROM Employee e WHERE e.salary IN (SELECT DISTINCT(salary) FROM Employee ORDER BY salary DESC LIMIT n,1);");
	}
}