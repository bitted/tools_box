package com.cyou.hithere.center.util.https;  
  
  
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.FileInputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;  
import java.security.KeyStore;  
  


import javax.net.ssl.KeyManagerFactory;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLServerSocket;  
import javax.net.ssl.TrustManagerFactory;  
  
/** 
 * 
 * @author Leo 
 */  
public class Server implements Runnable{  
  
    private static final int    DEFAULT_PORT                    = 7777;  
  
    private static final String SERVER_KEY_STORE_PASSWORD       = "123456";  
    private static final String SERVER_TRUST_KEY_STORE_PASSWORD = "123456";  
  
    private SSLServerSocket     serverSocket;  
  
    /** 
     * 启动程序 
     *  
     * @param args 
     */  
    public static void main(String[] args) {  
        Server server = new Server();  
        server.init();  
        Thread thread = new Thread(server);  
        thread.start();  
    }  
  
    public synchronized void start() {  
        if (serverSocket == null) {  
            System.out.println("ERROR");  
            return;  
        }  
        while (true) {  
            try {  
                Socket s = serverSocket.accept();  
                InputStream input = s.getInputStream();  
                OutputStream output = s.getOutputStream();  
  
                BufferedInputStream bis = new BufferedInputStream(input);  
                BufferedOutputStream bos = new BufferedOutputStream(output);  
  
                byte[] buffer = new byte[20];  
                bis.read(buffer);  
                System.out.println("------receive:--------"+new String(buffer).toString());  
  
                bos.write("yes".getBytes());  
                bos.flush();  
  
                s.close();  
            } catch (Exception e) {  
                System.out.println(e);  
            }  
        }  
    }  
    public void init() {  
        try {  
            SSLContext ctx = SSLContext.getInstance("SSL");  
            String kclient = "/Users/admin/Desktop/im.dev/guangquanerdev.keystore";
			String tclient = "/Users/admin/Desktop/im.dev/tclient1.keystore";
			
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");  
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");  
  
            KeyStore ks = KeyStore.getInstance("JKS");  
            KeyStore tks = KeyStore.getInstance("JKS");  
  
            ks.load(new FileInputStream(kclient), SERVER_KEY_STORE_PASSWORD.toCharArray());  
            tks.load(new FileInputStream(tclient), SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());  
  
            kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());  
            tmf.init(tks);  
  
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);  
  
            serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(DEFAULT_PORT);  
            serverSocket.setNeedClientAuth(true);   
        } catch (Exception e) {  
            System.out.println(e);  
        }  
    }  
  
    @Override
	public void run() {  
        // TODO Auto-generated method stub  
        start();  
    }  
}  
