package util;

import java.util.Scanner;

public class DialogPlatform {

    private static Scanner scanner = new Scanner(System.in);

    public static String[] setupFilters(){
        String[] array =new String[7];
        System.out.println("Enter digits of MCC with regex ';' like: '111;222'." +
                "Set input free if you wanna get all of them");
        array[0] = scanner.nextLine();
        System.out.println("Enter types of RADIO with regex ';' like: 'LTE;GSM'." +
                "Set input free if you wanna get all of them");
        array[1] = scanner.nextLine();
        System.out.println("Enter min and max AREA with regex ';' like: '0;10000'." +
                "Set input free if you wanna get all of them");
        array[2] = scanner.nextLine();
        System.out.println("Enter min and max CELL with regex ';' like: '0;10000'." +
                "Set input free if you wanna get all of them");
        array[3] = scanner.nextLine();
        System.out.println("Enter  max RANGE like: '3000'." +
                "Set input free if you wanna get all of them");
        array[4] = scanner.nextLine();
        System.out.println("Enter min and max CREATE time like: '2017-10-12 22:00:00;2018-1-30 12:00:00'." +
                "Set input free if you wanna get all of them");
        array[5] = scanner.nextLine();
        System.out.println("Enter min and max UPDATE time like: '2017-10-12 22:00:00;2018-1-30 12:00:00'." +
                "Set input free if you wanna get all of them");
        array[6] = scanner.nextLine();
        return array;
    }
}
