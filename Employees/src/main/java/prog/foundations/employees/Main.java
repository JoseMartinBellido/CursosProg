package prog.foundations.employees;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
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

        // General regex for all the text
        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<birthDate>\\d{1,2}/\\d{1,2}/\\d{4}),"
                + "\\s*(?<role>\\w+),\\s*\\{(?<details>.*)}\\n";

        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        // Total salaries of all employees
        int totalSalaries = 0;
        IEmployee employee;
        // Matching the people regex
        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            totalSalaries += employee.getSalary();
            System.out.println(employee);

        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", currencyInstance.format(totalSalaries));

    }

}
