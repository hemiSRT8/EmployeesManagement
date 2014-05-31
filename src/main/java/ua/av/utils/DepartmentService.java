package ua.av.utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DepartmentService {

    public Comparator amountOfEmployeesComparator(final Map<String, List<Long>> departments) {
        return new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (departments.get(o1).size() >= departments.get(o2).size()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
    }

    public Comparator salaryExpenseComparator(final Map<String, Double> salaryExpense) {
        return new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (salaryExpense.get(o1) >= salaryExpense.get(o2)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
    }

}
