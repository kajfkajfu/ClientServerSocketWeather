import java.io.*;

public class Client {
    public static void main(String[] args) {

        while (true) {
            try (Connector connector = new Connector("127.0.0.1", 8000)) {
                System.out.println("Connected to the server");
                String request = "Visaginas";
                System.out.println("request: " + request);
                connector.writeLine(request);
                String response = connector.readLine();
                System.out.println("Response: " + response);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
