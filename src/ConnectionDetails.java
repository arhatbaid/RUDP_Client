public class ConnectionDetails {
    private String hostName;
    private int portNumber;
    private String fileName;
    private int noOfPartition;

    public static String ESTABLISH_CONNECTION = "0";
    public static String SEND_DATA = "1";
    public static String SEND_META_DATA = "2";
    public static String SEND_ACK = "3";

    public static String CONNECTED = "4";
    public static String DISCONNECTED = "5";
    public static String IS_CONNECTING = "6";


    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getNoOfPartition() {
        return noOfPartition;
    }

    public void setNoOfPartition(int noOfPartition) {
        this.noOfPartition = noOfPartition;
    }
}
