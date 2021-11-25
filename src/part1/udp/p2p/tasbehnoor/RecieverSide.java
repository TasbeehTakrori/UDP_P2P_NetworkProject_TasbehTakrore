package part1.udp.p2p.tasbehnoor;

import java.awt.Color;
import java.net.*;
import javax.swing.text.*;
import static part1.udp.p2p.tasbehnoor.PeerFrame.jLabel4;
import static part1.udp.p2p.tasbehnoor.PeerFrame.jTextPane1;

 public class RecieverSide extends Thread{
    int portNum;
    DatagramSocket recieveSkt;
    byte [] MSGBytes=new byte[1024];
    String msg=null;
    String IP1;
         public RecieverSide (int portNum,String IPAddress)
    {   /// GET PORT NUMBER AND IP ADDRESS of reciever 
                               System.out.println("Recive. Side const## ");
        this.IP1= IPAddress;
        this.portNum=portNum;
        try {
            recieveSkt=new DatagramSocket(portNum,InetAddress.getByName(IP1));//rECIEVER SOCKET (DOOR)
        } catch (Exception ex){
           System.out.print(ex);
        }
    }
         
         
    void appendToPane(String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        javax.swing.text.AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = jTextPane1.getDocument().getLength();
        jTextPane1.setCaretPosition(len);
        jTextPane1.setCharacterAttributes(aset, false);
        jTextPane1.replaceSelection(msg);
        System.out.println("I am inside Append in Reciver + msg="+msg);

    }
    
        @Override
    public void run()
    {
          try
        {
        while(true)
        {
           System.out.println("Recive. Side run##");

           DatagramPacket recievePkt =new DatagramPacket(MSGBytes,MSGBytes.length);/// DATA RECIVED AS PACKET 
           recieveSkt.receive(recievePkt);
         msg=new String(recievePkt.getData());/// GET DATA AND  TRANSFER FROM BYTE TO STRING 
                                       // System.out.println("Recive.msg**+ :" + msg);
           
           if(msg != null) // MESSAGE RECIEVED 
           {
               String [] msgparts = msg.split("~");
               jLabel4.setText("Status: Received From IP: " +msgparts[0]+", Port: "+msgparts[1]);
               
               appendToPane("• "+msgparts[2].trim()+"\n", Color.BLUE);
           }
        }
        }
        catch(Exception e)
        {     
            
            System.out.println("Rec. Side Exc: "+e);
        }
        }
 
}

