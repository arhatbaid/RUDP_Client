import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ConnectionClass {

    private static ConnectionDetails connectionDetails = null;
    private static DatagramSocket socket = null;
    private static InetAddress address = null;
    private static DatagramPacket dataPacket = null;

    public ConnectionClass(ConnectionDetails connectionDetails) {
        ConnectionClass.connectionDetails = connectionDetails;
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(connectionDetails.getHostName());
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void sendDataToServer() {
        File file = new File("arhat.jpeg");
        try {
            byte[] arrFile = Files.readAllBytes(file.toPath());
            int totalFileLength = arrFile.length;
            byte[] dataToBeSent;
            int currentPos = 0;
            while(totalFileLength >= currentPos){
                dataToBeSent = Arrays.copyOfRange(arrFile, currentPos, currentPos + 64999);
                if (dataPacket != null) {
                    dataPacket = new DatagramPacket(dataToBeSent, dataToBeSent.length, dataPacket.getAddress(), dataPacket.getPort());
                }else {
                    dataPacket = new DatagramPacket(dataToBeSent, dataToBeSent.length, InetAddress.getByName(connectionDetails.getHostName()), connectionDetails.getPortNumber());
                }
                socket.send(dataPacket);
                currentPos = currentPos + 65000;
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void receiveAckFromServer() {
        byte[] ack = new byte[1024];
        try {
            dataPacket = new DatagramPacket(ack, ack.length);
            socket.receive(dataPacket);
            ack = dataPacket.getData();
            System.out.println("Status : " + new String(ack));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAckToServer(byte[] data) {
        try {
            if (dataPacket != null) {
                dataPacket = new DatagramPacket(data, data.length, dataPacket.getAddress(), dataPacket.getPort());
            }else {
                dataPacket = new DatagramPacket(data, data.length, InetAddress.getByName(connectionDetails.getHostName()), connectionDetails.getPortNumber());
            }
            socket.send(dataPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
