import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Path sourceFile = Paths.get("src/TestModule.java");
        String classesDir = "src";

        long lastModified = 0;

        while (true) {
            long currentModified = Files.getLastModifiedTime(sourceFile).toMillis();
            if (currentModified > lastModified) {
                lastModified = currentModified;

                // Компіляція TestModule.java
                Process compile = new ProcessBuilder("javac", sourceFile.toString())
                        .inheritIO()
                        .start();
                compile.waitFor();

                // Новий ClassLoader
                CustomClassLoader loader = new CustomClassLoader(classesDir);
                Class<?> clazz = loader.findClass("TestModule");
                Object obj = clazz.getDeclaredConstructor().newInstance();

                System.out.println(obj);
                Thread.sleep(1000);
            }
        }
    }
}
