import java.io.*;
import java.net.*;
public class Server {
    public static void main(String args[])
    {
        Server das = new Server();
        das.go();
    }

    public void go()
    {
        try
        {
            ServerSocket serversock = new ServerSocket(4242);

            while(true)
            {
                Socket outgoing = serversock.accept();
                PrintWriter writer = new PrintWriter(outgoing.getOutputStream());
                writer.println("Hello there");
                writer.close();

            }
        }
        catch(Exception e)
        {
            System.out.println("Server Side Problem");
        }
    }
}
