package com.example.demo.service;

import com.example.demo.entity.Employees;
import com.example.demo.entity.Emp;
import com.example.demo.entity.MappingTable;
import com.example.demo.repository.EmployeesRepository;
import com.example.demo.repository.EmpRepository;
import com.example.demo.repository.MappingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private char generateRandomAlphabet() {
        return (char) ('a' + new Random().nextInt(26));
    }

    private int generateRandomNumber() {
        return new Random().nextInt(10);
    }

    @Transactional
    public void transformAndInsertData() {
        List<Employees> employees = employeeRepository.findAll();
        List<MappingTable> mappings = mappingTableRepository.findAll();

        for (MappingTable mapping : mappings) {
            if (!mapping.getSecure() && "pan".equals(mapping.getSourceColumnName())) {
                // Proceed only if 'secure' is false and source column is 'pan'
                String pattern = mapping.getPattern();

                List<Emp> empList = new ArrayList<>();

                for (Employees employee : employees) {
                    String transformedValue = applyPattern(employee, "pan", pattern);

                    // Only insert if transformed PAN is different from original
                    if (transformedValue != null && !transformedValue.equals(employee.getPan())) {
                        Emp emp = new Emp();
                        emp.setFirstName(employee.getFname());
                        emp.setLastName(employee.getLname());
                        emp.setPanNumber(transformedValue);

                        empList.add(emp);
                    }
                }

                // Insert only modified PAN values
                if (!empList.isEmpty()) {
                    empRepository.saveAll(empList);
                }
            }
        }
    }

    private String applyPattern(Employees employee, String sourceColumn, String pattern) {
        String value = "";

        switch (sourceColumn) {
            case "id":
                value = employee.getId().toString();
                break;
            case "fname":
                value = employee.getFname();
                break;
            case "lname":
                value = employee.getLname();
                break;
            case "pan":
                value = employee.getPan();
                break;
        }

        if (value != null && "pan".equals(sourceColumn)) {
            StringBuilder result = new StringBuilder();
            for (char ch : pattern.toCharArray()) {
                if (ch == '$') {
                    result.append(generateRandomAlphabet());
                } else if (ch == '#') {
                    result.append(generateRandomNumber());
                } else {
                    result.append(ch);
                }
            }
            return result.toString();
        }
        return value;
    }
}
