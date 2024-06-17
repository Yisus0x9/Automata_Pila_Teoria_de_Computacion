package fileservice;


import java.io.*;
import java.io.File;

public class ArchivoServicio {
    File archivo;
    public ArchivoServicio(File archivo){
        //if(archivo.exists()){archivo.delete();}
        this.archivo=archivo;

    }


    public void escribir(String cad,boolean append){
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter(archivo,append))){
            escritor.append(cad+"\n");
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    public StringBuffer leer(){
        StringBuffer arch=new StringBuffer();
        try(BufferedReader lector=new BufferedReader(new FileReader(archivo))){
            while(lector.readLine()!=null){
                arch.append(lector.readLine()).append("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return arch;
    }
}
