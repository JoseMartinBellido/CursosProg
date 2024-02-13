package prog.foundations.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee{

    private int linesOfCode = 0;
    private int yearsOfExp = 0;
    private int iq = 0;

    private final String progRegex = "\\w+=(?<locpd>\\w+),\\s*\\w+=(?<yoe>\\w+),\\s*\\w+=(?<iq>\\w+)";
    private final Pattern progPat = Pattern.compile(progRegex);

    public Programmer(String personText) {
        super(personText);
        Matcher progMat = progPat.matcher(peopleMat.group("details"));
        if (progMat.find()) {
            linesOfCode = Integer.parseInt(progMat.group("locpd"));
            yearsOfExp = Integer.parseInt(progMat.group("yoe"));
            iq = Integer.parseInt(progMat.group("iq"));
        }
    }

    public int getSalary() {
        return 3000 + linesOfCode * yearsOfExp + iq;
    }

}
