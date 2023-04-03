package com.example.streamapitutorial;

import com.example.streamapitutorial.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class StreamApiTutorialApplication {

    static List<Employee> empList = new ArrayList<>();

    static {
        empList.add(new Employee("Pradeep", "Ghanto", 15000.0, List.of("Project1", "Project2","Project2")));
        empList.add(new Employee("Kiran", "Nukala", 6000.0, List.of("Project2", "Project3")));
        empList.add(new Employee("Sasi", "NagiReddy", 4500.0, List.of("Project3", "Project4")));
    }

    public static void main(String[] args) {
        //  SpringApplication.run(StreamApiTutorialApplication.class, args);

        // case1: forEach
        System.out.println("CASE-1 :: Employee List:=");
        System.out.println("##################################");
        empList.stream().forEach(emp -> System.out.println(emp));

        // case2: filter & map()
        System.out.println("\nCASE-2 :: Filtered Employees whose sal>5000 \nSalary incremented using map function");
        System.out.println("##################################");
        List<Employee> filteredEmployeesList = empList.stream()
                .filter(emp -> emp.getSalary() > 5000.00)
                .map(emp -> new Employee(emp.getFirstName(), emp.getLastName(), emp.getSalary() * 1.10, emp.getProjects()))
                .collect(Collectors.toList());
        filteredEmployeesList.stream().forEach(emp -> System.out.println(emp));


        // case3: flatmap()
        System.out.println("\nCASE-3 :: Using flatMap() to get all employee projects:=");
        System.out.println("##################################");
        Set<String> employeeProjects = empList.stream()
                .map(emp -> emp.getProjects())
                .flatMap(projects -> projects.stream())
                .collect(Collectors.toSet());
        System.out.println("Employee projects:= "+ employeeProjects);


        // case4: max()
        System.out.println("\nCASE-4 :: Using max() to get highest salaried Employee=");
        System.out.println("##################################");
        Employee highestSalriedEmp =
                empList
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(highestSalriedEmp);

        // case5: sorted()
        System.out.println("\nCASE-5 :: Using sorted() to sort Employees by their Name=");
        System.out.println("##################################");
        List<Employee> sortedEmployees =
                        empList
                        .stream()
                        .sorted((e1,e2) -> e1.getFirstName().compareToIgnoreCase(e2.getFirstName()))
                                .collect(Collectors.toList());
        sortedEmployees.stream().forEach(emp->System.out.println(emp));
    }

}
