/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

/**
 *
 * @author kevin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        //--- primero se guarda el archivo
        //new conector().guardarFile("segundo.txt", "002", "/home/kevin/hola.txt");
        
        HashMap<String,Blob> list = new HashMap<>();        
        FileOutputStream output;
        InputStream inStream=null;
          //retorna una lidst con el archivo y su nombre
        list =new conector().bajar_archivo();
        
        for (Map.Entry<String, Blob> entry : list.entrySet()) {
            String key = entry.getKey();            
            output=new FileOutputStream(key);
            Blob value = entry.getValue();
            System.out.println(key);
            inStream = value.getBinaryStream();
            int length = -1;
            int size = (int) value.length();
            byte[] buffer = new byte[size];
            while ((length = inStream.read(buffer)) != -1){
                output.write(buffer, 0, length);
            }
        }
         
         
         
         
         
         
         
    
        
    }
    
}
