package pl.zajavka.many2many;

import pl.zajavka.HibernateUtil;

import java.util.List;
import java.util.Set;

public class SecondLevelCacheExample {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.deleteAll();

        List<Employee> employeesCreated = createEmployees(employeeRepository);

        employeeRepository.listEmployees()
                .forEach(employee -> System.out.println("###Employee listing: " + employee));

        employeeRepository.getEmployeeWithStats(3);
        employeeRepository.getEmployeeWithStats(3);
        HibernateUtil.closeSessionFactory();
    }

    private static List<Employee> createEmployees(final EmployeeRepository employeeRepository) {
        Project project1 = ExampleData.someProject1();
        Project project2 = ExampleData.someProject2();
        Project project3 = ExampleData.someProject3();
        Employee employee1 = ExampleData.someEmployee1();
        Employee employee2 = ExampleData.someEmployee2();
        Employee employee3 = ExampleData.someEmployee3();
        employee1.setProjects(Set.of(project1, project2));
        employee2.setProjects(Set.of(project2));
        employee3.setProjects(Set.of(project2, project3));
        return employeeRepository.insertData(List.of(employee1, employee2, employee3));
    }

}
