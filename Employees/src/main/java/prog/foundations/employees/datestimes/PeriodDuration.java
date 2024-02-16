package prog.foundations.employees.datestimes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PeriodDuration {

  public static void main(String[] args) {

    // LocalDate
    LocalDate d1 = LocalDate.of(2000, 1, 1);
    LocalDate d2 = LocalDate.of(2002, 6, 10);
    
    // LocalTime
    LocalTime lt1 = LocalTime.of(10, 30);
    LocalTime lt2 = LocalTime.of(13, 57, 1);
    
    // LocalDateTime
    LocalDateTime ldt1 = LocalDateTime.of(d1, lt1);
    LocalDateTime ldt2 = LocalDateTime.of(d2, lt2);
    
    // Periods for dates, Durations for times
    Period dateDiff = Period.between(d1, d2);
    System.out.println(dateDiff);
    System.out.printf("%d years, %d months, %d days%n"
        , dateDiff.getYears(), dateDiff.getMonths(), dateDiff.getDays());
    
    Duration timeDiff = Duration.between(lt1, lt2);
    System.out.println(timeDiff.toHours());
    
    // ZonedDateTime
    ZonedDateTime zdt1 = ZonedDateTime.of(ldt1, ZoneId.systemDefault());
    System.out.println(zdt1);
    
    ZonedDateTime zdt2 = ZonedDateTime.of(ldt1, ZoneId.of("-5"));
    System.out.println(zdt2);
    
    LocalDateTime xmas = LocalDateTime.of(2021, 12, 25, 12, 00);
    // California - GMT-08
    ZonedDateTime zxmas = ZonedDateTime.of(xmas, ZoneId.of("-8"));
    // This method transforms a LocalDateTime from one Zone to another one
    System.out.println(zxmas.withZoneSameInstant(ZoneId.of("+0")));
    
  }

}
