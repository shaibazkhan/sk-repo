package com.address.book;


import com.address.book.core.AddressBookDataAnalyser;
import com.address.book.core.CsvFileDataParser;
import com.address.book.core.PersonNotFoundException;
import com.address.book.domain.AddressBook;

public class MainApplication {

    public static void main(String[] args) {
        AddressBook addressBook = new CsvFileDataParser("AddressBook.txt").parse();

        AddressBookDataAnalyser dataAnalyser = new AddressBookDataAnalyser(addressBook);

        System.out.println("====================================");
        System.out.println("See Below for questions and answers.");
        System.out.println("====================================");

        printAnswerForMaleCountInTheAddressBook(dataAnalyser);
        printAnswerForOldestPersonInTheAddressBook(dataAnalyser);
        printAnswerForAgeDifference(dataAnalyser);
    }

    private static void printAnswerForMaleCountInTheAddressBook(AddressBookDataAnalyser dataAnalyser){
        System.out.println("Q.How many males are in the address book?");
        System.out.printf("Ans.%d", dataAnalyser.totalNumberOfMales());
    }

    private static void printAnswerForOldestPersonInTheAddressBook(AddressBookDataAnalyser dataAnalyser){
        System.out.println();
        System.out.println("Q.Who is the oldest person in the address book?");
        System.out.printf("Ans.%s", dataAnalyser.findOldestPerson().getName());
    }

    private static void printAnswerForAgeDifference(AddressBookDataAnalyser dataAnalyser){
        System.out.println();
        System.out.println("Q.How many days older is Bill than Paul?");
        try {
            System.out.printf("Ans.%d days older.", dataAnalyser.findAgeDifferenceInDaysBetween("Bill McKnight", "Paul Robinson"));
        }catch (PersonNotFoundException e){
            System.err.print(e.getMessage());
        }
    }
}
