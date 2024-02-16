package prog.foundations.employees.bigdata;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


public class BigDataDate {

  record Person(String firstName, String lastName, BigDecimal salary, String state, char gender
      , LocalDate birthDate, LocalTime birthTime) {
    
    int getAge() {
      return Period.between(LocalDate.now(), birthDate).getYears();
    }
  }
  
  public static void main(String[] args) {
    try {
      
      // The following instruction declares a map between the state and the summing of all salaries
      List<Person> map = Files.lines(Path.of("people.csv")).skip(1)
          .map(s -> s.split(","))
          .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)
              , LocalDate.parse(a[10], DateTimeFormatter.ofPattern("M/d/yyyy"))
              , LocalTime.parse(a[11], DateTimeFormatter.ofPattern("hh:mm:ss a"))))
          .collect(Collectors.toList());

      int a = 5;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
