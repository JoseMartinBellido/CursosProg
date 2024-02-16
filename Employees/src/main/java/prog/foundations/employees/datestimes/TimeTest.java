package prog.foundations.employees.datestimes;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class TimeTest {

  public static void main(String[] args) {

    LocalDate now = LocalDate.now();
    
    // Adding to a date
    System.out.println(now.plusDays(5));
    System.out.println(now.plus(3, ChronoUnit.YEARS));
    System.out.println(now.minus(3, ChronoUnit.DECADES));
    
    // Knowing the new years day of 2010
    LocalDate newYears = LocalDate.of(2010, 1, 1);
    System.out.println(newYears.getDayOfWeek());
    
    // Dates between 2 dates (Stream) using a period
    List<LocalDate> leapYears = newYears.datesUntil(LocalDate.now(), Period.ofYears(1))
      .filter(LocalDate::isLeapYear)
      .collect(Collectors.toList());
    
    System.out.println(leapYears);
    
    // LocalDate implements overrides equals and implements Comparable, so 2 dates with the same year, month and day
    // are equal objects
    LocalDate d1 = LocalDate.of(2000, 1, 1);
    LocalDate d2 = LocalDate.of(2000, 1, 1);
    System.out.println(d1.equals(d2));
    System.out.println(d1.compareTo(d2));
    
    // LocalTime
    System.out.println(LocalTime.now());
    
    // Same equals and compareTo than LocalDate
    LocalTime lt1 = LocalTime.of(10, 30);
    LocalTime lt2 = LocalTime.of(10, 30, 1);
    System.out.println(lt1.equals(lt2));
    
    // There is a combination of two both classes
    LocalDateTime ldt1 = LocalDateTime.of(d1, lt1);
    LocalDateTime ldt2 = LocalDateTime.of(d2, lt2);
    System.out.println(ldt1.equals(ldt2));
    System.out.println(ldt1);
    
    // Calculating the period between 2 LocalDates
    
    
    // It's possible to extract the shaping from these classes
    Period diff = Period.between(d1, d2);
    System.out.printf("%d years, %d months, %d days%n", diff.getYears(), diff.getMonths(), diff.getDays());
    
    System.out.println(Instant.now());
    
  }

}
