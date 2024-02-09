import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        int portNumber = 9999; // Server'ın dinleyeceği port

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server başlatıldı. Port: " + portNumber);

            while (true) {
                System.out.println("Bağlantı bekleniyor...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Bağlantı kabul edildi: " + clientSocket);

                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Gelen veri: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Bağlantıyı kapatma işlemi burada olmaz.  
        // clientSocket.close(); 
    }
}
