package com.company.mytest;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class IOTest implements Serializable{
    /**
     * 递归地列出一个目录下多有文件
     * @param dir
     */
    public static void listAllFiles(File dir){
        if(dir==null)   return;
        if(dir.isFile()) System.out.println(dir.getName());
        for(File file:dir.listFiles()){
            listAllFiles(file);
        }
    }

    /**
     * 实现文件复制
     * @param src
     * @param dist
     * @throws IOException
     */
    public static void copyFile(String src,String dist) throws IOException{
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);
        byte[] buffer = new byte[20*1024];
        int cnt=0;
        while((cnt=in.read(buffer,0,buffer.length))!=-1){
            out.write(buffer,0,cnt);
        }
        in.close();
        out.close();
    }
    /**
     * 使用NIO快速复制文件
     */
    public static void fastCopy(String src,String dist) throws IOException{
        FileInputStream fin = new FileInputStream(src); //获取源文件的输入字节流
        FileOutputStream fout = new FileOutputStream(dist); //获取目标文件的输出字节流
        //获取输入字节流的文件通道
        FileChannel fcin = fin.getChannel();
        //获取输出字节流的文件通道
        FileChannel fcout = fout.getChannel();

        //为缓冲区分配1024个字节
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while(true){
            int r = fcin.read(buffer);
            if(r==-1)
                break;
            //切换读写
            buffer.flip();
            //把缓冲区的内容写入到输出文件中
            fcout.write(buffer);
            buffer.clear();
        }

    }

    /**
     * 实现逐行输出文本文件的内容
     */
    public static void readFileContent(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }
        //装饰者模式使得BufferedReader组合了一个Reader对象
        //在调用BufferedReader的close()方法时会调用Reader的close(),因此只需要一个close()
        bufferedReader.close();
    }

    private static class A implements Serializable {

        private int x;
        private String y;

        A(int x, String y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x = " + x + "  " + "y = " + y;
        }
    }

    private static String readDataFromSocketChannel(SocketChannel socketChanne) throws IOException{
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuffer data = new StringBuffer();
        while(true){
            byteBuffer.clear();
            int n = socketChanne.read(byteBuffer);
            if(n==-1)
                break;
            byteBuffer.flip();
            int limit = byteBuffer.limit();
            char[] dst = new char[limit];
            for(int i=0;i<limit;i++){
                dst[i] = (char)byteBuffer.get(i);
            }
            data.append(dst);
            byteBuffer.clear();
        }
        return data.toString();
    }
    public static void main(String[] args) throws FileNotFoundException,IOException,ClassNotFoundException{
        //套接字NIO实例
        Selector selector = Selector.open();

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket = ssChannel.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8989);
        serverSocket.bind(address);

        while(true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterable = keys.iterator();

            while(keyIterable.hasNext()){
                SelectionKey key = keyIterable.next();
                if(key.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();

                    //服务器会为每个连接创建一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);

                }else if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    System.out.println(readDataFromSocketChannel(socketChannel));
                    socketChannel.close();

                }
                keyIterable.remove();
            }
        }
    }
}
