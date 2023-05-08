import java.io.*;
import java.net.*;
public class Client {
    public static void main(String args[])
    {
        Client dac = new Client();
        dac.go();
    }

    public void go()
    {
        try
        {
            Socket incoming = new Socket("127.0.0.1",4242);
            InputStreamReader stream = new InputStreamReader(incoming.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            String advice = reader.readLine();
            reader.close();
            System.out.println("Today's advice is "+advice);
        }
        catch(Exception e)
        {
            System.out.println("Client Side Error");
        }
    }
}
