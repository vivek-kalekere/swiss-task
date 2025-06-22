package service.processor.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Employee;
import service.processor.FileProcessor;

public class FileReader implements FileProcessor {
	@Override
	public List<Employee> processFile(String filePath) {
		List<Employee> employees = new ArrayList<>();
		validate(filePath);
		try {
			Path path = Paths.get(filePath);
			List<String> lines = Files.readAllLines(path).stream().collect(Collectors.toList());
			if (lines.size() > 1) {// first line is header
				for (int i = 1; i < lines.size(); i++) {
					String line = lines.get(i);
					String[] cells = line.split(",");
					Employee e = new Employee(Integer.parseInt(cells[0]), cells[1], cells[2], cells[3], cells[4], 
							fetchManagerId(cells), new BigDecimal(cells[6]), LocalDate.parse(cells[7], DateTimeFormatter.ofPattern("d-MMM-yy")));
					employees.add(e);
				}
			}
		} catch (IOException exception) {
			throw new IllegalArgumentException("Exception during file processing: " + filePath, exception);
		}
		return employees;
	}

	private Integer fetchManagerId(String[] cells) {
		String managerId = cells[5];
		if (null != managerId && !managerId.equalsIgnoreCase("null")) {
			return Integer.valueOf(managerId);
		}
		return null;
	}

	private void validate(String path) {
		File file = new File(path);
		if (!file.exists()) {
			throw new IllegalArgumentException("File does not exist: " + path);
		}
	}
}
