import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ClientSimplified {

    private static ConnectionDetails connectionDetails = null;

    public static void main(String[] args){
        setServerInfo();
        ConnectionClass connectionClass = new ConnectionClass(connectionDetails);
//        byte[] fileBytes = getFileBytes();
//        int fileSize =  fileBytes.length;
//        connectionClass.sendAckToServer(ConnectionDetails.ESTABLISH_CONNECTION.getBytes());
        connectionClass.sendDataToServer();
//        connectionClass.receiveAckFromServer();
    }

    private static void setServerInfo() {
        connectionDetails = new ConnectionDetails();
        connectionDetails.setHostName("localhost");
        connectionDetails.setPortNumber(5555);
    }
}
