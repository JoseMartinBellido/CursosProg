package prog.foundations.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// An object of an abstract class cannot be declared
public abstract class Employee implements IEmployee {
    
    protected String lastName;
    protected String firstName;
    protected LocalDate birthDate;

    protected final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    protected static final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<birthDate>\\d{1,2}/\\d{1,2}/\\d{4}),"
            + "\\s*(?<role>\\w+),\\s*\\{(?<details>.*)}";
    protected static final Pattern peoplePat = Pattern.compile(peopleRegex);
    protected final Matcher peopleMat;

    protected Employee() {
    peopleMat = null;
    lastName = "N/A";
    firstName = "N/A";
    birthDate = null;
    }

    public Employee(String personText) {
        peopleMat = peoplePat.matcher(personText);
        if (peopleMat.matches()) {
            lastName = peopleMat.group("lastName");
            firstName = peopleMat.group("firstName");
            birthDate = LocalDate.from(dtFormatter.parse(peopleMat.group("birthDate")));
        }
    }

    public static final IEmployee createEmployee(String employeeText) {
        Matcher peopleMat = peoplePat.matcher(employeeText);
        if (peopleMat.find()) {
            return switch (peopleMat.group("role")) {
                // Matching every role
                case "Programmer" -> new Programmer(employeeText);
                case "Manager" -> new Manager(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "CEO" -> new Ceo(employeeText);
                // Lambda to asign default
                //default -> () -> 0;
                default -> new DummyEmployee();
            };
        } else {
            // Anonymous Class
            return new Employee(){
                @Override
                public int getSalary() {
                    return 0;
                }
            };
        }

    }

    // An abstract method, when declared, hasn't body, as made in an interface
    // public double getBonus();
    @Override
    public abstract int getSalary();

    // It is possible to implement a body for a method that refers to another not defined abstract method
    public double getBonus() {
        return getSalary() * 1.1;
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s - %s", lastName, firstName, moneyFormat.format(getSalary()),
                moneyFormat.format(getBonus()));
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(birthDate, employee.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, birthDate);
    }

    // Static nested class
    private static final class DummyEmployee extends Employee {

        public DummyEmployee() {
            super();
        }

        @Override
        public int getSalary() {
            return 0;
        }
    }
}
