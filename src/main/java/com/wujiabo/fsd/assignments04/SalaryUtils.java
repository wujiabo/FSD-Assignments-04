package com.wujiabo.fsd.assignments04;

import java.math.BigDecimal;

public class SalaryUtils {
    public static BigDecimal increment(BigDecimal salary, BigDecimal percent, Integer frequency) {
        Double _salary = salary.doubleValue();
        for (int i = 0; i < frequency; i++) {
            _salary = BigDecimalUtils.multiply(_salary, BigDecimalUtils.add(1, BigDecimalUtils.divide(percent.doubleValue(), 100)));
        }
        return BigDecimal.valueOf(_salary);
    }

    public static BigDecimal deduction(BigDecimal salary, BigDecimal percent, Integer frequency) {
        Double _salary = salary.doubleValue();
        for (int i = 0; i < frequency; i++) {
            _salary = BigDecimalUtils.multiply(_salary, BigDecimalUtils.subtract(1, BigDecimalUtils.divide(percent.doubleValue(), 100)));
        }
        return BigDecimal.valueOf(_salary);
    }
}
