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
    public Server(DefaultTableModel modelo_server, int puerto_APP) {//uso defaulttablemodel porque use una lista para que tire el nombre-mensaje y solo use addrow para que se vea mejor
        modelo = modelo_server;
        puerto = puerto_APP;
        Thread thread1 = new Thread( this);
        thread1.start();
    }
    
    
    public void Update(int puerto_APP){//funcion para actualizar el port
        puerto = puerto_APP;
        
    }

    @Override
    public void run() {
        try{
            //creando los sockets del server *** aquí se presenta el error
            ServerSocket Servidor = new ServerSocket(puerto);
            while(true){
                try (Socket cliente = Servidor.accept()) {//aquí actúa como receptor 
                    DataInputStream mensaje_entrante = new DataInputStream(cliente.getInputStream());
                    mensaje = mensaje_entrante.readUTF().split("°");//aqui se separa el mensaje en la tabla gracias a compresor
                    modelo.addRow(new Object[]{mensaje[0],mensaje[1]});//se agregaría el mensaje a la tabla
                    cliente.close();//cerramos el socket
                }
                
            }
        }catch(IOException e){
            System.out.println("F por el socket");//aquí termina el error al abrir el segundo programa
        }
    }
}
