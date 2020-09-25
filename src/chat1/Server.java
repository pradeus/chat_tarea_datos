/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 50684
 */
public class Server implements Runnable{//esto es para hacer los threads
    
    
    public String mensaje[];
    public final DefaultTableModel modelo;
    private int puerto;
    
//esta funcion genera el thread del server, recibe la lista que será el display del chat
    public Server(DefaultTableModel modelo_server, int puerto_APP) {
        modelo = modelo_server;
        puerto = puerto_APP;
        Thread thread1 = new Thread( this);
        thread1.start();
    }
    
    
    public void Update(int puerto_APP){
        puerto = puerto_APP;
        
    }

    @Override
    public void run() {
        try{
            //creando los sockets del server
            ServerSocket Servidor = new ServerSocket(puerto);
            while(true){
                try (Socket cliente = Servidor.accept()) {
                    DataInputStream mensaje_entrante = new DataInputStream(cliente.getInputStream());
                    mensaje = mensaje_entrante.readUTF().split("°");
                    modelo.addRow(new Object[]{mensaje[0],mensaje[1]});
                    cliente.close();
                }
                
            }
        }catch(IOException e){
            System.out.println("F por el socket");
        }
    }
}
