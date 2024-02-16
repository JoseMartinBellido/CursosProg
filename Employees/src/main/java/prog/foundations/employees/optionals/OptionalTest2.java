package prog.foundations.employees.optionals;

import java.time.Year;
import java.util.Optional;

public class OptionalTest2 {

  record Car(String make, String model, String color, Year year) {}
  
  record Person(String firstName, String lastName, Optional<Car> car) {}
  
  public static void main(String[] args) {

    Person p1 = new Person("Tom", "Thumb", Optional.of(new Car("Tesla", "X", "red", Year.of(2018))));
    Person p2 = new Person("Jerry", "Thumb", Optional.of(new Car("Tesla", "Y", "red", Year.of(2020))));
    Person p3 = null;
    
    Optional<Person> optPerson = Optional.of(p1);
    // Using this map, Person::car returns an Optional, not the String value
    System.out.println(optPerson
        // flatMap unwraps the optional
        .flatMap(Person::car)
        .map(Car::make) 
        .orElse("Unknown car make"));
    
  }

}
