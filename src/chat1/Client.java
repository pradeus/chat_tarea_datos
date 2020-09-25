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

//esto es una sobrecarga
public void Update_Data(String IP_APP, int PUERTO_APP){
    Puerto=PUERTO_APP;
    IP=IP_APP;
}

//funcion encargada de tirarle el mensaje al socket output
public void enviar_mensaje(String nombre, String texto) throws IOException{
    try{
        try (Socket cliente = new Socket(IP,Puerto)) {
            DataOutputStream mensaje_saliente = new DataOutputStream(cliente.getOutputStream());
            mensaje_saliente.writeUTF(compresor(nombre,texto));
        }
        
    }catch(IOException e){
        System.out.println("F por el cliente");
    }
    }
//esto es una abstraccion, un compresor lo interpreto como una maquina que comprimira el mensaje previo a ser enviado
public String compresor(String nombre, String texto){
    mensaje = nombre+"°"+texto;
        return mensaje; 
}
}
