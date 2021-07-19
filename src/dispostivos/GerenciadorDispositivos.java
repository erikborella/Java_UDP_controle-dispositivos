/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispostivos;

import java.util.HashMap;

/**
 *
 * @author erik0
 */
public class GerenciadorDispositivos {
    HashMap<String, DispositivoBase> listaDispositos;

    public GerenciadorDispositivos() {
        this.listaDispositos = new HashMap<>();
    }
    
    public void adicionarDispositivo(DispositivoBase dispositivo) {
        listaDispositos.put(dispositivo.local, dispositivo);
    }
    
    public DispositivoBase get(String local) {
        if (listaDispositos.containsKey(local))
            return listaDispositos.get(local);
        else
            return null;
    }
    
    public DispositivoBase set(String local, TipoStatus status) {
        DispositivoBase dispositivo = this.get(local);
        
        if (dispositivo == null)
            return null;
        
        dispositivo.status = status;
        
        return dispositivo;
    }
    
}
