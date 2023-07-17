package pl.zajavka.cache;


public class CacheRunner {
    public static void main(String[] args) {
        CachedEmployeeRepository cachedEmployeeRepository = new CachedEmployeeRepository();
        cachedEmployeeRepository.deleteAll();

        cachedEmployeeRepository.insertData(ExampleData.someEmployee1());
        var employee = cachedEmployeeRepository.insertData(ExampleData.someEmployee2());
        cachedEmployeeRepository.insertData(ExampleData.someEmployee3());

        cachedEmployeeRepository.l1c(employee.getEmployeeId());
    }
}
