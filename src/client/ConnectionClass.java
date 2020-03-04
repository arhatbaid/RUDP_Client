package client;

import java.io.IOException;
import java.net.*;

public class ConnectionClass {

    private static ConnectionDetails connectionDetails = null;
    private static DatagramSocket socket = null;
    private static InetAddress address = null;
    private static DatagramPacket dataPacket = null, sendAckPacket = null, receiveAckPacket = null;

    public ConnectionClass(ConnectionDetails connectionDetails) {
        ConnectionClass.connectionDetails = connectionDetails;
    }

    public void intUDP() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(connectionDetails.getHostName());
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void sendDataToServer(byte[] data) {
        try {
            dataPacket = new DatagramPacket(data, data.length, InetAddress.getByName(connectionDetails.getHostName()), connectionDetails.getPortNumber());
            socket.send(dataPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveAckFromServer() {
        byte[] ack = new byte[1];
        try {
            receiveAckPacket = new DatagramPacket(ack, ack.length);
            socket.receive(receiveAckPacket);
            ack = receiveAckPacket.getData();
            System.out.println("Status : " + new String(ack));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAckToServer(byte[] data) {
        try {
            receiveAckPacket = new DatagramPacket(data, data.length, InetAddress.getByName(connectionDetails.getHostName()), connectionDetails.getPortNumber());
            socket.send(receiveAckPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
