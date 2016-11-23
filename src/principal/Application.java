package principal;

import inventariable.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import persistencia.GestorPersistencia;

/**
 *
 * @author Montse Co
 */
public class Application {

    static private String FITXER = "empresapersist";
    static private Empresa[] empreses = new Empresa[4];
    static private Integer comptaEmpreses = 0;
    static private Empresa empresactual = null;
    static private Integer tipusActiu = 0;
    static private GestorPersistencia gp = new GestorPersistencia();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            menuPrincipal();
        } catch (EmpresaExcepcio e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuPrincipal() throws EmpresaExcepcio {
        Integer opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Gestió de empreses");
            System.out.println("\n2. Gestió dels ordinadors");
            System.out.println("\n3. Gestió dels impressores");
            System.out.println("\n4. Gestió de mobles");
            System.out.println("\n5. Gestió de naus");
            try {
                opcio = teclado.nextInt();
            } catch (InputMismatchException e) {
                throw new EmpresaExcepcio("1");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmpresaExcepcio("3");
            }
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    menuEmpresa();
                    break;
                case 2:
                    if (empresactual != null) {
                        tipusActiu = 1;
                        menuActius();
                    } else {
                        System.out.println("\nPrimer s'ha de fixar la empresa al menú Gestió de empreses");
                    }
                    break;
                case 3:
                    if (empresactual != null) {
                        tipusActiu = 2;
                        menuActius();
                    } else {
                        System.out.println("\nPrimer s'ha de fixar la empresa al menú Gestió de empreses");
                    }
                    break;
                case 4:
                    if (empresactual != null) {
                        tipusActiu = 3;
                        menuActius();
                    } else {
                        System.out.println("\nPrimer s'ha de fixar la empresa al menú Gestió de empreses");
                    }
                    break;
                case 5:
                    if (empresactual != null) {
                        tipusActiu = 4;
                            menuNaus();
                    } else {
                        System.out.println("\nPrimer s'ha de fixar la empresa al menú Gestió de empreses");
                    }
                    break;
                default:

                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuEmpresa() throws EmpresaExcepcio, InputMismatchException, ArrayIndexOutOfBoundsException {
        Integer opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            Integer indexSel = -1;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Fixa Empresa");
            System.out.println("\n3. Modificació");
            System.out.println("\n4. Llista d'empreses");
            System.out.println("\n5. Carrega empresa");
            System.out.println("\n6. Desa empresa");
            opcio = teclado.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    empreses[comptaEmpreses] = Empresa.addEmpresa();
                    comptaEmpreses++;
                    break;
                case 2:
                    indexSel = selectEmpresa();
                    if (indexSel >= 0) {
                        empresactual = empreses[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquesta empresa");
                    }
                    break;
                case 3:
                    indexSel = selectEmpresa();
                    if (indexSel >= 0) {
                        empreses[indexSel].updateEmpresa();
                    } else {
                        System.out.println("\nNo existeix aquesta empresa");
                    }
                    break;
                case 4:
                    for (int i = 0; i < comptaEmpreses; i++) {
                        empreses[i].showEmpresa();
                    }
                    break;
                case 5: //Carregar empresa
                    comptaEmpreses = 0;
                    empreses = new Empresa[1];
                    gp.carregarEmpresa("XML", FITXER);
                    empreses[comptaEmpreses] = gp.getGestor().getEmpresa();
                    comptaEmpreses++;
                    break;
                case 6: //Desar empresa
                    indexSel = selectEmpresa();
                    if (indexSel >= 0) {
                        gp.desarEmpresa("XML", FITXER, empreses[indexSel]);
                    } else {
                        System.out.println("\nNo existeix aquesta empresa");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuActius() throws InputMismatchException {
        Integer opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificació");
            System.out.println("\n3. Llista");
            opcio = teclado.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipusActiu) {
                        case 1:
                            empresactual.addOrdinador(null);
                            break;
                        case 2:
                            empresactual.addImpressora(null);
                            break;
                        case 3:
                            empresactual.addMoble(null);
                            break;
                    }
                    break;
                case 2:
                    Integer indexSel = empresactual.selectActiu(tipusActiu, "");
                    if (indexSel >= 0) {
                        empresactual.getActius()[indexSel].updateActiu();
                    } else {
                        System.out.println("\nNo existeix aquest actiu");
                    }
                    break;
                case 3:
                    for (int i = 0; i < empresactual.getComptaActius(); i++) {
                        if (empresactual.getActius()[i] instanceof Ordinador && tipusActiu == 1) {
                            empresactual.getActius()[i].showActiu();
                        }
                        if (empresactual.getActius()[i] instanceof Impressora && tipusActiu == 2) {
                            empresactual.getActius()[i].showActiu();
                        }
                        if (empresactual.getActius()[i] instanceof Moble && tipusActiu == 3) {
                            empresactual.getActius()[i].showActiu();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuNaus() throws InputMismatchException {
        Integer opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Afegir ordinador");
            System.out.println("\n3. Afegir impressora");
            System.out.println("\n4. Afegir moble");
            System.out.println("\n5. Llista de naus");
            opcio = teclado.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    empresactual.addNau(null);
                    break;
                case 2:
                    empresactual.addActiuNau(1);
                    break;
                case 3:
                    empresactual.addActiuNau(2);
                    break;
                case 4:
                    empresactual.addActiuNau(3);
                    break;
                case 5:
                    for (int i = 0; i < empresactual.getComptaActius(); i++) {
                        if (empresactual.getActius()[i] instanceof Nau) {
                            empresactual.getActius()[i].showActiu();
                        }

                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectEmpresa() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nCodi de empresa?:");
        Integer codeSel = teclado.nextInt();
        Integer indexSel = -1;
        for (int i = 0; i < comptaEmpreses; i++) {
            if (empreses[i].getCode() == codeSel) {
                indexSel = i;
                break;
            }
        }
        return indexSel;
    }

}
