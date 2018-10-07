/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 *
 * @author Asus
 */
public class AlmacénRobotizado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
       
         Scanner lectura=new Scanner(System.in);
        int k=0;
        do{
            System.out.println("Bienvenido, escoja una opcion");
            System.out.println("1.Ingresar tipo producto ");
            System.out.println("2.Ingresar existencias de un producto");
            System.out.println("3.Comprar producto");
            
            k = lectura.nextInt();             
            switch(k){
                case 1:
                    System.out.println("Ingrese el nombre del producto: ");
                    String nombre=lectura.next();
                    System.out.println("Ingrese el precio del producto: ");
                    int cod=lectura.nextInt();
                    
                    if(almacen.addProducto(nombre, cod)){
                        System.out.println("Se ha adicionado satisfactoriamente el producto");
                    }else{
                        System.out.println("Problemas al adicionar el producto, ya existe");
                    }
                    break;
                case 2:
                    ArrayList<Producto> productos=almacen.getProductos();
                    int m=0,n=0,estante=0,caja=0,cantidad =0;
                    System.out.println("Productos existentes: ");
                    for(Producto p: productos){
                        m++;
                        System.out.println(m+". "+p.getNombre());
                    }
                    do{
                    System.out.println("Ingrese el numero del producto: ");
                    n=lectura.nextInt();
                    }while(n<1 || n>productos.size());
                    do{
                    System.out.println("Ingrese la cantidad de productos a incluir: ");
                    cantidad=lectura.nextInt();
                    }while(cantidad<1);
                    do{
                    System.out.println("Ingrese el estante donde se encuentra: ");
                    estante=lectura.nextInt()-1;
                    }while(estante<0 || estante >19);
                    do{
                    System.out.println("Ingrese caja donde se encuentra: ");
                    caja=lectura.nextInt()-1;
                    }while(caja<0 || caja>2);
                    Integer[] array=new Integer[3];
                    array[0]=cantidad;
                    array[1]=estante;
                    array[2]=caja;
                    HashMap<Integer,Integer[]> product = new HashMap<>();
                    product.put(n, array);
                    almacen.ingresarProductos(product);
                    break;
//                case 3:
//                     HashMap<Integer, Estudiante> estudiantes = 
//                              universidad.listarEstudiantes();
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     for (Estudiante estudiante : estudiantes.values()) {
//                           System.out.println(estudiante.getCodigo()  + " . " + estudiante.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     Estudiante estudiante = universidad.buscarEstudiante(cod);
//                     
//                     System.out.println("Seleccione el proyecto");
//                     ArrayList<Proyecto> proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                    cod =  lectura.nextInt();
//                    
//                    if(estudiante != null){
//                       if(universidad.adicionarEstudianteProyecto(estudiante, cod)){
//                           System.out.println("Se ha adicionado satisfactoriamente el estudiante al proyecto");
//                       }else{
//                           System.out.println("Problemas al adicionar al estudiante en el proyecto");
//                       }
//                    }
//                    
//                    
//                    break;
//                case 4:
//                    
//                    estudiantes = 
//                              universidad.listarEstudiantes();
//                                    
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     
//                    break;
//                case 5:
//                     proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                     
//                    break;
//                    
//                   
//                case 6:
//                     System.out.println("Buscar estudiante.");
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     estudiantes = 
//                              universidad.listarEstudiantes();
//                     
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     estudiante = universidad.buscarEstudiante(cod);
//                     
//                     if(estudiante != null){
//                         System.out.println(estudiante.getCodigo() + " " + estudiante.getNombre());                         System.out.println("Esta registrado en los siguientes proyectos");
//                     }else{
//                         System.out.println("El estudiante seleccionado no existe");
//                     }                     
//                     break;//                case 3:
//                     HashMap<Integer, Estudiante> estudiantes = 
//                              universidad.listarEstudiantes();
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     for (Estudiante estudiante : estudiantes.values()) {
//                           System.out.println(estudiante.getCodigo()  + " . " + estudiante.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     Estudiante estudiante = universidad.buscarEstudiante(cod);
//                     
//                     System.out.println("Seleccione el proyecto");
//                     ArrayList<Proyecto> proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                    cod =  lectura.nextInt();
//                    
//                    if(estudiante != null){
//                       if(universidad.adicionarEstudianteProyecto(estudiante, cod)){
//                           System.out.println("Se ha adicionado satisfactoriamente el estudiante al proyecto");
//                       }else{
//                           System.out.println("Problemas al adicionar al estudiante en el proyecto");
//                       }
//                    }
//                    
//                    
//                    break;
//                case 4:
//                    
//                    estudiantes = 
//                              universidad.listarEstudiantes();
//                                    
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     
//                    break;
//                case 5:
//                     proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                     
//                    break;
//                    
//                   
//                case 6:
//                     System.out.println("Buscar estudiante.");
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     estudiantes = 
//                              universidad.listarEstudiantes();
//                     
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     estudiante = universidad.buscarEstudiante(cod);
//                     
//                     if(estudiante != null){
//                         System.out.println(estudiante.getCodigo() + " " + estudiante.getNombre());                         System.out.println("Esta registrado en los siguientes proyectos");
//                     }else{
//                         System.out.println("El estudiante seleccionado no existe");
//                     }                     
//                     break;
//                case 3:
//                     HashMap<Integer, Estudiante> estudiantes = 
//                              universidad.listarEstudiantes();
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     for (Estudiante estudiante : estudiantes.values()) {
//                           System.out.println(estudiante.getCodigo()  + " . " + estudiante.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     Estudiante estudiante = universidad.buscarEstudiante(cod);
//                     
//                     System.out.println("Seleccione el proyecto");
//                     ArrayList<Proyecto> proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                    cod =  lectura.nextInt();
//                    
//                    if(estudiante != null){
//                       if(universidad.adicionarEstudianteProyecto(estudiante, cod)){
//                           System.out.println("Se ha adicionado satisfactoriamente el estudiante al proyecto");
//                       }else{
//                           System.out.println("Problemas al adicionar al estudiante en el proyecto");
//                       }
//                    }
//                    
//                    
//                    break;
//                case 4:
//                    
//                    estudiantes = 
//                              universidad.listarEstudiantes();
//                                    
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     
//                    break;
//                case 5:
//                     proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                     
//                    break;
//                    
//                   
//                case 6:
//                     System.out.println("Buscar estudiante.");
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     estudiantes = 
//                              universidad.listarEstudiantes();
//                     
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     estudiante = universidad.buscarEstudiante(cod);
//                     
//                     if(estudiante != null){
//                         System.out.println(estudiante.getCodigo() + " " + estudiante.getNombre());                         System.out.println("Esta registrado en los siguientes proyectos");
//                     }else{
//                         System.out.println("El estudiante seleccionado no existe");
//                     }                     
//                     break;//                case 3:
//                     HashMap<Integer, Estudiante> estudiantes = 
//                              universidad.listarEstudiantes();
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     for (Estudiante estudiante : estudiantes.values()) {
//                           System.out.println(estudiante.getCodigo()  + " . " + estudiante.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     Estudiante estudiante = universidad.buscarEstudiante(cod);
//                     
//                     System.out.println("Seleccione el proyecto");
//                     ArrayList<Proyecto> proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                    cod =  lectura.nextInt();
//                    
//                    if(estudiante != null){
//                       if(universidad.adicionarEstudianteProyecto(estudiante, cod)){
//                           System.out.println("Se ha adicionado satisfactoriamente el estudiante al proyecto");
//                       }else{
//                           System.out.println("Problemas al adicionar al estudiante en el proyecto");
//                       }
//                    }
//                    
//                    
//                    break;
//                case 4:
//                    
//                    estudiantes = 
//                              universidad.listarEstudiantes();
//                                    
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     
//                    break;
//                case 5:
//                     proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                     
//                    break;
//                    
//                   
//                case 6:
//                     System.out.println("Buscar estudiante.");
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     estudiantes = 
//                              universidad.listarEstudiantes();
//                     
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     estudiante = universidad.buscarEstudiante(cod);
//                     
//                     if(estudiante != null){
//                         System.out.println(estudiante.getCodigo() + " " + estudiante.getNombre());                         System.out.println("Esta registrado en los siguientes proyectos");
//                     }else{
//                         System.out.println("El estudiante seleccionado no existe");
//                     }                     
//                     break;
            }
            
           
        }while(k>=0 && k<=4);
    }
    
}
