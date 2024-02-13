package prog.foundations.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsStuff {
    public static void main(String[] args) {
        String peopleText = """
            Flinstone, Fred, 01/01/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone2, Fred2, 01/01/1900, Programmer, {locpd=1300,yoe=14,iq=100}
            Flinstone3, Fred3, 01/01/1900, Programmer, {locpd=2300,yoe=8,iq=185}
            Flinstone4, Fred4, 01/01/1900, Programmer, {locpd=1630,yoe=3,iq=115}
            Flinstone5, Fred5, 01/01/1900, Programmer, {locpd=5,yoe=10,iq=100}
            Rubble, Barney, 02/02/1905, Manager, {orgSize=300, dr=10}
            Rubble2, Barney2, 02/02/1905, Manager, {orgSize=300, dr=4}
            Rubble3, Barney3, 02/02/1905, Manager, {orgSize=300, dr=2}
            Rubble4, Barney4, 02/02/1905, Manager, {orgSize=300, dr=20}
            Rubble5, Barney5, 02/02/1905, Manager, {orgSize=300, dr=8}
            Flinstone, Wilma, 03/03/1910, Analyst, {projectCount=3}
            Flinstone2, Wilma2, 03/03/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma3, 03/03/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma4, 03/03/1910, Analyst, {projectCount=8}
            Flinstone5, Wilma5, 03/03/1910, Analyst, {projectCount=9}
            Rubble, Betty, 04/04/1915, CEO, {avgStockPrice=300}
            RubbleFake, BettyFake, 04/04/1915, CEOFake, {avgStockPrice=300}
            """;

        try {
            Map<String, List<Employee>> empsBySurname = Files.lines(
                    Path.of("C:/Users/Jose/IdeaProjects/Employees/src/main/java/prog/foundations/employees/employees.txt"))
                    .map(Employee::createEmployee)
                    .map(e -> (Employee)e)
                    .collect(Collectors.groupingBy(Employee::getLastName));

            System.out.println(empsBySurname);

        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }

        // Consumer -> void return
        /*peopleText.lines()
                .forEach(System.out::println);
        */        //.forEach(s -> System.out.println(s));

//        peopleText.lines()
//                .map(Employee::createEmployee)
//                .forEach(System.out::println);

        Collection<String> nums = List.of("one", "two", "three", "four", "five");
        //nums.stream()
        Stream.of("one", "two", "three", "four", "five")
                .map(String::toUpperCase)
                .map(String::hashCode)
                .map(Integer::toHexString)
                .forEach(System.out::println);

        record Car(String make, String model){}

        Stream.of(new Car("Ford", "Bronco"), new Car("Tesla", "X")
        , new Car("Tesla", "3"))
                .filter(c -> c.make.equals("Tesla"))
                .forEach(System.out::println);


        // Check if the value of the variable is null. In that case, it's ignored
        String myVar = null;
        Stream.ofNullable(myVar).forEach(System.out::println);

        // Stream of int numbers. If we use only Stream, it's composed of Integers
        IntStream.of(1, 2, 3, 4, 5)
                .forEach(System.out::print);

        System.out.println();

        // range is exclusive
        IntStream.range(1, 11)
                .forEach(System.out::print);

        System.out.println();

        // rangeClosed is inclusive
        // map only works with objects, but IntStream works with primitive data.
        // It's necessary mapToObj
        IntStream.rangeClosed(1, 5)
                .mapToObj(String::valueOf)
                .map(s -> s.concat("-"))
                .forEach(System.out::print);

        System.out.println();

        // boxed() converts the primitive types to their Wrappers
        IntStream.rangeClosed(1, 5)
                .boxed()
                .map(String::valueOf)
                .map(s -> s.concat("-"))
                .forEach(System.out::print);

        System.out.println();

        String[] names = {"terry", "sam", "jake"};
        Arrays.stream(names)
                .map(String::toUpperCase)
                .forEach(System.out::print);

    }

}
