package prog.foundations.employees;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class StreamStuff5 {
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


        Predicate<Employee> dummySelector = e -> "N/A".equals(e.getLastName());

        boolean allOver3k = peopleText.lines()
                .map(Employee::createEmployee)
                .map(e -> (Employee)e)
                .filter(dummySelector.negate())
                .anyMatch(e -> e.getSalary() > 20000);
                //.allMatch(e -> e.getSalary() > 3000);
                //.noneMatch(e -> e.getSalary() < 0>);

        System.out.println(allOver3k);

        Optional<Employee> optionalEmp = peopleText.lines()
                .map(Employee::createEmployee)
                .map(e -> (Employee)e)
                .filter(dummySelector.negate())
                .findFirst();

        System.out.println(optionalEmp
                .map(Employee::getFirstName)
                .orElse("nobody"));
    }
}
