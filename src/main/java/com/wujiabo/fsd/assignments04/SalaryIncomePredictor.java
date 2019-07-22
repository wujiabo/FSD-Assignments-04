package com.wujiabo.fsd.assignments04;

import java.math.BigDecimal;
import java.util.Scanner;

public class SalaryIncomePredictor {

    private static boolean File_Mail = false;

    public static void main(String[] args) {

        //The starting salary
        String startingSalary = null;
        //Increment to be received in percent
        String incrementPercent = null;
        //How frequently is increment received (quarterly, half-yearly, annuallyetc.)
        String incrementFrequency = null;
        //Deductions on income
        String deductionsPercent = null;
        //How frequently aredeductions done (quarterly, half-yearly, annually etc.)
        String deductionsFrequency = null;
        //Prediction for (years)
        String predictionYears = null;


        Scanner sc = new Scanner(System.in);
        while(startingSalary == null){
            System.out.println("The starting salary:");
            String _startingSalary = sc.next();
            if(validateStartingSalary(_startingSalary)){
                startingSalary = _startingSalary;
            }
        }

        while(incrementPercent == null){
            System.out.println("The increment(%):");
            String _incrementPercent = sc.next();
            if(validatePercent(_incrementPercent)){
                incrementPercent = _incrementPercent;
            }
        }

        while(incrementFrequency == null){
            System.out.println("The frequency of increment(times a year):");
            String _incrementFrequency = sc.next();
            if(validateFrequency(_incrementFrequency)){
                incrementFrequency = _incrementFrequency;
            }
        }

        while(deductionsPercent == null){
            System.out.println("The deduction(%):");
            String _deductionsPercent = sc.next();
            if(validatePercent(_deductionsPercent)){
                deductionsPercent = _deductionsPercent;
            }
        }

        while(deductionsFrequency == null){
            System.out.println("The frequency of deductions(times a year):");
            String _deductionsFrequency = sc.next();
            if(validateFrequency(_deductionsFrequency)){
                deductionsFrequency = _deductionsFrequency;
            }
        }

        System.out.println("end");

    }

    private static boolean validateFrequency(String frequency) {
        Integer frequencyInt = null;
        try{
            frequencyInt = Integer.valueOf(frequency);
        }catch (Exception e){
            System.out.println("Not a integer number.");
            return false;
        }
        if(frequencyInt < 1){
            System.out.println("Do not accept a number less than 1 for frequency of increment or deductions.");
            return false;
        }
        return true;
    }

    private static boolean validatePercent(String percent) {
        BigDecimal percentBD = null;
        try{
            percentBD = new BigDecimal(percent);
        }catch (Exception e){
            System.out.println("Not a number.");
            return false;
        }
        if(percentBD.compareTo(new BigDecimal(0)) == -1){
            System.out.println("Do not accept a negative number for increment or deduction.");
            return false;
        }
        return true;
    }

    private static boolean validateStartingSalary(String startingSalary) {
        BigDecimal startingSalaryBD = null;
        try{
            startingSalaryBD = new BigDecimal(startingSalary);
        }catch (Exception e){
            System.out.println("Not a number.");
            return false;
        }
        if(startingSalaryBD.compareTo(new BigDecimal(1)) == -1){
            System.out.println("Do not accept a number less than 1 for the starting salary.");
            return false;
        }
        return true;
    }

}
