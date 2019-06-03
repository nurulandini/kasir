/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER
 */
public class session {
    private static String id;
    private static String nama_lengkap;
    public static String getId(){
        return id;
    }
    
    public static void setId(String id){
        session.id= id;
    }
    
    public static String getNama_lengkap(){
        return nama_lengkap;
    }
    
    public static void setNama_lengkap(String nama_lengkap){
        session.nama_lengkap=nama_lengkap;
    }
    
    
}
