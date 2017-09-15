/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kevin
 */
public class conector {
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/galeria?useSSL=false";
    String usr="root";
    String password="1234";

    public conector() {
        try {
            conexion = DriverManager.getConnection(url,usr,password);            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void guardarFile(String nombre, String cod,String path_archivo) throws FileNotFoundException, SQLException, IOException {{
            FileInputStream auxLibro = null;
            File file = new File(path_archivo);auxLibro = new FileInputStream(file);PreparedStatement pstm = conexion.prepareStatement("insert into " + 
                        " LIBRO (NOMBRE_LIBRO, COD_LIBRO,BOOK) " + " values(?,?,?)");
            pstm.setString(1, nombre);         
            pstm.setString(2, cod);
            pstm.setBinaryStream(3, auxLibro,(int) file.length());
            pstm.execute();
            pstm.close();
            auxLibro.close();
            
        }
   }    
    public HashMap bajar_archivo() throws SQLException, IOException {       
        //FileOutputStream output = new FileOutputStream("hola3.txt");
        HashMap<String,Blob> lista = new HashMap<>();
        Blob archivo=null;
        String nombre="";
        Statement st;            
        ResultSet rs;
        st = conexion.createStatement();
        rs=st.executeQuery("select NOMBRE_LIBRO,BOOK from LIBRO where NOMBRE_LIBRO="+"'segundo.txt'"); //selecion del libro con respecto a su nombre
        while(rs.next()){            
            nombre = rs.getObject("NOMBRE_LIBRO").toString();
            archivo = rs.getBlob("BOOK");            
            lista.put(nombre, archivo);
        }
        return lista; 
    }
    
}
