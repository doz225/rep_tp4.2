import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";  // Adresse IP du serveur, peut être modifiée si le serveur est distant
        int serverPort = 1234;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            String request = "Donne-moi l'heure";
            byte[] sendData = request.getBytes();
            InetAddress serverIP = InetAddress.getByName(serverAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Heure actuelle reçue du serveur : " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
