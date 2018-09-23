/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import becker.robots.*;

/**
 *
 * @author Asus
 */
public class Almacen {
    public City ciudad;
    public Robot empleado;
    public Robot empleado2;
    public Robot empleado3;
    public Robot empleado4;
    public Robot empleado5;
    public Robot empleado6;
    public Robot empleado7;
    public Robot empleado8;
    public Estante[] estantes;
    
    public Almacen(){
        this.ciudad=new City();
        for(int i=0;i<6;i++){
            Wall pared=new Wall(ciudad,i,4,Direction.EAST);
            pared=new Wall(ciudad,i,5,Direction.WEST);
        }
         for(int i=0;i<8;i++){
            Wall pared1=new Wall(ciudad,0,i+1,Direction.NORTH);
            Wall pared=new Wall(ciudad,i,1,Direction.WEST);
            pared=new Wall(ciudad,i,8,Direction.EAST);
            pared=new Wall(ciudad,i,8,Direction.EAST);
        }
         for(int i=0;i<3;i++){
            Wall pared1=new Wall(ciudad,7,i+1,Direction.SOUTH);
            pared1 = new Wall(ciudad,7,i+6,Direction.SOUTH);
        }
         this.empleado=new Robot(ciudad,0,2,Direction.SOUTH);
         this.empleado=new Robot(ciudad,0,3,Direction.SOUTH);
         this.empleado=new Robot(ciudad,0,6,Direction.SOUTH);
         this.empleado=new Robot(ciudad,0,7,Direction.SOUTH);
         this.empleado=new Robot(ciudad,7,2,Direction.NORTH);
         this.empleado=new Robot(ciudad,7,3,Direction.NORTH);
         this.empleado=new Robot(ciudad,7,6,Direction.NORTH);
         this.empleado=new Robot(ciudad,7,7,Direction.NORTH);
         this.estantes =  new Estante[20]; 
    }
}
