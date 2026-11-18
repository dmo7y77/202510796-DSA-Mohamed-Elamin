import java.util.Scanner;

public class MyRestaurantApp {
    public static void main(String[] args) {

        final double JUICE_PRICE = 12.00;
        final double MEMBER_DISCOUNT = 0.15;
        final double CHILD_DISCOUNT = 0.05;
        final double SERVICE_TIP = 0.15;
        final int CHILD_MAX_AGE = 12;

        Scanner scan = new Scanner(System.in);

        
        System.out.print("Enter client name: ");
        String clientName = scan.nextLine();

        System.out.print("Enter client age: ");
        int clientAge = scan.nextInt();

        System.out.print("Enter food cost: ");
        double foodCost = scan.nextDouble();

        System.out.print("Enter number of juices: ");
        int juiceCount = scan.nextInt();

        System.out.print("Is the client a club member? (true/false): ");
        boolean isMember = scan.nextBoolean();

        System.out.print("Rate our service (1 to 5): ");
        int rating = scan.nextInt();

        
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating entered!");
            return;
        }

        
        double juicesTotal = juiceCount * JUICE_PRICE;
        double totalBeforeDisc = foodCost + juicesTotal;

        
        double discRate = 0.0;
        if (isMember) {
            discRate = MEMBER_DISCOUNT;
        } else if (clientAge <= CHILD_MAX_AGE) {
            discRate = CHILD_DISCOUNT;
        }
        double discountAmount = totalBeforeDisc * discRate;

        
        double tipAmount = (rating >= 3) ? totalBeforeDisc * SERVICE_TIP : 0.0;

        
        double finalTotal = totalBeforeDisc - discountAmount + tipAmount;

        int roundedTotal = (int) finalTotal;

printReceipt(clientName, foodCost, juicesTotal, discountAmount, tipAmount, finalTotal, roundedTotal);

        scan.close();
    }

    public static void printReceipt(String clientName, double foodCost, double juicesTotal, double discountAmount, double tipAmount, double finalTotal, int roundedTotal) {
       
        System.out.println("\n--- RECEIPT ---");
        System.out.println("Name: " + clientName);
        System.out.println("Food: $" + foodCost);
        System.out.println("Juices: $" + juicesTotal);
        System.out.println("Discount: $" + discountAmount);
        System.out.println("Tip: $" + tipAmount);
        System.out.println("Total: $" + finalTotal);
        System.out.println("Rounded: $" + roundedTotal);
    }
}