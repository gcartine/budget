package gcartine.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class RegisterLine {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // These come straight from the input data
    private final String account;
    private final String flag;
    private final String date;
    private final String payee;
    private final String groupAndCategory;
    private final String group;
    private final String category;
    private final String memo;
    private final BigDecimal outflow;
    private final BigDecimal inflow;
    private final String cleared;

    // these are derived from the other properties
    private final BigDecimal delta; // outflow plus inflow
    private final LocalDate localDate; // derived from this.date
    private final YearMonth yearMonth; // derived from this.date via this.localDate



    public RegisterLine(String[] s) {
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
        this.account = s[0];
        this.flag = s[1];
        this.date = s[2];
        this.payee = s[3];
        this.groupAndCategory = s[4];
        this.group = s[5];
        this.category = s[6];
        this.memo = s[7];
        this.outflow = new BigDecimal(s[8].replaceAll("[$,]", ""));
        this.inflow = new BigDecimal(s[9].replaceAll("[$,]", ""));
        this.cleared = s[10];

        this.delta = this.outflow.add(this.inflow);
        this.localDate = LocalDate.parse(this.date, formatter);
        this.yearMonth = YearMonth.of(this.localDate.getYear(), this.localDate.getMonth());
//        System.out.println(this.localDate);
//        System.out.println(this.yearMonth);
    }

    public String getAccount() {
        return account;
    }

    public String getFlag() {
        return flag;
    }

    public String getDate() {
        return date;
    }

    public String getPayee() {
        return payee;
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

    public String getMemo() {
        return memo;
    }

    public BigDecimal getOutflow() {
        return outflow;
    }

    public BigDecimal getInflow() {
        return inflow;
    }

    public String getCleared() {
        return cleared;
    }

    public BigDecimal getDelta() {
        return delta;
    }


    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

}
