package JFM_2;

class InefficientUsage {
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
