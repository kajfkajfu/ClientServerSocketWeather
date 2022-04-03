import service.Thermometer;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        Thermometer thermometer = new Thermometer();

        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Server started");

            while (true) {
                Connector connector = new Connector(server);
                new Thread(() -> {
                    String city = connector.readLine();
                    System.out.println("City: " + city);
                    String temperature = thermometer.getTemperature(city);
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    connector.writeLine(temperature);
                    System.out.println("Temperature: " + temperature);
                    try {
                        connector.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
