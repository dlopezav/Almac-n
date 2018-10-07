/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

/**
 *
 * @author Asus
 */
public class Estante {
    private int numero;
    private Caja[] cajas;
    private boolean tomado;

    public Estante(int numero) {
        this.numero = numero;
        this.cajas=new Caja[3];
        for (int i = 0; i < 3; i++) {
            this.cajas[i]=new Caja();
        }
    }
    
    public void deleteCaja(int i){
        this.cajas[i]=null;
    }
    public int getNumero() {
        return numero;
    }

    public boolean isTomado() {
        return tomado;
    }

    public void setTomado(boolean tomado) {
        this.tomado = tomado;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Caja[] getCajas() {
        return cajas;
    }
    
}
