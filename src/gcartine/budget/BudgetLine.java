package gcartine.budget;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class BudgetLine {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");

    // These are straight from the data
    private final String month;
    private final String groupAndCategory;
    private final String group;
    private final String category;
    private final BigDecimal budgeted;
    private final BigDecimal activity;
    private final BigDecimal available;

    // These are calculated from the data
    private final BigDecimal delta; // this.budgeted plus this.activity
    private final YearMonth yearMonth; // derived from this.month
    //private final LocalDate localDate; // derived from this.month via this.yearMonth

    public BudgetLine(String [] s) {
//        boolean b = false;
//        for (int i = 0; i < s.length; i++) {
//            if (s[i].contains("Ellen")) {
//                b = true;
//            }
//        }
//        if (b) {
//            for (int i = 0; i < s.length; i++) {
//                System.out.println(i + "  " + s[i]);
//            }
//        }
        System.out.flush();
        this.month = s[0];
        this.groupAndCategory = s[1];
        this.group = s[2];
        this.category = s[3];
        this.budgeted = new BigDecimal(s[4].replaceAll("[$,]", ""));
        this.activity = new BigDecimal(s[5].replaceAll("[$,]", ""));
        this.available = new BigDecimal(s[6].replaceAll("[$,]", ""));

        this.delta = this.budgeted.add(this.activity);
        this.yearMonth = YearMonth.parse(month, formatter);
        //this.localDate = this.yearMonth.atDay(1);
    }

    public BigDecimal getActivity() {
        return activity;
    }

    public String getMonth() {
        return month;
    }

    public String getGroupAndCategory() {
        return groupAndCategory;
    }

    public String getGroup() {
        return group;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getBudgeted() {
        return budgeted;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public BigDecimal getDelta() {
        return delta;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

//    public LocalDate getLocalDate() {
//        return localDate;
//    }


}
