import automata.StackAutomata;
import tda_pila.Pila;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Test {
    private static JFrame j=new JFrame("Automata de Pila");
    private static StackAutomata pila;
    private static String cad;
    public static void main(String[] args) {
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        j.setSize(500,600);
       // j.setVisible(true);
        //j.add(pila, BorderLayout.CENTER);
        j.setLocationRelativeTo(null);

        int op=0;
       do{
           pila=new StackAutomata("ID.txt");
            System.out.println("Eliga una Opcion\n\t1.Ingresar Cadena\n\t2.Cadena Random\n\t3.Salir");
            op=new Scanner(System.in).nextInt();
            if(op==3){
                System.exit(0);
                break;
            }

            if(op==1){
                System.out.println("Ingrese una cadena");
                cad=new Scanner(System.in).nextLine();

                if(cad.length()<=30) {
                    j.add(pila, BorderLayout.CENTER);
                    j.setVisible(true);
                }

                if(pila.veryfi(cad)){
                    System.out.println("LA CADENA PERTENECE AL LENGUAJE DEL AUTOMATA");
                    JOptionPane.showMessageDialog(null,"Cadena Aceptada","Resultado",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    System.out.println("LA CADENA NO PERTENECE AL LENGUAJE DEL AUTOMATA");
                    JOptionPane.showMessageDialog(null,"Cadena no Aceptada","Resultado",JOptionPane.INFORMATION_MESSAGE);
                }

            }
            if(op==2){
                System.out.println("Cadena Generada");
                cad=Test.randomString();
                if(cad.length()<=30) {
                    j.add(pila, BorderLayout.CENTER);
                    j.setVisible(true);
                }
                if(pila.veryfi(cad)){
                    System.out.println("LA CADENA PERTENECE AL LENGUAJE DEL AUTOMATA");
                    JOptionPane.showMessageDialog(null,"Cadena Aceptada","Resultado",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    System.out.println("LA CADENA NO PERTENECE AL LENGUAJE DEL AUTOMATA");
                    JOptionPane.showMessageDialog(null,"Cadena no Aceptada","Resultado",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            pila.reset();
        }while(op!=3);

    }

    public static String randomString(){
        int caso=(int)(Math.random()*10+1);
        int largo=(int)(Math.random()*50000+1);
        int ref=0;
        StringBuffer cad=new StringBuffer();
        if(caso%2==0){
            while(ref<largo){cad.append("0"); ref++;}
            ref=0;
            while(ref<largo){cad.append("1"); ref++;}
            //System.out.println("La Cadena generada es: "+cad);

        }else{
            while(ref<largo/(int)((Math.random()*5)+2)){cad.append("0"); ref++;}
            ref=0;
            while(ref<largo/(int)((Math.random()*5)+2)){cad.append("1"); ref++;}
            //System.out.println("La Cadena generada es: "+cad);
        }

        return cad.toString();
    }
}
