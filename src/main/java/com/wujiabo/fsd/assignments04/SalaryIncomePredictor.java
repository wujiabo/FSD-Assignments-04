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

        Boolean increaseOrDecrease = null;


        Scanner sc = new Scanner(System.in);
        while (startingSalary == null) {
            System.out.println("The starting salary:");
            String _startingSalary = sc.next();
            if (validateStartingSalary(_startingSalary)) {
                startingSalary = new BigDecimal(_startingSalary);
            }
        }

        while (increaseOrDecrease == null) {
            System.out.println("(1)Increase or (2)Decrease:");
            String _increaseOrDecrease = sc.next();
            if (validateIncreaseOrDecrease(_increaseOrDecrease)) {
                increaseOrDecrease = "1".equals(_increaseOrDecrease);
            }
        }

        while (increaseOrDecrease && incrementPercent == null) {
            System.out.println("The increment(%):");
            String _incrementPercent = sc.next();
            if (validatePercent(_incrementPercent)) {
                incrementPercent = new BigDecimal(_incrementPercent);
            }
        }

        while (increaseOrDecrease && incrementFrequency == null) {
            System.out.println("The frequency of increment(times a year):");
            String _incrementFrequency = sc.next();
            if (validateFrequency(_incrementFrequency)) {
                incrementFrequency = Integer.valueOf(_incrementFrequency);
            }
        }

        while (!increaseOrDecrease && deductionsPercent == null) {
            System.out.println("The deduction(%):");
            String _deductionsPercent = sc.next();
            if (validatePercent(_deductionsPercent)) {
                deductionsPercent = new BigDecimal(_deductionsPercent);
            }
        }

        while (!increaseOrDecrease && deductionsFrequency == null) {
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
        printReport(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears,increaseOrDecrease);
    }

    private static boolean validateIncreaseOrDecrease(String increaseOrDecrease) {
        if(!"1".equals(increaseOrDecrease) && !"2".equals(increaseOrDecrease)){
            System.out.println("Choose (1)increase or (2)decrease.");
            return false;
        }
        return true;
    }

    private static void printReport(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears,Boolean increaseOrDecrease) {
        printIncrementReport(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears,increaseOrDecrease);
        printDeductionReport(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears,increaseOrDecrease);
        printPrediction(startingSalary, incrementPercent, incrementFrequency, deductionsPercent, deductionsFrequency, predictionYears,increaseOrDecrease);
    }

    private static void printPrediction(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears,Boolean increaseOrDecrease) {
        System.out.println("c.Prediction");
        System.out.println("Year|Starting Salary|Increment Amount|Deduction Amount|Salary Growth");
        for (int i = 1; i <= predictionYears; i++) {
            BigDecimal startingSalaryPrint = startingSalary;
            if(increaseOrDecrease){
                startingSalary = SalaryUtils.increment(startingSalary, incrementPercent, incrementFrequency);
            }else{
                startingSalary = SalaryUtils.deduction(startingSalary, deductionsPercent, deductionsFrequency);
            }
            Double incrementAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            Double deductionAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            Double salaryGrowth = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            if(increaseOrDecrease){
                System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + BigDecimalUtils.moneyFormat(incrementAmount) + "|/|" + BigDecimalUtils.moneyFormat(salaryGrowth));
            }else{
                System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|/|" + BigDecimalUtils.moneyFormat(deductionAmount) + "|" + BigDecimalUtils.moneyFormat(salaryGrowth));
            }
        }
    }

    private static void printDeductionReport(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears,Boolean increaseOrDecrease) {
        System.out.println("b.Deduction Report");
        if(increaseOrDecrease){
            System.out.println("-none-");
            return;
        }
        System.out.println("Year|Starting Salary|Number of deductions|Deduction%|Deduction Amount");
        for (int i = 1; i <= predictionYears; i++) {
            BigDecimal startingSalaryPrint = startingSalary;
            if(increaseOrDecrease){
                startingSalary = SalaryUtils.increment(startingSalary, incrementPercent, incrementFrequency);
            }else{
                startingSalary = SalaryUtils.deduction(startingSalary, deductionsPercent, deductionsFrequency);
            }
            Double incrementAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            Double deductionAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            Double salaryGrowth = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            if(increaseOrDecrease){
                System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + BigDecimalUtils.moneyFormat(incrementAmount) + "|" + SalaryUtils.totalPercent(startingSalaryPrint,startingSalary) + "%|" + BigDecimalUtils.moneyFormat(incrementAmount));
            }else{
                System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + BigDecimalUtils.moneyFormat(-deductionAmount) + "|" + SalaryUtils.totalPercent(startingSalaryPrint,startingSalary) + "%|" + BigDecimalUtils.moneyFormat(deductionAmount));
            }
        }
    }

    private static void printIncrementReport(BigDecimal startingSalary, BigDecimal incrementPercent, Integer incrementFrequency, BigDecimal deductionsPercent, Integer deductionsFrequency, Integer predictionYears,Boolean increaseOrDecrease) {
        System.out.println("a.Increment Report");
        if(!increaseOrDecrease){
            System.out.println("-none-");
            return;
        }
        System.out.println("Year|Starting Salary|Number of Increments|Increment%|Increment Amount");
        for (int i = 1; i <= predictionYears; i++) {
            BigDecimal startingSalaryPrint = startingSalary;
            if(increaseOrDecrease){
                startingSalary = SalaryUtils.increment(startingSalary, incrementPercent, incrementFrequency);
            }else{
                startingSalary = SalaryUtils.deduction(startingSalary, deductionsPercent, deductionsFrequency);
            }
            Double incrementAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            Double deductionAmount = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            Double salaryGrowth = BigDecimalUtils.subtract(startingSalary.doubleValue(), startingSalaryPrint.doubleValue());
            if(increaseOrDecrease){
                System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + BigDecimalUtils.moneyFormat(incrementAmount) + "|" + SalaryUtils.totalPercent(startingSalaryPrint,startingSalary) + "%|" + BigDecimalUtils.moneyFormat(incrementAmount));
            }else{
                System.out.println(i + "|" + BigDecimalUtils.moneyFormat(startingSalaryPrint.doubleValue()) + "|" + BigDecimalUtils.moneyFormat(-deductionAmount) + "|" + SalaryUtils.totalPercent(startingSalaryPrint,startingSalary) + "%|" + BigDecimalUtils.moneyFormat(deductionAmount));
            }
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
