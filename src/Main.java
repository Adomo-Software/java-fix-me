import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    static class InefficientUsage {
        static void memtest(Runnable runnable) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

            runnable.run();

            long afterMemory = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = afterMemory - beforeMemory;
            System.err.printf("Memory used: %.2f MB%n", memoryUsed / (1024.0 * 1024.0));
        }
        static void problemCode() {
            String result = "";
            for (int i = 0; i < 10000; i++) {
                result = result + "Iteration: " + i; // String concatenation creates new string object on the heap
            }
            System.out.println(result);
        }
        static void solutionCode() {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                result.append("Iteration: ").append(i);
            }
            System.out.println(result);
        }
        public static void main(String[] args) {
            memtest(InefficientUsage::problemCode);  // Memory used ~38-70MG
            memtest(InefficientUsage::solutionCode); // Memory used ~1.25-3MG
        }
    }

    static class CollectionModification {
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
}