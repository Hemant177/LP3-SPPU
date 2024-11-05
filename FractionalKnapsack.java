import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int weight;
    int value;
    
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsack {
    
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                double ratio1 = (double) item1.value / item1.weight;
                double ratio2 = (double) item2.value / item2.weight;
                return Double.compare(ratio2, ratio1);
            }
        });
        
        double totalValue = 0;
        int currentWeight = 0;
        
        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // If we can take the whole item, take it
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                // If we can't take the whole item, take a fraction of it
                int remainingCapacity = capacity - currentWeight;
                totalValue += ((double) item.value / item.weight) * remainingCapacity;
                break;
            }
        }
        
        return totalValue;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        
        Item[] items = new Item[n];
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value for item " + (i + 1) + ": ");
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            items[i] = new Item(weight, value);
        }
        
        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();
        
        double maxValue = getMaxValue(items, capacity);
        System.out.printf("Maximum value in Knapsack = %.2f", maxValue);
        
        scanner.close();
    }
}
