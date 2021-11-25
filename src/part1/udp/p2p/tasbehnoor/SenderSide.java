package part1.udp.p2p.tasbehnoor;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
        
public class SenderSide  extends Thread{
    
    String IP2;
    int port2;
    String msg;
    byte [] changeTOByte=new byte[1024];
    
    public SenderSide (String IPAddress,int portNum,String msg)
    {
        System.out.println("Send. Side const: ");
        
        this.IP2=IPAddress;
        this.port2=portNum;
        this.msg=msg;
    }
    
    @Override
   public void run()
    {
        try
        {
        System.out.println(msg);
        DatagramSocket SenderSkt =new DatagramSocket();
        changeTOByte =msg.getBytes();
        DatagramPacket pkts=new DatagramPacket(changeTOByte,changeTOByte.length,InetAddress.getByName(IP2),port2);
        SenderSkt.send(pkts);
        }
        catch(Exception e)
        {
            System.out.println("Send. Side Exc: "+e);
        }
    }
}
