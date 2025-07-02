package org.example;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> pizzas = new ArrayList<String>();
        ArrayList<Integer> quantities = new ArrayList<Integer>();

        Scanner input = new Scanner(System.in);

        System.out.println("\n1. Add Order \n2. Update Order \n3. Remove Order \n4. View Orders \n5. Exit");

        while (true) {

            System.out.println(); // print new line
            System.out.print("Choose option: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                return;
            }

            int option = input.nextInt();
            System.out.println(); // print new line

            switch (option) {
                case 1:
                    int quantity = -1;

                    System.out.print("Pizza Type: ");
                    String pizzaType = input.next();

                    do {
                        System.out.print("Quantity: ");
                        if (input.hasNextInt()) {
                            quantity = input.nextInt();

                            if (quantity <= 0) {
                                System.out.println("Quantity must be positive.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            input.next();
                        }
                    } while (quantity <= 0);

                    addOrder(pizzas, quantities, pizzaType, quantity);
                    break;

                case 2:
                    int indexToUpdate = -1;
                    int newQuantity = -1;

                    printOrders(pizzas, quantities);

                    do {
                        System.out.print("\nOrder number to update: ");
                        if (input.hasNextInt()) {
                            indexToUpdate = input.nextInt();

                            if (indexToUpdate <= 0 || indexToUpdate > pizzas.size()) {
                                System.out.printf("Invalid order number. Please enter a number between 1 and %d.\n", pizzas.size());
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            input.next();
                        }
                    } while (indexToUpdate <= 0 || indexToUpdate > pizzas.size());

                    do {
                        System.out.print("New quantity: ");
                        if (input.hasNextInt()) {
                            newQuantity = input.nextInt();

                            if (newQuantity <= 0) {
                                System.out.println("Quantity must be positive.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            input.next();
                        }
                    } while (newQuantity <= 0);

                    updateOrder(quantities, indexToUpdate-1, newQuantity);
                    break;

                case 3:
                    int indexToRemove = -1;

                    printOrders(pizzas, quantities);

                    do {
                        System.out.print("\nOrder number to remove: ");

                        if (input.hasNextInt()) {
                            indexToRemove = input.nextInt();

                            if (indexToRemove <= 0 || indexToRemove > pizzas.size()) {
                                System.out.printf("Invalid order number. Please enter a number between 1 and %d.\n", pizzas.size());
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            input.next();
                        }
                    } while (indexToRemove <= 0 || indexToRemove > pizzas.size());

                    removeOrder(pizzas, quantities, indexToRemove-1);
                    break;

                case 4:
                    printOrders(pizzas, quantities);
                    break;

                case 5:
                    System.out.println("---Thank you!---");
                    System.exit(0); // Exit program

                default:
                    System.out.println("Invalid option. Please choose between 1-5.");
                    break;
            }
        }
    }

    public static void addOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, String pizzaType, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        pizzas.add(pizzaType);
        quantities.add(quantity);

        System.out.println("Order added successfully!");
    };

    public static void updateOrder(ArrayList<Integer> quantities, int index, int newQuantity) {
        if (quantities.isEmpty()) {
            System.out.println("There are no existing orders yet.");
            return;
        };

        if (index < 0 || index > quantities.size()) {
            System.out.println("Index does not exist.");
            return;
        }

        if (newQuantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        };

        quantities.set(index, newQuantity);
        System.out.println("Order updated successfully!");
    }

    public static void removeOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, int index) {
        if (pizzas.isEmpty()) {
            System.out.println("There are no existing orders yet.");
            return;
        };

        if (index < 0 || index > pizzas.size()) {
            System.out.println("Index does not exist.");
            return;
        }

        pizzas.remove(index);
        quantities.remove(index);

        System.out.println("Order removed successfully!");
    }

    public static void printOrders(ArrayList<String> pizzas, ArrayList<Integer> quantities) {
        System.out.println("--- Current Orders ---");

        if (pizzas.isEmpty()) {
            System.out.println("There are no existing orders yet.");
        } else {
            for (int i = 0; i < pizzas.size(); i++) {
                System.out.printf("%d. %S x %d\n", i+1, pizzas.get(i), quantities.get(i));
            }
        }
    }
}