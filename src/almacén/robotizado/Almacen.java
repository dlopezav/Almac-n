/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import becker.robots.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class Almacen {
    private City ciudad;
    private Robot[] empleado;
    private Estante[] estantes;
    private Thing[] stand;
    
    
    public Almacen(){
        this.ciudad=new City(0, 0, 12, 12);
        for(int i=1;i<8;i++){
            Wall pared=new Wall(ciudad,i,4,Direction.WEST);
            pared=new Wall(ciudad,i,3,Direction.EAST);
        } 
        for(int i=1;i<11;i++){
            Wall pared=new Wall(ciudad,1,i,Direction.NORTH);
            if(i<10){
                pared=new Wall(ciudad,i,1,Direction.WEST);
                pared=new Wall(ciudad,i,10,Direction.EAST);
                pared=new Wall(ciudad,i,10,Direction.EAST);
            }
        }
         for(int i=0;i<4;i++){
            Wall pared=new Wall(ciudad,9,i+1,Direction.SOUTH);
            pared = new Wall(ciudad,9,i+7,Direction.SOUTH);
        }
         this.empleado= new Robot[8];
         this.estantes =  new Estante[20];
         for (int i = 0; i < 20; i++) {
            this.estantes[i]=new Estante(i);
        }
         this.stand =new Thing[20];
         for (int i = 0; i < 8; i++) {
            this.empleado[i]=new Robot(ciudad,8-i,2,Direction.EAST);
        }
         for (int i = 6; i>0; i--) {
            stand[6-i]=new Thing(ciudad,i+1,4);
            stand[6-i].getIcon().setLabel("Estante"+String.valueOf(7-i));
            if(i>1){
            stand[11-i]=new Thing(ciudad,1,11-i);
            stand[11-i].getIcon().setLabel("Estante"+String.valueOf(13-i));
            }
            stand[17-i]=new Thing(ciudad,8-i,10);
            stand[17-i].getIcon().setLabel("Estante"+String.valueOf(18-i));
        }
        stand[17]=new Thing(ciudad,8,10);
        stand[17].getIcon().setLabel("Estante"+String.valueOf(18));

        stand[18]=new Thing(ciudad,9,9);
        stand[18].getIcon().setLabel("Estante"+String.valueOf(19));
        stand[19]=new Thing(ciudad,9,8);
        stand[19].getIcon().setLabel("Estante"+String.valueOf(20));  
        ingresarProductos("Shampoo", 5);
    }
    public boolean ingresarProductos(String producto, int cant){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.estantes[i].getCajas()[j]!=null){
                    if(this.estantes[i].getCajas()[j].getProducto().equals(producto)&&this.estantes[i].getCajas()[j].getCantidad()<7){
                        int max=7-this.estantes[i].getCajas()[j].getCantidad();
                        if(cant<max){
                            this.estantes[i].getCajas()[j].setCantidad(this.estantes[i].getCajas()[j].getCantidad()+cant);

                            tomar(empleado[0],i);
                            return true;
                        }else{
                            cant=cant-max;
                            this.estantes[i].getCajas()[j].setCantidad(7);
                            tomar(empleado[0],i);
                        }
                    }  
                }else{
                    if(this.estantes[i].getCajas()[j]==null){
                        if(cant<7){
                            this.estantes[i].addCaja(new Caja(cant,producto));
                            tomar(empleado[0],i);
                            return true;
                        }else{
                            this.estantes[i].addCaja(new Caja(7,producto));
                            cant-=7;
                            tomar(empleado[0],i);
                        }
                        
                    }
                }
            }
        }
        return false;
    }
    private void move(Robot R,int c){
        for (int i = 0; i < c; i++) {
            R.move();
        }  
    }
    private void turn(Robot R,int c){
        for (int i = 0; i < c; i++) {
            R.turnLeft();
        }  
    }
    private void comprovar(Robot R,int i, int c, boolean t){
        if(c==i){
                turn(R,1);
                move(R,1);
                R.pickThing();
                turn(R,2);
                move(R,1);
                turn(R,1);
            }
    }
    private void recorrer(Robot R, int c,boolean t){
        move(R,3);
        turn(R,1);
        for (int i = 0; i < 6; i++) {
            comprovar(R,i,c+1,t);
            move(R,1);
        }
        turn(R,3);
        for (int i = 6; i < 10; i++) {
            comprovar(R,i,c,t);
            move(R,1);
        }
        comprovar(R,10,c,t);
        turn(R,3);
        for (int i = 11; i < 17; i++) {
            comprovar(R,i,c,t);
            move(R,1);
        }
        comprovar(R,17,c,t);
        turn(R,3);
        for (int i = 18; i < 20; i++) {
            comprovar(R,i,c,t);
            move(R,1);
        }
    }
    public void tomar(Robot R,int c){
        recorrer(R,c,true);
        move(R,1);
        turn(R,1);
        move(R,2);
        R.putThing();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
        R.pickThing();
        turn(R,2);
        move(R,2);
        turn(R,1);
        move(R,1);
        
    }
}
