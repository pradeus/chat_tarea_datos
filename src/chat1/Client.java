/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//defino las variables de la clase "cliente"
public class Client {
    private int Puerto;
    private String IP;
    private String mensaje;
    
 
//esto toma la ip y el puerto cuando se le llama
public Client(String IP_APP, int PUERTO_APP){
    Puerto=PUERTO_APP;
    IP=IP_APP;
}

//esta funcion actualiza el puerto y la ip en caso de ser necesario
public void Update_Data(String IP_APP, int PUERTO_APP){
    Puerto=PUERTO_APP;
    IP=IP_APP;
}

//funcion encargada de tirarle el mensaje al socket output
public void enviar_mensaje(String nombre, String texto) throws IOException{
    try{
        try (Socket cliente = new Socket(IP,Puerto)) {//se crea el socket tipo cliente
            DataOutputStream mensaje_saliente = new DataOutputStream(cliente.getOutputStream());
            mensaje_saliente.writeUTF(compresor(nombre,texto));//se manda a compresor
        }
        
    }catch(IOException e){
        System.out.println("F por el cliente");
    }
    }
//esto es una funcion que junta el nombre con el mensaje en un string separado por este elemento °
public String compresor(String nombre, String texto){
    mensaje = nombre+"°"+texto;
        return mensaje; 
}
}
