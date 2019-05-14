package NIOTest;

import java.io.IOException;
import java.net.Socket;
import java.time.Instant;
import java.util.Date;

public class IOClient {
    public static void main(String[] args){
        new Thread(()->{
            try{
                Socket socket = new Socket("127.0.0.1",8989);
                while(true){

                    socket.getOutputStream().write((new Date() +" : hello world").getBytes());
                    Thread.sleep(2000);
                }
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
