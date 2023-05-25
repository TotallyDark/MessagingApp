import javax.swing.*;

public class Friend extends JPanel {
    private String name;
    private String ipAddress;
    private String portalNumber;

    public Friend(String name, String ipAddress, String portalNumber) {
        this.name = name;
        this.ipAddress = ipAddress;
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
    public String getPortalNumber(){
        return portalNumber;
    }
    public String setPortalNumber(String portalNumber){
        this.portalNumber = portalNumber;
        return portalNumber;
    }
}
