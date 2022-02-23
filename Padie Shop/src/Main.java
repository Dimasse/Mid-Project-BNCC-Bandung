import User.Admin;
import jdk.swing.interop.SwingInterOpUtils;
import myDatabase.Database;
import User.Members;
import myDatabase.Struk;

import java.net.PasswordAuthentication;
import java.net.UnknownServiceException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    // Format Rupiah
    Locale indonesia = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(indonesia);

    // ArrayList Buat CheckOut
    ArrayList <String> myItem = new ArrayList<>();
    ArrayList <Double> myPrice = new ArrayList<>();
    ArrayList <Integer> myTotalItem = new ArrayList<>();

    // Struk buat History Product
    Struk myStruk[] = new Struk[500];
    int numberStruk = 1;

    // Declare Class
    Scanner scan = new Scanner(System.in);
    Members newMember = new Members();
    Database data = new Database();
    Admin myAdmin = new Admin();

    // Declare Variabel
    boolean status = true;
    double money = 1000;
    String input;
    int choose;

    public Main() {

        for (int i = 0; i < 500; i++)
            myStruk[i] = new Struk();

        register();

        while(true) {
            // Main Menu
                System.out.print(
                        "\n=== \t MAIN MENU \t ===\n" +
                                "\n1. Buy Products\n" +
                                "2. History Products\n" +
                                "3. Add Money\n" +
                                "4. Check Money\n" +
                                "5. Logout\n" +
                                "0. Exit\n" +
                                ">> "
                );
                choose = scan.nextInt();
                scan.nextLine();

                switch (choose) {
                    // Buy Products
                    case 1:
                            System.out.print(
                                    "\n>> Buy Product\n" +
                                    "1. Choose Product\n" +
                                    "2. CheckOut\n" +
                                    "0. Back\n" +
                                    ">> "
                            );
                            choose = scan.nextInt();
                            scan.nextLine();
                        // Choose Product
                            if (choose == 1) {
                                do {
                                    boolean kembar = false, tertarik = true;
                                    myAdmin.displayProduct();
                                    myItem.add(myAdmin.setItem("-"));
                                    myPrice.add(myAdmin.setPrice(0));
                                    myTotalItem.add(myAdmin.setTotalItem(0));

                                    if (myItem.get(myItem.size() - 1).equals("-") && myPrice.get(myPrice.size() - 1) == 0 && myTotalItem.get(myTotalItem.size()-1) == 0) {
                                        myItem.remove(myItem.size()-1);
                                        myPrice.remove(myPrice.size()-1);
                                        myTotalItem.remove(myTotalItem.size()-1);
                                        tertarik = false;
                                    }

                                    if (tertarik) {
                                            for (int i = 0; i < myItem.size()-1; i++) {
                                                if (myItem.get(i).equals(myItem.get(myItem.size() - 1))){
                                                    kembar = true;
                                                    System.out.println("Sorry, This Item Has Been Added to Your Bucket!");
                                                    myItem.remove(myItem.size()-1);
                                                    myPrice.remove(myPrice.size()-1);
                                                    myTotalItem.remove(myTotalItem.size()-1);
                                                    break;
                                                }
                                            }

                                    }

                                    if (!kembar && tertarik)
                                        System.out.println("Item Successfully Added to Your Bucket!");

                                    System.out.print(
                                            "\nType \"CONTINUE\" to buy more products or type \"CANCEL\" to go back to the menu!\n" +
                                                    ">> "
                                    );

                                    input = scan.nextLine();

                                } while (input.equals("CONTINUE"));

                            // CheckOut
                            }else if (choose == 2){
                                    if (myPrice.isEmpty())
                                        System.out.println("\nSorry, No Items Here!");
                                    else {
                                        System.out.println("\nHere Are Your Item's Lists :");
                                        double total = 0;
                                        for (int i = 0; i < myItem.size(); i++) {
                                            System.out.println(myTotalItem.get(i) + " x " + myItem.get(i) + " - " + formatRupiah.format(myPrice.get(i)));
                                            total += myPrice.get(i);
                                        }

                                        System.out.print(
                                                        "\nTotal =  " + formatRupiah.format(total) +
                                                        "\nYour Money = " + formatRupiah.format(money) + "\n" +
                                                        "\nDo You Want to Buy  All The Stuffs?\n"
                                        );

                                    do {
                                        System.out.print(
                                                "Type \"BUY\" to Buy All the Stuffs or Type \"CANCEL\" to Cancel Buy!\n" +
                                                ">> "
                                        );
                                        input = scan.nextLine();
                                        if (input.equals("BUY")) {
                                            if (money >= total) {
                                                System.out.println(
                                                        "\nItems Are Successfully Bought!\n" +
                                                        "\nHere is the Struct :"
                                                );

                                                System.out.println("ID : #" + numberStruk);
                                                for (int i = 0; i < myItem.size(); i++) {
                                                    System.out.println(myTotalItem.get(i) + " x " + myItem.get(i) + " - " + formatRupiah.format(myPrice.get(i)));
                                                    myStruk[numberStruk].strukName.add(myItem.get(i));
                                                    myStruk[numberStruk].strukPrice.add(myPrice.get(i));
                                                    myStruk[numberStruk].strukCount.add(myTotalItem.get(i));
                                                }

                                                numberStruk++;
                                                for (int j = myItem.size() - 1; j >= 0; j--) {
                                                    myItem.remove(j);
                                                    myPrice.remove(j);
                                                    myTotalItem.remove(j);
                                                }
                                            }else
                                                System.out.println("\nSorry, You Don't Have Enough Money to Buy All of Theese Items!");
                                        } else {
                                            System.out.println("CANCELED");
                                            break;
                                        }
                                    }while (!input.equals("BUY"));

                                }
                        }
                        break;

                    // History Product
                    case 2:
                        do {
                            if (numberStruk == 1)
                                System.out.println(
                                        "\nYou Don't Have Any History Product\n" +
                                        "Please Buy Something!"
                                );
                            else {
                                System.out.println("\nStruck List : ");
                                for (int i = 1; i < numberStruk; i++)
                                    System.out.println("Struck ID #" + i);
                                System.out.print(
                                        "Please Enter Struct ID You Want to See!\n" +
                                        ">> "
                                );
                                choose = scan.nextInt();
                                scan.nextLine();
                                status = false;

                                for (int i = 1; i < numberStruk; i++) {
                                    if (choose == i) {
                                        status = true;
                                        System.out.println("\nStruct #" + i + " list :");
                                        for (int j = 0; j < myStruk[i].strukName.size(); j++)
                                            System.out.println(myStruk[i].strukCount.get(j) + " x " + myStruk[i].strukName.get(j) + " - " + formatRupiah.format(myStruk[i].strukPrice.get(j)));
                                        break;
                                    }
                                }

                                if (!status)
                                    System.out.println("\nSorry, Struct ID Not Found!");

                            }
                        }while (!status) ;
                        break;

                    // Add Money
                    case 3:
                        while (true) {
                            System.out.print(
                                    "\nHow Much Money You Want to Add?\n" +
                                    ">> Rp"
                            );
                            choose = scan.nextInt();
                            scan.nextLine();
                            if (choose < 0)
                                System.out.println("\nInvalid Input!, Please Try Again!");
                            else
                                break;
                        }
                        money += choose;
                        System.out.println("" +
                                "\nYour Money is Successfully Added!\n" +
                                "You have  " + formatRupiah.format(money) + " now"
                        );
                        break;

                    // Check Money
                    case 4:
                        System.out.println("\nYou have + " + formatRupiah.format(money) + " now");
                        break;

                    // LogOut
                    case 5:
                        System.out.println("Logout Success!");
                        register();
                        break;

                    // Exit
                    case 0:
                        System.out.println(
                                "Thank you for using this demo app\n" +
                                "if you find any kind of bugs, make sure to make a report to : dimasdanizaini123@gmail.com"
                        );
                        System.exit(0);;
                        break;

                }
        }

    }

    // Register
    void register( ){

        System.out.println("\n --- Padie Shop ---");
        while(true){
            System.out.println("\n=== \t REGISTER MENU \t ===");
            // Registration Rule
            System.out.println("" +
                    "\nPlease Notice That :"
            );
            System.out.println(
                    "-> Name must have at last 3 characters  and 16 maximum characters\n" +
                    "     Name cannot contain special characters like \"! ? , . + - _\""
            );
            System.out.println("" +
                    "-> Username must have at last 3 characters and 16 maximum characters"
            );
            System.out.println(
                    "-> Password must have at last 8 characters and 40 characters\n" +
                    "     Password must combine letter and number"
            );
            System.out.println(
                    "-> EMail must have at last 5 characters and 30 characters\n" +
                    "     Email must contain \"@\" ending with \".com\" or \".net\"\n"
            );
            // Input Full Name;
            System.out.print("Full Name\t\t\t : ");
            String FULLNAME = newMember.setFullName(scan.nextLine());
            // Input Username
            System.out.print("Username\t \t :  ");
            String USERNAME = newMember.setUserName(scan.nextLine());
            // Input Password
            System.out.print("Password\t\t\t : ");
            String PASSWORD = newMember.setPassword(scan.nextLine());
            // Input Email Addres
            System.out.print("Email \t\t\t \t : ");
            String EMAIL = newMember.setEmail(scan.nextLine());
            // Cek Eror
            status = true;
            status = cekErorUsername(USERNAME, status);
            status = cekErorFullName(FULLNAME, status);
            status = cekErorPassword(PASSWORD, status);
            status = cekErorEmail(EMAIL, status);
                if (status) {
                    System.out.println("Register Complete!");
                    login(USERNAME, PASSWORD);
                    System.out.println("\nWhat'sup " + FULLNAME + "  :D");
                    break;
                }
        }
    }

    // Login
    void login(String username, String password) {
        String USERNAME, PASSWORD;
        System.out.println("\n=== \t LOGIN MENU \t ===");
        // Login Rule
        System.out.println("" +
                "\nPlease Notice That :"
        );
        System.out.println(
                "-> Username and password must the same with the previous registration\n" +
                "-> To Login as Admin, use :\n" +
                "     Username \t\t : admin\n" +
                "     Password \t\t : admin123\n"
        );
        while (true) {
            // Input Uername
            System.out.print("Username\t\t : ");
            USERNAME = scan.nextLine();
            // Input Password
            System.out.print("Password \t\t : ");
            PASSWORD = scan.nextLine();
            // Check Username and Password
            if ((USERNAME.equals(username) && PASSWORD.equals(password))){
                System.out.println("Login Success!");
                break;
            }
            // Check Login as Admin
            if (USERNAME.equals("admin") && PASSWORD.equals("admin123")){
                System.out.println("\n === Admin Mode! ===");
                myAdmin.pickPorduct();
                login(username, password);
                break;
            }
            System.out.println("Username or Password is Wrong! Please Input Again!\n");
        }
    }

    // Buat cek eror register
    boolean cekErorUsername(String USERNAME, boolean status){
        if (USERNAME.equals("-1") || USERNAME.equals("-2")){
            if (status) {
                status = false;
                System.out.println("\nRegistrasion was Failed Because :");
            }

            if (USERNAME.equals("-1"))
                System.out.println("> Username is too short");
            else
                System.out.println("> Username is too long");

        }
        return status;
    }
    boolean cekErorFullName(String FULLNAME, boolean status){
        if (FULLNAME.equals("-1") || FULLNAME.equals("-2") || FULLNAME.equals("-3")) {
            if (status) {
                status = false;
                System.out.println("\nRegistrasion was Failed Because :");
            }

            if (FULLNAME.equals("-1"))
                System.out.println("> Your name is too short");
            else if (FULLNAME.equals("-2"))
                System.out.println("> your Name is too long");
            else
                System.out.println("Your name cannot containt a special charachter like :  \"! ? , + - _\"");
        }
        return status;
    }
    boolean cekErorEmail(String EMAIL, boolean status){
        if (EMAIL.equals("-1") || EMAIL.equals("-2") || EMAIL.equals("-3") || EMAIL.equals("-4")){
            if (status) {
                status = false;
                System.out.println("\nRegistrasion was Failed Because :");
            }

            if (EMAIL.equals("-1"))
                System.out.println("> Email is too short");
            else if (EMAIL.equals("-2"))
                System.out.println("> Email is too long");
            else if (EMAIL.equals("-3"))
                System.out.println("> Email must contains \"@\"");
            else
                System.out.println("> Email must end with \".com\" or \".net\"");

        }
        return status;
    }
    boolean cekErorPassword(String PASSWORD, boolean status) {
        if (PASSWORD.equals("-1") || PASSWORD.equals("-2") || PASSWORD.equals("-3")){
            if (status) {
                status = false;
                System.out.println("\nRegistrasion was Failed Because :");
            }

            if (PASSWORD.equals("-1"))
                System.out.println("> Password is too short");
            else if (PASSWORD.equals("-2"))
                System.out.println("> Passwordis too long");
            else
                System.out.println("Password must contain at last a number");

        }
        return status;
    }

    // Ga tahu ini apaan
    public static void main(String[] args) {
        new Main();
    }
}
