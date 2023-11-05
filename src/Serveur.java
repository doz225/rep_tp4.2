import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serveur {
    public static void main(String[] args) {
        int port = 1234;

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Serveur en attente de demandes...");

            byte[] receiveData = new byte[1024];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                if (request.equals("Donne-moi l'heure")) {
                    String dateTime = dateFormat.format(new Date());
                    byte[] sendData = dateTime.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                    serverSocket.send(sendPacket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
