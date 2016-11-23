package principal;

import inventariable.Impressora;
import inventariable.Moble;
import java.util.Scanner;
import inventariable.Ordinador;

/**
 *
 * @author Montse Co
 */
public class Empresa {

    static private Integer comptaCode = 1; //El proper code a assignar
    private Integer code;
    private String name;
    private String address;
    private Actiu[] actius = new Actiu[100];
    private Integer comptaActius = 0;

    public Empresa(String name, String address) {
        this.code = Empresa.getComptaCode();
        setComptaCode();
        this.name = name;
        this.address = address;
    }

    public Empresa(Integer code, String name, String address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public static Integer getComptaCode() {
        return comptaCode;
    }

    public static void setComptaCode() {
        Empresa.comptaCode++;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Actiu[] getActius() {
        return actius;
    }

    public void setActius(Actiu[] actius) {
        this.actius = actius;
    }

    public Integer getComptaActius() {
        return comptaActius;
    }

    public void setComptaActius(Integer comptaActius) {
        this.comptaActius = comptaActius;
    }

    public static Empresa addEmpresa() {
        Scanner teclado = new Scanner(System.in);
        String nameEmpresa;
        String addressEmpresa;
        System.out.println("Nom de la empresa:");
        nameEmpresa = teclado.next();
        System.out.println("Adreça de la empresa:");
        addressEmpresa = teclado.next();
        return new Empresa(nameEmpresa, addressEmpresa);
    }

    public void updateEmpresa() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nNom de la empresa: " + this.getName());
        System.out.println("\nEntra el nou nom:");
        this.setName(teclado.next());
        System.out.println("\nAddreça de la empresa: " + this.getAddress());
        System.out.println("\nEntra la nova adreça:");
        this.setAddress(teclado.next());
    }

    public void showEmpresa() {
        System.out.println("\nLes dades de la empresa amb codi " + this.getCode() + " són:");
        System.out.println("\nNom:" + this.getName());
        System.out.println("\nAddreça:" + this.getAddress());
    }

    /*
     Ordinadors
     */
    public void addOrdinador(Ordinador ordinador) {
        try {

            if (ordinador == null) {
                ordinador = Ordinador.addOrdinador();
            }
            selectOrdinador(ordinador.getCodi());
            actius[comptaActius] = ordinador;
            comptaActius++;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /*
     Impressores
     */
    public void addImpressora(Impressora impressora) {
        if (impressora == null) {
            actius[comptaActius] = Impressora.addImpressora();
        } else {
            actius[comptaActius] = impressora;
        }
        comptaActius++;
    }

    /*
     Mobles
     */
    public void addMoble(Moble moble) {
        if (moble == null) {
            actius[comptaActius] = Moble.addMoble();
        } else {
            actius[comptaActius] = moble;
        }
        comptaActius++;
    }

    /*
     Naus
     */
    public void addNau(Nau nau) {
        if (nau == null) {
            actius[comptaActius] = Nau.addNau();
        } else {
            actius[comptaActius] = nau;
        }
        comptaActius++;
    }

    public Integer selectActiu(Integer tipusActiu, String codi) {
        Scanner teclado = new Scanner(System.in);
        Integer laClasse = tipusActiu;
        String codiSel;
        if (codi.equals("")) {
            switch (laClasse) {
                case 0:
                    System.out.println("\nQuè vols seleccionar?:");
                    System.out.println("1. Ordinador:");
                    System.out.println("2. Impressora:");
                    System.out.println("3. Moble:");
                    System.out.println("4. Nau:");
                    laClasse = teclado.nextInt();
                    break;
                case 1:
                    System.out.println("Codi del Ordinador?:");
                    break;
                case 2:
                    System.out.println("Codi de la impressora?:");
                    break;

                case 3:
                    System.out.println("Codi del Moble?:");
                    break;
                case 4:
                    System.out.println("Codi de la Nau?:");
                    break;
            }

            codiSel = teclado.next();
        } else {
            codiSel = codi;
        }
        Integer indexSel = -1;
        for (int i = 0; i < comptaActius; i++) {
            if (actius[i] instanceof Ordinador && laClasse == 1) {
                if (((Ordinador) actius[i]).getCodi().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
            if (actius[i] instanceof Impressora && laClasse == 2) {
                if (((Impressora) actius[i]).getCodi().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
            if (actius[i] instanceof Moble && laClasse == 3) {
                if (((Moble) actius[i]).getCode().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
            if (actius[i] instanceof Nau && laClasse == 4) {
                if (((Nau) actius[i]).getCode().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
        }
        return indexSel;
    }

    public void addActiuNau(Integer tipusActiu) {
        Nau nausel = null;
        Integer indexSelNau = this.selectActiu(4, "");
        if (indexSelNau >= 0) {
            nausel = (Nau) this.getActius()[indexSelNau];
        } else {
            System.out.println("\nNo existeix aquesta nau");
        }
        Integer indexSel = this.selectActiu(tipusActiu, "");
        if (indexSel >= 0) {
            nausel.addActiuNau(this.getActius()[indexSel]);
        } else {
            System.out.println("\nNo existeix aquest actiu");
        }
    }

    public Integer selectOrdinador(String preCodi) throws EmpresaExcepcio {
        Integer indexSel = -1;
        for (int i = 0; i < actius.length; i++) {
            Ordinador ordinador = (Ordinador) actius[i];
            if (ordinador != null) {
                if (ordinador.getCodi().equals(preCodi)) {
                    indexSel = i;
                    throw new EmpresaExcepcio("3");
                }
            }

        }
        return indexSel;
    }
}
