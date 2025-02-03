package com.example.demo.service;

import com.example.demo.entity.Employees;
import com.example.demo.entity.Emp;
import com.example.demo.entity.MappingTable;
import com.example.demo.repository.EmployeesRepository;
import com.example.demo.repository.EmpRepository;
import com.example.demo.repository.MappingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DataTransferService {

    @Autowired
    private EmployeesRepository employeeRepository;

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private MappingTableRepository mappingTableRepository;

    // Helper method to generate a random alphabet
    private char generateRandomAlphabet() {
        return (char) ('a' + new Random().nextInt(26)); // Random lowercase letter
    }

    // Helper method to generate a random number
    private int generateRandomNumber() {
        return new Random().nextInt(10); // Single-digit random number
    }

    public void transformAndInsertData() {
        // Retrieve all mapping entries from MappingTable
        List<MappingTable> mappings = mappingTableRepository.findAll();

        for (MappingTable mapping : mappings) {
            if (!mapping.getSecure()) {  // Only process non-secure mappings
                String sourceColumnName = mapping.getSourceColumnName();
                String pattern = mapping.getPattern();
                String destinationColumnName = mapping.getDestinationColumnName(); // Assuming this field exists in MappingTable

                // Fetch data from Employees table based on source column name
                List<Employees> employees = employeeRepository.findAll();

                for (Employees employee : employees) {
                    // Prepare the transformed value based on the column and pattern
                    String transformedValue = applyPattern(employee, sourceColumnName, pattern);

                    // Prepare the Emp entity with transformed values
                    Emp emp = new Emp();

                    // Mapping for first_name and last_name
                    emp.setFirstName(employee.getFname()); // first_name as fname
                    emp.setLastName(employee.getLname()); // last_name as lname

                    // Mapping for pan_number with transformation
                    if ("pan".equals(sourceColumnName)) {
                        emp.setPanNumber(transformedValue); // Apply pattern transformation to pan_number
                    }

                    // Insert transformed data into the Emp table
                    empRepository.save(emp);
                }
            }
        }
    }

    private String applyPattern(Employees employee, String sourceColumn, String pattern) {
        String value = "";

        // Get the value based on the source column
        switch (sourceColumn) {
            case "id":
                value = employee.getId().toString(); // id remains unchanged
                break;
            case "fname":
                value = employee.getFname(); // fname remains unchanged
                break;
            case "lname":
                value = employee.getLname(); // lname remains unchanged
                break;
            case "pan":
                value = employee.getPan(); // pan needs transformation
                break;
        }

        // Apply pattern transformation for pan_number (if the column is 'pan')
        if (value != null && "pan".equals(sourceColumn)) {
            StringBuilder result = new StringBuilder();
            for (char ch : pattern.toCharArray()) {
                if (ch == '$') {
                    result.append(generateRandomAlphabet()); // Replace $ with a random letter
                } else if (ch == '#') {
                    result.append(generateRandomNumber()); // Replace # with a random digit
                } else {
                    result.append(ch); // Keep other characters as is
                }
            }
            return result.toString();
        }

        // Return original value for non-pan columns
        return value;
    }
}
