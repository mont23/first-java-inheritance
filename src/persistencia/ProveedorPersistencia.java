/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import principal.Empresa;
import principal.EmpresaExcepcio;




/**
 *
 * @author MiguelAngel
 */
public interface ProveedorPersistencia {
    public void desarEmpresa(String nomFitxer, Empresa empresa) throws EmpresaExcepcio;
    public void carregarEmpresa(String nomFitxer) throws EmpresaExcepcio;
    
    
}
