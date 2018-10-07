/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import becker.robots.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private ArrayList<Producto> productos=new ArrayList<>();
    private int robot=0;

    public boolean addProducto(String nombre, int precioPU) {
        for(Producto p: productos){
            if(p.getNombre().equals(nombre)){
                return false;
            }             
        }
        productos.add(new Producto(nombre,precioPU));
        return true;
    }
    public void deleteProducto(Producto producto){
        this.productos.remove(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
       
    public Almacen(){
        this.ciudad=new City(0, 0, 11, 14);
        for(int i=1;i<8;i++){
            Wall pared=new Wall(ciudad,i,4,Direction.WEST);
            pared=new Wall(ciudad,i,3,Direction.EAST);
        } 
        for(int i=1;i<13;i++){
            Wall pared=new Wall(ciudad,1,i,Direction.NORTH);
            if(i<10){
                pared=new Wall(ciudad,i,1,Direction.WEST);
                pared=new Wall(ciudad,i,12,Direction.EAST);
                //pared=new Wall(ciudad,i,10,Direction.EAST);
            }
        }
         for(int i=1;i<9;i++){
            Wall pared=new Wall(ciudad,9,i,Direction.SOUTH); 
        }
        for (int i = 6; i < 11; i++) {
            Wall pared=new Wall(ciudad,3,i,Direction.NORTH);
            pared=new Wall(ciudad,4,i,Direction.NORTH);
            pared=new Wall(ciudad,i-3,6,Direction.WEST);
            pared=new Wall(ciudad,i-3,10,Direction.EAST);
            if(i>6){
                pared=new Wall(ciudad,i-3,9,Direction.WEST);
                pared=new Wall(ciudad,i-3,7,Direction.EAST);
            }
        }
        Wall pared =new Wall(ciudad,7,8,Direction.SOUTH);
         this.empleado= new Robot[8];
         this.estantes =  new Estante[20];
         for (int i = 0; i < 20; i++) {
            this.estantes[i]=new Estante(i);
        }
         this.stand =new Thing[20];
         for (int i = 0; i < 8; i++) {
            this.empleado[i]=new Robot(ciudad,8-i,2,Direction.EAST);
        }
         for (int i = 6; i>=0; i--) {
            if(i>0){
            stand[6-i]=new Thing(ciudad,i+1,4);
            stand[6-i].getIcon().setLabel("Estante"+String.valueOf(7-i));
            }
            stand[13-i]=new Thing(ciudad,1,11-i);
            stand[13-i].getIcon().setLabel("Estante"+String.valueOf(13-i));
            
            stand[19-i]=new Thing(ciudad,8-i,12);
            stand[19-i].getIcon().setLabel("Estante"+String.valueOf(20-i));
        }
         
        
    }
    public boolean ingresarProductos(HashMap<Integer, Integer[]> pedido){
        
        pedido.entrySet().forEach((it) -> {
            if(!this.estantes[it.getValue()[0]].isTomado())ingresar(empleado[robot++],it.getValue()[0]);
            productos.get(it.getKey()).ingreso(it.getValue()[0], it.getValue()[1], it.getValue()[2]);
            estantes[it.getValue()[1]].getCajas()[it.getValue()[2]].setProducto(productos.get(it.getKey()));
            estantes[it.getValue()[1]].getCajas()[it.getValue()[2]].setCantidad(it.getValue()[0]);
            String label="";
            for (int i = 0; i < 3; i++) {
                if(estantes[it.getValue()[1]].getCajas()[i].getProducto()!=null)label+=estantes[it.getValue()[1]].getCajas()[i].getProducto().getNombre();
            }
            stand[it.getValue()[1]].getIcon().setLabel(label);
            stand[it.getValue()[1]].getIcon().setSize(1);
        });
        this.robot=0;
        pedido.entrySet().forEach((it) -> {
            if(this.estantes[it.getValue()[0]].isTomado())devolver(empleado[robot],it.getValue()[0]);
        });
        this.robot=0;
        return true;
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
                if(t)R.pickThing();
                else R.putThing();
                turn(R,2);
                move(R,1);
                turn(R,1);
            }
    }
    private void recorrer(Robot R, int c,boolean t){
        for (int i = 0; i < 6; i++) {
            move(R,1);
            comprovar(R,i,c,t);            
        }
        turn(R,3);
        for (int i = 6; i < 12; i++) {
            comprovar(R,i,c,t);
            move(R,1);
        }
        comprovar(R,12,c,t);
        turn(R,3);
        for (int i = 13; i < 19; i++) {
            comprovar(R,i,c,t);
            move(R,1);
        }
        comprovar(R,19,c,t);
        
        
    }
    private void ingresar(Robot R,int c){
        estantes[c].setTomado(true);
        move(R,1);
        turn(R,3);
        move(R,8-R.getStreet());
        turn(R,1);
        move(R,2);
        turn(R,1);
        recorrer(R,c,true);
        turn(R,3);
        move(R,1);
        turn(R,3);
        if(robot<5){
            move(R,4);
            turn(R,1);
            move(R,1);
            turn(R,1);
            move(R,4-robot);
        }else{
            move(R,7-robot);
        }
        
    }
    public void devolver(Robot R, int c){
            if(this.robot<4)move(R,robot);
            else{
                move(R,robot-4);
                turn(R,1);
                move(R,1);                
            }
            R.putThing();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
            R.pickThing();
            move(R, 1);
            turn(R,3);
            move(R,4);
            turn(R,3);
            recorrer(R, c, false);
            move(R,1);
            turn(R,3);
            move(R,10);
            turn(R,3);    
            move(R,++robot);
            turn(R,3);
            move(R,1);
            
    }
}
