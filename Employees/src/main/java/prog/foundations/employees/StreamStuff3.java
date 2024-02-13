package prog.foundations.employees;

import java.util.Comparator;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamStuff3 {

    public static void main(String[] args) {
        String peopleText = """
            Flinstone, Fred, 01/01/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone2, Fred2, 01/01/1900, Programmer, {locpd=1300,yoe=14,iq=100}
            Flinstone3, Fred3, 01/01/1900, Programmer, {locpd=2300,yoe=8,iq=185}
            Flinstone4, Fred4, 01/01/1900, Programmer, {locpd=1630,yoe=3,iq=115}
            Flinstone5, Fred5, 01/01/1900, Programmer, {locpd=5,yoe=10,iq=100}
            Flinstone5, Fred5, 01/01/1900, Programmerzzzzz, {locpd=5,yoe=10,iq=100}
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

        Predicate<Employee> dummyEmployeeSelector = e -> "N/A".equals(e.getLastName());
        Predicate<Employee> overFiveKSelector = e -> e.getSalary() > 5000;

        OptionalInt result = peopleText
                .lines()
                .map(Employee::createEmployee)
                .map(e -> (Employee)e)
                // It's the same doing Predicate.not() that ().negate()
                // It's possible to concat different predicate functions using and(), or using or() for two options
                .filter((dummyEmployeeSelector).negate().and(overFiveKSelector))
                .collect(Collectors.toSet()).stream()
                .sorted(Comparator.comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName)
                        .thenComparingInt(Employee::getSalary))
                .skip(5)
                .mapToInt(StreamStuff3::showEmployeeAndGetSalary)
                .max();

        System.out.println(result.orElse(0));

    }

    public static int showEmployeeAndGetSalary(IEmployee e) {
        System.out.println(e);
        return e.getSalary();
    }
}
