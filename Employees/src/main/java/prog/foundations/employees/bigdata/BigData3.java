package prog.foundations.employees.bigdata;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class BigData3 {

  record Person(String firstName, String lastName, BigDecimal salary, String state, char gender) {}
  
  public static void main(String[] args) {
    try {

      long startTime = System.currentTimeMillis();
      
      // The following instruction declares a map between the state and the summing of all salaries
      TreeMap<String, Map<Character, String>> result = Files.lines(Path.of("people.csv")).skip(1)
          .map(s -> s.split(","))
          .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
          //.collect(Collectors.groupingBy(Person::state, TreeMap::new, 
          //    Collectors.collectingAndThen(Collectors.summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
          .collect(
              // Groups by state in a map of String (state) -> Map (Map gender -> long)
              Collectors.groupingBy(Person::state, TreeMap::new
                  // Groups the new Maps by gender with a char (gender) -> long (salary)
              , Collectors.groupingBy(Person::gender, 
                  Collectors.collectingAndThen(
                      // Calculates the average salary per gender (per state)
                      Collectors.reducing(BigDecimal.ZERO, Person::salary, (a,b) -> a.add(b)),
                      NumberFormat.getCurrencyInstance()::format))));

      
      
      Map<Boolean, Map<String, Long>> result2 = Files.lines(Path.of("people.csv")).skip(1)
          .map(s -> s.split(","))
          .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
          .collect(Collectors.partitioningBy(p -> p.gender == 'F', 
              Collectors.groupingBy(Person::state, Collectors.counting())));


      long endTime = System.currentTimeMillis();

      System.out.println(endTime - startTime);
      System.out.println(result);
      System.out.println(result2);
      
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
