package pl.lukabrasi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //klasy anonimowe (lambdy)
        Math math = new Math() {
            @Override
            public double calculate(double a, double b) {
                return a * b;
            }
        };

        Math math1 = (a, b) -> a * b;

        System.out.println(math.calculate(5, 6));
        System.out.println(math1.calculate(5, 6));

//operacje na strumieniach -- operacja filtrująca
        List<String> names = Arrays.asList("Dominik", "MAgda", "Zofia", "Stefan");

        //operacje na strumieniach -- operacja mapująca
        names.stream().filter(s -> s.endsWith("a"))
                .map(s -> s.substring(0, s.length() - 1))
                .forEach(s -> System.out.println(s));

        //inne metody:
        //count()
        //distinct()
        //sorted((s,s1)-> s.compareTo(s1)* -1)
        //reduce((s,s1)-> s+s1) redukuje zbior do jednego elementu w sposob wskazany jak ma polaczyc
        //max((s,s1)->Integer.compare(s.lenght(),s1.lenght()) //zwrocenie najdluzszej wartosci na podstawie uzycia metody compare z klasy integer
    //collect(Collectors.toList())


        //grupowanie
        List<String>names2 = Arrays.asList("Kamil","Lukas","Ola","Jan");

        Map<Integer,List<String>> stringListMap = names2.stream()
                .collect(Collectors.groupingBy(s-> s.length()));
        System.out.println(stringListMap);



    }
}
