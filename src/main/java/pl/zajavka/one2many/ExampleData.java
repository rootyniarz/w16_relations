package pl.zajavka.one2many;

class ExampleData {
    static Owner someOwner1() {
        return Owner.builder()
                .name("Stefan")
                .surname("Nowacki")
                .phone("+48 589 245 114")
                .email("stefan@zajavka.pl")
                .build();
    }

    static Owner someOwner2() {
        return Owner.builder()
                .name("Adrian")
                .surname("Paczkomat")
                .phone("+48 894 256 331")
                .email("adrian@zajavka.pl")
                .build();
    }

    static Owner someOwner3() {
        return Owner.builder()
                .name("Anna")
                .surname("Wanna")
                .phone("+48 999 77 331")
                .email("anna_wanna@zajavka.pl")
                .build();
    }

    static Owner someOwner4() {
        return Owner.builder()
                .name("Justyna")
                .surname("Przyczyn")
                .phone("+48 000 111 222")
                .email("just@zajavka.pl")
                .build();
    }

    static Owner someOwner5() {
        return Owner.builder()
                .name("Sylwia")
                .surname("Młynek")
                .phone("+48 987 654 321")
                .email("sylviaa@zajavka.pl")
                .build();
    }

    static Pet somePet1() {
        return Pet.builder()
                .name("Fafik")
                .breed(Breed.DOG)
                .build();
    }

    static Pet somePet2() {
        return Pet.builder()
                .name("Kiciek")
                .breed(Breed.CAT)
                .build();
    }

    static Pet somePet3() {
        return Pet.builder()
                .name("Szymek")
                .breed(Breed.MONKEY)
                .build();
    }

    static Pet somePet4() {
        return Pet.builder()
                .name("Gucio")
                .breed(Breed.DOG)
                .build();
    }

    static Pet somePet5() {
        return Pet.builder()
                .name("Filemon")
                .breed(Breed.CAT)
                .build();
    }

    static Pet somePet6() {
        return Pet.builder()
                .name("Reksio")
                .breed(Breed.DOG)
                .build();
    }

    public static Toy someToy1() {
        return Toy.builder()
                .what("car")
                .color("black")
                .build();
    }
    public static Toy someToy2() {
        return Toy.builder()
                .what("cat")
                .color("white")
                .build();
    }
    public static Toy someToy3() {
        return Toy.builder()
                .what("bear")
                .color("green")
                .build();
    }
    public static Toy someToy4() {
        return Toy.builder()
                .what("duck")
                .color("blue")
                .build();
    }
}