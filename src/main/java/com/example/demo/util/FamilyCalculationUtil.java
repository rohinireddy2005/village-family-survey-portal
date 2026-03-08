package com.example.demo.util;

import com.example.demo.entity.Family;
import com.example.demo.entity.Member;
import java.util.List;

public class FamilyCalculationUtil {

    private static final double BPL_THRESHOLD = 120000;

    public static double calculateTotalIncome(Family family, List<Member> members) {

        double total = family.getHeadIncome() != null ? family.getHeadIncome() : 0;

        if (members != null) {
            for (Member m : members) {
                if (m.getIncome() != null) {
                    total += m.getIncome();
                }
            }
        }
        return total;
    }

    public static String calculateBplApl(double totalIncome) {
        return totalIncome < BPL_THRESHOLD ? "BPL" : "APL";
    }

    public static String calculateCategory(double income, int size) {
        if (income < 100000 && size <= 3) return "Very Poor";
        if (income < 200000) return "Poor";
        if (income < 500000) return "Middle";
        return "Upper";
    }
}
