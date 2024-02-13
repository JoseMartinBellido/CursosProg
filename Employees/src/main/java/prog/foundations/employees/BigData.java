package prog.foundations.employees;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class BigData {
    public static void main(String[] args) {
        try {

            long startTime = System.currentTimeMillis();
            long totalSalaries = Files.lines(Path.of("Hr5m.csv"))
                    .skip(1)
                    // Getting the salary
                    .map(s -> Long.parseLong(s.split(",")[25]))
                    .reduce(0L, Long::sum);

            long endTime = System.currentTimeMillis();

            System.out.printf("%,d,00€%n", totalSalaries);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
