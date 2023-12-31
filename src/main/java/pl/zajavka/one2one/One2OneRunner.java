package pl.zajavka.one2one;

import pl.zajavka.HibernateUtil;

import java.util.List;

public class One2OneRunner {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.deleteAll();

        Customer customer1 = customerRepository.insertCustomer(ExampleData.someCustomer1());
        Customer customer2 = customerRepository.insertCustomer(ExampleData.someCustomer2());

        List<Customer> customers = customerRepository.listCustomers();
        customers.forEach(customer -> System.out.println("###c" + customer));


//        customerRepository.criteriaDeleteExample3();


//        customerRepository.listCustomers()
//                .forEach(customer -> System.out.println("### Customer listing: " + customer));
//
//        System.out.println("### Customer1: " + customerRepository.getCustomer(customer1.getId()));
//        System.out.println("### Customer2: " + customerRepository.getCustomer(customer2.getId()));
//
//        Address newAddress = Address.builder()
//                .country("Poland")
//                .city("Sopot")
//                .postalCode("81-713")
//                .address("Młyńska 2")
//                .build();
//        customerRepository.updateCustomer(customer1.getId(), newAddress);
//        System.out.println("### Customer1 update: " + customerRepository.getCustomer(customer1.getId()));
//
//        customerRepository.listCustomers()
//                .forEach(customer -> System.out.println("### Customer listing: " + customer));
//
//        customerRepository.deleteCustomer(customer2.getId());
//        customerRepository.listCustomers()
//                .forEach(customer -> System.out.println("### Customer listing: " + customer));

        HibernateUtil.closeSessionFactory();

    }
}
