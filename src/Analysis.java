import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

public class Analysis {

    public static TreeMap<String, Double> percentPrisonerVax(ArrayList<CovidPrisonCases> data) {
        TreeMap<String,Double> vaxResults = new TreeMap<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getPrisonerPop() != 0) {
                String state = data.get(i).getState();
                Double percent = (double) (data.get(i).getPrisonerFullVax() + data.get(i).getPrisonerPartialVax() * 100 / data.get(i).getPrisonerPop());
                percent = (double) Math.round(percent * 1000d) / 1000d;
                percent /= 10;
                vaxResults.put(state, percent);
            }

            else {
                String state = data.get(i).getState();
                vaxResults.put(state, null);
            }
        }
        return vaxResults;
    
    }
    
    public static TreeMap<String, Double> percentStaffVax(ArrayList<CovidPrisonCases> data) {
        TreeMap<String,Double> vaxResults = new TreeMap<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getStaffPop() != 0) {
                String state = data.get(i).getState();
                Double percent = (double) (data.get(i).getStaffFullVax() + data.get(i).getStaffPartialVax() * 100 / data.get(i).getStaffPop());
                percent = (double) Math.round(percent * 1000d) / 1000d;
                percent /= 10;
                vaxResults.put(state, percent);
            }

            else {
                String state = data.get(i).getState();
                vaxResults.put(state, null);
            }
        }
        return vaxResults;
    
    }

    public static BigDecimal covidPrisonerDeath(ArrayList<CovidPrisonCases> data, String state) {
        for (int i = 0; i < data.size(); i++) {

            if ((data.get(i).getState()).equals(state)) {
                return data.get(i).covidPrisonerDeathsPercent();
                }
            }

        return new BigDecimal(0);
    }

    public static BigDecimal covidStaffDeath(ArrayList<CovidPrisonCases> data, String state) {
        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i).getState()).equals(state)) {
                return data.get(i).covidStaffDeathsPercent();
            }
        }

        return new BigDecimal(0);
    }

    public static int averageCovidCasesPrisoners(ArrayList<CovidPrisonCases> data) {
        int total = 0;
        int pop = 0;

        for (int i = 0; i < data.size(); i++) {
            total += data.get(i).getTotalPrisonerCases();
            pop += data.get(i).getPrisonerPop();
        }

        return total * 100 / pop;
    }

    public static int averageCovidCasesStaff(ArrayList<CovidPrisonCases> data) {
        int total = 0;
        int pop = 0;

        for (int i = 0; i < data.size(); i++) {
            total += data.get(i).getTotalStaffCases();
            pop += data.get(i).getStaffPop();
        }

        return total * 100 / pop;
    }
}