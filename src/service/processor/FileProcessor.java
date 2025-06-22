package service.processor;

import java.util.List;

import domain.Employee;

public interface FileProcessor {

    List<Employee> processFile(String filePath);
}
