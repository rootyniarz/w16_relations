package pl.zajavka.one2one;

public class ExampleData {
    static Address someAddress1() {
        return Address.builder()
                .country("Poland")
                .city("Szczecin")
                .postalCode("70-112")
                .address("Witolda Starkiewicza 3")
                .build();
    }

    static Address someAddress2() {
        return Address.builder()
                .country("Poland")
                .city("Gdynia")
                .postalCode("81-357")
                .address("3 maja 16")
                .build();
    }

    static Customer someCustomer1() {
        return Customer.builder()
                .name("Stefan")
                .surname("Nowacki")
                .phone("+48 589 245 114")
                .email("stefan@zajavka.pl")
                .address(someAddress1())
                .build();
    }

    static Customer someCustomer2() {
        return Customer.builder()
                .name("Adrian")
                .surname("Paczkomat")
                .phone("+48 894 256 331")
                .email("adrian@zajavka.pl")
                .address(someAddress2())
                .build();
    }
}
