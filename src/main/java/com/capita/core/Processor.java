package com.capita.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Processor {
    public static void main(String args[]) {
        final String filePath = "C:\\Users\\charu.jain\\PerCapitaIncome\\src\\main\\resources\\Sample_Input.csv";
        List<Income> incomeList = readDataLineByLine(filePath);
        processListAndWriteOutputToFile(incomeList);
    }

    private static final String COMMA_DELIMITER = ",";

    public static void processListAndWriteOutputToFile(List<Income> incomeList) {
        Map<String, Map<String, List>> map = new TreeMap<>();

        for (Income income : incomeList) {
            Map<String, List> genderAvgIncome = new HashMap<String, List>();
            List l = new ArrayList<Double>();

            if (map.containsKey(income.getCountry())) {
                genderAvgIncome = map.get(income.getCountry());
                if (genderAvgIncome.containsKey(income.getGender())) {
                    l = genderAvgIncome.get(income.getGender());
                    l.add(String.valueOf(income.getAvgIncome()) + "-" + income.getCurrency());
                } else {
                    l.add(String.valueOf(income.getAvgIncome()) + "-" + income.getCurrency());
                    genderAvgIncome.put(income.getGender(), l);
                }
            } else {
                l.add(String.valueOf(income.getAvgIncome()) + "-" + income.getCurrency());
                genderAvgIncome.put(income.getGender(), l);
                map.put(income.getCountry(), genderAvgIncome);
            }
        }
        System.out.println(map);

        Map<String, Map<String, Double>> countryIncomeMap = new TreeMap<String, Map<String, Double>>();

        for (Map.Entry<String, Map<String, List>> entry : map.entrySet()) {
            String country = entry.getKey();
            Map<String, List> genderListAvgIncomeMap = entry.getValue();

            Map<String, Double> genderAvgIncomeMap = new HashMap<String, Double>();


            for (Map.Entry<String, List> entry1 : genderListAvgIncomeMap.entrySet()) {
                String gender = entry1.getKey();
                List incomes = entry1.getValue();
                Double finalAmt = computeAvgSalary(incomes);

                if (countryIncomeMap.containsKey(country)) {
                    genderAvgIncomeMap = countryIncomeMap.get(country);
                    genderAvgIncomeMap.put(gender, finalAmt);
                } else {
                    genderAvgIncomeMap.put(gender, finalAmt);
                    countryIncomeMap.put(country, genderAvgIncomeMap);
                }
            }
        }

        writeOutputToFile(countryIncomeMap);
        System.out.println(countryIncomeMap);
    }

    static final String NEW_LINE_SEPARATOR = "\n";

    static void writeOutputToFile(Map<String, Map<String, Double>> countryIncomeMap) {


        //CSV file header
        final String FILE_HEADER = "Country,Gender,Average Income(Dollar)";

        //Create new students objects

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("C:\\Users\\charu.jain\\PerCapitaIncome\\src\\main\\resources\\Sample_Output.csv");

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new student object list to the CSV file
            for (Map.Entry<String, Map<String, Double>> map : countryIncomeMap.entrySet()) {

                String country = map.getKey();

                Map<String, Double> genderMap = map.getValue();

                for (Map.Entry<String, Double> entry : genderMap.entrySet()) {
                    fileWriter.append(country);
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(entry.getKey());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(entry.getValue()));

                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }

    static Double computeAvgSalary(List<String> incomes) {
        Double sum = 0.0d;
        for (String income : incomes) {
            Double value = Double.valueOf(income.split("-")[0]);
            String currencyVal = income.split("-")[1];
            sum += value / (Currency.valueOf(currencyVal).conversionRate);
        }

        return sum / (incomes.size());
    }

    public static List<Income> readDataLineByLine(String file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file));) {

            List<Income> incomeList = new ArrayList<Income>();
            String line = "";
            //Read to skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] incomeDetails = line.split(COMMA_DELIMITER);
                if (incomeDetails.length > 0) {
                    //Save the employee details in Employee object
                    Income income = new Income(incomeDetails[0], incomeDetails[1], incomeDetails[2], Currency.valueOf(incomeDetails[3]), Double.parseDouble(incomeDetails[4]));
//                    System.out.println(income);
                    incomeList.add(income);
                }
            }

//            System.out.println(incomeList);

            //Lets print the Employee List
//            for (Income e : incomeList) {
//                System.out.println(e.getCountry() + "   " + e.getCity() + "   "
//                        + e.getCurrency() + "   " + e.getGender() + "   " + e.getAvgIncome());
//            }

            return incomeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}