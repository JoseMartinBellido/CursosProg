package prog.foundations.employees.bigdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class BigData {

  record Person(String firstName, String lastName, long salary, String state) {}
  
  public static void main(String[] args) {
    try {

      long startTime = System.currentTimeMillis();
      
      Long result = Files.lines(Path.of("Hr5m.csv")).skip(1)
          // Getting the salary
          .map(s -> s.split(","))
          .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
          .collect(Collectors.summingLong(Person::salary));


      long endTime = System.currentTimeMillis();

      System.out.printf("$%,d.00%n", result);
      System.out.println(endTime - startTime);
      
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
