package automata;

import fileservice.ArchivoServicio;
import tda_pila.Pila;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class StackAutomata extends JPanel {
    //Constantes al escrbir las DI
    private final static String EPS="ε";
    private final static String CAMBID="⊦";
    private  static Pila<String> aut;//Pila
    private static String ID;
    private  static StringBuffer cadPanel =new StringBuffer();//cadena a lanzar en el panel
    private static String cadena;
    private static boolean ref;
    private static int xcad=127;
    private ArchivoServicio srv;

    public StackAutomata(String nombreArchID){
            aut=new Pila<>();
            setLayout(new BorderLayout());
            setBackground(new Color(100,100,110));
            srv=new ArchivoServicio(new File(nombreArchID));
    }

    public boolean veryfi(String cadenaS){
        this.cadena=cadenaS;
        System.out.println("La Cadena Ingresada es: " + cadena+ " : "+aut.getLenght());
        //Bandera que indica si se realizará la animación
        ref= cadenaS.length()<=30 ? true : false;
        //se ingresan todos los unos

        int i=pushUnos(cadenaS.toCharArray());//se recupera el índice donde quedo la cadena
        JOptionPane.showMessageDialog(null,"Se han ingresado todos los 0´s","Stack llena",JOptionPane.INFORMATION_MESSAGE);
        aut.listar();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // la función popUnos saca los ceros de acuerdo con la cadena y retorna si es aceptada o no
        return popUnos(i,cadena.toCharArray());
    }

    private int pushUnos(char []cadena){
        int i=0;
        pause();
        ID="(q,"+this.cadena.substring(i,this.cadena.length())+","+aut.listar()+")"+CAMBID;
        System.out.println(ID.toString());
        srv.escribir(ID.toString(),true);
        while(cadena[i]!='1'){
            if(cadena[i]=='0'){
                aut.push("X");
                i++;
                cadPanel.append(0);
                xcad+=8;
                pause();
                ID="(q,"+this.cadena.substring(i,this.cadena.length())+","+aut.listar()+")"+CAMBID;
                System.out.println(ID.toString());
                srv.escribir(ID.toString(),true);
            }else{
                System.out.println("Simbolo no aceptado");
            }
        }
        return i;
    }

    private boolean popUnos(int i,char []cadena){
        pause();
        while((i<this.cadena.length() && cadena[i]=='1') ) {
            aut.pop();
            i++;
            cadPanel.append(1);
            xcad+=8;
            pause();
            if(!escribir(i)){return false;}
        }
        return (aut.isEmpty() && i>=cadena.length) ? true : false;
    }

    private boolean escribir(int i){
        if(aut.isEmpty()){
            if(i>=this.cadena.length()){
                ID="(f,"+EPS+","+aut.listar()+")"+CAMBID;
                System.out.println(ID.toString());
                srv.escribir(ID.toString(),true);
            }else{
               ID="(p,"+this.cadena.substring(i,this.cadena.length())+","+aut.listar()+")"+CAMBID;
                System.out.println(ID.toString());
                srv.escribir(ID.toString(),true);
                //JOptionPane.showMessageDialog(null,"Cadena no Aceptada","Resultado",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }else{
            if(i>=this.cadena.length()){
                ID="(p,"+EPS+","+aut.listar()+")"+CAMBID;
                System.out.println(ID.toString());
                srv.escribir(ID.toString(),true);
            }else{
               ID="(p,"+this.cadena.substring(i,this.cadena.length())+","+aut.listar()+")"+CAMBID;
                System.out.println(ID.toString());
                srv.escribir(ID.toString(),true);
            }
        }
        return true;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        int i=0,tam=40,y=this.getHeight()-70,x=(this.getWidth()/2)-tam;
        if(aut.getLenght()>=12){tam=30;}
        g.setColor(Color.WHITE);
        g.setFont(new Font("Hack Nerd Font",1,14));
        g.drawString("la Cadena es: "+ cadPanel.toString(), 20,30);
        g.setColor(Color.RED);
        g.fillOval(xcad,35,10,10);
        g.setColor(Color.BLUE);
        g.fillRect(x,y,tam,tam);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,tam,tam);
        g.drawString("Z0",x+12,y+20);

        if(aut.getLenght()>=1) {
                while (i <= aut.getLenght()-1) {
                    g.setColor(Color.BLUE);
                    y -= tam;
                    g.fillRect(x, y, tam, tam);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, tam, tam);
                    g.drawString("X",x+15,y+20);
                    g.setColor(Color.BLACK);
                    i++;
                }
            }
    }

    public void reset(){
        this.cadPanel =new StringBuffer();
        srv.escribir("\n\n",true);
    }
    private void pause(){
        if (ref) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
