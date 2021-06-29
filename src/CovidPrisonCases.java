import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class CovidPrisonCases {
    static List<List<String>> records;

    private String state;
    private int totalStaffCases;
    private int totalStaffDeaths;
    private int staffPartialVax;
    private int staffFullVax;
    private int totalPrisonerCases;
    private int totalPrisonerDeaths;
    private int prisonerPartialVax;
    private int prisonerFullVax;
    private int staffPop;
    private int prisonerPop;

    public CovidPrisonCases(String state, int totalStaffCases, int totalStaffDeaths, int staffFullVax, int totalPrisonerCases, int totalPrisonerDeaths, int prisonerFullVax, int prisonerPartialVax, int staffPartialVax, int staffPop, int prisonerPop) {
        this.state = state;
        this.totalStaffCases = totalStaffCases;
        this.totalStaffDeaths = totalStaffDeaths;
        this.staffPartialVax = staffPartialVax;
        this.staffFullVax = staffFullVax;
        this.totalPrisonerCases = totalPrisonerCases;
        this.totalPrisonerDeaths = totalPrisonerDeaths;
        this.prisonerPartialVax = prisonerPartialVax;
        this.prisonerFullVax = prisonerFullVax;
        this.staffPop = staffPop;
        this.prisonerPop = prisonerPop;

    }

    public CovidPrisonCases(String[] fields) {
        this.state = fields[0];
        this.totalStaffCases = Integer.parseInt(fields[1]);
        this.totalStaffDeaths = Integer.parseInt(fields[2]);
        this.staffPartialVax = Integer.parseInt(fields[3]);
        this.staffFullVax = Integer.parseInt(fields[4]);
        this.totalPrisonerCases = Integer.parseInt(fields[5]);
        this.totalPrisonerDeaths = Integer.parseInt(fields[6]);
        this.prisonerPartialVax = Integer.parseInt(fields[7]);
        this.prisonerFullVax = Integer.parseInt(fields[8]);
        this.staffPop = Integer.parseInt(fields[9]);
        this.prisonerPop = Integer.parseInt(fields[10]);
        
    }

    public String getState() {return state;}

    public int getTotalStaffCases() {return totalStaffCases;}

    public int getTotalStaffDeaths() {return totalStaffDeaths;}

    public int getStaffPartialVax() {return staffPartialVax;}

    public int getStaffFullVax() {return staffFullVax;}

    public int getTotalPrisonerCases() {return totalPrisonerCases;}

    public int getTotalPrisonerDeaths() {return totalPrisonerDeaths;}

    public int getPrisonerPartialVax() {return prisonerPartialVax;}

    public int getPrisonerFullVax() {return prisonerFullVax;}

    public int getStaffPop() {return staffPop;}

    public int getPrisonerPop() {return prisonerPop;}

    public static ArrayList<CovidPrisonCases> readDataFile(String fileName) throws IOException {
        ArrayList<CovidPrisonCases> data = new ArrayList<>();
        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);
        fileReader.nextLine();

        while (fileReader.hasNextLine()) {
            String[] fields = fileReader.nextLine().split(",");
            CovidPrisonCases cases = new CovidPrisonCases(fields);
            data.add(cases);
        }

        fileReader.close();
        return data;
        
    }

   public BigDecimal covidPrisonerDeathsPercent() {
    BigDecimal a1 = new BigDecimal(getTotalPrisonerDeaths());
    BigDecimal a2 = new BigDecimal(getTotalPrisonerCases());
    BigDecimal b = new BigDecimal(100);

    a1.multiply(b);
    a1.divide(a2);
    return a1;
   }


   public BigDecimal covidStaffDeathsPercent() {
    BigDecimal a1 = new BigDecimal(getTotalStaffDeaths());
    BigDecimal a2 = new BigDecimal(getTotalStaffCases());
    BigDecimal b = new BigDecimal(100);

    a1.multiply(b);
    a1.divide(a2);
    return a1;
   }
}
