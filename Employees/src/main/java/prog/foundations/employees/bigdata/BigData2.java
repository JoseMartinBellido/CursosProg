package prog.foundations.employees.bigdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import prog.foundations.employees.bigdata.BigData.Person;

public class BigData2 {

  record Person(String firstName, String lastName, long salary, String state) {}
  
  public static void main(String[] args) {
    try {

      long startTime = System.currentTimeMillis();
      
      
      // The following declaration defines a map grouping by state -> person
      Map<String, List<Person>> people = Files.lines(Path.of("people.csv")).skip(1)
          // Getting the salary
          .map(s -> s.split(","))
          .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
          // groupingBy groups all elements by 1 parameter using a function. In this case, we group by state
          // MapFactory is, basically, the constructor of the type of the map
          // The collector's downstream is the second type of elements in the map
          .collect(Collectors.groupingBy(Person::state, TreeMap::new, Collectors.toList()));
      
      // The following instruction declares a map between the state and the summing of all salaries
      Map<String, String> salaryMap = Files.lines(Path.of("people.csv")).skip(1)
          .map(s -> s.split(","))
          .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
          //.collect(Collectors.groupingBy(Person::state, TreeMap::new, 
          //    Collectors.collectingAndThen(Collectors.summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
          .collect(Collectors.groupingBy(Person::state, TreeMap::new, 
                  Collectors.collectingAndThen(Collectors.summingLong(Person::salary), 
                      NumberFormat.getCurrencyInstance()::format)));
      
      salaryMap.forEach((key, value) -> System.out.println(key + " -> " + value));

      long endTime = System.currentTimeMillis();

      System.out.println(people);
      System.out.println(endTime - startTime);
      
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
