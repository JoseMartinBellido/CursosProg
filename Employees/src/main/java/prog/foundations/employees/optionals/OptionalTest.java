package prog.foundations.employees.optionals;

import java.util.Optional;

public class OptionalTest {

  public static void main(String[] args) {

    String msg = null;
    //Permits the object be null
    Optional<String> optMsg = Optional.ofNullable(msg);
    //Doesn't permit the object be null
    //Optional<String> optMsg2 = Optional.of(msg2);
    
    // These instructions makes the program blow up
    //System.out.println(optMsg2);
    //System.out.println(optMsg2.get());
    
    // It can be checked, but this way can be written more efficiently
    if (optMsg.isPresent()) {
    // if (!optMsg.isEmpty()) {
      System.out.println(optMsg);
    } else {
      System.out.println("bla bla bla");
    }
    
    // This way is better (Two both are similar)
    System.out.println(optMsg.orElse("alternative").toUpperCase());
    System.out.println(optMsg.orElseGet(() -> "my alt"));
    System.out.println(optMsg.or(() -> Optional.of("nothing here")));
    
    // It's mandatory to throw a RuntimeException type
    //System.out.println(optMsg.orElseThrow(() -> new IllegalArgumentException()));
    
    System.out.println(optMsg.filter(s -> s.length() > 3).orElse("Invalid"));
    
    String msg2 = "Cat";
    Optional<String> optMsg2= Optional.ofNullable(msg2);
    System.out.println(optMsg2.filter(s -> s.length() > 3).orElse("Invalid"));
    
  }

}
