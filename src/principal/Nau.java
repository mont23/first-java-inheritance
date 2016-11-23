/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import inventariable.Impressora;
import inventariable.Moble;
import java.util.Scanner;
import inventariable.Ordinador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Montse Co
 */
public class Nau implements Actiu {

    private String code;
    private String name;
    private List<Actiu> actius = new ArrayList<Actiu>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Actiu> getActius() {
        return actius;
    }

    public void setActius(List<Actiu> actius) {
        this.actius = actius;
    }

    public Nau(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Nau addNau() {
        Scanner teclado = new Scanner(System.in);
        String codeNau;
        String nameNau;
        System.out.println("Codi de la nau:");
        codeNau = teclado.next();
        System.out.println("Nom de la nau:");
        nameNau = teclado.next();
        return new Nau(codeNau, nameNau);
    }

    public void addActiuNau(Actiu actiu) {
        actius.add(actiu);
    }

    @Override
    public void showActiu() {
        System.out.println("\nLes dades de la nau amb codi " + this.getCode() + " són:");
        System.out.println("\nNom:" + this.getName()); 
        System.out.println("Aquesta nau està format pels actius:");
        for (int i = 0; i < actius.size(); i++) {
            actius.get(i).showActiu();
        }

    }

    @Override
    public void updateActiu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nCodi de la nau és: " + this.getCode());
        System.out.println("\nEntra el nou codi:");
        this.setCode(teclado.next());
        System.out.println("\nNom de la nau és: " + this.getName());
        System.out.println("\nEntra el nou nom:");
        this.setName(teclado.next());
    }

}
