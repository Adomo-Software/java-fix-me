package JFM_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class CollectionModification {
    public static void problemCode() {
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "orange", "grape"));
        for (String fruit : fruits) { // For-each loop used for iteration
            if (fruit.equals("banana")) {
                fruits.remove(fruit); // Modification of the list during iteration
            }
        }
        System.out.println(fruits);
    }

    public static void solutionCode() {
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "orange", "grape"));
        for (int i = 0; i < fruits.size(); i++) {
            String fruit = fruits.get(i);
            if (fruit.equals("banana")) {
                fruits.remove(i);
                i--;
            }
        }
        System.out.println(fruits);
    }

    public static void solutionCode2() {
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "orange", "grape"));
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            if (fruit.equals("banana")) {
                iterator.remove();
            }
        }
        System.out.println(fruits);
    }

    public static void solutionCode3() {
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "orange", "grape"));
        fruits.removeIf(fruit -> fruit.equals("banana"));
        System.out.println(fruits);
    }

    public static void main(String[] args) {
        solutionCode();
        solutionCode2();
        solutionCode3();
    }
}
