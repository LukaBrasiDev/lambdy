package pl.lukabrasi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        //  11. Zgrupuj ludzi, których suma liter w imieniu i nazwisku jest taka sama (edited)

        Map<Integer, List<Person>> grouped = personList
                .stream()
                .collect(Collectors.groupingBy(s -> s.getName().length() + s.getSurname().length()));
        System.out.println(grouped);

        //   10. Zamień wiek człowieka na psie lata, (n * 6 - 2), wyświetl tych ludzi, których wiek przekracza 50 lat po zmianie

       /* personList.stream()
                        .map(s -> new Person(s.getAge() * 6 - 2, s.getName(), s.getSurname())
                        .filter(s -> s.getAge() > 50)
                        .forEach(s -> System.out.println(s));*/


       /* 4. Wyobraź sobie, że tworzymy nadczłowieka. To człowiek składający się z wszystkich innych ludzi,
        jego imię tworzą pierwsze litery innych ludzi, jego nazwisko tworzą ostatnie litery innych ludzi,
        jego wiek to suma lat całej reszty ludzi (edited)*/

       personList.stream()
               .map(s -> new Person(s.getAge(),s.getName().substring(0,1),s.getSurname().substring(0,1)))
               .reduce((s,s1) -> new Person(s.getAge()+s1.getAge(),s.getName()+s1.getName(),s.getSurname()+s1.getSurname()))
               .ifPresent(s -> System.out.println(s.getName()+" : "+s.getAge()));


       //9.

        personList.stream()
                .map(s -> new Animal(s.getAge()/10, s.getName()+s.getSurname()))
                .forEach(s-> System.out.println(s));




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
