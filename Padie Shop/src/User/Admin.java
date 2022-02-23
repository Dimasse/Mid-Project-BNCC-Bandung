package User;

import myDatabase.Database;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Database {

    // Declare class + atribute
    Scanner scan = new Scanner(System.in);
    Double newPrice;
    int choose;
    String newItem;
    boolean sukses = false, walk = true;

    // Buat milih produk Admin
    public void pickPorduct() {

        aksesSekali = isiArrayList();
        walk = true;
        while (walk) {
            System.out.print(
                    "\nPlease Pick One of These Products to Change The Price!\n" +
                            "1. Food [F]\n" +
                            "2. Cloth [C]\n" +
                            "3. Technology [T]\n" +
                            "9. Logout\n" +
                            ">> "
            );

            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                // Food
                case 1:
                    sukses = false;
                    while (!sukses) {
                        System.out.print(
                                "\nPlease Enter The New Item!\n" +
                                        "Note That : \n" +
                                        "-> Your Item Must be Ended With \"[F]\"!\n" +
                                        "     Ex : Ayam Geprek Hot Banget [F]\n" +
                                        "-> Your product's name must have at last 3 or 16 maximum characters length!\n" +
                                        ">> "
                        );
                        newItem = scan.nextLine();
                        sukses = checkProduct(newItem, "[F]", newItem, itemFood, priceFood);
                    }
                    break;

                // Cloth
                case 2:
                    sukses = false;
                    while (!sukses) {
                        System.out.println(
                                "\nPlease Enter The New Item!\n" +
                                        "Note That : \n" +
                                        "-> Your Item Must be Ended With \"[C]\"!\n" +
                                        "     Ex : Sweeter Hitam Putih Kekinian [C]\n" +
                                        "-> Your product's name must have at last 3 or 16 maximum characters length!\n" +
                                        ">> "
                        );
                        newItem = scan.nextLine();
                        sukses = checkProduct(newItem, "[C]", newItem, itemCloth, priceCloth);
                    }
                    break;

                // Technology
                case 3:
                    sukses = false;
                    while (!sukses) {
                        System.out.print(
                                "\nPlease Enter The New Item!\n" +
                                        "Note That : \n" +
                                        "-> Your Item Must be Ended With \"[T]\"!\n" +
                                        "     Ex : Tablet Advan Murah Meriah [T]\n" +
                                        "-> Your product's name must have at last 3 or 16 maximum characters length!\n" +
                                        ">> "
                        );
                        newItem = scan.nextLine();
                        sukses = checkProduct(newItem, "[T]", newItem, itemTechnology, priceTechnology);
                    }
                    break;

                case 9:
                    System.out.println("Logout Successfully");
                    walk = false;
                    break;

                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }

    // Cek nama produk ama harga
        boolean checkProduct (String input, String category, String item, ArrayList<String> itemTemp, ArrayList < Double > priceTemp){
            if (input.length() < 3) {
                System.out.println(
                        "\nYour Product name is too short!\n" +
                        "Please try again!"
                );
                return false;
            }
            if (input.length() > 16) {
                System.out.println(
                        "\nYour Product name is too long!\n" +
                                "Please try again!"
                );
                return false;
            }
            if (input.endsWith(category)) {
                itemTemp.add(item);
                System.out.print(
                        "\nEnter The Price! (Must More Than Rp 1.000,00)\n" +
                        ">> Rp"
                );
                choose = scan.nextInt();
                scan.nextLine();
                if (choose >= 1000) {
                    priceTemp.add(choose + (10.d / 100.d) * choose);
                    System.out.println("Item Has Been Successfully Added!");
                    return true;
                } else {
                    System.out.println(
                            "Price Is Too Low!\n" +
                            "Please try again!"
                    );
                    return false;
                }
            } else {
                System.out.println(
                        "\nYour product's name must be ended with " + category + "!\n" +
                        "Please try again!"
                );
                return false;
            }
        }
}

