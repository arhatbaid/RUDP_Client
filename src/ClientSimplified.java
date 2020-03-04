import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ClientSimplified {

    private static ConnectionDetails connectionDetails = null;

    public static void main(String[] args){
        setServerInfo();
        ConnectionClass connectionClass = new ConnectionClass(connectionDetails);
        connectionClass.intUDP();
        byte[] fileBytes = getFileBytes();
        int fileSize =  fileBytes.length;
        connectionClass.sendDataToServer(getFileBytes());
        connectionClass.receiveAckFromServer();
    }

    private static byte[] getFileBytes() {
        File file = new File("arhat.jpg");
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static void setServerInfo() {
        connectionDetails = new ConnectionDetails();
        connectionDetails.setHostName("localhost");
        connectionDetails.setPortNumber(5555);
    }
}
