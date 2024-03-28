import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double payment = scan.nextDouble();
        scan.close();

        Locale md = new Locale("md", "MD");

        NumberFormat us     = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat china  = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat france = NumberFormat.getCurrencyInstance(Locale.FRANCE);

        // Custom format for India
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setCurrencySymbol("Rs.");
        symbols.setDecimalPrefix("Rs.");
        String pattern = "#,##0.##";
        DecimalFormat indiaFormat = new DecimalFormat(pattern, symbols);
        String formattedIndiaPayment = indiaFormat.format(payment);

        System.out.println("US: "     + us.format(payment));
        System.out.println("India: "  + formattedIndiaPayment);
        System.out.println("China: "  + china.format(payment));
        System.out.println("France: " + france.format(payment));
    }
}