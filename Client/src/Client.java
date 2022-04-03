import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Enter the city where you what to know the temperature");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        scanner.close();

        while (true) {
            try (Connector connector = new Connector("127.0.0.1", 8000)) {
                System.out.println("Connected to the server");
                System.out.println("City: " + city);
                connector.writeLine(city);
                String temperature = connector.readLine();
                System.out.println("Precise temperature: " + temperature);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
