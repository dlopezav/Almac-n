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
    private City ciudad;
    private Robot[] empleado;
    private Estante[] estantes;
    private Thing[] stand;
    
    
    public Almacen(){
        this.ciudad=new City();
        for(int i=0;i<7;i++){
            Wall pared=new Wall(ciudad,i,4,Direction.WEST);
            pared=new Wall(ciudad,i,3,Direction.EAST);
        } 
        for(int i=0;i<9;i++){
            Wall pared=new Wall(ciudad,0,i+1,Direction.NORTH);
            pared=new Wall(ciudad,i,1,Direction.WEST);
            pared=new Wall(ciudad,i,8,Direction.EAST);
            pared=new Wall(ciudad,i,8,Direction.EAST);
        }
         for(int i=0;i<3;i++){
            Wall pared=new Wall(ciudad,8,i+1,Direction.SOUTH);
            pared = new Wall(ciudad,8,i+6,Direction.SOUTH);
        }
         this.empleado= new Robot[8];
         this.estantes =  new Estante[20]; 
         this.stand =new Thing[20];
         for (int i = 0; i < 8; i++) {
            this.empleado[i]=new Robot(ciudad,7-i,2,Direction.EAST);
        }
         this.empleado[0].move();
         
         
    }
}
