package NIOTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {
    public static void main(String[] args) throws IOException {
        //服务器处理客户端请求
        ServerSocket serverSocket = new ServerSocket(8989);
        //收到客户端请求之后为每个客户端创建一个线程进行处理
        new Thread(()->{
            while(true){
                try{
                    Socket socket = serverSocket.accept();
                    //为每一个新连接都创建一个线程，复制读取数据
                    new Thread(()->{
                        try{
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while((len=inputStream.read(data))!=-1){
                                System.out.println(new String(data,0,len));
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }).start();
                }catch (IOException e){
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
