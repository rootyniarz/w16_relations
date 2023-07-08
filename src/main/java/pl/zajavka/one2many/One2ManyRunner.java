package pl.zajavka.one2many;

import pl.zajavka.HibernateUtil;

public class One2ManyRunner {
    public static void main(String[] args) {
        OwnerRepository ownerRepository = new OwnerRepository();
        PetRepository petRepository = new PetRepository();
//        ownerRepository.insertData(ExampleData.someOwner1(),Set.of(ExampleData.somePet1(), ExampleData.somePet2()));
//        ownerRepository.insertData(ExampleData.someOwner2(),Set.of(ExampleData.somePet3(), ExampleData.somePet4()));
//        ownerRepository.insertData(ExampleData.someOwner3(),Set.of());
//        ownerRepository.insertData(ExampleData.someOwner4(),Set.of());
//        ownerRepository.insertData(ExampleData.someOwner5(),Set.of());
//        petRepository.insertData(ExampleData.somePet5());
//        petRepository.insertData(ExampleData.somePet6());


//        ownerRepository.selectExample2()
//                .forEach(entity -> System.out.println("Entity: " + Arrays.asList(entity)));
//        ownerRepository.selectExample2a()
//                .forEach(entity -> System.out.println("Entity: " + entity));
//        ownerRepository.selectExample3()
//                .forEach(entity -> System.out.println("Entity: " + entity));
//        ownerRepository.selectExample4()
//                .forEach(entity -> System.out.println("Entity: " + entity));
//        ownerRepository.selectExample5()
//                .forEach(entity -> System.out.println("Entity: " + entity));
//        System.out.println("RESULT: " + ownerRepository.selectExample6("Justyna"));

//        ownerRepository.saveTestData();
        ownerRepository.selectExample9();

//       ownerRepository.insertHQL();
//        System.out.println(ownerRepository.findAll());
//        ownerRepository.updateHQL("romek@zajavka.pl","997","nowyemail@mail.pl");
//        System.out.println("Usunieto:" + ownerRepository.deleteHQL("nowyemail@mail.pl"));
//        ownerRepository.deleteAll();
//
//        Owner owner1 = ownerRepository.insertData(
//                ExampleData.someOwner1(),
//                Set.of(ExampleData.somePet1(), ExampleData.somePet2()));
//
//        Owner owner2 = ownerRepository.insertData(
//                ExampleData.someOwner2(),
//                Set.of(ExampleData.somePet3(), ExampleData.somePet4()));
//
//        ownerRepository.listOwners()
//                .forEach(owner -> System.out.println("###Owner listing: " + owner));
//
//        System.out.println("###Owner1: " + ownerRepository.getOwner(owner1.getId()));
//        System.out.println("###Owner2: " + ownerRepository.getOwner(owner2.getId()));
//
//        Pet newPet = Pet.builder().name("Drapek").breed(Breed.MONKEY).owner(owner1).build();
//        ownerRepository.updateOwner(owner1.getId(), newPet);
//
//        System.out.println("###Owner updated: " + ownerRepository.getOwner(owner1.getId()));
//
//        ownerRepository.listOwners()
//                .forEach(owner -> System.out.println("###Owner listing: " + owner));
//
//        ownerRepository.deleteOwner(owner2.getId());
//        ownerRepository.listOwners()
//                .forEach(owner -> System.out.println("###Owner listing: " + owner));

        HibernateUtil.closeSessionFactory();
    }

}
