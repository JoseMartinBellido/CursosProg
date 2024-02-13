package prog.foundations.employees;

import java.time.LocalDate;

public record Weirdo(String lastName, String firstName, LocalDate dob) implements Comparable<Weirdo>{
    @Override
    public int compareTo(Weirdo otherWeirdo) {
        return lastName.compareTo(otherWeirdo.lastName);
    }
}
