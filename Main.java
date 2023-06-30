/*ESCOBAR, PRINCESS - SANTIAGO, MONICA*/
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public int mainMenu() {
        Scanner s = new Scanner (System.in);
        System.out.println("==============================================");
        System.out.println("       █░░░█ █▀▀ █░░ █▀▀ █▀▀█ █▀▄▀█ █▀▀");
        System.out.println("       █▄█▄█ █▀▀ █░░ █░░ █░░█ █░▀░█ █▀▀");
        System.out.println("       ░▀░▀░ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀▀ ▀░░░▀ ▀▀▀");
        System.out.println("==============================================\n\n\n");
        System.out.println("What brings you here today?\n");
        System.out.println("[1] Create a Vending Machine");
        System.out.println("[2] Test a Vending Machine");
        System.out.println("[3] Exit");
        System.out.print("Enter selection: ");
        return Console.readInteger(1, 3);
    } 
    
    public int displayTestMenu() {
        System.out.println("Choose a feature to test: \n");
        System.out.println("[1] Test Vending Machine Features");
        System.out.println("[2] Perform Vending Machine Maintenance");
        System.out.println("[3] Exit");
        System.out.print("Enter your choice: ");
        return Console.read.Integer(1, 3);
    }
    
    public int displayMaintMenu() {
        System.out.println("[1] Restock Items");
        System.out.println("[2] Add Items");
        System.out.println("[3] Change Item Price");
        System.out.println("[4] Collect Money");
        System.out.println("[5] Replenish Denomination");
        System.out.println("[6] Print Transaction History");
        System.out.println("[7] Exit");
        System.out.println("Enter your selection: ");
        return Console.readInteger(1, 7);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.startProgram();
    }

    public void startProgram(){
        VendingMachine vendingMachine = null;
        while (true) {
            int choice = mainMenu();

            switch (choice) {
                case 1:
                    vendingMachine = createVM (s);
                    break;
                case 2:
                    if (vendingMachine != null) {
                        testVM (s);
                    } else {
                        System.out.println("Error: Vending machine not found.");
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection! Please try again.");
            }
        } 
    }

    public VendingMachine createVM() {
        System.out.println("Choose your Vending Machine option:\n");
        System.out.println("[1] Regular Vending Machine");
        System.out.println("[2] Special Vending Machine");

        int choice = Console.readInteger(1,2);
        VendingMachine vendingMachine = null;

        switch (choice) {
            case 1:
                vendingMachine = createRegVM();
                break;
            case 2:
                System.out.println("Oops! you're too excited, this feature isn't available yet.");
                break;
        }
        return vendingMachine;
    }

    public RegVenMachine createRegVM() {
        RegVenMachine vendingMachine = new RegularVendingMachine();
        Scanner scanner = new Scanner(System.in);

        int numSlots = readValidInteger(scanner, 8, Integer.MAX_VALUE, "Enter no. of slot items (MIN: 8): ");

        int itemCount = readValidInteger(scanner, 10, Integer.MAX_VALUE, "Enter no. of items (MIN: 10): ");

        for (int i = 0; i < itemCount; i++) {
            System.out.println("===================================");
            System.out.println("Enter details for Item " + (i + 1) + ":");
            System.out.println("===================================");
            String itemName = Console.readString("Name of item: ");
            int price = readValidInteger(scanner, 0, Integer.MAX_VALUE, "Enter a price for the item: ");
            int calories = readValidInteger(scanner, 0, Integer.MAX_VALUE, "Enter no. of calories of the item: ");

            Item item = new Item(itemName, price, calories);
            vendingMachine.addItem(String.valueOf(i + 1), item);

            System.out.println("Item " + (i + 1) + ":");
            System.out.println("Name: " + item.getItemName());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Calories: " + item.getCalories());
        }

        System.out.println("Your Regular Vending Machine is created successfully!");
        System.out.println("Enjoy your goodies...");
        return vendingMachine;
    }

    public int readValidInteger(Scanner scanner, int min, int max, String message) {
        int value;
        while (true) {
            System.out.print(message);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }

    public void testVM (VendingMachine vm) {
        while (true) {
            int choice = displayTestMenu();
            switch(choice) {
                case 1:
                    vm.venMachineFeatures();
                    break;
                case 2:
                    checkMaintenance(vm);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid selection! Please try again.");
            }
        }
    }

    public void checkMaintenance(VendingMachine vm) {
        while (true) {
            int choice = displayMaintMenu();
            switch(choice){
                case 1:
                    vm.restockVen();
                    break;
                case 2:
                    vm.addVenItem();
                    break;
                case 3:
                    vm.changeVenItem();
                    break;
                case 4:
                    vm.collectVenMoney();
                    break;
                case 5:
                    vm.addVenMoney();
                    break;
                case 6:
                    vm.printVenTransaction();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid Selection! Please try again.");
                }
            }
        }
    }
