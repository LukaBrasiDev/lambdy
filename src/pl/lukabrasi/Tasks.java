package pl.lukabrasi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tasks {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(23, "Jan", "Kowalski"));
        personList.add(new Person(26, "Zofia", "Nowak"));
        personList.add(new Person(83, "Maria", "Wiśniewska"));
        personList.add(new Person(26, "Andrzej", "Kowalski"));
        personList.add(new Person(63, "Zenon", "Olszewski"));
        personList.add(new Person(27, "Kuba", "Wolski"));
        personList.add(new Person(33, "Maryla", "Wolska"));

//obliczeie sredniego wieku osob z listy
        personList.stream()
                .mapToInt(s -> s.getAge())
                .average()
                .ifPresent(s -> System.out.println(s));


        //obliczenie najstarszego i dodanie mu do imienia stringa mędrzec
        personList.stream()
                .max((s, s1) -> Integer.compare(s.getAge(), s1.getAge()))
                .ifPresent(s -> System.out.println("Mędrzec: " + s.getName()));

//zad8
        //    8. Usuń ludzi ze zbioru, którzy występują podwójnie (są zdublowani). Następnie podaj ile takich ludzi było. (istnieje do tego metoda, przeszukaj dokumentacje)

        System.out.println(personList.size() - personList
                .stream()
                .distinct()
                .count()
        );

//zad5
        //5. Stwórz listę lat ludzi (List<Integer>) na podstawie listy ludzi

        List<Integer> ageList;
        ageList = personList
                .stream()
                .map(s -> s.getAge())
                .collect(Collectors.toList());

        //6.  Znajdź człowieka, którego suma cyfr lat jest taka sama jak suma liter w imieniu


        personList.stream()
                .filter(s -> s.getName().length() == sumOfDigit(s.getAge()))
                .forEach(s -> System.out.println(s.getName()));

//7. Posortuj ludzi po wieku od najstarszego do najmłodszego
        personList.stream()
                .sorted((s, s1) -> Integer.compare(s.getAge(), s1.getAge()) * -1)
                .forEach(s -> System.out.println(s.getName() + " "));


    }


    public static int sumOfDigit(int age) {
        int sum = 0;
        while (age != 0) {
            sum += age % 10;
            age /= 10;
        }
        return sum;
    }
}
