/*ESCOBAR, PRINCESS - SANTIAGO, MONICA*/
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
    }

    public int mainMenu() {
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
        return readInteger(1, 3);
    }

    public int displayTestMenu() {
        System.out.println("Choose a feature to test: \n");
        System.out.println("[1] Test Vending Machine Features");
        System.out.println("[2] Perform Vending Machine Maintenance");
        System.out.println("[3] Exit");
        System.out.print("Enter your choice: ");
        return readInteger(1, 3);
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
        return readInteger(1, 7);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.startProgram();
    }

    public void startProgram() {
        RegularVendingMachine vendingMachine = null;
        while (true) {
            int choice = mainMenu();

            switch (choice) {
                case 1:
                    vendingMachine = createVM();
                    break;
                case 2:
                    if (vendingMachine != null) {
                        testVM(vendingMachine);
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
    private RegularVendingMachine currentVendingMachine;
    
    public RegularVendingMachine createVM() {
        System.out.println("Choose your Vending Machine option:\n");
        System.out.println("[1] Regular Vending Machine");
        System.out.println("[2] Special Vending Machine");
        System.out.println("[0] Back");

        int choice = readInteger(0, 2);
        RegularVendingMachine vendingMachine = null;

        switch (choice) {
            case 0:
                System.out.println("Returning to main menu.");
                break;
            case 1:
                vendingMachine = createRegVM();
                break;
            case 2:
                System.out.println("Service will open soon. Please check back later.");
                break;
        }

        if (vendingMachine != null) {
            currentVendingMachine = vendingMachine;
        }

        return currentVendingMachine;
    }


    public RegularVendingMachine createRegVM() {
        RegularVendingMachine vendingMachine = new RegularVendingMachine();

        int numSlots = readValidInteger(8, Integer.MAX_VALUE, "Enter no. of slot items (MIN: 8): ");

        int itemCount = readValidInteger(10, Integer.MAX_VALUE, "Enter no. of items (MIN: 10): ");

        for (int i = 0; i < itemCount; i++) {
            System.out.println("===================================");
            System.out.println("Enter details for Item " + (i + 1) + ":");
            System.out.println("===================================");            
            String itemName = readString("Name of item: ");
            double price = readValidDouble(0, Double.MAX_VALUE, "Enter a price for the item: ");
            int calories = readValidInteger(0, Integer.MAX_VALUE, "Enter no. of calories of the item: ");


            Item item = new Item();
            item.setItemName(itemName);
            item.setItemPrice(price);
            item.setItemCalories(calories);
            vendingMachine.addItem(String.valueOf(i + 1), item);

            System.out.println("Item " + (i + 1) + ":");
            System.out.println("Name: " + item.getItemName());
            System.out.println("Price: " + item.getItemPrice());
            System.out.println("Calories: " + item.getItemCalories());
        }

        System.out.println("Your Regular Vending Machine is created successfully!");
        System.out.println("Enjoy your goodies...");
        return vendingMachine;
    }

    public int readInteger(int min, int max) {
        int value;
        while (true) {
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

    public int readValidInteger(int min, int max, String message) {
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

    public double readValidDouble(double min, double max, String message) {
        double value;
        while (true) {
            System.out.print(message);
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    }

    public String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void testVM(RegularVendingMachine vm) {
        while (true) {
            int choice = displayTestMenu();
            switch (choice) {
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

    public void checkMaintenance(RegularVendingMachine vm) {
        while (true) {
            int choice = displayMaintMenu();
            switch (choice) {
                case 1:
                    vm.restockItem();
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
