package zozo.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("Список фамилий призывников: ");
        persons.stream()
                .filter(value -> value.getSex() == Sex.MAN)
                .filter(value -> value.getAge()>= 18 && value.getAge() < 28)
                .map(value -> value.getFamily() )
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Количество несовершеннолетних: ");
        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println(count);

        System.out.println("Список потенциально работоспособных людей с высшим образованием: ");
                persons.stream()
                .filter(value -> (value.getAge() >= 18) && (value.getSex() == Sex.MAN ? value.getAge() < 65 : value.getAge() < 60))
                .filter(value -> value.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(value -> value.getFamily()))
                .collect(Collectors.toList())
                .forEach(System.out::println);


    }
}