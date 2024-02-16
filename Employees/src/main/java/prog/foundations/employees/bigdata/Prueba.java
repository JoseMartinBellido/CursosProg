package prog.foundations.employees.bigdata;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Prueba {

  public static void main(String[] args) {

    String prueba = "05:05:12 PM";
    
    //LocalTime a = LocalTime.parse(prueba, DateTimeFormatter.ofPattern("hh:mm:ss a"));
    
    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
    
  }

}
