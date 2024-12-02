package gcartine.budget;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonthlyReportGenerator {

    private static final String PATH = "C:\\Users\\Greg\\Dropbox\\YNAB\\YNAB text files\\";
    private static final String BUDGET_FILE = PATH + "Greg s Budget as of 2024-12-02 17-15 - Budget.csv";
    private static final String REGISTER_FILE = PATH + "Greg s Budget as of 2024-12-02 17-15 - Register.csv";
//    private static final String BUDGET_FILE = PATH + "Greg s Budget as of 2024-07-16 14-48 - Budget.csv";
//    private static final String REGISTER_FILE = PATH + "Greg s Budget as of 2024-07-16 14-48 - Register.csv";

    public static void main(String[] args) throws Exception {

        MonthlyReportGenerator m = new MonthlyReportGenerator();
        m.generateReport();
    }

    private MonthlyReportGenerator() {
    }

    public void generateReport() throws Exception {
        List<BudgetLine> budgetData = getDataFromBudgetFile();
        List<RegisterLine> registerData = getDataFromRegisterFile();

        // TODO derive report data from the objects above
        Map<YearMonth, List<Expense>> expensesByMonth = new HashMap<>();



        // TODO output

        System.out.println("Hello World");

    }

    private List<BudgetLine> getDataFromBudgetFile() throws Exception {
        LineNumberReader budgetLNR = new LineNumberReader(new FileReader(BUDGET_FILE));
//        String s = budgetLNR.readLine();
//        while (s != null) {
//            System.out.println(s);
//            s = budgetLNR.readLine();
//            // Here's the file header: ﻿"Month","Category Group/Category","Category Group","Category","Budgeted","Activity","Available"
//        }
        final String h = "\"Month\",\"Category Group/Category\",\"Category Group\",\"Category\",\"Budgeted\",\"Activity\",\"Available\"";
        String s = budgetLNR.readLine().substring(1); // drop zwnbsp at beginning of file
        if (! h.equals(s)) {
            throw new RuntimeException();
        }
        List<BudgetLine> result = new ArrayList<>();
        while ((s= budgetLNR.readLine()) != null) {
//            System.out.println(s);
//            System.out.flush();
            String[] l = splitCSV(s);
            BudgetLine bl = new BudgetLine(l);
            result.add(bl);
        }
        return result;
    }

    // AAA combine this method with the similar method into one method using functional programming
    private List<RegisterLine> getDataFromRegisterFile() throws Exception {
        LineNumberReader registerLNR = new LineNumberReader((new FileReader(REGISTER_FILE)));
        // BBB nuke this block
//        String s2 = registerLNR.readLine();
//        while (s2 != null) {
//            System.out.println(s2);
//            s2 = registerLNR.readLine();
//            // Here's the file header: "Account","Flag","Date","Payee","Category Group/Category","Category Group","Category","Memo","Outflow","Inflow","Cleared"
//        }
        final String h = "\"Account\",\"Flag\",\"Date\",\"Payee\",\"Category Group/Category\",\"Category Group\",\"Category\",\"Memo\",\"Outflow\",\"Inflow\",\"Cleared\"";
        String s = registerLNR.readLine().substring(1); // drop zwnbsp at beginning of file
//        System.out.println(s);
        if (! h.equals(s)) {
            throw new RuntimeException();
        }
        List<RegisterLine> result = new ArrayList<>();
        while ((s= registerLNR.readLine()) != null) {
//            System.out.println(s);
            String[] l = splitCSV(s);
//            for (int i = 0; i < l.length; i++) {
//                System.out.println(i + " " + l[i]);
//            }
            RegisterLine bl = new RegisterLine(l);
            result.add(bl);
//            for (String t : l) {
//                System.out.print(t + " , ");
//            }
//            System.out.println();
        }
        return result;
    }

//    private String[] parseCSVLine(String line) {
//        // Split the line by comma
//        String[] tokens = splitCSV(line);
//
//        for (int i = 0; i < tokens.length; i++) {
//            // Remove leading and trailing quotation marks
////            tokens[i] = tokens[i].replaceAll("^\"|\"$", "");
//
//            // Remove dollar signs and handle negative numbers
////            if (tokens[i].matches("-?\\$\\d+(\\.\\d{2})?")) {
////                tokens[i] = tokens[i].replace("$", "");
////            }
//        }
//        return tokens;
//    }

    // This method handles inconveniently imbedded comas and quotation marks
    public String[] splitCSV(String input) {
        List<String> result = new ArrayList<>();
        // Regex to handle quoted fields with embedded commas and quotes
        String regex = "(?<=^|,)(\"(?:[^\"]|\"\")*\"|[^,]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String field = matcher.group().trim();
            // Remove surrounding quotes and handle escaped quotes
            if (field.startsWith("\"") && field.endsWith("\"")) {
                field = field.substring(1, field.length() - 1).replace("\"\"", "\"");
            }
            result.add(field);
        }

        return result.toArray(new String[0]);
    }


}

// TODO z There are "AAA" todos all over this code. Find them and do them after I achieve MVP
// AAA import the data from the two files concurrently.
// AAA redo this in C# and become a full microsoft programmer?

