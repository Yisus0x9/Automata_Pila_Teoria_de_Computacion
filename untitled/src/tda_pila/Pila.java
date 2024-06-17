package tda_pila;

import javax.swing.*;
import java.awt.*;

public class Pila<T> extends JPanel {

    public Nodo<T> tope;
    private int lenght=0;

    public Pila(){
        Nodo<T> nuevo=new Nodo<>((T)"Z0");
        tope=nuevo;
        setLayout(new BorderLayout());
        setBackground(new Color(100,100,110));
    }

    public boolean isEmpty(){
        if(tope.getValor()=="Z0") {
            System.out.println("La pila esta en el estado Z0");
            return true;
        }
        return  false;
    }

    public Pila push(T valor){
        Nodo<T> nuevo=new Nodo<>(valor);
            nuevo.next= tope;
            tope=nuevo;

        lenght++;
        return this;
    }

    public void pop(){
        if(!isEmpty()){
            Nodo<T> temp= tope.next;
            tope.next=null;
            tope =temp;
            temp=null;
            System.gc();
        }
        lenght--;
    }

    public String listar(){
        StringBuffer pila=new StringBuffer();
        Nodo<T> aux= tope;
        while(aux!=null){
            pila.append(aux.getValor());
            aux=aux.next;
        }
        return pila.toString();
    }

    public T getValor(){
        return tope.getValor();
    }
    public int getLenght(){
        return  lenght;
    }

    public void setLenght(int lenght){
        this.lenght=lenght;
    }



    private class Nodo<T>{
        private T valor;
        private Nodo next;

        public Nodo(T valor){
            this.valor=valor;
            next=null;
        }

        public T getValor(){
            return  valor;
        }

        public Nodo getNext(){
            return next;
        }
    }
}

