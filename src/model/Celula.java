/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

class Celula implements Serializable{
    
    boolean estado; //true:aberto false:fechado
    int valor;  
    
    Celula(){
        this.estado = false;
        this.valor = 0; 
    }
    
    Celula(int valor){
        this.estado = false;
        this.valor = valor; 
    }
    
    public void setEstado(){
        this.estado = !estado;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
    public int getValor(){
        return valor;
    }   
    
    public void setValor(int valor){
        this.valor = valor;
    }

}