package myDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Database {

    Scanner scan = new Scanner(System.in);

    // Declare stribute
    private double price, x;
    private String input, item, y;
    private int choose, totalItem, z;
    public int aksesSekali = 0;
    boolean found = false;

    // Getter
    public double getPrice() {return price;}
    public String getItem() {return item;}
    public int getTotalItem() {return totalItem;}

    // Format Rupiah
    Locale indonesia = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(indonesia);

    // PriceList
    public ArrayList <Double> priceFood = new ArrayList<>();
    public ArrayList <Double> priceCloth = new ArrayList<>();
    public ArrayList <Double> priceTechnology = new ArrayList<>();

    // Item List
    public ArrayList <String> itemFood = new ArrayList<>();
    public ArrayList <String> itemCloth = new ArrayList<>();
    public ArrayList <String> itemTechnology =new ArrayList<>();

    // Setter
    public Integer setTotalItem(int totalItem) {
        totalItem = z;
        return this.totalItem = totalItem;
    }
    public double setPrice(double price) {
        price = x;
        return this.price = price;
    }
    public String setItem(String item) {
        item = y;
        return this.item = item;
    }

    // Buat isi itemnya apa aja
    public int isiArrayList(){
        if (aksesSekali == 0) {
            // Food
            itemFood.add("Nasi Goreng Bambang Pamungkas [F]");
            priceFood.add(18_000.d);
            itemFood.add("Bubur Ayam Paoa Zola [F]");
            priceFood.add(12_000.d);
            itemFood.add("Soto Betawi Rasa Makasar Asli Lamongan [F]");
            priceFood.add(20_000.d);
            // Cloth
            itemCloth.add("Jaket Pria Abu-Abu Murah Meriah [C]");
            priceCloth.add(52_000.d);
            itemCloth.add("Kaos Hitam O Neck [C]");
            priceCloth.add(25_000.d);
            itemCloth.add("Celana Jeans Katun Kedap Air[C]");
            priceCloth.add(84_000.d);
            // Technology
            itemTechnology.add("TV Samsul 16:9 4K [T]");
            priceTechnology.add(599_900.d);
            itemTechnology.add("Ipin XXXXXXXXXXXY PRO [T]");
            priceTechnology.add(9999999999999.d);
            itemTechnology.add("Laptop USAS GOR 1 TB SSD Retoma Display++  [T]");
            priceTechnology.add(9999999999999999.d);
        }
            return 1;
    }

    // Buat nampilih produk + milih
    public void displayProduct(){

        aksesSekali = isiArrayList();

        x = 0; y = "-"; z = 0;
        // Menu item
        System.out.print(
                "\nPlease Choose The Category!\n" +
                "1. Food [F]\n" +
                "2. Cloth [C]\n" +
                "3. Technology [T]\n" +
                ">> "
        );

        choose = scan.nextInt();
        scan.nextLine();

        switch (choose){
        // Food
            case 1:
                while (true) {
                    System.out.println("\nCategory : Food [F]");
                    for (int i = 0; i < itemFood.size(); i++)
                        System.out.println((i + 1) + ". " + itemFood.get(i) + " - " + formatRupiah.format(priceFood.get(i)));
                    System.out.print(">> ");
                    choose = scan.nextInt();
                    scan.nextLine();
                    found = false;
                    for (int i = 0; i < itemFood.size(); i++) {
                        if (choose == i+1) {
                            found = true;
                            System.out.println("\nInterseted? Buy right now!!");
                            System.out.print(
                                    "Type \"ADD\" to add this item to your bucket or type \"NO\" to go back!\n" +
                                    ">> "
                                    );
                            input = scan.nextLine();
                            if (input.equals("ADD")) {
                                System.out.print(
                                        "How Many Item You Want to Add?\n" +
                                        ">> "
                                );
                                choose = scan.nextInt();
                                scan.nextLine();
                                x = priceFood.get(i) * choose;
                                y = itemFood.get(i);
                                z = choose;
                                break;
                            }
                            System.out.println("CANCELED");
                            break;
                        }
                    }
                    if (!found)
                        System.out.println(
                                "Item Not Found!\n" +
                                "Please Input Again!"
                        );
                    else
                        break;
                }
                break;

            // Cloth
            case 2:
                while (true) {
                    System.out.println("\nCategory : Cloth [C]");
                    for (int i = 0; i < itemCloth.size(); i++)
                        System.out.println((i + 1) + ". " + itemCloth.get(i) + " - " + formatRupiah.format(priceCloth.get(i)));
                    System.out.print(">> ");
                    choose = scan.nextInt();
                    scan.nextLine();
                    found = false;
                    for (int i = 0; i < itemFood.size(); i++) {
                        if (choose == i+1) {
                            found = true;
                            System.out.println("\nInterseted? Buy right now!!");
                            System.out.print(
                                    "Type \"ADD\" to add this item to your bucket or type \"NO\" to go back!\n" +
                                    ">> "
                            );
                            input = scan.nextLine();
                            if (input.equals("ADD")) {
                                System.out.print(
                                        "How Many Item You Want to Add?\n" +
                                        ">> "
                                );
                                choose = scan.nextInt();
                                scan.nextLine();
                                x = priceCloth.get(i) * choose;
                                y = itemCloth.get(i);
                                z = choose;
                                break;
                            }
                            System.out.println("CANCELED");
                            break;
                        }
                    }
                    if (!found)
                        System.out.println(
                                "Item Not Found!\n" +
                                "Please Input Again!"
                        );
                    else
                        break;
                }
            break;

            // Technology
            case 3:
                while (true) {
                    System.out.println("\nCategory : Tchnology [T]");
                    for (int i = 0; i < itemTechnology.size(); i++)
                        System.out.println((i + 1) + ". " + itemTechnology.get(i) + " - " + formatRupiah.format(priceTechnology.get(i)));
                    System.out.print(">> ");
                    choose = scan.nextInt();
                    scan.nextLine();
                    found = false;
                    for (int i = 0; i < itemTechnology.size(); i++) {
                        if (choose == i+1) {
                            found = true;
                            System.out.println("\nInterseted? Buy right now!!");
                            System.out.print(
                                    "Type \"ADD\" to add this item to your bucket or type \"NO\" to go back!\n" +
                                    ">> "
                            );
                            input = scan.nextLine();
                            if (input.equals("ADD")) {
                                System.out.print(
                                        "How Many Item You Want to Add?\n" +
                                        ">> "
                                );
                                choose = scan.nextInt();
                                scan.nextLine();
                                x = priceTechnology.get(i) * choose;
                                y = itemTechnology.get(i);
                                z = choose;
                                break;
                            }
                            System.out.println("CANCELED");
                            break;
                        }
                    }
                    if (!found)
                        System.out.println(
                                "Item Not Found!\n" +
                                "Please Input Again!"
                        );
                    else
                        break;
                }
                break;
                }

        }

    }

