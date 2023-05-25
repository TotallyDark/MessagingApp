import javax.swing.*;

public class Friend extends JPanel {
    private String name;
    private String ipAddress;
    private int portalNumber;

    public Friend(String name, String ipAddress, int portalNumber) {
        this.name = name;
        this.ipAddress = name;
        this.portalNumber = portalNumber;
    }
    public String getFriendName(){
        return name;
    }
    public String setFriendName(String name){
        this.name = name;
        return name;
    }
    public String getIpAddress(){
        return ipAddress;
    }
    public String setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
        return ipAddress;
    }
    public int getPortalNumber(){
        return portalNumber;
    }
    public int setPortalNumber(int portalNumber){
        this.portalNumber = portalNumber;
        return portalNumber;
    }
}
