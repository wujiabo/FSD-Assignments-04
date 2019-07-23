package com.wujiabo.fsd.assignments04;

import java.math.BigDecimal;
import java.util.Scanner;

public class SalaryIncomePredictor {

    public static void main(String[] args) {

        //The starting salary
        BigDecimal startingSalary = null;
        //Increment to be received in percent
        BigDecimal incrementPercent = null;
        //How frequently is increment received (quarterly, half-yearly, annuallyetc.)
        Integer incrementFrequency = null;
        //Deductions on income
        BigDecimal deductionsPercent = null;
        //How frequently aredeductions done (quarterly, half-yearly, annually etc.)
        Integer deductionsFrequency = null;
        //Prediction for (years)
        Integer predictionYears = null;


        Scanner sc = new Scanner(System.in);
        while (startingSalary == null) {
            System.out.println("The starting salary:");
            String _startingSalary = sc.next();
            if (validateStartingSalary(_startingSalary)) {
                startingSalary = new BigDecimal(_startingSalary);
            }
        }

        while (incrementPercent == null) {
            System.out.println("The increment(%):");
            String _incrementPercent = sc.next();
            if (validatePercent(_incrementPercent)) {
                incrementPercent = new BigDecimal(_incrementPercent);
            }
        }

        while (incrementFrequency == null) {
            System.out.println("The frequency of increment(times a year):");
            String _incrementFrequency = sc.next();
            if (validateFrequency(_incrementFrequency)) {
                incrementFrequency = Integer.valueOf(_incrementFrequency);
            }
        }

        while (deductionsPercent == null) {
            System.out.println("The deduction(%):");
            String _deductionsPercent = sc.next();
            if (validatePercent(_deductionsPercent)) {
                deductionsPercent = new BigDecimal(_deductionsPercent);
            }
        }

        while (deductionsFrequency == null) {
            System.out.println("The frequency of deductions(times a year):");
            String _deductionsFrequency = sc.next();
            if (validateFrequency(_deductionsFrequency)) {
                deductionsFrequency = Integer.valueOf(_deductionsFrequency);
            }
        }


        while (predictionYears == null) {
            System.out.println("The prediction for (years):");
            String _predictionYears = sc.next();
            if (validateYears(_predictionYears)) {
                predictionYears = Integer.valueOf(_predictionYears);
            }
        }
        printReport(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears);
    }

    private static void printReport(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears) {
        printIncrementReport(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears);
        printDeductionReport(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears);
        printPrediction(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears);
    }

    private static void printPrediction(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears) {
        System.out.println("c.Prediction");
        System.out.println("Year|Starting Salary|Increment Amount|Deduction Amount|Salary Growth");
        for (int i = 1; i <= predictionYears; i++) {
            BigDecimal startingSalaryPrint = startingSalary;
            startingSalary = SalaryUtils.increment(startingSalary, incrementPercent, incrementFrequency);
            BigDecimal temp1 = startingSalary;
            Double incrementAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            startingSalary = SalaryUtils.deduction(startingSalary, deductionsPercent, deductionsFrequency);
            Double deductionAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), temp1.doubleValue());
            Double salaryGrowth = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + BigDecimalUtils.moneyFormat(incrementAmount) + "|" + BigDecimalUtils.moneyFormat(deductionAmount) + "|" + BigDecimalUtils.moneyFormat(salaryGrowth));
        }
    }

    private static void printDeductionReport(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears) {
        System.out.println("b.Deduction Report");
        System.out.println("Year|Starting Salary|Number of deductions|Deduction%|Deduction Amount");
        for (int i = 1; i <= predictionYears; i++) {
            BigDecimal startingSalaryPrint = startingSalary;
            startingSalary = SalaryUtils.increment(startingSalary, incrementPercent, incrementFrequency);
            BigDecimal temp1 = startingSalary;
            Double incrementAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            startingSalary = SalaryUtils.deduction(startingSalary, deductionsPercent, deductionsFrequency);
            Double deductionAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), temp1.doubleValue());
            Double salaryGrowth = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + deductionsFrequency + "|" + deductionsPercent + "%|" + BigDecimalUtils.moneyFormat(deductionAmount));
        }
    }

    private static void printIncrementReport(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears) {
        System.out.println("a.Increment Report");
        System.out.println("Year|Starting Salary|Number of Increments|Increment%|Increment Amount");
        for (int i = 1; i <= predictionYears; i++) {
            BigDecimal startingSalaryPrint = startingSalary;
            startingSalary = SalaryUtils.increment(startingSalary, incrementPercent, incrementFrequency);
            BigDecimal temp1 = startingSalary;
            Double incrementAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            startingSalary = SalaryUtils.deduction(startingSalary, deductionsPercent, deductionsFrequency);
            Double deductionAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), temp1.doubleValue());
            Double salaryGrowth = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + incrementFrequency + "|" + incrementPercent + "%|" + BigDecimalUtils.moneyFormat(incrementAmount));
        }
    }

    private static boolean validateYears(String years) {
        Integer yearsInt = null;
        try {
            yearsInt = Integer.valueOf(years);
        } catch (Exception e) {
            System.out.println("Not a integer number.");
            return false;
        }
        if (yearsInt < 1) {
            System.out.println("Do not accept a number less than 1 for prediction years.");
            return false;
        }
        return true;
    }

    private static boolean validateFrequency(String frequency) {
        Integer frequencyInt = null;
        try {
            frequencyInt = Integer.valueOf(frequency);
        } catch (Exception e) {
            System.out.println("Not a integer number.");
            return false;
        }
        if (frequencyInt < 1) {
            System.out.println("Do not accept a number less than 1 for frequency of increment or deductions.");
            return false;
        }
        if (frequencyInt > 4) {
            System.out.println("Do not accept a number greater than 4 for frequency of increment or deductions.");
            return false;
        }
        return true;
    }

    private static boolean validatePercent(String percent) {
        BigDecimal percentBD = null;
        try {
            percentBD = new BigDecimal(percent);
        } catch (Exception e) {
            System.out.println("Not a number.");
            return false;
        }
        if (percentBD.compareTo(new BigDecimal(0)) == -1) {
            System.out.println("Do not accept a negative number for increment or deduction.");
            return false;
        }
        if (percentBD.compareTo(new BigDecimal(100)) == 1) {
            System.out.println("Do not accept a number greater than 100 for increment or deduction.");
            return false;
        }
        return true;
    }

    private static boolean validateStartingSalary(String startingSalary) {
        BigDecimal startingSalaryBD = null;
        try {
            startingSalaryBD = new BigDecimal(startingSalary);
        } catch (Exception e) {
            System.out.println("Not a number.");
            return false;
        }
        if (startingSalaryBD.compareTo(new BigDecimal(1)) == -1) {
            System.out.println("Do not accept a number less than 1 for the starting salary.");
            return false;
        }
        return true;
    }

}
