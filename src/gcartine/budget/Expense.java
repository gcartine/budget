package gcartine.budget;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.YearMonth;

public class Expense {

    public static final String ORDINARY_EXPENSES = "Ordinary Expenses";
    public static final String SPECIAL_EXPENSES = "Special Expenses";

    public static Expense getOutputLine(@NotNull BudgetLine line) {
        if (line.getCategory().equals(ORDINARY_EXPENSES)
            || line.getCategory().equals(SPECIAL_EXPENSES)) {
            return new Expense(line);
        }
        else {
            throw new RuntimeException("Cannot process category " + line.getCategory());
        }
    }

    private final YearMonth yearMonth; // derived from this.month
    private final String groupAndCategory;
    private final String group;
    private final String category;
    private final BigDecimal expense;

    public Expense(@NotNull BudgetLine line) {
        this.yearMonth = line.getYearMonth();
        this.groupAndCategory = line.getGroupAndCategory();
        this.group = line.getGroup();
        this.category = line.getCategory();
        if (this.category.equals(ORDINARY_EXPENSES)) {
            this.expense = line.getActivity().negate();
        } else if (this.category.equals(SPECIAL_EXPENSES)) {
            this.expense = line.getBudgeted();
        } else {
            throw new RuntimeException("Cannot process category " + this.category);
        }
    }

    public YearMonth getYearMonth() {
        return yearMonth;
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

    public BigDecimal getExpense() {
        return expense;
    }
}
