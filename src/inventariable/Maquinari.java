/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariable;

import java.util.Scanner;
import principal.Actiu;

/**
 *
 * @author Montse Co
 */
public abstract class Maquinari implements Actiu {

    private String codi;
    private String descripcio;
    private String marca;
    private Integer memoria;

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getMemoria() {
        return memoria;
    }

    public void setMemoria(Integer memoria) {
        this.memoria = memoria;
    }

    public Maquinari(String codi, String descripcio, String marca, Integer memoria) {
        this.codi = codi;
        this.descripcio = descripcio;
        this.marca = marca;
        this.memoria = memoria;
    }

    @Override
    public void updateActiu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nCodi del maquinari: " + this.getCodi());
        System.out.println("\nEntra el nou codi:");
        this.setCodi(teclado.next());
        System.out.println("\nDescripció del maquinari: " + this.getDescripcio());
        System.out.println("\nEntra la nova descripció:");
        this.setDescripcio(teclado.next());
        System.out.println("\nMarca del maquinari: " + this.getMarca());
        System.out.println("\nEntra la nova marca:");
        this.setMarca(teclado.next());
        System.out.println("\nMemòria: " + this.getMemoria());
        System.out.println("\nEntra la nova memòria:");
        this.setMemoria(teclado.nextInt());
    }

    @Override
    public void showActiu() {
        System.out.println("\nLes dades del maquinari amb codi " + this.getCodi() + " són:");
        System.out.println("\nDescripció:" + this.getDescripcio());
        System.out.println("\nMarca:" + this.getMarca());
        System.out.println("\nMemòria:" + this.getMemoria());
    }

}
